package com.buaa.pms.service.opt;

import com.buaa.pms.entity.*;
import com.buaa.pms.model.OptResult;
import com.buaa.pms.model.OptTaskNode;
import com.buaa.pms.model.ResOcpyNode;
import com.buaa.pms.model.Task;
import com.buaa.pms.service.*;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GaOpt {
    @Resource
    PmsTaskService pmsTaskService;
    @Resource
    PmsTaskLinkService pmsTaskLinkService;
    @Resource
    PmsTaskGroupService pmsTaskGroupService;
    @Resource
    PmsGroupService pmsGroupService;
    @Resource
    PmsProjectService pmsProjectService;
    @Resource
    PmsProcessService pmsProcessService;
    @Resource
    PmsTaskResPlanService pmsTaskResPlanService;
    @Resource
    PmsTaskResReqService pmsTaskResReqService;
    @Resource
    PmsHumanService pmsHumanService;
    @Resource
    PmsEquipmentService pmsEquipmentService;
    @Resource
    PmsPlaceService pmsPlaceService;
    @Resource
    PmsKnowledgeService pmsKnowledgeService;
    @Resource
    PmsAllocateResourceService pmsAllocateResourceService;

    public static final int IMPORTANCEVALUE_HIGHEST = 4;    // 任务最大重要性值
    public static final long MS_OF_HOUR = 1000 * 3600;      // 1小时的毫秒数
    public static final long MS_OF_DAY = 1000 * 3600 * 24;  // 1天的毫秒数

    public static final int MAX_G = 10;           // 最大迭代次数
    public static final int CHROMOSOME_NUM = 50;  // 种群规模（个体数量）
    public static final double SF = 0.5;        // 变异操作时的缩放因子
    public static final double MR = 0.2;        // 变异概率
    public static final double CR = 0.8;        // 交叉概率

    public static final int PRIO_FACTOR_NUM = 4;

    private Calendar calendar = Calendar.getInstance();

    // 编码上每一位基因的取值范围
    int[] genValueLimit;

    // 各基因位对应任务的资源方案权重累加（比如优先级为1,1,2的三个方案，则权重分别为2,2,1，权重累加则为[2,4,5]），便于按优先级随机选择资源方案
    List<Integer>[] resPlanPriCumulation;

    // 待优化任务的总数量
    private int optTaskCountSum;

    // 虚拟首节点
    OptTaskNode startOptTaskNode;
    // 虚拟尾结点
    OptTaskNode endOptTaskNode;

    // 优化的时间起点，即所有项目的最早开始时间
    private Timestamp optOrigin;

    // 优化的时间终点，即所有项目的最晚完成时间
    private Timestamp optDestination;

    // 优化起点与终点时间相隔的天数
    private double optDays;

    // 所有参与优化的项目，key为项目UID，value为对应项目实例
    private Map<String, PmsProject> pmsProjectMap;

    // 所有参与优化的项目的最大步长，key为项目UID，value为对应项目的任务数量最大的路径上的任务个数
    private Map<String, Double> projMaxPathTaskCountMap;

    // 项目的无紧前任务的任务和无紧后任务的任务，key是项目UID，value是项目中没有紧前任务的任务节点和无紧后任务的任务两个list组成的数组
    // 两者分别是数组的第0个元素和第1个元素
    private Map<String, List<OptTaskNode>[]> projFirstLastTasksMap;

    // 所有参与优化的流程，key为流程UID，value为对应流程实例
    private Map<String, PmsProcess> pmsProcessMap;

    // 所有任务组及其包含的任务，key为任务组UID，value为其包含的任务数组
    private Map<String, List<OptTaskNode>> groupUidOptTaskNodesMap;

    // 每个任务组的首尾任务节点，key为任务组Uid，value为任务组内首尾节点组成的数组，下标0为首节点，下标1为尾结点
    private Map<String, OptTaskNode[]> groupUidFirstAndLastTaskNode;

    // 任务Uid和其所属任务组Uid的map
    private Map<String, String> taskUidAndGroupUid;

    // 所有参与优化的任务，key为任务UID，value为对应OptTaskNode
    private Map<String, OptTaskNode> optTaskNodeMap;

    // 所有参与优化的任务，按照任务编号taskNo排列存储
    private List<OptTaskNode> optTaskNodeListByNo;

    // 所有任务的资源方案，key为任务UID，value为该任务包含的资源方案
    private Map<String, List<PmsTaskResPlan>> taskResPlanMap;

    // 所有涉及到的非独占资源的数量，key为资源UID，value为该资源实例的数量
    private Map<String, Double> resAmountMap;

    // 所有资源方案的资源需求，key为资源方案UID，value为该资源包含的资源需求
    private Map<String, List<PmsTaskResReq>> resPlanResReqMap;

    // 资源占用情况，key为资源UID，value为该资源被占用的时间段链表
    private Map<String, ResOcpyNode> resOcpyNodeMap;

    private Map<String, ResOcpyNode> resOcpyNodeMapOrig;

    /**
     * 遗传算法，本地优化的初始化
     * @param procUidList
     * @return
     */
    public OptTaskNode initTaskNodes(List<String> procUidList) {
        // 若待优化流程集合为空，则返回null
        if (procUidList == null || procUidList.isEmpty()) {
            return null;
        }

        // 优化的时间起点，即所有项目的最早开始时间
        optOrigin = new Timestamp(Long.MAX_VALUE);

        // 优化的时间终点，即所有项目的最晚完成时间
        optDestination = new Timestamp(Long.MIN_VALUE);

        // 优化起点与终点时间相隔的天数
        optDays = 0;

        // 所有参与优化的项目，key为项目UID，value为对应项目实例
        pmsProjectMap = new HashMap<>();

        // 所有参与优化的项目的最大步长，key为项目UID，value为对应项目的任务数量最大的路径上的任务个数
        projMaxPathTaskCountMap = new HashMap<>();

        // 项目的无紧前任务的任务和无紧后任务的任务，key是项目UID，value是项目中没有紧前任务的任务节点和无紧后任务的任务两个list组成的数组
        // 两者分别是数组的第0个元素和第1个元素
        projFirstLastTasksMap = new HashMap<>();

        // 所有参与优化的流程，key为流程UID，value为对应流程实例
        pmsProcessMap = new HashMap<>();

        // 所有任务组及其包含的任务，key为任务组UID，value为其包含的任务数组
        groupUidOptTaskNodesMap = new HashMap<>();

        // 每个任务组的首尾任务节点，key为任务组Uid，value为任务组内首尾节点组成的数组，下标0为首节点，下标1为尾结点
        groupUidFirstAndLastTaskNode = new HashMap<>();

        // 任务Uid和其所属任务组Uid的map
        taskUidAndGroupUid = new HashMap<>();

        // 所有参与优化的任务，key为任务UID，value为对应OptTaskNode
        optTaskNodeMap = new HashMap<>();

        // 所有参与优化的任务，按照任务编号taskNo排列存储
//        optTaskNodeListByNo = new ArrayList<>();

        // 所有任务的资源方案，key为任务UID，value为该任务包含的资源方案
        taskResPlanMap = new HashMap<>();

        // 所有涉及到的非独占资源的数量，key为资源UID，value为该资源实例的数量
        resAmountMap = new HashMap<>();
        // 所有资源方案的资源需求，key为资源方案UID，value为该资源包含的资源需求
        resPlanResReqMap = new HashMap<>();

        // 资源占用情况，key为资源UID，value为该资源被占用的时间段链表
        resOcpyNodeMap = new HashMap<>();

        resOcpyNodeMapOrig = new HashMap<>();

        startOptTaskNode = new OptTaskNode();  // 虚拟首节点
        endOptTaskNode = new OptTaskNode();    // 虚拟尾结点

        List<String> projUidList = new ArrayList<>();   // 待优化项目UID集合
        // 获取全部待优化流程
        for (PmsProcess pmsProcess : pmsProcessService.selectByUidList(procUidList)) {
            pmsProcessMap.put(pmsProcess.getProcUid(), pmsProcess);
            projUidList.add(pmsProcess.getProcProjUid());
        }
        // 获取全部待优化项目，并得到优化起点和终点时间
        for (PmsProject pmsProject : pmsProjectService.selectByUidList(projUidList)) {
            pmsProjectMap.put(pmsProject.getProjUid(), pmsProject);
            Timestamp projEarly = pmsProject.getProjEarlyStartDateTime();
            Timestamp projLate = pmsProject.getProjLateFinishDateTime();
            // 优化起点为各项目最早的最早开始时间，终点为最晚的最晚结束时间
            if (optOrigin != null && projEarly != null && projEarly.before(optOrigin)) {
                optOrigin.setTime(projEarly.getTime());
            }
            if (optDestination != null && projLate != null && projLate.after(optDestination)) {
                optDestination.setTime(projLate.getTime());
            }
        }
        // 得到优化起点与终点时间相隔的天数
        optDays = (optDestination.getTime() - optOrigin.getTime()) / (MS_OF_DAY);

        // 获取全部待优化任务，实例化任务链表的节点OptTaskNode
        for (PmsTask pmsTask : pmsTaskService.selectByProcUidList(procUidList)) {
            // 未开始的任务参与计划优化
            if (pmsTask.getTaskState() <= 1) {
                optTaskNodeMap.put(pmsTask.getTaskUid(), new OptTaskNode(pmsTask));
            }
        }
        optTaskCountSum = optTaskNodeMap.size();    // 待优化任务总数

        resPlanPriCumulation = new ArrayList[optTaskCountSum];
        optTaskNodeListByNo = new ArrayList<OptTaskNode>(optTaskCountSum);

        // 获取全部任务组与任务的关系，初始化同组任务map，groupUidOptTaskNodesMap以及taskUidAndGroupUid
//        for (PmsTaskGroup taskGroup : pmsTaskGroupService.selectByProcUidList(procUidList)) {
//            if (groupUidOptTaskNodesMap.containsKey(taskGroup.getTaskGroupGroupUid())) {
//                groupUidOptTaskNodesMap.get(taskGroup.getTaskGroupGroupUid()).add(optTaskNodeMap.get(taskGroup.getTaskGroupTaskUid()));
//            } else {
//                List<OptTaskNode> optTaskNodeList = new ArrayList<>();
//                optTaskNodeList.add(optTaskNodeMap.get(taskGroup.getTaskGroupTaskUid()));
//                groupUidOptTaskNodesMap.put(taskGroup.getTaskGroupGroupUid(), optTaskNodeList);
//            }
//            taskUidAndGroupUid.put(taskGroup.getTaskGroupTaskUid(), taskGroup.getTaskGroupGroupUid());
//        }

        // 获取全部待优化任务的资源方案，初始化“任务-资源方案”taskResPlanMap
        for (PmsTaskResPlan pmsTaskResPlan : pmsTaskResPlanService.selectByProcUidList(procUidList)) {
            String pmsTaskUid = pmsTaskResPlan.getResPlanTaskUid();
            if (!taskResPlanMap.containsKey(pmsTaskUid)) {
                taskResPlanMap.put(pmsTaskUid, new LinkedList<PmsTaskResPlan>());
            }
            taskResPlanMap.get(pmsTaskUid).add(pmsTaskResPlan);
        }
        // 一个任务的多个资源方案按照优先级排序
        for (List<PmsTaskResPlan> pmsTaskResPlans : taskResPlanMap.values()) {
            Collections.sort(pmsTaskResPlans, (o1, o2) -> {
                return o1.getResPlanPriority() - o2.getResPlanPriority();
            });
        }
        // 获取资源方案的资源需求，初始化“资源方案-资源需求”resPlanResReqMap
        // 初始化相关资源的已占用情况resOcpyNodeMapOrig
        for (PmsTaskResReq pmsTaskResReq : pmsTaskResReqService.selectByProcUidList(procUidList)) {
            resOcpyNodeMapOrig.put(pmsTaskResReq.getResReqResUid(), new ResOcpyNode(new Timestamp(0), new Timestamp(0)));
            String resPlanUid = pmsTaskResReq.getResReqResPlanUid();
            if (!resPlanResReqMap.containsKey(resPlanUid)) {
                resPlanResReqMap.put(resPlanUid, new LinkedList<PmsTaskResReq>());
            }
            resPlanResReqMap.get(resPlanUid).add(pmsTaskResReq);
            // 获取涉及的非独占资源的原数量
            switch (pmsTaskResReq.getResReqResType()) {
                case 2:
                    PmsPlace pmsPlace = pmsPlaceService.selectByUid(pmsTaskResReq.getResReqResUid());
                    resAmountMap.put(pmsPlace.getPlaceUid(), pmsPlace.getPlaceArea().doubleValue());break;
                case 3:
                    PmsKnowledge pmsKnowledge = pmsKnowledgeService.selectByUid(pmsTaskResReq.getResReqResUid());
                    resAmountMap.put(pmsKnowledge.getKnowlUid(), pmsKnowledge.getKnowlAmount().doubleValue());
                default:
//                    System.out.println("res is not type of 2,3");
                    break;
            }
        }
        List<PmsAllocateResource> pmsAllocateResources = pmsAllocateResourceService.selectByResUidList(new ArrayList<String>(resOcpyNodeMapOrig.keySet()));
        Collections.sort(pmsAllocateResources, (o1, o2) -> {        // 按照占用开始时间对集合pmsAllocateResources进行排序，以便对resOcpyNodeMapOrig初始化时不用再排序
            if (o1.getArResStartDateTime().before(o2.getArResStartDateTime())) return -1;
            else if (o1.getArResStartDateTime().after(o2.getArResStartDateTime())) return 1;
            return 0;
        });
        Map<String, ResOcpyNode> arListTailMap = new HashMap<>(resOcpyNodeMapOrig);     // 记录resOcpyNodeMapOrig的value中的list的尾结点，以便插入新节点
        for (PmsAllocateResource pmsAllocateResource : pmsAllocateResources) {
            if (pmsAllocateResource.getArResStartDateTime().after(optOrigin) || pmsAllocateResource.getArResFinishDateTime().before(optDestination))
                continue;   // 不再此次优化的时间段之内的占用就忽略
            String resUid = pmsAllocateResource.getArResUid();
            ResOcpyNode newResOcpyNode = new ResOcpyNode(pmsAllocateResource);
            arListTailMap.get(resUid).sucOcpy = newResOcpyNode;
            arListTailMap.put(resUid, newResOcpyNode);
        }
        Iterator<Map.Entry<String, ResOcpyNode>> it = resOcpyNodeMapOrig.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, ResOcpyNode> entry = it.next();
            if (entry.getValue().sucOcpy == null)
                it.remove();        // 如果除了虚拟头结点外，没有有效的资源占用，就删除该元素
        }

        // 获取全部任务连接，并赋予任务节点optTaskNode中的普通连接集合，使待优化任务节点连接起来
        for (PmsTaskLink pmsTaskLink : pmsTaskLinkService.selectByProcUidList(procUidList)) {
            OptTaskNode preTaskNode = optTaskNodeMap.get(pmsTaskLink.getTaskLinkPreTaskUid());  // 连接中的前任务
            OptTaskNode sucTaskNode = optTaskNodeMap.get(pmsTaskLink.getTaskLinkSucTaskUid());  // 连接中的后任务
            preTaskNode.getNormalSucTasks().add(sucTaskNode);
            sucTaskNode.getNormalPreTasks().add(preTaskNode);
        }
        // 确定任务的互斥任务集合，此处任务关系指，两个任务没有前后关系但又不能同时进行；
        // 找出任务组的首尾节点，初始化groupUidFirstAndLastTaskNode。
        if (!groupUidOptTaskNodesMap.isEmpty()) {
            for (Map.Entry<String, List<OptTaskNode>> entry : groupUidOptTaskNodesMap.entrySet()) {
                String groupUid = entry.getKey();
                List<OptTaskNode> optTaskNodes = entry.getValue();
                if (optTaskNodes.isEmpty()) {
                    continue;
                }
                // 找出任务组的首尾节点，初始化groupUidFirstAndLastTaskNode
                OptTaskNode firstTaskNode = optTaskNodes.get(0);
                OptTaskNode lastTaskNode = optTaskNodes.get(optTaskNodes.size() - 1);
                while (!firstTaskNode.getNormalPreTasks().isEmpty() && optTaskNodes.contains(firstTaskNode.getNormalPreTasks().get(0))) {
                    firstTaskNode = firstTaskNode.getNormalPreTasks().get(0);
                }
                while (!lastTaskNode.getNormalSucTasks().isEmpty() && optTaskNodes.contains(lastTaskNode.getNormalSucTasks().get(0))) {
                    lastTaskNode = lastTaskNode.getNormalSucTasks().get(0);
                }
                OptTaskNode[] firstAndLastTaskNode = new OptTaskNode[2];
                firstAndLastTaskNode[0] = firstTaskNode;
                firstAndLastTaskNode[1] = lastTaskNode;
                groupUidFirstAndLastTaskNode.put(groupUid, firstAndLastTaskNode);
                // 确定任务的互斥任务集合
                for (OptTaskNode optTaskNode : optTaskNodes) {
                    optTaskNode.setMutexTasks(new ArrayList<>(optTaskNodes));
                    optTaskNode.getMutexTasks().remove(optTaskNode);
                }
            }
            // 确定任务组内以及与任务组相关的任务节点的实际紧前任务和紧后任务集合
            for (Map.Entry<String, List<OptTaskNode>> entry : groupUidOptTaskNodesMap.entrySet()) {
                String groupUid = entry.getKey();
                List<OptTaskNode> curGroupTaskNodes = entry.getValue();
                List<OptTaskNode> preTaskNodes = groupUidFirstAndLastTaskNode.get(groupUid)[0].getNormalPreTasks();
                List<OptTaskNode> sucTaskNodes = groupUidFirstAndLastTaskNode.get(groupUid)[1].getNormalSucTasks();
                Set<OptTaskNode> preTaskNodeSetOfGroup = new HashSet<>();
                Set<OptTaskNode> sucTaskNodeSetOfGroup = new HashSet<>();
                // 任务组的紧前任务
                if (preTaskNodes != null && !preTaskNodes.isEmpty()) {
                    for (OptTaskNode pre : preTaskNodes) {
                        Set<OptTaskNode> sucTaskNodeSetOfPre = new HashSet<>(pre.getSucTasks());
                        sucTaskNodeSetOfPre.addAll(curGroupTaskNodes);
                        sucTaskNodeSetOfPre.addAll(pre.getNormalSucTasks());
                        if (!taskUidAndGroupUid.containsKey(pre.getPmsTask().getTaskUid())) {
                            preTaskNodeSetOfGroup.add(pre);
                        } else {
                            List<OptTaskNode> preGroupTaskNodes = groupUidOptTaskNodesMap.get(taskUidAndGroupUid.get(pre.getPmsTask().getTaskUid()));
                            preTaskNodeSetOfGroup.addAll(preGroupTaskNodes);
                        }
                        pre.setSucTasks(new ArrayList<>(sucTaskNodeSetOfPre));
                    }
                }
                preTaskNodes = new ArrayList<>(preTaskNodeSetOfGroup);
                // 任务组的紧后任务
                if (sucTaskNodes != null && !sucTaskNodes.isEmpty()) {
                    for (OptTaskNode suc : sucTaskNodes) {
                        Set<OptTaskNode> preTaskNodeSetOfSuc = new HashSet<>(suc.getPreTasks());
                        preTaskNodeSetOfSuc.addAll(curGroupTaskNodes);
                        preTaskNodeSetOfSuc.addAll(suc.getNormalPreTasks());
                        if (!taskUidAndGroupUid.containsKey(suc.getPmsTask().getTaskUid())) {
                            sucTaskNodeSetOfGroup.add(suc);
                        } else {
                            List<OptTaskNode> sucGroupTaskNodes = groupUidOptTaskNodesMap.get(taskUidAndGroupUid.get(suc.getPmsTask().getTaskUid()));
                            sucTaskNodeSetOfGroup.addAll(sucGroupTaskNodes);
                        }
                        suc.setPreTasks(new ArrayList<>(preTaskNodeSetOfSuc));
                    }
                }
                sucTaskNodes = new ArrayList<>(sucTaskNodeSetOfGroup);

                for (OptTaskNode taskNodeOfGroup : curGroupTaskNodes) {
                    taskNodeOfGroup.setPreTasks(preTaskNodes);
                    taskNodeOfGroup.setSucTasks(sucTaskNodes);
                }
            }
        }
        // 确定所有任务的实际紧前紧后节点，紧前紧后任务数量和当前紧前任务数量，将虚拟首节点和尾结点加入任务连接图中
        // 赋值任务节点中的资源方案和资源需求集合resPlanReqPairList
        for (OptTaskNode optTaskNode : optTaskNodeMap.values()) {
            if (optTaskNode.getPreTasks() == null || optTaskNode.getPreTasks().isEmpty()) {
                optTaskNode.setPreTasks(optTaskNode.getNormalPreTasks());
            }
            int preTaskCount = optTaskNode.getPreTasks().size();    // 紧前任务数量
            optTaskNode.setPreTaskCount(preTaskCount);
            optTaskNode.setCurPreTaskCount(preTaskCount);
            if (preTaskCount == 0) {
                startOptTaskNode.getSucTasks().add(optTaskNode);
            }

            if (optTaskNode.getSucTasks() == null || optTaskNode.getSucTasks().isEmpty()) {
                optTaskNode.setSucTasks(optTaskNode.getNormalSucTasks());
            }
            int sucTaskCount = optTaskNode.getSucTasks().size();    // 紧后任务数量
            optTaskNode.setSucTaskCount(sucTaskCount);
            optTaskNode.setCurSucTaskCount(sucTaskCount);
            if (sucTaskCount == 0) {
                endOptTaskNode.getPreTasks().add(optTaskNode);
            }
            // 任务的资源方案和资源需求集合resPlanReqPairList
            List<PmsTaskResPlan> pmsTaskResPlans = taskResPlanMap.get(optTaskNode.getPmsTask().getTaskUid());
            if (pmsTaskResPlans != null && !pmsTaskResPlans.isEmpty()) {
                for (PmsTaskResPlan pmsTaskResPlan : pmsTaskResPlans) {
                    Pair<String, List<PmsTaskResReq>> pair = new Pair<>(pmsTaskResPlan.getResPlanUid(), resPlanResReqMap.get(pmsTaskResPlan.getResPlanUid()));
                    if (pair.getValue() != null && !pair.getValue().isEmpty())
                        optTaskNode.getResPlanReqPairList().add(pair);
                }
                optTaskNode.setResPlanCount(pmsTaskResPlans.size());    // 该任务的资源方案个数
            }
        }
        // 确定任务的
        // 后续任务数量（包括自身）最多的路径上的任务个数sucTaskCountSum;
        // 后续任务工期和（包括自身）最大的路径上的任务工期和sucTaskDurSum;
        // 任务最晚完成时间与优化起点日期相差的天数lateFinish;
        // 任务重要性importance
        // 以及优先级评价函数中相应规则项的值
        // 倒序广度优先遍历
        Queue<OptTaskNode> traversalQueue = new LinkedList<>(endOptTaskNode.getPreTasks());
        while (!traversalQueue.isEmpty()) {
            OptTaskNode optTaskNode = traversalQueue.poll();
            for (OptTaskNode preTask : optTaskNode.getPreTasks()) {
                preTask.setCurSucTaskCount(preTask.getCurSucTaskCount() - 1);
                // 紧前任务入队
                if (preTask.getCurSucTaskCount() == 0) {
                    traversalQueue.add(preTask);
                    preTask.setCurSucTaskCount(preTask.getCurSucTaskCount());
                }
            }
            // 以当前任务为起点到该任务所在项目终点的路径中：
            // 最大的任务数量sucTaskCountSum
            // 最长的任务工期和sucTaskDurSum
            double sucTaskCountSum = 0;
            double sucTaskDurSum = 0;
            for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
                sucTaskCountSum = Math.max(sucTaskCountSum, sucTask.getSucTaskCountSum());
                sucTaskDurSum = Math.max(sucTaskDurSum, sucTask.getSucTaskDurSum());
            }
            optTaskNode.setSucTaskCountSum(sucTaskCountSum + 1);   // 加上自身的数量1
            optTaskNode.setSucTaskDurSum(sucTaskDurSum + optTaskNode.getPmsTask().getTaskPlanDur()); // 加上自身工期
            // 任务最晚完成时间与优化起点日期相差的天数lateFinish
            optTaskNode.setLateFinish((optTaskNode.getPmsTask().getTaskLateFinishDateTime().getTime() - optOrigin.getTime()) / (MS_OF_DAY));
            // 任务重要性importance
            optTaskNode.setImportance(optTaskNode.getPmsTask().getTaskPriority());
        }
        // 设定每个项目原本没有紧前任务的任务的最早开始时间，若已设定，则保留；若未设定，则设为项目最早开始时间
        for (OptTaskNode firstOptTaskNode : startOptTaskNode.getSucTasks()) {
            PmsTask pmsTask = firstOptTaskNode.getPmsTask();
            if (pmsTask.getTaskEarlyStartDateTime() == null) {
                pmsTask.setTaskEarlyStartDateTime(pmsProjectMap.get(pmsTask.getTaskProjUid()).getProjEarlyStartDateTime());
            }
            // 初始化projMaxPathTaskCountMap，项目的最大步长，key为项目UID，value为对应项目的任务数量最大的路径上的任务个数
            if (!projMaxPathTaskCountMap.containsKey(pmsTask.getTaskProjUid())) {
                projMaxPathTaskCountMap.put(pmsTask.getTaskProjUid(), firstOptTaskNode.getSucTaskCountSum());
            } else  {
                projMaxPathTaskCountMap.put(pmsTask.getTaskProjUid(), Math.max(projMaxPathTaskCountMap.get(pmsTask.getTaskProjUid()), firstOptTaskNode.getSucTaskCountSum()));
            }
            // 初始化projFirstLastTasksMap，key是项目UID，value是项目中没有紧前任务的任务节点和无紧后任务的任务两个list组成的数组
            if (!projFirstLastTasksMap.containsKey(pmsTask.getTaskProjUid())) {
                projFirstLastTasksMap.put(pmsTask.getTaskProjUid(), new ArrayList[]{ new ArrayList<OptTaskNode>(), new ArrayList<OptTaskNode>() });
            }
            projFirstLastTasksMap.get(pmsTask.getTaskProjUid())[0].add(firstOptTaskNode);
        }
        // 赋值任务节点中的项目最大步长，即项目包含最多任务数量的路径中的任务数量maxProjPathTaskCount
        // 计算任务优先级评价函数中各规则项的值
        // 为每个任务指定编号taskNo，任务资源基因的取值上限genValueLimit
        // 正序广度优先遍历每个项目
        genValueLimit = new int[optTaskCountSum];

        int taskNo = 0;
        for (List<OptTaskNode>[] firstLastOptTaskNodes : projFirstLastTasksMap.values()) {
            List<OptTaskNode> firstOptTaskNodes = firstLastOptTaskNodes[0];
            double maxProjPathTaskCount = projMaxPathTaskCountMap.get(firstOptTaskNodes.get(0).getPmsTask().getTaskProjUid());
            // 正序广度优先遍历一个项目
            Queue<OptTaskNode> queue = new LinkedList<>(firstOptTaskNodes);
            while (!queue.isEmpty()) {
                OptTaskNode optTaskNode = queue.poll();
                for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
                    sucTask.setCurPreTaskCount(sucTask.getCurPreTaskCount() - 1);
                    // 紧后任务入队
                    if (sucTask.getCurPreTaskCount() == 0) {
                        queue.add(sucTask);
                        sucTask.setCurPreTaskCount(sucTask.getPreTaskCount());
                    }
                }
                // 指定任务编号
                optTaskNode.setTaskNo(taskNo);
                optTaskNodeListByNo.add(taskNo, optTaskNode);
                // 后半段基因取值的上限为资源方案的权重的数字总和
                resPlanPriCumulation[taskNo] = new ArrayList<>();

                List<PmsTaskResPlan> tempResPlans = taskResPlanMap.get(optTaskNode.getPmsTask().getTaskUid());
                if (tempResPlans != null && !tempResPlans.isEmpty()) {
                    List<Integer> priList = new ArrayList<>();
                    for (PmsTaskResPlan pmsTaskResPlan : taskResPlanMap.get(optTaskNode.getPmsTask().getTaskUid())) {
                        if (!resPlanResReqMap.containsKey(pmsTaskResPlan.getResPlanUid())) {    // 忽略空的资源方案
                            continue;
                        }
                        if (priList.isEmpty()) {
                            priList.add(pmsTaskResPlan.getResPlanPriority());
                        }
                        else {
                            if (pmsTaskResPlan.getResPlanPriority() != priList.get(priList.size() - 1)) {   // 去掉重复值
                                priList.add(pmsTaskResPlan.getResPlanPriority());
                            }
                        }
                    }
                    if (!priList.isEmpty()) {
                        // 反转priList
                        Collections.reverse(priList);
                        // 初始化genValueLimit和resPlanPriCumulation
                        genValueLimit[taskNo] = priList.get(0);
                        resPlanPriCumulation[taskNo].add(genValueLimit[taskNo]);
                        int priIndex = 0;       // priList的下标
                        for (int i = 1; i < tempResPlans.size(); i++) {
                            if (!resPlanResReqMap.containsKey(tempResPlans.get(i).getResPlanUid())) {    // 忽略空的资源方案
                                continue;
                            }
                            int resPlanPriority = tempResPlans.get(i).getResPlanPriority();
                            int preResPlanPriority = tempResPlans.get(i - 1).getResPlanPriority();
                            if (resPlanPriority != preResPlanPriority) {
                                priIndex++;
                            }
                            genValueLimit[taskNo] += priList.get(priIndex);
                            resPlanPriCumulation[taskNo].add(genValueLimit[taskNo]);
                        }
                    }
                }
                taskNo++;

                // 赋值任务节点中的maxProjPathTaskCount
                optTaskNode.setMaxProjPathTaskCount(maxProjPathTaskCount);

                // 计算任务优先级评价函数中各规则项的值
                // 后续任务数量规则项的值
                optTaskNode.setSucTaskCountSumValue(optTaskNode.getSucTaskCountSum() / maxProjPathTaskCount);
                // 后续任务工期和规则项的值
                double projPlanDur = pmsProjectMap.get(optTaskNode.getPmsTask().getTaskProjUid()).getProjPlanDur();
                optTaskNode.setSucTaskDurSumValue(optTaskNode.getSucTaskDurSum() / projPlanDur);
                // 任务最晚完成时间规则项的值
                optTaskNode.setLateFinishValue(1 - optTaskNode.getLateFinish() / optDays);
                // 任务重要性规则项的值
                optTaskNode.setImportanceValue(optTaskNode.getImportance() / IMPORTANCEVALUE_HIGHEST);

                // 将projFirstLastTasksMap补充完整，补充每个项目的无紧后任务的任务
                if (optTaskNode.getSucTaskCount() == 0) {
                    projFirstLastTasksMap.get(optTaskNode.getPmsTask().getTaskProjUid())[1].add(optTaskNode);
                }
            }
        }

        return startOptTaskNode;
    }

    /**
     * 流程优化主体
     * @param procUidList
     * @return
     */
    public List<PmsTask> optimize(List<String> procUidList) {
        initTaskNodes(procUidList);  // 初始化任务节点图，获得任务节点网络的虚拟首节点

        int maxG = MAX_G;                       // 最大迭代次数
        int G = 0;
        // 种群的代数
        int chromosomeNum = CHROMOSOME_NUM;              // 种群中的个体（染色体）个数
        int genNum = optTaskCountSum << 1;   // 一个染色体上的基因个数，即编码长度
        int[][] population = new int[chromosomeNum][genNum];  // 种群存在数组中
        double[] populationFitness = new double[chromosomeNum];     // 记录种群中个体的适应度值
        int[][] sunPopulation = new int[chromosomeNum << 1][genNum];  // 交叉和变异得到的个体
//        double[] sunPopulationFitness = new double[chromosomeNum];     // 交叉和变异个体的适应度值
        // 初始化种群
        Random random = new Random();
        if (startOptTaskNode != null) {
            List<OptTaskNode> readyTaskNodes = new LinkedList<>();
            for (int i = 0; i < chromosomeNum; i++) {
                int genIndex = 0;
                // 正序广度优先遍历所有任务
                readyTaskNodes.addAll(startOptTaskNode.getSucTasks());
                while (!readyTaskNodes.isEmpty()) {
                    int taskIndex = random.nextInt(readyTaskNodes.size());
                    OptTaskNode optTaskNode = readyTaskNodes.remove(taskIndex);     // 随机选择一个无紧前任务的任务
                    int taskNo = optTaskNode.getTaskNo();
                    population[i][genIndex] = taskNo;            // 填充一位基因
                    if (genValueLimit[taskNo] == 0)
                        population[i][optTaskCountSum + genIndex] = -1;  // 若任务没有资源方案，则对应资源基因置为-1，否则按优先级随机选取一个资源方案
                    else {
                        population[i][optTaskCountSum + genIndex] = getResPlanNo(taskNo, random.nextInt(genValueLimit[taskNo]));
//                        optTaskNode.setResPlanGenIndex(optTaskCountSum + genIndex);
                    }
                    genIndex++;
                    // 紧后任务入队
                    for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
                        sucTask.setCurPreTaskCount(sucTask.getCurPreTaskCount() - 1);
                        // 无紧前任务的紧后任务入队
                        if (sucTask.getCurPreTaskCount() == 0) {
                            readyTaskNodes.add(sucTask);
                            sucTask.setCurPreTaskCount(sucTask.getPreTaskCount());
                        }
                    }
                }
            }
        }

        // 计算初始种群中个体的适应度函数，初始化populationFitness
        System.out.println("**********************适应度*********************");
        System.out.println("第" + G + "代");
        for (int i = 0; i < chromosomeNum; i++) {
            populationFitness[i] = fitness(G, decode(population[i]));
            System.out.print(populationFitness[i] + "\t");
        }
        System.out.println();
        // 开始迭代进化
        while (++G <= maxG) {
            System.out.println("第" + G + "代");
            // 逐个遍历种群的个体，对第i个个体进行“交叉->变异”操作，将新产生的个体保存在sunPopulation中
            int sunIndex = 0;
            for (int i = 0; i < chromosomeNum; i++) {
                int[] target = population[i];                            // 目标个体
                if (Math.random() < CR) {
                    int[] crossover = cross(target, population, i);               // 得到交叉个体（返回的是new int[]）
                    if (Math.random() < MR) {
                        int[] mutation = mutate(crossover, genValueLimit);   // 得到变异个体（返回的是变异后的crossover，不是new int[]）
                        sunPopulation[sunIndex++] = mutation;
                    } else {
                        sunPopulation[sunIndex++] = crossover;
                    }
                }
            }

            // 选择操作，比较个体与其对应的交叉个体的适应度值，选择适应度值小（综合工期+罚函数值小）的个体进入下一代
            int[][] newPopulation = new int[chromosomeNum][genNum];
            double[] newPopulationFitness = new double[chromosomeNum];
            int newIndex = 0;       // 新种群newPopulationd的下标指示
            // 交叉变异产生的个体锦标赛，前后对称的两个个体比较，留下优秀的
            for (int i = 0, j = sunIndex - 1; i <= j; i++, j--) {
                double firstFitness = fitness(G, decode(sunPopulation[i]));
                double secondFitness = fitness(G, decode(sunPopulation[j]));
                if (firstFitness < secondFitness) {
                    newPopulation[newIndex++] = sunPopulation[i];
                    newPopulationFitness[i] = firstFitness;
                } else {
                    newPopulation[newIndex++] = sunPopulation[j];
                    newPopulationFitness[i] = secondFitness;
                }
            }
            // 原种群个体锦标赛，新种群补充完整
            int spareNum = chromosomeNum - newIndex;    // 还需要补充的个体个数
            int team = chromosomeNum / spareNum;        // 每组的个体数量，组内竞标
            int remainder = chromosomeNum % team;       // 分组后剩下凑不够一组的个体数量，之后将这些个体合并进第一组参与竞赛
            int index = 0;                              // 原种群的下标指示
            double tmpFitness = Double.MAX_VALUE;
            // 第一组与“剩余个体”一起竞赛
            for (index = 0; index < remainder + team; index++) {
                if (populationFitness[index] < tmpFitness) {
                    newPopulation[newIndex] = population[index];
                    newPopulationFitness[newIndex] = populationFitness[index];
                }
            }
            newIndex++;
            // 逐个组进行组内竞赛，胜出的留下
            while (index < chromosomeNum && newIndex < chromosomeNum) {
                tmpFitness = Double.MAX_VALUE;
                for (int i = 0; i < team; i++) {
                    if (populationFitness[index] < tmpFitness) {
                        newPopulation[newIndex] = population[index];
                        newPopulationFitness[newIndex] = populationFitness[index];
                    }
                    index++;
                }
                newIndex++;
            }
            population = newPopulation;
            populationFitness = newPopulationFitness;

            // 打印新一代适应度值
            for (int i = 0; i < chromosomeNum; i++) {
                populationFitness[i] = fitness(G, decode(population[i]));
                System.out.print(populationFitness[i] + "\t");
            }
            System.out.println();
        }
        // 找出优化后，种群中的最佳个体
        int[] bestIndividual  = population[0];
        double bestFitness = populationFitness[0];
        for (int i = 0; i < chromosomeNum; i++) {
            if (bestFitness > populationFitness[i]) {
                bestFitness = populationFitness[i];
                bestIndividual = population[i];
            }
        }
        System.out.println("****************************************************************");
        System.out.println("最佳个体适应度值：" + bestFitness);
        System.out.println("最佳个体目标函数值：" + fitness(0, decode(bestIndividual)));
        // 最佳个体解码，重置任务优先级、资源占用表
        decode(bestIndividual);
        // 正序广度优先遍历，打印优化结果
        List<PmsTask> result = new LinkedList<>();
        Queue<OptTaskNode> priQueue = new PriorityQueue<>(cmpOptTaskNodeByPriority);  // 任务优先队列，按任务优先级评价的降序排列
        priQueue.addAll(startOptTaskNode.getSucTasks());
        while (!priQueue.isEmpty()) {
            OptTaskNode optTaskNode = priQueue.poll();
            for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
                sucTask.setCurPreTaskCount(sucTask.getCurPreTaskCount() - 1);
                // 可执行的紧后任务入队
                if (sucTask.getCurPreTaskCount() == 0) {
                    sucTask.setCurPreTaskCount(sucTask.getPreTaskCount());  // 恢复当前紧前任务值，以便下次遍历时使用
                    priQueue.add(sucTask);
                }
            }
            PmsTask pmsTask = optTaskNode.getPmsTask();
            result.add(pmsTask);
