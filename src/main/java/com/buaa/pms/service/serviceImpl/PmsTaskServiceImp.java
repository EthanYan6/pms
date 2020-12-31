package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.*;
import com.buaa.pms.mapper.PmsTaskMapper;
import com.buaa.pms.model.OptTaskNode;
import com.buaa.pms.model.Task;
import com.buaa.pms.service.*;
import com.buaa.pms.service.PmsGroupService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PmsTaskServiceImp implements PmsTaskService {
    @Resource
    PmsTaskMapper  pmsTaskMapper;

    @Resource
    PmsTaskLinkService pmsTaskLinkService;

    @Resource
    PmsGroupService pmsGroupService;

    @Resource
    PmsTaskGroupService pmsTaskGroupService;

    @Resource
    PmsTaskResPlanService pmsTaskResPlanService;

    @Resource
    PmsTaskResReqService pmsTaskResReqService;

    @Override
    public List<PmsTask> selectAll() {
        return pmsTaskMapper.selectAll();
    }

    @Override
    public List<PmsTask> selectByProjUid(String taskProjUid) {
        return pmsTaskMapper.selectByProjUid(taskProjUid);
    }

    @Override
    public List<PmsTask> selectPublishedByProjUid(String taskProjUid) {
        List<PmsTask> tasksPublished = new ArrayList<>();
        List<PmsTask> pmsTasks = pmsTaskMapper.selectByProjUid(taskProjUid);
        for (PmsTask task : pmsTasks) {
            if (task.getTaskState() != 0)
                tasksPublished.add(task);
        }
        return tasksPublished;
    }

    @Override
    public List<Task> getTaskListByProjUid(String taskProjUid) {
        List<PmsTask> pmsTasks = this.selectByProjUid(taskProjUid);
        List<PmsTaskLink> pmsTaskLinks = pmsTaskLinkService.selectByProjUid(taskProjUid);
        List<PmsTaskGroup> pmsTaskGroups = pmsTaskGroupService.selectByProjUid(taskProjUid);
        List<PmsGroup> pmsGroups = pmsGroupService.selectByProjUid(taskProjUid);
        return this.getTaskListByPmsTasksAndTaskLinksAndtaskGroups(pmsTasks, pmsTaskLinks, pmsTaskGroups, pmsGroups);
    }

    @Override
    public List<PmsTask> selectByProcUidList(List<String> taskProcUidList) {
        if (taskProcUidList != null && !taskProcUidList.isEmpty())
            return pmsTaskMapper.selectByProcUidList(taskProcUidList);
        return null;
    }

    @Override
    public List<PmsTask> selectByProcUid(String taskProcUid) {
        return pmsTaskMapper.selectByProcUid(taskProcUid);
    }

    @Override
    public List<Task> getTaskListByProcUid(String taskProcUid) {
        List<PmsTask> pmsTasks = this.selectByProcUid(taskProcUid);
        List<PmsTaskLink> pmsTaskLinks = pmsTaskLinkService.selectByProcUid(taskProcUid);
        List<PmsTaskGroup> pmsTaskGroups = pmsTaskGroupService.selectByProcUid(taskProcUid);
        List<PmsGroup> pmsGroups = pmsGroupService.selectByProcUid(taskProcUid);
        return this.getTaskListByPmsTasksAndTaskLinksAndtaskGroups(pmsTasks, pmsTaskLinks, pmsTaskGroups, pmsGroups);
    }

    @Override
    public List<List<Task>> getProcChartTaskListByProcUid(String taskProcUid) {
        List<Task> taskList = this.getTaskListByProcUid(taskProcUid);
        Map<String, Task> taskMap = new HashMap<>(taskList.size() << 1);
        // 根据普通连接，正序广度优先遍历
        List<List<Task>> procChartTaskList = new LinkedList<>();
        Set<String> taskUidSet = new HashSet<>();
        Queue<Task> queue = new LinkedList<>();
        for (Task task : taskList) {
            taskMap.put(task.getPmsTask().getTaskUid(), task);
            // 无紧前任务的任务入队
            if (task.getTaskNormalPreTasks().isEmpty())
                queue.add(task);
        }
        int size = queue.size();
        // 分层遍历
        while (!queue.isEmpty()) {
            List<Task> tasks = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Task task = queue.poll();
                task.setTaskRealSucTasks(new LinkedList<PmsTask>());
                tasks.add(task);
                for (PmsTask sucPmsTask : task.getTaskNormalSucTasks()) {
                    task.getTaskRealSucTasks().add(sucPmsTask);
                    // 紧后任务节点入队
                    if (taskUidSet.add(sucPmsTask.getTaskUid())) {
                        queue.add(taskMap.get(sucPmsTask.getTaskUid()));
                    }
                }
            }
            procChartTaskList.add(tasks);
            size = queue.size();
        }
        return procChartTaskList;
    }

    @Override
    public List<PmsTask> getPmsTaskListByGroupUid(String groupUid) {
        List<String> pmsTaskUidList = new ArrayList<>();
        for(PmsTaskGroup pmsTaskGroup : pmsTaskGroupService.selectByGroupUid(groupUid)) {
            pmsTaskUidList.add(pmsTaskGroup.getTaskGroupTaskUid());
        }
        return this.selectByUidList(pmsTaskUidList);
    }

    @Override
    public List<PmsTask> selectByParUid(String taskParUid) {
        return pmsTaskMapper.selectByParUid(taskParUid);
    }

    @Override
    public List<PmsTask> selectByUidList(List taskUidList) {
        if (taskUidList != null && !taskUidList.isEmpty())
            return pmsTaskMapper.selectByUidList(taskUidList);
        return new ArrayList<>();
    }

    @Override
    public PmsTask selectByUid(String taskUid) {
        return pmsTaskMapper.selectByUid(taskUid);
    }

    @Override
    public void save(PmsTask pmsTask) {
        // 分配UUID
        pmsTask.setTaskUid(new MyUUID().getUUID());
        // 新增任务默认状态 0-“编制中”
        if (pmsTask.getTaskState() == null)
            pmsTask.setTaskState(0);
        pmsTaskMapper.save(pmsTask);
    }

    @Override
    public void deleteByUid(String taskUid) {
        // 删除任务相关连接
        pmsTaskLinkService.deleteByTaskUid(taskUid);
        // 删除任务资源方案和资源需求
        pmsTaskResPlanService.deleteByTaskUid(taskUid);
        pmsTaskResReqService.deleteByTaskUid(taskUid);
        // 删除任务
        pmsTaskMapper.deleteByUid(taskUid);
    }

    @Override
    public void deleteByProjUid(String projUid) {
        // 删除任务相关连接
        pmsTaskLinkService.deleteByProjUid(projUid);
        // 删除任务资源方案和资源需求
        pmsTaskResPlanService.deleteByProjUid(projUid);
        pmsTaskResReqService.deleteByProjUid(projUid);
        // 删除任务
        pmsTaskMapper.deleteByProjUid(projUid);
    }

    @Override
    public void deleteByProcUid(String procUid) {
        // 删除任务相关连接
        pmsTaskLinkService.deleteByProcUid(procUid);
        // 删除资源方案和资源需求
        pmsTaskResPlanService.deleteByProcUid(procUid);
        pmsTaskResReqService.deleteByProcUid(procUid);
        // 删除任务
        pmsTaskMapper.deleteByProcUid(procUid);
    }

    @Override
    public void deleteByParUid(String parUid) {
        pmsTaskMapper.deleteByParUid(parUid);
    }

    @Override
    public void update(PmsTask pmsTask) {
        // 任务状态若为空，则保持以前的状态
        if (pmsTask.getTaskState() == null) {
            int state = pmsTaskMapper.selectByUid(pmsTask.getTaskUid()).getTaskState();
            pmsTask.setTaskState(state);
        }
        pmsTaskMapper.update(pmsTask);
    }

    @Override
    public void updatePriorityByProj(PmsProject pmsProject) {
        pmsTaskMapper.updatePriorityByProj(pmsProject);
    }

    @Override
    public void updatePmsTaskIds(List<PmsTask> pmsTasks) {
        if (pmsTasks != null && !pmsTasks.isEmpty())
            pmsTaskMapper.updatePmsTaskIds(pmsTasks);
    }

    @Override
    public void updatePmsTasks(List<PmsTask> pmsTasks) {
        if (pmsTasks != null && !pmsTasks.isEmpty())
            pmsTaskMapper.updatePmsTasks(pmsTasks);
    }

    @Override
    public void saveOrUpdate(PmsTask pmsTask) {
        if (pmsTask.getTaskUid() == null || pmsTask.getTaskUid().equals("")) {
            this.save(pmsTask);
        } else {
            this.update(pmsTask);
        }
    }

    @Override
    public void saveOrUpdateTask(Task task) {
        // 保存任务
        PmsTask pmsTask = task.getPmsTask();
        this.saveOrUpdate(pmsTask);
        // 保存任务连接
        pmsTaskLinkService.deleteBySucTaskUid(task.getPmsTask().getTaskUid());
        List<PmsTaskLink> pmsTaskLinks = new ArrayList<>();
        MyUUID myUUID = new MyUUID();
        for (PmsTask normalPreTask : task.getTaskNormalPreTasks()) {
            PmsTaskLink normalTaskLink = new PmsTaskLink();
            normalTaskLink.setTaskLinkUid(myUUID.getUUID());
            normalTaskLink.setTaskLinkType(0);
            normalTaskLink.setTaskLinkPreTaskUid(normalPreTask.getTaskUid());
            normalTaskLink.setTaskLinkSucTaskUid(pmsTask.getTaskUid());
            normalTaskLink.setTaskLinkProcUid(pmsTask.getTaskProcUid());
            normalTaskLink.setTaskLinkProjUid(pmsTask.getTaskProjUid());
            pmsTaskLinks.add(normalTaskLink);
        }
        if (!pmsTaskLinks.isEmpty())
            pmsTaskLinkService.saveTaskLinks(pmsTaskLinks);
    }

    @Override
    public List<Task> getTaskListByPmsTasksAndTaskLinksAndtaskGroups(List<PmsTask> pmsTasks, List<PmsTaskLink> pmsTaskLinks, List<PmsTaskGroup> pmsTaskGroups, List<PmsGroup> pmsGroups) {
        List<Task> taskList = new ArrayList<>();
        Map<String, PmsTask> pmsTaskMap = new HashMap<>();
        Map<String, List<PmsTask>> pmsTaskNormalPreMap = new HashMap<>();
        Map<String, List<PmsTask>> pmsTaskNormalSucMap = new HashMap<>();
        Map<String, String> taskUidAndGroupUidMap = new HashMap<>();
        Map<String, PmsGroup> pmsGroupMap = new HashMap<>();
        for (PmsTask pmsTask : pmsTasks) {
            pmsTaskMap.put(pmsTask.getTaskUid(), pmsTask);
        }
        for (PmsTaskLink pmsTaskLink : pmsTaskLinks) {
            if (!pmsTaskNormalPreMap.containsKey(pmsTaskLink.getTaskLinkSucTaskUid())) {
                List<PmsTask> normalPreTasks = new ArrayList<>();
                normalPreTasks.add(pmsTaskMap.get(pmsTaskLink.getTaskLinkPreTaskUid()));
                pmsTaskNormalPreMap.put(pmsTaskLink.getTaskLinkSucTaskUid(), normalPreTasks);
            } else {
                pmsTaskNormalPreMap.get(pmsTaskLink.getTaskLinkSucTaskUid()).add(pmsTaskMap.get(pmsTaskLink.getTaskLinkPreTaskUid()));
            }
            if (!pmsTaskNormalSucMap.containsKey(pmsTaskLink.getTaskLinkPreTaskUid())) {
                List<PmsTask> normalSucTasks = new ArrayList<>();
                normalSucTasks.add(pmsTaskMap.get(pmsTaskLink.getTaskLinkSucTaskUid()));
                pmsTaskNormalSucMap.put(pmsTaskLink.getTaskLinkPreTaskUid(), normalSucTasks);
            } else {
                pmsTaskNormalSucMap.get(pmsTaskLink.getTaskLinkPreTaskUid()).add(pmsTaskMap.get(pmsTaskLink.getTaskLinkSucTaskUid()));
            }
        }
        for (PmsTaskGroup taskGroup : pmsTaskGroups) {
            taskUidAndGroupUidMap.put(taskGroup.getTaskGroupTaskUid(), taskGroup.getTaskGroupGroupUid());
        }
        for (PmsGroup group : pmsGroups) {
            pmsGroupMap.put(group.getGroupUid(), group);
        }
        for (PmsTask pmsTask : pmsTasks) {
            List<PmsTask> normalPreTasks = pmsTaskNormalPreMap.get(pmsTask.getTaskUid());
            List<PmsTask> normalSucTasks = pmsTaskNormalSucMap.get(pmsTask.getTaskUid());
            PmsGroup pmsGroup = pmsGroupMap.get(taskUidAndGroupUidMap.get(pmsTask.getTaskUid()));
            Task task = new Task(pmsTask, normalPreTasks == null ? new ArrayList<>() : normalPreTasks, normalSucTasks == null ? new ArrayList<>() : normalSucTasks, pmsGroup);
            taskList.add(task);
        }
        return taskList;
    }
}
