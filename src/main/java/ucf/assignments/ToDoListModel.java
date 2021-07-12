package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.Scanner;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListModel {

    public void addNewTask(String desc, String due_date, ObservableList toDoList) {
        // Create new task in the currently displayed list
        // Assign the new task the values that were inputted in the text fields
        toDoList.add(new Task("No", desc, due_date));
    }

    public ObservableList changeView(String viewOption, ObservableList toDoList) {
        int size = toDoList.size();
        ObservableList<Task> tempList = FXCollections.observableArrayList();

        // If viewOption == "View All"
        if (viewOption.equals("View All")) {
            // Add every value to tempList
            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                tempList.add(cur_task);
            }
            return tempList;
        }
        // If viewOption == "View Completed"
        else if (viewOption.equals("View Completed")) {
            // Add every task with a "true" completed variable from the indexed toDoList to tempList
            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                if (cur_task.getComplete().equals("Yes"))
                    tempList.add(cur_task);
            }
            return tempList;
        }
        // If viewOption == "View Incomplete"
        else if (viewOption.equals("View Incomplete")) {
            // Add every task with a "false" completed variable from the indexed toDoList to tempList
            for (int i = 0; i < size; i++) {
                Task cur_task = (Task) toDoList.get(i);
                if (cur_task.getComplete().equals("No"))
                    tempList.add(cur_task);
            }
            return tempList;
        }
        return null;
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
            Task loadedTask = new Task("No", "", "");
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
                    if (tempInput.equals("Yes")) {
                        taskList.add(new Task("Yes", loadedDesc, loadedDueDate));
                    }
                    else {
                        taskList.add(new Task("No", loadedDesc, loadedDueDate));
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
            if (cur_task.getComplete().equals("Yes"))
                input += "Completed: Yes\n\n";
            else
                input += "Completed: No\n\n";
        }
        // Return input
        return input;
    }

    public boolean writeToFile(File file, String input) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(input);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String wrapIfLong(String description) {
        // If description length is greater than 155 characters, wrap string to fit description column
        if (description.length() >= 149) {
            if (description.charAt(148) == ' ' || description.charAt(150) == ' ') {
                String wrappedStr = description.replaceAll(".{149}", "$0\n");
                return wrappedStr;
            }

            String wrappedStr = description.replaceAll(".{148}", "$0-\n");
            return wrappedStr;
        }

        return description;
    }

    public void clearList(ObservableList toDoList) {
        toDoList.clear();
    }
}
