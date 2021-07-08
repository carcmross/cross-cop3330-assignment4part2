package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class Task {
    private Boolean complete;
    private String desc;
    private String due_date;

    public Task(boolean complete, String desc, String due_date) {
        this.complete = complete;
        this.desc = desc;
        this.due_date = due_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = false;
    }


}
