package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListModel {

    private ArrayList<ToDoList> toDoLists;

    public void addNewList(String title, ObservableList ToDoLists) {
        // Add list to ToDoLists
        // Make the value of title the title property of the new list element
    }

    public void removeList(String listTitle, ObservableList toDoLists) {
        // Traverse ToDoLists until a list matching the listTitle is found
        // Remove the list at the index at which a match is found
    }

    public void addNewTask(String title, String desc, String due_date, ObservableList toDoLists, int index) {
        // Create new task in the currently displayed list
        // Assign the new task the values that were inputted in the text fields
    }

    public void changeView(String viewOption, ObservableList toDoLists, TableView taskView, int index) {
        // If viewOption == "View All"
        // - clear taskView and copy every task from the indexed toDoList to taskView
        // If viewOption == "View Completed"
        // - clear taskView and copy every task with a "true" completed variable from the indexed toDoList to taskView
        // If viewOption == "View Incomplete"
        // - clear taskView and copy every task with a "false" completed variable from the indexed toDoList to taskView
    }

    public void editListTitle(String oldTitle, String newTitle, ObservableList toDoLists) {
        // Traverse toDoLists until title matches oldTitle
        // Assign the list the value of newTitle
    }

    public void editTask(String oldTitle, String newTitle, String newDesc, String newDueDate, ObservableList toDoLists,
                            int index) {
        // Traverse list at index of toDoLists until oldTitle is found
        // Change values of task at index of oldTitle to newTitle, newDesc, and newDueDate parameters
    }

    public void deleteTask(String taskTitle, ObservableList ToDoLists, int index) {
        // Traverse array of the currently selected list's tasks until taskTitle matches
        // Remove task from the to-do list's tasks
    }

    public void viewCurrentList(ObservableList toDoLists, TableView taskView, int index, String viewOption) {
        // Check if taskView is empty
        // - If empty, clear taskView
        // copy the contents of the indexed list to taskView
    }

    public void loadMultipleLists(String fileName, String loadedInput) {
        // Read the file given in fileName
        // Assign the read input to loadedInput
    }

    public void loadList(String fileName, String loadedInput) {
        // Read the file given in fileName
        // Assign the read input to loadedInput
    }

    public void saveList(String fileName) {
        // Save the currently selected list to a local external directory as a .txt file
        // Create a table using string formatting to organize the components of each list
    }

    public void saveMultiple(String fileName, ObservableList temp_list) {
        // Save the currently selected lists in temp_list to a local external directory as a .txt file
        // Use the titles of each list along with a colon as a header to separate individual lists
        // Create a table to organize the components of each list
    }
}
