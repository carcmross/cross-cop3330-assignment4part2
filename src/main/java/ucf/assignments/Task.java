package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class Task {
    private String desc;
    private String due_date;

    @Override
    public String toString() {
        return "Task{" +
                "desc='" + desc + '\'' +
                ", due_date='" + due_date + '\'' +
                ", complete='" + complete + '\'' +
                '}';
    }

    private String complete;

    public Task(String complete, String desc, String due_date) {
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

    public String getDueDate() {
        return due_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }
}
