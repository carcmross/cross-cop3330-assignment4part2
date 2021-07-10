package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListsManagerController implements Initializable {

    private ToDoListModel model = new ToDoListModel();

    public Task selectedTask;

    public static ObservableList<Task> toDoList = FXCollections.observableArrayList();

    private String displayMode = "View All";

    public static String curDesc;

    public static String curDueDate;

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

    @FXML
    private DatePicker newTaskDatePicker;


    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewOptions.setValue("View All");
        viewOptions.getItems().addAll("View All", "View Completed", "View Incomplete");
        displayMode = "View All";

        taskColumn1.setCellValueFactory(new PropertyValueFactory<Task, String>("Complete"));
        taskColumn2.setCellValueFactory(new PropertyValueFactory<Task, String>("Desc"));
        taskColumn3.setCellValueFactory(new PropertyValueFactory<Task, String>("DueDate"));
        taskView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void addTaskButtonClicked(ActionEvent actionEvent) {
        // Get value of newTaskDesc, and newDueDate text fields
        String descInput = newTaskDesc.getText();
        String dueDateInput = newDueDate.getText();
        descInput = model.wrapIfLong(descInput);

        if (generateErrors(descInput, dueDateInput))
            return;

        model.addNewTask(descInput, dueDateInput, toDoList);

        // Add task to taskView
        model.changeView(displayMode, toDoList, taskView);

        // Clear newTaskDesc, and newDueDate text fields
        newTaskDesc.clear();
        newDueDate.clear();
    }

    private boolean generateErrors(String descInput, String dueDateInput) {
        // - If newTaskDesc exceeds 256, generate pop-up warning
        if (descTooLong(descInput))
            return true;

        // Make sure that none of the fields are blank
        if (inputIsBlank(descInput, dueDateInput))
            return true;

        if (dateFormatWrong(dueDateInput))
            return true;

        if (taskAlreadyExists(descInput))
            return true;
        return false;
    }

    public boolean taskAlreadyExists(String descInput) {
        for (int i = 0; i < toDoList.size(); i++) {
            if (descInput.equals(toDoList.get(i).getDesc())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("DUPLICATE TASK");
                alert.setContentText("Task already exists in list.");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    public boolean dateFormatWrong(String dueDateInput) {
        if (dueDateInput.matches("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}") != true) {
            // - Also ensure that due date is in the format YYYY-MM-DD before continuing
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("WRONG DATE FORMAT");
            alert.setContentText("Please verify that the due date is in the following format:\nYYYY-MM-DD");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean inputIsBlank(String descInput, String dueDateInput) {
        if (descInput.isBlank() || dueDateInput.isBlank()) {
            // - if any fields are blank, create pop-up window telling user to type into those fields
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("BLANK TEXT FIELD");
            alert.setContentText("Please verify that the description and due date text fields contain input.");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean descTooLong(String descInput) {
        if (descInput.length() > 256) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("DESCRIPTION IS TOO LONG");
            alert.setContentText("Please enter a description between 1 and 256 characters.");
            alert.showAndWait();
            return true;
        }
        return false;
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
    public void editTaskButtonClicked(ActionEvent actionEvent) throws IOException {
        if (taskView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("NO TASK SELECTED");
            alert.setContentText("Please select a task before attempting to edit.");
            alert.showAndWait();
            return;
        }

        selectedTask = taskView.getSelectionModel().getSelectedItem();
        curDesc = selectedTask.getDesc();
        curDesc = model.wrapIfLong(curDesc);
        curDueDate = selectedTask.getDueDate();
        EditTaskManagerController editor = new EditTaskManagerController();
        editor.openEditWindow(selectedTask.getDesc(), selectedTask.getDueDate());

        if (generateErrors(EditTaskManagerController.tempDesc, EditTaskManagerController.tempDueDate))
            return;

        model.editTask(selectedTask.getDesc(), EditTaskManagerController.tempDesc,
                            EditTaskManagerController.tempDueDate, toDoList);
        EditTaskManagerController.tempDesc = "";
        EditTaskManagerController.tempDueDate = "";
        model.changeView(displayMode, toDoList, taskView);
    }

    @FXML
    public void saveSingleButtonClicked(ActionEvent actionEvent) throws IOException {
        // Return warning alert if toDoList has 0 tasks
        if (toDoList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("NO EXISTING TASKS");
            alert.setContentText("Please make sure there are tasks in the list before attempting to save.");
            alert.showAndWait();
            return;
        }

        // Traverse the toDoList array until last element, storing each element in a string as you go
        // Generate pop-up window asking what the name of the .txt file should be
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save List");
        File file = fileChooser.showSaveDialog(new Stage());
        String fileInput = model.generateSaveOutput(toDoList);
        if (file != null) {
            // Write to file
            model.writeToFile(file, fileInput);
        }
    }

    @FXML
    public void loadSingleButtonClicked(ActionEvent actionEvent) {
        // Open up a window asking for the directory of the file where the list was saved
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load List");
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            if (toDoList.size() != 0) {
                toDoList.clear();
                taskView.getItems().clear();
            }
            toDoList = model.loadList(file);
            // Add ObservableList<Task> to taskView
            model.changeView(displayMode, toDoList, taskView);
        }
    }

    @FXML
    public void viewButtonClicked(ActionEvent actionEvent) {
        // Change the value of displayMode to the value of the viewOptions choicebox
        displayMode = viewOptions.getValue();

        // Pass the index of the matching list onto the changeView function
        model.changeView(displayMode, toDoList, taskView);
    }

    @FXML
    public void clearButtonClicked(ActionEvent actionEvent) {
        model.clearList(toDoList);
        taskView.getItems().clear();
    }
}
