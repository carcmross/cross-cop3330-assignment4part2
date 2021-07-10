package ucf.assignments;

import javafx.scene.control.CheckBox;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class Task {
    private String desc;
    private String due_date;
    private CheckBox complete;

    public Task(CheckBox complete, String desc, String due_date) {
        this.complete = new CheckBox();
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

    public CheckBox getComplete() {
        return complete;
    }

    public void setComplete(CheckBox complete) {
        this.complete = complete;
    }
}