//            System.out.println("**************");
//            System.out.println(optTaskNode.getPriorityValue());
//            System.out.println(pmsTask.getTaskName());
//            System.out.println(pmsTask.getTaskPlanStartDateTime());
//            System.out.println(pmsTask.getTaskPlanFinishDateTime());
        }
        return result;
    }

    /**
     * 解码，得到个体对应的计划中，每个项目的开始、结束时间，存入projStartFinishTimeMap并返回
     * @param chromosome
     * @return
     */
    public Map<String, Timestamp[]> decode(int[] chromosome) {

        Map<String, Timestamp[]> projStartFinishTimeMap = new HashMap<>();
        Map<String, OptTaskNode> readyTaskMap = new HashMap<>();        // 存储已被安排时间的任务节点，key为任务UID
//        resOcpyNodeMap = new HashMap<>();
        resOcpyNodeMap.clear();     // 还原资源占用情况resOcpyNodeMap
        for (Map.Entry<String, ResOcpyNode> entry : resOcpyNodeMapOrig.entrySet()) {
            String resUid = entry.getKey();
            ResOcpyNode origNode = entry.getValue();
            ResOcpyNode newNode = new ResOcpyNode(origNode);
            resOcpyNodeMap.put(resUid, newNode);
            while (origNode.sucOcpy != null) {
                origNode = origNode.sucOcpy;
                newNode.sucOcpy = new ResOcpyNode(origNode);
                newNode.sucOcpy.preOcpy = newNode;
                newNode = newNode.sucOcpy;
            }
        }
        // 按个体编码的顺序遍历任务，解码，求个体的工期Dur和完成时间Finish
        for (int i = 0; i < optTaskCountSum; i++) {
            int taskNo = chromosome[i];
            OptTaskNode optTaskNode = optTaskNodeListByNo.get(taskNo);

            // 安排任务开始、结束时间
            PmsTask pmsTask = optTaskNode.getPmsTask();     // 获取任务实例
            // 确定安排任务开始时间的搜索起点，即任务最早开始时间及其紧前任务的计划完成时间中，最晚的时间
            Timestamp taskStart = new Timestamp(0);
            Timestamp taskFinish = new Timestamp(0);
            if (pmsTask.getTaskEarlyStartDateTime() != null) {
                taskStart.setTime(pmsTask.getTaskEarlyStartDateTime().getTime());
            }
            // 开始时间置为紧前任务的结束时间
            for (OptTaskNode preTask : optTaskNode.getPreTasks()) {
                if (preTask.getPmsTask().getTaskPlanFinishDateTime().after(taskStart))
                    taskStart.setTime(preTask.getPmsTask().getTaskPlanFinishDateTime().getTime());
            }
            taskStart.setTime(startTime(taskStart, pmsTask.getTaskWorkModel()).getTime());          // 根据紧前任务完成时间和当前任务执行模式得到当前任务的最早开始时间
            taskFinish.setTime(endTime(taskStart, pmsTask.getTaskPlanDur(), pmsTask.getTaskWorkModel()).getTime());  // 当前任务开始时间对应的结束时间
            // 初始化任务计划开始、结束时间
            pmsTask.setTaskPlanStartDateTime(taskStart);
            pmsTask.setTaskPlanFinishDateTime(taskFinish);
            int resPlanIndex = chromosome[i + optTaskCountSum];   // 个体选定的该任务的资源方案在任务资源方案list中的下标
            if (resPlanIndex == -1) {   // 如果没有资源方案，则不考虑资源
                readyTaskMap.put(pmsTask.getTaskUid(), optTaskNode);    // 加入已安排任务集合
                continue;
            }
            List<PmsTaskResReq> resReqs = new ArrayList<>();    // 个体编码中的任务资源方案中的资源需求
            if (optTaskNode.getResPlanReqPairList() != null && !optTaskNode.getResPlanReqPairList().isEmpty()) {
                resReqs = optTaskNode.getResPlanReqPairList().get(resPlanIndex).getValue();
            }
            // 根据资源需求和资源占用情况搜索任务可行的最早开始时间，工作时间为8点到18点
            double dur = pmsTask.getTaskPlanDur();  // 任务预计工期
            List<ResOcpyNode> newResNodes = new LinkedList<>(); // 暂存新的资源占用节点
            boolean resAllocate = false;
            while (!resAllocate) {
                resAllocate = true;
                // 考虑互斥任务
                if (!optTaskNode.getMutexTasks().isEmpty()) {
                    boolean mTimeOk = false;
                    // 保证避开所有互斥任务
                    while (!mTimeOk) {
                        mTimeOk = true;
                        for (OptTaskNode mutexTaskNode : optTaskNode.getMutexTasks()) {
                            PmsTask mutexTask = mutexTaskNode.getPmsTask();
                            if (readyTaskMap.containsKey(mutexTask.getTaskUid())) {
                                // 若初始搜索时间段与该任务的已经安排的互斥任务有重合，则将开始时间向后推到互斥任务的结束时间，以避开重合
                                if (taskStart.before(mutexTask.getTaskPlanFinishDateTime()) && taskFinish.after(mutexTask.getTaskPlanStartDateTime())) {
                                    if (taskStart.before(mutexTask.getTaskPlanFinishDateTime()))
                                    taskStart.setTime(mutexTask.getTaskPlanFinishDateTime().getTime());
                                    taskFinish.setTime(endTime(taskStart, pmsTask.getTaskPlanDur(), pmsTask.getTaskWorkModel()).getTime());
                                    mTimeOk = false;
                                }
                            }
                        }
                    }
                }
                newResNodes.clear();    // 新一轮尝试安排任务时间之前，清空上轮产生的资源占用节点
                taskFinish.setTime(endTime(taskStart, dur, pmsTask.getTaskWorkModel()).getTime());  // 当前任务开始时间对应的结束时间
                for (PmsTaskResReq resReq : resReqs) {
                    String resUid = resReq.getResReqResUid();
                    if (resOcpyNodeMap.containsKey(resUid)) {   // 如果资源占用map包含了表示该资源情况的链表
                        ResOcpyNode newResNode = new ResOcpyNode(resReq);   // 任务资源需求产生的新的资源占用
                        ResOcpyNode preNode = resOcpyNodeMap.get(resUid);
                        ResOcpyNode resOcpyNode = preNode.sucOcpy;  // 指向当前节点
                        if (resOcpyNode.getResAmount() == null || resOcpyNode.getResAmount() == 0) {       // 如果没有资源数量要求，即该资源为独占资源（人力/设备）
                            // 找到第一个占用结束时间在任务开始时间之后的资源占用节点
                            while (resOcpyNode != null && !resOcpyNode.getResFinishDateTime().after(taskStart)) {
                                preNode = resOcpyNode;
                                resOcpyNode = resOcpyNode.sucOcpy;
                            }
                            if (resOcpyNode == null || !resOcpyNode.getResStartDateTime().before(taskFinish)) {      // 如果所有已占用都与任务执行期无关，则资源从任务开始时占用
                                newResNode.setResStartDateTime(new Timestamp(taskStart.getTime()));
                                newResNode.setResFinishDateTime(endTime(taskStart, resReq.getResReqResWork(), resReq.getResReqResWorkModel()));
                                newResNode.preOcpy = preNode;   // 暂时单向连上，挂在链表上，以便之后定位，搜索完毕后再将新节点嵌入链表中
                                newResNodes.add(newResNode);
                            } else {
                                // 判断资源需求能否得到满足，即在任务期内，资源有一整段的空闲，且这段空闲能够满足资源需求
                                while (preNode != null) {
                                    Timestamp resStartTime = new Timestamp(preNode.getResFinishDateTime().getTime());    // 空闲时间段的前边界
                                    if (resStartTime.before(taskStart)) {
                                        resStartTime.setTime(taskStart.getTime());
                                    }
                                    Timestamp resEndTime = endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel());
                                    Timestamp postTime = null;      // 空闲时间段的后边界
                                    if (resOcpyNode != null) {        // 如果没有超出链表末端，则为时间后边界赋值，否则postTime为空，即没有后边界
                                        postTime = resOcpyNode.getResStartDateTime();
                                    }
                                    // 如果搜索的资源占用结束时间超出了任务结束时间，则该任务时间不可行，任务开始时间后移到下一个整点
                                    if (resEndTime.after(taskFinish)) {
                                        resAllocate = false;
                                        moveTimeToNextPoint(taskStart);
                                        break;
                                    }
                                    // 如果找到了满足资源需求的时间段
                                    if (!resStartTime.before(taskStart) && (postTime == null || !resEndTime.after(postTime))) {
                                        newResNode.setResStartDateTime(resStartTime);
                                        newResNode.setResFinishDateTime(endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel()));
                                        newResNode.preOcpy = preNode;   // 暂时单向连上，挂在链表上，以便之后定位，搜索完毕后再将新节点嵌入链表中
                                        newResNodes.add(newResNode);
                                        break;
                                    }
                                    preNode = resOcpyNode;
                                    resOcpyNode = resOcpyNode == null ? null : resOcpyNode.sucOcpy;
                                }
                            }
                        } else {        // 如果包含资源数量信息，说明是非独占资源
                            // 判断资源需求能否得到满足，即在任务期间，有足够的剩余资源来完成任务
                            double amountSum = resAmountMap.get(resReq.getResReqResUid());     // 资源总量
                            double amountCur =  amountSum;
                            double amountReq = resReq.getResReqResAmount();
                            Timestamp resStartTime = new Timestamp(taskStart.getTime());    // 资源占用开始时间
                            Timestamp resEndTime = endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel());
                            // 查找第一个资源占用结束时间在任务开始时间之后的节点
                            while (resOcpyNode != null && !resOcpyNode.getResFinishDateTime().after(resStartTime)) {
                                preNode = resOcpyNode;
                                resOcpyNode = resOcpyNode.sucOcpy;
                            }
                            ResOcpyNode curHead = resOcpyNode;      // 记录当前搜到的节点，作为计算资源占用的起点
                            if (resOcpyNode == null) {      // 如果所有已占用都与现资源需求安排无关，则资源直接安排
                                newResNode.setResStartDateTime(resStartTime);
                                newResNode.setResFinishDateTime(endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel()));
                                newResNode.setResAmount(resReq.getResReqResAmount());
                                newResNode.preOcpy = preNode;   // 暂时单向连上，挂在链表上，以便之后定位，搜索完毕后再将新节点嵌入链表中
                                newResNodes.add(newResNode);
                            } else {
                                while (!resEndTime.after(taskFinish)) {
                                    while (resOcpyNode != null && resOcpyNode.getResStartDateTime().before(resEndTime)) {
                                        if (resOcpyNode.getResStartDateTime().before(resEndTime) && resOcpyNode.getResFinishDateTime().after(resStartTime)) {
                                            amountCur -= resOcpyNode.getResAmount();
                                        }
                                        // preNode = resOcpyNode;
                                        resOcpyNode = resOcpyNode.sucOcpy;
                                    }
                                    if (amountCur < amountReq) {    // 如果该资源时间段不满足资源需求，资源开始时间后移一个整点
                                        moveTimeToNextPoint(resStartTime);
                                        resEndTime = endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel());
                                    } else {
                                        // 查找最后一个资源占用开始时间在resStartTime之前的节点preNode，作为新节点的前节点，使资源占用链以占用开始时间排序
                                        while (curHead != null && curHead.getResStartDateTime().before(resStartTime)) {
                                            preNode = curHead;
                                            curHead = curHead.sucOcpy;
                                        }
                                        newResNode.setResStartDateTime(resStartTime);
                                        newResNode.setResFinishDateTime(endTime(resStartTime, resReq.getResReqResWork(), resReq.getResReqResWorkModel()));
                                        newResNode.setResAmount(resReq.getResReqResAmount());
                                        newResNode.preOcpy = preNode;   // 暂时单向连上，挂在链表上，以便之后定位，搜索完毕后再将新节点嵌入链表中
                                        newResNodes.add(newResNode);
                                        break;
                                    }
                                    amountCur = amountSum;
                                    resOcpyNode = curHead;
                                }
                                // 如果搜索的资源占用结束时间超出了任务结束时间，则该任务时间不可行，任务开始时间后移到下一个整点
                                if (resEndTime.after(taskFinish)) {
                                    resAllocate = false;
                                    moveTimeToNextPoint(taskStart);
                                    pmsTask.setTaskPlanStartDateTime(taskStart);
                                    pmsTask.setTaskPlanFinishDateTime(taskFinish);
                                    break;
                                }
                            }
                        }
                    } else {
                        // 构造一个虚拟首节点，方便构造和遍历链表
                        ResOcpyNode preNode = new ResOcpyNode();
                        preNode.setResStartDateTime(new Timestamp(0));
                        preNode.setResFinishDateTime(new Timestamp(0));
                        resOcpyNodeMap.put(resUid, preNode);
                        // 将当前资源安排加入资源占用链表
                        ResOcpyNode resOcpyNode = new ResOcpyNode(resReq);
                        resOcpyNode.setResStartDateTime(taskStart);
                        resOcpyNode.setResFinishDateTime(endTime(taskStart, resReq.getResReqResWork(), resReq.getResReqResWorkModel()));
                        resOcpyNode.setResAmount(resReq.getResReqResAmount());
                        preNode.sucOcpy = resOcpyNode;
                        resOcpyNode.preOcpy = preNode;

                    }
                    if (!resAllocate) {
                        break;
                    }
                }
            }
            // 设定任务计划开始、结束时间
            pmsTask.setTaskPlanStartDateTime(taskStart);
            pmsTask.setTaskPlanFinishDateTime(taskFinish);
            // 将排好的资源占用时间加入资源占用链表中
            for (ResOcpyNode newResNode : newResNodes) {
                ResOcpyNode preNode = newResNode.preOcpy;
                ResOcpyNode sucNode = preNode.sucOcpy;
                preNode.sucOcpy = newResNode;
//                newResNode.preOcpy = preNode; // 之前已赋值
                if (sucNode != null) {
                    sucNode.preOcpy = newResNode;
                    newResNode.sucOcpy = sucNode;
                }
            }
            readyTaskMap.put(pmsTask.getTaskUid(), optTaskNode);    // 加入已安排任务集合
        }

        // 得到个体对应的计划中，每个项目的开始结束时间，存入projStartFinishTimeMap并返回
        for (Map.Entry<String, List<OptTaskNode>[]> entry : projFirstLastTasksMap.entrySet()) {
            String projUid = entry.getKey();
            List<OptTaskNode>[] value = entry.getValue();
            Timestamp start = new Timestamp(Long.MAX_VALUE);    // 项目的开始时间
            Timestamp finish = new Timestamp(Long.MIN_VALUE);   // 项目的结束时间
            for (OptTaskNode first : value[0]) {
                if (start.after(first.getPmsTask().getTaskPlanStartDateTime()))
                    start.setTime(first.getPmsTask().getTaskPlanStartDateTime().getTime());
            }
            for (OptTaskNode last : value[1]) {
                if (finish.before(last.getPmsTask().getTaskPlanFinishDateTime()))
                    finish.setTime(last.getPmsTask().getTaskPlanFinishDateTime().getTime());
            }
            projStartFinishTimeMap.put(projUid, new Timestamp[]{start, finish});
        }

        return projStartFinishTimeMap;
    }

    /**
     * 自定义优先队列的比较器，按任务优先级评价的降序排列
     */
    static Comparator<OptTaskNode> cmpOptTaskNodeByPriority = new Comparator<OptTaskNode>() {
        @Override
        public int compare(OptTaskNode o1, OptTaskNode o2) {
            return Double.compare(o2.getPriorityValue(), o1.getPriorityValue());
        }
    };

    /**
     * 求任务的优先级评价值
     * @param optTaskNode
     * @param chromosome
     * @return
     */
    public double priorityValue(OptTaskNode optTaskNode, double[] chromosome) {
        return (chromosome[0] * optTaskNode.getSucTaskCountSumValue()
                + chromosome[1] * optTaskNode.getSucTaskDurSumValue()
                + chromosome[2] * optTaskNode.getLateFinishValue()
                + chromosome[3] * optTaskNode.getImportanceValue());
    }

    /**
     * 返回资源方案在list中的下标
     * @param taskNo
     * @param r
     * @return
     */
    public int getResPlanNo(int taskNo, int r) {
        List<Integer> cumulation = resPlanPriCumulation[taskNo];
        for (int i = 0; i < cumulation.size(); i++) {
            if (r < cumulation.get(i))
                return i;
        }
        return cumulation.size() - 1;
    }

    /**
     * 由紧前任务结束时间和当前任务工作模式得到当前任务开始时间
     * @param end
     * @param model
     * @return
     */
    public Timestamp startTime(Timestamp end, int model) {
        Timestamp start = new Timestamp(end.getTime());
        if (model == 1) {       // 若执行模式为连续，则全体都可以开始，无需考虑工作时间制度
            return start;
        }
        // 若执行模式为普通，即为朝八晚六的工作模式
        calendar.setTime(start);
        int h = calendar.get(Calendar.HOUR_OF_DAY);     // 小时时间
        if (8 <= h && h < 18) {         // 如果在日常工作时间内，则可以直接作为任务开始时间
            return start;
        }
        // 如果不在日常工作时间内，则开始时间推迟到下一个工作时间
        if (h < 8) {
            start.setTime(start.getTime() + (8 - h) * MS_OF_HOUR);
        } else if (h >= 18) {
            start.setTime(start.getTime() + (24 + 8 - h) * MS_OF_HOUR);
        }
        return start;
    }

    /**
     * 由开始时间、时长以及工作模式得到结束时间
     * @param start
     * @param dur
     * @param model
     * @return
     */
    public Timestamp endTime(Timestamp start, double dur, int model) {
        Timestamp end = new Timestamp(start.getTime());
        calendar.setTime(start);
        int durDays = (int)dur;      // 任务预计工期的天数部分
        int durHours = (int) ((dur - durDays) * 10);  // 任务预计工期的小时数部分
        int remainHours = 18 - calendar.get(Calendar.HOUR_OF_DAY);   // 当前开始时间到下班时间之间的小时数
        if (model == 1 && remainHours < durHours) {
            end.setTime(start.getTime() + remainHours * MS_OF_HOUR);
            remainHours = 0;
        }
        end.setTime(end.getTime() + durDays * MS_OF_DAY + durHours * MS_OF_HOUR);
        if (remainHours < durHours) {
            end.setTime(end.getTime() + 14 * MS_OF_HOUR);   // 如果当天剩余的工作时间无法满足工期的小时数部分，则需要多跨一天
        }
        return end;
    }

    /**
     * 将时间后移一个单位
     * @param time
     */
    public void moveTimeToNextPoint(Timestamp time) {
        calendar.setTime(time);
        if (calendar.get(Calendar.HOUR_OF_DAY) == 18) {
            time.setTime(time.getTime() + 14 * MS_OF_HOUR);
        } else {
            time.setTime(time.getTime() + MS_OF_HOUR);
        }
    }

    /**
     * 根据个体解码信息，计算适应度值
     * @param G
     * @param projStartFinishTimeMap
     * @return
     */
    public double fitness(int G, Map<String, Timestamp[]> projStartFinishTimeMap) {
        double fitness = 0;
        double g = G >> 2;      // 罚函数中的系数，G为种群代数
        for (Map.Entry<String, Timestamp[]> entry : projStartFinishTimeMap.entrySet()) {
            PmsProject pmsProject = pmsProjectMap.get(entry.getKey());  // 当前遍历到的项目实例
            Timestamp start = entry.getValue()[0];                      // 解码得到的该项目的计划开始时间
            Timestamp finish = entry.getValue()[1];                     // 解码得到的该项目的计划结束时间
            Timestamp projLateFinish = pmsProject.getProjLateFinishDateTime();  // 项目的最晚完成时间
            double estimateDur = pmsProject.getProjPlanDur();                   // 编制时预估的项目工期
            double planDur = (finish.getTime() - start.getTime()) / (double)MS_OF_DAY;  // 解码得到的计划工期(天)
            double f = planDur / estimateDur;           // 个体适应度值
            if (finish.after(projLateFinish)) {         // 如果项目超期了，适应度值就加上罚函数
                double exceedTime = (finish.getTime() - projLateFinish.getTime()) / MS_OF_DAY + 1;  // 超期天数
                f += g * exceedTime / estimateDur;      // 加上罚函数对个体进行惩罚
            }
            fitness += f;
        }
        return fitness;
    }

    /**
     * 变异操作
     * @param crossover
     * @param genValueLimit
     * @return
     */
    public int[] mutate(int[] crossover, int[] genValueLimit) {
        Random random = new Random();
        int genNum = crossover.length;
        // 任务顺序单点变异
        int point = (int) (Math.random() * optTaskCountSum);
        OptTaskNode optTaskNode = optTaskNodeListByNo.get(crossover[point]);
        int taskNo = crossover[point];
        int resPlanNo = crossover[point + optTaskCountSum];

        Set<Integer> preTaskNoSet = new HashSet<>();
        Set<Integer> sucTaskNoSet = new HashSet<>();
        for (OptTaskNode preTask : optTaskNode.getPreTasks()) {
            preTaskNoSet.add(preTask.getTaskNo());
        }
        for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
            sucTaskNoSet.add(sucTask.getTaskNo());
        }

        int preTaskIndex = point - 1, sucTaskIndex = point + 1;     // 紧前任务和紧后任务在个体编码中的下标
        while (preTaskIndex > 0 && !preTaskNoSet.contains(crossover[preTaskIndex])) {
            preTaskIndex--;
        }
        while (sucTaskIndex < optTaskCountSum - 1 && !sucTaskNoSet.contains(crossover[sucTaskIndex])) {
            sucTaskIndex++;
        }

        int newPoint = (int) (Math.random() * (sucTaskIndex - preTaskIndex - 1)) + preTaskIndex + 1;    // 变异后的位置
        if (newPoint < point) {
            for (int i = point; i > newPoint; i--) {
                crossover[i] = crossover[i - 1];
            }
            for (int i = point + optTaskCountSum; i > newPoint + optTaskCountSum; i--) {
                crossover[i] = crossover[i - 1];
            }
            crossover[newPoint] = taskNo;
            crossover[newPoint + optTaskCountSum] = resPlanNo;
        } else if (newPoint > point) {
            for (int i = point; i < newPoint; i++) {
                crossover[i] = crossover[i + 1];
            }
            for (int i = point + optTaskCountSum; i < newPoint + optTaskCountSum; i++) {
                crossover[i] = crossover[i + 1];
            }
            crossover[newPoint] = taskNo;
            crossover[newPoint + optTaskCountSum] = resPlanNo;
        }
        // 任务资源方案逐点变异
        for (int i = optTaskCountSum; i < genNum; i++) {
            if (random.nextDouble() < 0.2) {
                if (crossover[i] != -1) {
                    int curTaskNo = crossover[i - optTaskCountSum];
                    crossover[i] = getResPlanNo(curTaskNo, random.nextInt(genValueLimit[curTaskNo]));
                }
            }
        }
        return crossover;
    }

    /**
     * 交叉操作
     * @param target
     * @param population
     * @param index
     * @return
     */
    public int[] cross(int[] target, int[][] population, int index) {
        int genNum = target.length;
        int[] targetMate;                       // 另一个参与交叉的个体
        int[] crossover = new int[genNum];      // 交叉得到的新个体
        while (true) {
            int tmp = (int) (Math.random() * population.length);
            if (tmp != index) {
                targetMate = population[tmp];
                break;
            }
        }
        // 特殊的单点交叉
        int point = (int) (Math.random() * optTaskCountSum);      // 交叉点
        int[] check = new int[optTaskCountSum];
        // 保留目标个体的前半段或后半段
        if (Math.random() < 0.5) {      // 保留前半段
            for (int i = 0; i <= point; i++) {
                check[target[i]] = 1;
            }
            for (int i = 0; i <= point; i++) {
                crossover[i] = target[i];
                crossover[optTaskCountSum + i] = target[optTaskCountSum + i];
            }
            int j = point + 1;
            for (int i = 0; i < optTaskCountSum && j < optTaskCountSum; i++) {
                if (check[targetMate[i]] == 0) {
                    crossover[j] = targetMate[i];
                    crossover[optTaskCountSum + j] = targetMate[optTaskCountSum + i];
                    j++;
                }
            }
        } else {                         // 保留后半段
            for (int i = point; i < optTaskCountSum; i++) {
                check[target[i]] = 1;
            }
            for (int i = point; i < optTaskCountSum; i++) {
                crossover[i] = target[i];
                crossover[optTaskCountSum + i] = target[optTaskCountSum + i];
            }
            int j = 0;
            for (int i = 0; i < optTaskCountSum && j < point; i++) {
                if (check[targetMate[i]] == 0) {
                    crossover[j] = targetMate[i];
                    crossover[optTaskCountSum + j] = targetMate[optTaskCountSum + i];
                    j++;
                }
            }
        }
        return crossover;
    }

    public OptResult testWeb(List<String> procUidList) {
        List<Task> taskList = pmsTaskService.getTaskListByPmsTasksAndTaskLinksAndtaskGroups(optimize(procUidList), pmsTaskLinkService.selectByProcUidList(procUidList), pmsTaskGroupService.selectByProcUidList(procUidList), pmsGroupService.selectByProcUidList(procUidList));
        // 真紧前任务，为前端甘特图用
        for (Task task : taskList) {
            List<OptTaskNode> realPreTasks = optTaskNodeMap.get(task.getPmsTask().getTaskUid()).getPreTasks();
            List<PmsTask> pmsTasks = new ArrayList<>();
            for (OptTaskNode optTaskNode : realPreTasks) {
                pmsTasks.add(optTaskNode.getPmsTask());
            }
            task.setTaskRealPreTasks(pmsTasks);
        }

        List<List<ResOcpyNode>> resOcpyNodesList = new LinkedList<>();
        for (Map.Entry<String, ResOcpyNode> entry : resOcpyNodeMap.entrySet()) {
            List<ResOcpyNode> resOcpyNodes = new LinkedList<>();
            ResOcpyNode resOcpyNode = entry.getValue().sucOcpy;
            while (resOcpyNode != null && resOcpyNode.getPmsTaskResReq() == null) {     // 过滤掉非优化项目的占用节点
                resOcpyNode = resOcpyNode.sucOcpy;
            }
            if (resOcpyNode == null) {
                continue;
            }
            String resUid = entry.getKey();
            String resName = null;
            int resType = resOcpyNode.getResType();
            if (resType == 0) {
                resName = pmsHumanService.selectByUid(resUid).getHumName();
            }
            if (resType == 1) {
                resName = pmsEquipmentService.selectByUid(resUid).getEquipName();
            }
            if (resType == 2) {
                resName = pmsPlaceService.selectByUid(resUid).getPlaceName();
            }
            if (resType == 3) {
                resName = pmsKnowledgeService.selectByUid(resUid).getKnowlName();
            }

            while (resOcpyNode != null) {
                ResOcpyNode newNode = new ResOcpyNode(resOcpyNode);
                if (newNode.getPmsTaskResReq() == null) {
                    resOcpyNode = resOcpyNode.sucOcpy;
                    continue;
                }
                newNode.setProjName(pmsProjectMap.get(newNode.getPmsTaskResReq().getResReqProjUid()).getProjName());
                newNode.setTaskName(optTaskNodeMap.get(newNode.getPmsTaskResReq().getResReqTaskUid()).getPmsTask().getTaskName());
                newNode.setResName(resName);
                newNode.setPreOcpy(null);
                newNode.setSucOcpy(null);
//                newNode.setPmsTaskResReq(null);
                resOcpyNodes.add(newNode);
                resOcpyNode = resOcpyNode.sucOcpy;
            }
            resOcpyNodesList.add(resOcpyNodes);
        }

        // 根据普通连接，正序广度优先遍历
        List<List<Task>> procChartTaskList = new LinkedList<>();
        Set<String> taskUidSet = new HashSet<>();
        Queue<OptTaskNode> queue = new LinkedList<>();
        for (int i = 0; i < startOptTaskNode.getSucTasks().size(); i++) {
            // 仿真不包括辅线任务
            if (startOptTaskNode.getSucTasks().get(i).getPmsTask().getTaskType() != 1)
                queue.add(startOptTaskNode.getSucTasks().get(i));
        }
        int size = queue.size();
        // 分层遍历
        while (!queue.isEmpty()) {
            List<Task> tasks = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                OptTaskNode optTaskNode = queue.poll();
                Task task = new Task(optTaskNode.getPmsTask());
                task.setTaskRealSucTasks(new LinkedList<>());
                tasks.add(task);
                for (OptTaskNode sucTask : optTaskNode.getSucTasks()) {
                    task.getTaskRealSucTasks().add(sucTask.getPmsTask());
                    // 紧后任务节点入队
                    if (taskUidSet.add(sucTask.getPmsTask().getTaskUid())) {
                        // 仿真不包括辅线任务
                        if(sucTask.getPmsTask().getTaskType() != 1)
                            queue.add(sucTask);
                    }
                }
            }
            procChartTaskList.add(tasks);
            size = queue.size();
        }

        OptResult optResult = new OptResult(taskList, resOcpyNodesList, procChartTaskList);
        return optResult;
    }
}
