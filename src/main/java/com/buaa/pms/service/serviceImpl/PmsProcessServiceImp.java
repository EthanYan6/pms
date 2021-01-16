package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.*;
import com.buaa.pms.mapper.PmsProcessMapper;
import com.buaa.pms.model.Process;
import com.buaa.pms.service.*;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsProcessServiceImp implements PmsProcessService {
    @Resource
    PmsProcessMapper  pmsProcessMapper;

    @Resource
    PmsProjectService pmsProjectService;

    @Resource
    PmsTaskService pmsTaskService;

    @Resource
    PmsTaskLinkService pmsTaskLinkService;

    @Resource
    PmsTaskResPlanService pmsTaskResPlanService;

    @Resource
    PmsTaskResReqService pmsTaskResReqService;

    @Override
    public List<PmsProcess> selectAll() {
        return pmsProcessMapper.selectAll();
    }

    @Override
    public List<Process> getProcList() {
        List<Process> procList = new ArrayList<>();
        for (PmsProcess pmsProcess : this.selectAll()) {
            Process proc = this.getProcessFromPmsProcess(pmsProcess);
            procList.add(proc);
        }
        return procList;
    }

    @Override
    public List<PmsProcess> selectPublished() {
        return pmsProcessMapper.selectPublished();
    }

    @Override
    public List<PmsProcess> selectByProjUid(String procProjUid) {
        return pmsProcessMapper.selectByProjUid(procProjUid);
    }

    @Override
    public List<Process> getProcListByProjUid(String procProjUid) {
        List<Process> procList = new ArrayList<>();
        for (PmsProcess pmsProcess : this.selectByProjUid(procProjUid)) {
            Process proc = this.getProcessFromPmsProcess(pmsProcess);
            procList.add(proc);
        }
        return procList;
    }

    @Override
    public List<PmsProcess> selectByUidList(List<String> procUidList) {
        if (procUidList != null && !procUidList.isEmpty())
            return pmsProcessMapper.selectByUidList(procUidList);
        return null;
    }

    @Override
    public PmsProcess selectByUid(String procUid) {
        return pmsProcessMapper.selectByUid(procUid);
    }

    @Override
    public void save(PmsProcess pmsProcess) {
        // 分配UUID
        pmsProcess.setProcUid(new MyUUID().getUUID());
        // 新增流程默认状态 0-“编制中”
        if (pmsProcess.getProcState() == null)
            pmsProcess.setProcState(0);
        pmsProcessMapper.save(pmsProcess);
    }

    @Override
    public void deleteByUid(String procUid) {
        pmsProcessMapper.deleteByUid(procUid);
        // 流程删除后，其包含的任务、对应的优化项目和仿真项目也要删除
        pmsTaskService.deleteByProcUid(procUid);
    }

    @Override
    public void deleteByProjUid(String projUid) {
        pmsProcessMapper.deleteByProjUid(projUid);
    }

    @Override
    public void update(PmsProcess pmsProcess) {
        // 项目状态若为空，则保持以前的状态
        System.out.println("项目状态: " + pmsProcess.getProcState());
        if (pmsProcess.getProcState() == null) {
            int state = pmsProcessMapper.selectByUid(pmsProcess.getProcUid()).getProcState();
            pmsProcess.setProcState(state);
        }
        pmsProcessMapper.update(pmsProcess);
    }

    @Override
    public void saveOrUpdate(PmsProcess pmsProcess) {
        if (pmsProcess.getProcUid() == null || pmsProcess.getProcUid().equals("")) {
            this.save(pmsProcess);
        } else {
            this.update(pmsProcess);
        }
    }

    // 发布流程及对应项目
    @Override
    public String publishProc(PmsProcess pmsProcess) {
        String SUCCESS = "success", FALSE = "false";
        PmsProject pmsProject = pmsProjectService.selectByUid(pmsProcess.getProcProjUid()); // 流程对应的项目
        if (pmsProject != null) {
            if (pmsProject.getProjState() != 0) {
                return FALSE;
            }
            PmsTask pmsTask = pmsTaskService.selectByUid(pmsProject.getProjTaskUid());  // 项目对应的任务
            if (pmsTask == null || pmsTask.getTaskType() != 5) {    // 如果项目对应的不是黑箱任务
                // 将流程及其对应的项目状态设为1-已发布
                pmsProcess.setProcState(1);
                this.saveOrUpdate(pmsProcess);
                pmsProject.setProjState(1);
                pmsProject.setProjPlanStartDateTime(pmsProcess.getProcPlanStartDateTime());
                pmsProject.setProjPlanFinishDateTime(pmsProcess.getProcPlanFinishDateTime());
                pmsProjectService.saveOrUpdate(pmsProject);
                // 将流程中的任务状态设为1-已发布
                List<PmsTask> tasks = pmsTaskService.selectByProcUid(pmsProcess.getProcUid());
                for (PmsTask task : tasks) {
                    task.setTaskState(1);
                }
                pmsTaskService.updatePmsTasks(tasks);
                return SUCCESS;
            }
            if (pmsTask.getTaskType() == 5) {   // 如果项目对应的是黑箱任务
                List<PmsTask>  pmsTaskList = pmsTaskService.selectByProcUid(pmsProcess.getProcUid());   // 黑箱任务对应流程中的任务
                if (pmsTaskList == null || pmsTaskList.isEmpty()) {
                    return FALSE;
                }
                int n = pmsTaskList.size();
                int index = pmsTask.getTaskId();
                // 修改黑箱任务所属流程中任务的编号
                PmsProcess upProcess = this.selectByUid(pmsTask.getTaskProcUid());  // 黑箱任务所属流程
                if (upProcess == null) {
                    return FALSE;
                }
                List<PmsTask> broTasks = pmsTaskService.selectByProcUid(pmsTask.getTaskProcUid());
                for (PmsTask broTask : broTasks) {
                    if (broTask.getTaskId() > index) {
                        broTask.setTaskId(broTask.getTaskId() + n - 1);
                    }
                }
                pmsTaskService.updatePmsTaskIds(broTasks);
                // 处理黑箱中的任务
                PmsTask firstTask = pmsTaskList.get(0);
                PmsTask lastTask = pmsTaskList.get(n - 1);
                // 修改黑箱任务对应流程中任务的连接
                List<PmsTaskLink> preLinks = pmsTaskLinkService.selectBySucTaskUid(pmsTask.getTaskUid());
                for (PmsTaskLink taskLink : preLinks) {
                    taskLink.setTaskLinkSucTaskUid(firstTask.getTaskUid());
                    taskLink.setTaskLinkProcUid(pmsTask.getTaskProcUid());
                    taskLink.setTaskLinkProjUid(pmsTask.getTaskProjUid());
//                    pmsTaskLinkService.saveOrUpdate(taskLink);
                }
                pmsTaskLinkService.updateTaskLinks(preLinks);
                List<PmsTaskLink> sucLinks = pmsTaskLinkService.selectByPreTaskUid(pmsTask.getTaskUid());
                for (PmsTaskLink taskLink : sucLinks) {
                    taskLink.setTaskLinkPreTaskUid(lastTask.getTaskUid());
                    taskLink.setTaskLinkProcUid(pmsTask.getTaskProcUid());
                    taskLink.setTaskLinkProjUid(pmsTask.getTaskProjUid());
//                    pmsTaskLinkService.saveOrUpdate(taskLink);
                }
                pmsTaskLinkService.updateTaskLinks(sucLinks);
                List<PmsTaskLink> blackLinks = pmsTaskLinkService.selectByProcUid(pmsProcess.getProcUid());
                for (PmsTaskLink taskLink : blackLinks) {
                    taskLink.setTaskLinkProcUid(pmsTask.getTaskProcUid());
                    taskLink.setTaskLinkProjUid(pmsTask.getTaskProjUid());
//                    pmsTaskLinkService.saveOrUpdate(taskLink);
                }
                pmsTaskLinkService.updateTaskLinks(blackLinks);
                // 修改黑箱任务对应流程中任务的所属流程和项目
                for (PmsTask task : pmsTaskList) {
                    task.setTaskParUid(pmsTask.getTaskParUid());
                    task.setTaskProcUid(pmsTask.getTaskProcUid());
                    task.setTaskProjUid(pmsTask.getTaskProjUid());
                    task.setTaskId(task.getTaskId() + index - 1);
                    task.setTaskState(1);
//                    pmsTaskService.saveOrUpdate(task);
                }
                pmsTaskService.updatePmsTasks(pmsTaskList);
                // 修改黑箱任务对应流程中资源计划的所属流程和项目
                List<PmsTaskResPlan> taskResPlanList = pmsTaskResPlanService.selectByProcUid(pmsProcess.getProcUid());
                for (PmsTaskResPlan taskResPlan : taskResPlanList) {
                    taskResPlan.setResPlanProcUid(pmsTask.getTaskProcUid());
                    taskResPlan.setResPlanProjUid(pmsTask.getTaskProjUid());
                }
                pmsTaskResPlanService.updatePmsTaskResPlans(taskResPlanList);
                // 修改黑箱任务对应流程中资源需求的所属流程和项目
                List<PmsTaskResReq> taskResReqList = pmsTaskResReqService.selectByProcUid(pmsProcess.getProcUid());
                for (PmsTaskResReq taskResReq : taskResReqList) {
                    taskResReq.setResReqProcUid(pmsTask.getTaskProcUid());
                    taskResReq.setResReqProjUid(pmsTask.getTaskProjUid());
                }
                pmsTaskResReqService.updatePmsTaskResReqs(taskResReqList);
                // 删除黑箱任务
                pmsTaskService.deleteByUid(pmsTask.getTaskUid());
                // 删除黑箱任务对应的项目
                this.deleteByProjUid(pmsProject.getProjUid());
                pmsProjectService.deleteByUid(pmsProject.getProjUid());
                return SUCCESS;
            }
        }
        return FALSE;

    }

    private Process getProcessFromPmsProcess(PmsProcess pmsProcess) {
        Process process = new Process();
        process.setProcUid(pmsProcess.getProcUid());
        process.setProcId(pmsProcess.getProcId());
        process.setProcName(pmsProcess.getProcName());
        process.setProcProjUid(pmsProcess.getProcProjUid());
        PmsProject procProj = pmsProjectService.selectByUid(pmsProcess.getProcProjUid());
        process.setProcProjName(procProj == null ? "" : procProj.getProjName());
        process.setProcAuthor(pmsProcess.getProcAuthor());
        process.setProcDescription(pmsProcess.getProcDescription());
        process.setProcPlanStartDateTime(pmsProcess.getProcPlanStartDateTime());
        process.setProcPlanFinishDateTime(pmsProcess.getProcPlanFinishDateTime());
        process.setProcPlanDur(pmsProcess.getProcPlanDur());
        process.setProcState(pmsProcess.getProcState());

        return process;
    }
}
