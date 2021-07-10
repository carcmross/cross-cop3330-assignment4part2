package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListModel {

    public void addNewTask(String desc, String due_date, ObservableList toDoList) {
        // Create new task in the currently displayed list
        // Assign the new task the values that were inputted in the text fields
        toDoList.add(new Task(new CheckBox(), desc, due_date));
    }

    public void changeView(String viewOption, ObservableList toDoList, TableView taskView) {
        int size = toDoList.size();

        // If viewOption == "View All"
        if (viewOption.equals("View All")) {
            // clear taskView and copy every task from the indexed toDoList to taskView
            if (taskView.getItems().size() != 0)
                taskView.getItems().clear();

            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                taskView.getItems().add(cur_task);
            }
            return;
        }
        // If viewOption == "View Completed"
        else if (viewOption.equals("View Completed")) {
            // clear taskView and copy every task with a "true" completed variable from the indexed toDoList to taskView
            if (taskView.getItems().size() != 0)
                taskView.getItems().clear();

            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                if (cur_task.getComplete().isSelected() == true)
                    taskView.getItems().add(cur_task);
            }
            return;
        }
        // If viewOption == "View Incomplete"
        else if (viewOption.equals("View Incomplete")) {
            // clear taskView and copy every task with a "false" completed variable from the indexed toDoList to taskView
            if (taskView.getItems().size() != 0)
                taskView.getItems().clear();

            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                if (cur_task.getComplete().isSelected() == false)
                    taskView.getItems().add(cur_task);
            }
            return;
        }
    }

    public void editTask(String oldDesc, String newDesc, String newDueDate, ObservableList toDoList) {
        // Traverse list at index of toDoLists until oldTitle is found
        for (int i = 0; i < toDoList.size(); i++) {
            Task cur_task = (Task) toDoList.get(i);
            if (cur_task.getDesc().equals(oldDesc)) {
                // Change values of task at index of oldTitle to newTitle, newDesc, and newDueDate parameters
                cur_task.setDesc(newDesc);
                cur_task.setDueDate(newDueDate);
            }
        }
    }

    public void deleteTask(String desc, ObservableList toDoList) {
        // Traverse array of the currently selected list's tasks until taskTitle matches
        int size = toDoList.size();
        for (int i = 0; i < size; i++) {
            Task cur_task = (Task) toDoList.get(i);
            if (cur_task.getDesc().equals(desc)) {
                // Remove task from the to-do list's tasks
                toDoList.remove(i);
                return;
            }
        }
        return;
    }

    public ObservableList loadList(File file) {
        // Read the file given in fileName
        try {
            ObservableList<Task> taskList = FXCollections.observableArrayList();
            Task loadedTask = new Task(new CheckBox(), "", "");
            String loadedDesc = "";
            String loadedDueDate = "";
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                String tempInput = in.nextLine();
                // Assign the read input to tempInput
                if (tempInput.startsWith("Description")) {
                    tempInput = tempInput.replace("Description: ", "");
                    loadedDesc = tempInput;
                }
                else if (tempInput.startsWith("Due Date")) {
                    tempInput = tempInput.replace("Due Date: ", "");
                    loadedDueDate = tempInput;
                }
                else if (tempInput.startsWith("Completed")) {
                    tempInput = tempInput.replace("Completed: ", "");
                    System.out.println(tempInput);
                    if (tempInput.equals("Yes")) {
                        taskList.add(new Task(new CheckBox(), loadedDesc, loadedDueDate));
                        taskList.get(taskList.size() - 1).getComplete().setSelected(true);
                    }
                    else {
                        taskList.add(new Task(new CheckBox(), loadedDesc, loadedDueDate));
                        taskList.get(taskList.size() - 1).getComplete().setSelected(false);
                    }
                }
                else if (tempInput.isEmpty() == false) {
                    loadedDesc += tempInput;
                    loadedDesc = wrapIfLong(loadedDesc);
                }
                else
                    continue;
            }
            return taskList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateSaveOutput(ObservableList toDoList) {
        // Generate input by formatting Task properties
        String input = "";
        for (int i = 0; i < toDoList.size(); i++) {
            Task cur_task = (Task) toDoList.get(i);
            input += String.format("Description: %s\n", cur_task.getDesc());
            input += String.format("Due Date: %s\n", cur_task.getDueDate());
            if (cur_task.getComplete().isSelected())
                input += "Completed: Yes\n\n";
            else
                input += "Completed: No\n\n";
        }
        // Return input
        return input;
    }

    public void writeToFile(File file, String input) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(input);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String wrapIfLong(String description) {
        if (description.length() >= 155) {
            if (description.charAt(154) != ' ' || description.charAt(156) == ' ') {
                String wrappedStr = description.replaceAll(".{155}", "$0\n");
                return wrappedStr;
            }
            String wrappedStr = description.replaceAll(".{154}", "$0-\n");
            return wrappedStr;
        }

        return description;
    }

    public void clearList(ObservableList toDoList) {
        toDoList.clear();
    }
}
