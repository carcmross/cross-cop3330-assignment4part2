package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListsManagerController implements Initializable {

    private ToDoListModel model;

    private String displayedList;

    private String displayMode;

    @FXML
    private TextField newTaskTitle;

    @FXML
    private TextField newTaskDesc;

    @FXML
    private TextField newDueDate;

    @FXML
    private TextField newListTitle;

    @FXML
    private ListView<String> toDoListsView;

    @FXML
    private TableView<Task> taskView;

    @FXML
    private ChoiceBox<String> viewOptions;

    @FXML
    private ChoiceBox<String> selectedList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewOptions.setValue("View All");
        viewOptions.getItems().addAll("View All", "View Completed", "View Incomplete");
        displayMode = "View All";
    }

    @FXML
    public void editListButtonClicked(ActionEvent actionEvent) {
        // Get currently selected element from the selectedList choice box
        // Store old title in a oldTitle variable
        // Generate pop-up window asking for new title of the list
        // Get value of the text field containing the new title and store in newTitle variable
        // Make sure the value of the text field doesn't match the title of any other existing lists
        // Close pop-up window after "Confirm" button is clicked
        // Generate pop-up window warning that new list title is too long if it exceeds 25 characters.
        // - If length is okay, call model.editListTitle("List current title", "List new title", "ToDoLists list here");
        // Traverse ListView until oldTitle matches with title of list
        // - Change name property of the matching list to newTitle
        // Traverse ToDoLists until oldTitle matches with title of list
        // - Change name property of the matching list to newTitle
        // Change the name of the list in the selectedList choice box options
        // - If the list being displayed was the one that was edited, edit the name of the displayedList variable
    }

    @FXML
    public void removeListButtonClicked(ActionEvent actionEvent) {
        // Get currently selected element from the selectedList choice box
        // Pass on the name of the currently selected element to the removeList function
        // - model.removeList("list title here", "ToDoLists arraylist here");
        // Set selectedList choice box to the value of displayedList
        // - If value of displayedList matches the list that was removed, set choice box text to blank string
        // - Also set displayedList to NULL and clear taskView table
        // Remove list from ListView array
    }

    @FXML
    public void removeTaskButtonClicked(ActionEvent actionEvent) {
        // Get currently selected task with selectionModel
        // Traverse ToDoLists array until name of displayedList is found
        // - Store index in index variable
        // - model.deleteTask("Task title here", "ToDoLists observablelist here", "index here");
        // Remove the task from the taskView table
    }

    @FXML
    public void editTaskButtonClicked(ActionEvent actionEvent) {
        // Generate pop-up window with current values of the currently selected task
        // Allow user to edit the text fields they want to change
        // Make sure no task in that list has the same title
        // Close pop-up window after "Confirm" button is clicked
        // Generate pop-up warning if new title exceeds 25 characters or new description exceeds 50 characters
        // Traverse the ToDoList until the title matches displayedList
        // - Pass the index to the editTask function
        // - model.editTask("Task title here", "Task description here", "Task due date here", "index here");
    }

    @FXML
    public void newListButtonClicked(ActionEvent actionEvent) {
        // Get value of newListTitle text field
        // Check to see if text field is blank
        // - if blank, create pop-up window that tells user to type a title into the text field then break
        // - If there are any lists with the same title, create pop-up window warning user then break from function
        // - If title of the list is longer than 25 characters, generate pop-up warning
        // - model.addNewList("list title here", "ToDoList observablelist here");
        // Add list to selectedList choice box
        // Clear newListTitle text field
    }

    @FXML
    public void saveSingleButtonClicked(ActionEvent actionEvent) {
        // Get the value of the selectedList choice box
        // Traverse the ToDoList array until the title matches the value of selectedList
        // - Store the index of the matching list
        // Generate pop-up window asking what the name of the .txt file should be
        // model.saveList("file name here", "ToDoList here", );
    }

    @FXML
    public void loadSingleButtonClicked(ActionEvent actionEvent) {
        // Open up a window asking for the directory of the file where the list was saved
        // model.loadList("fileName", "");
        // Create new ObservableList<ToDoList>
        // Add new element to ObservableList<ToDoList> with components of the file that was read
        // Clear taskView and toDoListsView
        // Add ObservableList<ToDoList> to toDoListsView
        // Add ObservableList<Task> to taskView
    }

    @FXML
    public void saveMultipleButtonClicked(ActionEvent actionEvent) {
        // Generate pop-up window allowing user to select which lists they'd like to store with check boxes
        // - In the same window ask for what the name of the .txt file should be
        // After confirm button is clicked:
        // - Store each list with a check next to it in a temporary list
        // - Close window after confirm button is clicked
        // - model.saveMultiple("file name here", "temporary list here");
    }

    @FXML
    public void loadMultipleButtonClicked(ActionEvent actionEvent) {
        // Open up a window asking for the directory of the file where the list was saved
        // model.loadMultiple("fileName", "");
        // Create new ObservableList<ToDoList>
        // Add new element to ObservableList<ToDoList> with components of the file that was read
        // Clear taskView and toDoListsView
        // Add ObservableList<ToDoList> to toDoListsView
        // Add ObservableList<Task> to taskView
    }

    @FXML
    public void addTaskButtonClicked(ActionEvent actionEvent) {
        // Get value of newTaskTitle, newTaskDesc, and newDueDate text fields
        // Make sure that none of the fields are blank
        // - if any fields are blank, create pop-up window telling user to type into those fields
        // - Also ensure that due date is in the format YYYY-MM-DD before continuing
        // - If newTaskTitle exceeds 25 characters or newTaskDesc exceeds 50 characters, generate pop-up warning
        // Traverse ToDoLists array to until list name of list matches displayedList
        // - Store the index of the list in index variable once found
        // Pass in ToDoLists array to the add function so the task can be added to the indexed list
        // model.addNewTask("newTaskTitle here", "newTaskDesc here", "newDueDate here", "ToDoLists array here",
        //                      "index here");
        // Add task to taskView
        // Clear newTaskTitle, newTaskDesc, and newDueDate text fields
    }

    @FXML
    public void viewButtonClicked(ActionEvent actionEvent) {
        // Get the value of the viewOptions choiceBox
        // Traverse the ToDoLists array until the title matches displayedList
        // Pass the index of the matching list onto the changeView function
        // - model.changeView("viewOption here", "index here");
        // Change the value of displayMode to the value of the viewOptions choicebox
    }

    @FXML
    public void viewListButtonClicked(ActionEvent actionEvent) {
        // Get currently selected element from selectedList choice box
        // Traverse ToDoLists arraylist until the title matches the value of the selectedList choice box
        // - Pass the value of the index to the viewCurrentList function
        // - {ass the value of displayMode as the viewOption parameter
        // model.viewCurrentList("ToDoLists arraylist here", "taskView here", "index here", "viewOption here");
        // Set displayedList to the the value of the selectedList choice box
    }

}
