package com.buaa.pms.model;

import java.sql.Timestamp;

public class ChartAllocateResourceDeal {
    String projName;
    String taskName;
    Timestamp startDateTime;
    Timestamp finishDateTime;

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Timestamp finishDateTime) {
        this.finishDateTime = finishDateTime;
    }
}
