package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListModel {

    public void addNewTask(String desc, String due_date, ObservableList toDoList) {
        // Create new task in the currently displayed list
        // Assign the new task the values that were inputted in the text fields
        toDoList.add(new Task(false, desc, due_date));
    }

    public void changeView(String viewOption, ObservableList toDoLists, TableView taskView, int index) {
        // If viewOption == "View All"
        // - clear taskView and copy every task from the indexed toDoList to taskView
        // If viewOption == "View Completed"
        // - clear taskView and copy every task with a "true" completed variable from the indexed toDoList to taskView
        // If viewOption == "View Incomplete"
        // - clear taskView and copy every task with a "false" completed variable from the indexed toDoList to taskView
    }

    public void editTask(String newDesc, String newDueDate, ObservableList toDoLists, int index) {
        // Traverse list at index of toDoLists until oldTitle is found
        // Change values of task at index of oldTitle to newTitle, newDesc, and newDueDate parameters
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

    public void loadList(String fileName, String loadedInput) {
        // Read the file given in fileName
        // Assign the read input to loadedInput
    }

    public void saveList(String fileName) {
        // Save the currently selected list to a local external directory as a .txt file
        // Create a table using string formatting to organize the components of each list
    }

    public String wrapIfLong(String description) {
        if (description.length() >= 135) {
            if (description.charAt(134) != ' ' || description.charAt(136) == ' ') {
                String wrappedStr = description.replaceAll(".{135}", "$0\n");
                return wrappedStr;
            }

            String wrappedStr = description.replaceAll(".{134}", "$0-\n");
            return wrappedStr;
        }

        return description;
    }

    public void clearList(ObservableList toDoList) {
        toDoList.clear();
    }
}
