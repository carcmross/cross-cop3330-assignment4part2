package ucf.assignments;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListsManagerController implements Initializable {

    private ToDoListModel model = new ToDoListModel();

    private ObservableList<Task> toDoList = FXCollections.observableArrayList();

    private String displayMode;

    @FXML
    private TextField newTaskDesc;

    @FXML
    private TextField newDueDate;

    @FXML
    private TableView<Task> taskView;

    @FXML
    private TableColumn<Task, String> taskColumn1;

    @FXML
    private TableColumn<Task, String> taskColumn2;

    @FXML
    private TableColumn<Task, String> taskColumn3;

    @FXML
    private ChoiceBox<String> viewOptions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewOptions.setValue("View All");
        viewOptions.getItems().addAll("View All", "View Completed", "View Incomplete");
        displayMode = "View All";

        taskColumn1.setCellValueFactory(new PropertyValueFactory<Task, String>("Complete"));
        taskColumn2.setCellValueFactory(new PropertyValueFactory<Task, String>("Desc"));
        taskColumn3.setCellValueFactory(new PropertyValueFactory<Task, String>("Due_date"));
    }

    @FXML
    public void addTaskButtonClicked(ActionEvent actionEvent) {
        // Get value of newTaskDesc, and newDueDate text fields
        String descInput = newTaskDesc.getText();
        String dueDateInput = newDueDate.getText();

        // Make sure that none of the fields are blank
        if (descInput.isBlank() || dueDateInput.isBlank()) {
            // - if any fields are blank, create pop-up window telling user to type into those fields
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("BLANK TEXT FIELD");
            alert.setContentText("Please verify that the description and due date text fields contain input.");
            alert.showAndWait();
            return;
        }

        if (dueDateInput.matches("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}") != true) {
            // - Also ensure that due date is in the format YYYY-MM-DD before continuing
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("WRONG DATE FORMAT");
            alert.setContentText("Please verify that the due date is in the following format:\nYYYY-MM-DD");
            alert.showAndWait();
            return;
        }

        if (descInput.length() > 256) {
            // - If newTaskDesc exceeds 256, generate pop-up warning
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("DESCRIPTION IS TOO LONG");
            alert.setContentText("Please enter a description between 1 and 256 characters.");
            alert.showAndWait();
            return;
        }

        model.addNewTask(descInput, dueDateInput, toDoList);
        // Add task to taskView
        taskView.setItems(toDoList);
        // Clear newTaskDesc, and newDueDate text fields
        newTaskDesc.clear();
        newDueDate.clear();
    }

    @FXML
    public void removeTaskButtonClicked(ActionEvent actionEvent) {
        // Get currently selected task with selectionModel
        Task cur_task = taskView.getSelectionModel().getSelectedItem();

        // Store task's description in a temp string
        String tempDesc = cur_task.getDesc();

        // Remove task from taskView
        taskView.getItems().removeAll(cur_task);

        System.out.println(toDoList.size());

        // Remove task from toDoList ObservableList
        model.deleteTask(tempDesc, toDoList);
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
    public void viewButtonClicked(ActionEvent actionEvent) {
        // Get the value of the viewOptions choiceBox
        // Traverse the ToDoLists array until the title matches displayedList
        // Pass the index of the matching list onto the changeView function
        // - model.changeView("viewOption here", "index here");
        // Change the value of displayMode to the value of the viewOptions choicebox
    }

    @FXML
    public void clearButtonClicked(ActionEvent actionEvent) {
        model.clearList(toDoList);
    }
}
