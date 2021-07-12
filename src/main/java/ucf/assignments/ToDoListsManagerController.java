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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class ToDoListsManagerController implements Initializable {

    private ToDoListModel model = new ToDoListModel();

    public Task selectedTask;

    private ObservableList<Task> toDoList = FXCollections.observableArrayList();

    private ObservableList tempList = FXCollections.observableArrayList();

    private String displayMode = "View All";

    public boolean sortMode = false;

    public static String curDesc;

    public static String curDueDate;

    @FXML
    private TextField newTaskDesc;

    @FXML
    private TextField newDueDate;

    @FXML
    private TableView<Task> taskView;

    @FXML
    private TableColumn<Task, Boolean> taskColumn1;

    @FXML
    private TableColumn<Task, String> taskColumn2;

    @FXML
    private TableColumn<Task, String> taskColumn3;

    @FXML
    private ChoiceBox<String> viewOptions;

    @FXML
    private CheckBox sortByDateCheckBox;

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public boolean generateErrors(String descInput, String dueDateInput) {
        // - If newTaskDesc exceeds 256, generate pop-up warning
        if (descTooLong(descInput))
            return true;

        // Make sure that none of the fields are blank
        if (inputIsBlank(descInput, dueDateInput))
            return true;

        if (dateFormatWrong(dueDateInput))
            return true;

        if (notGregorian(dueDateInput))
            return true;
        return false;
    }

    public boolean taskAlreadyExists(String descInput, ObservableList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task cur_task = (Task) taskList.get(i);
            if (descInput.equals(cur_task.getDesc())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("DUPLICATE TASK");
                alert.setContentText("Task already exists in list.");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    public boolean taskMatchesOther(String oldInput, String descInput, ObservableList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task cur_task = (Task) taskList.get(i);
            if (descInput.equals(cur_task.getDesc()) && !descInput.equals(oldInput)) {
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

    public boolean tableIsEmpty(TableView taskView) {
        if (taskView.getItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("NO EXISTING TASKS");
            alert.setContentText("Please make sure there are tasks in the list before you try to remove one.");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean listIsEmpty(ObservableList toDoList) {
        if (toDoList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("NO EXISTING TASKS");
            alert.setContentText("Please make sure there are tasks in the list before you try to clear it.");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public boolean notGregorian(String dueDate) {
        int month = Integer.parseInt(dueDate.substring(5, 7));
        int day = Integer.parseInt(dueDate.substring(8, 10));
        int year = Integer.parseInt(dueDate.substring(0,4));
        List<Integer> thirtyDays = Arrays.asList(4, 6, 9, 11);
        List<Integer> thirtyOneDays = Arrays.asList(1, 3, 5, 7, 8, 10, 12);

        // If month is greater than 12 or less than/equal to 0, generate warning
        if (month > 12 || month <= 0) {
            gregorianWarning();
            return true;
        }

        // If month is a month with 31 days, make sure the day input isn't greater than 30 or less than/equal to 0
        for (int i = 0; i < thirtyDays.size(); i++) {
            if (month == thirtyDays.get(i)) {
                if (day > 30 || day <= 0) {
                    gregorianWarning();
                    return true;
                }
            }
        }

        // If month is a month with 31 days, make sure the day input isn't greater than 31 or less than/equal to 0
        for (int i = 0; i < thirtyOneDays.size(); i++) {
            if (month == thirtyOneDays.get(i)) {
                if (day > 31 || day <= 0) {
                    gregorianWarning();
                    return true;
                }
            }
        }

        // If February and not a leap year, make sure day isn't greater than 28
        if (month == 2 && year % 4 != 0) {
            if (day > 28 || day <= 0) {
                gregorianWarning();
                return true;
            }
        }
        else if (month == 2 && year % 4 == 0){
            // if year isn't evenly divisible by both 100 and 400, it isn't a leap year
            if (year % 100 == 0 && year % 400 != 0) {
                if (day > 28 || day <= 0) {
                    gregorianWarning();
                    return true;
                }
            }
            // If any other year divisible by 4, its a leap year
            if (day > 29 || day <= 0) {
                gregorianWarning();
                return true;
            }
        }

        return false;
    }

    public void gregorianWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("DUE DATE OUT OF BOUNDS");
        alert.setContentText("Make sure your date is a valid date on the Gregorian Calendar.");
        alert.showAndWait();
    }

    public void displayInstructions() {
        // Generate pop-up window telling user instructions
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instructions");
        alert.setHeaderText("INSTRUCTIONS (DEDICATED TO REY)");
        alert.setContentText("Creating a task:\n\tTo add a task, type into the description and due date\n\tfields " +
                "and then press \"Add Task\".\n\n" + "Editing a task:\n\tTo edit a task, select the " +
                "task in the table, click edit \n\ttask, and then enter the new information you'd" +
                " like to \n\tassign that task.\n\n" + "Changing view:\n\tTo change the type of " +
                "task you'd like to see, choose the\n\tview option from the dropdown menu and click " +
                "\"View\".\n\n" + "Removing a task:\n\tTo remove a task, select the task you'd " +
                "like to remove,\n\tthen click remove.\n\n" + "Saving a list:\n\tTo save a list, " +
                "click the save button, then choose a name\n\tand location for the list to be " +
                "stored.\n\n" + "Loading a list:\n\tTo load a list, click the load button, then " +
                "navigate to where\n\tthe list file was stored and click \"Open\".\n\n" + "Marking " +
                "complete/Incomplete:\n\tTo mark a task as complete, select a task and press \n\tthe \"Mark " +
                "Complete/Incomplete\" button.");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewOptions.setValue("View All");
        viewOptions.getItems().addAll("View All", "View Completed", "View Incomplete");
        displayMode = "View All";

        taskColumn1.setCellValueFactory(new PropertyValueFactory<Task, Boolean>("Complete"));
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

        if (generateErrors(descInput, dueDateInput) || taskAlreadyExists(descInput, toDoList))
            return;

        model.addNewTask(descInput, dueDateInput, toDoList);

        // Add task to taskView
        if (taskView.getItems().size() != 0)
            taskView.getItems().clear();
        tempList = model.changeView(displayMode, toDoList);
        taskView.getItems().addAll(tempList);

        // Clear newTaskDesc, and newDueDate text fields
        newTaskDesc.clear();
        newDueDate.clear();
    }

    @FXML
    public void removeTaskButtonClicked(ActionEvent actionEvent) {
        // Return early if the list has nothing to remove
        if (tableIsEmpty(taskView))
            return;

        // Get currently selected task with selectionModel
        Task cur_task = taskView.getSelectionModel().getSelectedItem();

        // Store task's description in a temp string
        String tempDesc = cur_task.getDesc();

        // Remove task from taskView
        taskView.getItems().remove(cur_task);

        System.out.println(toDoList.size());

        // Remove task from toDoList ObservableList
        model.deleteTask(tempDesc, toDoList);
    }

    @FXML
    public void editTaskButtonClicked(ActionEvent actionEvent) throws IOException {
        // Return from function early if there is no task selected to edit
        if (taskView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("NO TASK SELECTED");
            alert.setContentText("Please select a task before attempting to edit.");
            alert.showAndWait();
            return;
        }

        // Get selected task and set curDesc and curDueDate to the task's current property values
        selectedTask = taskView.getSelectionModel().getSelectedItem();
        curDesc = selectedTask.getDesc();
        curDesc = model.wrapIfLong(curDesc);
        curDueDate = selectedTask.getDueDate();
        String temp = curDesc;

        // Open window to get new values from user for the selected task
        EditTaskManagerController editor = new EditTaskManagerController();
        editor.openEditWindow(selectedTask.getDesc(), selectedTask.getDueDate());

        // Generate error messages if any of the new values are invalid
        if (generateErrors(EditTaskManagerController.tempDesc, EditTaskManagerController.tempDueDate) ||
                                taskMatchesOther(temp, EditTaskManagerController.tempDesc, toDoList))
            return;

        model.editTask(selectedTask.getDesc(), EditTaskManagerController.tempDesc,
                            EditTaskManagerController.tempDueDate, toDoList);
        EditTaskManagerController.tempDesc = "";
        EditTaskManagerController.tempDueDate = "";
        // Call changeView to ensure that TableView displays according to the current displayMode
        if (taskView.getItems().size() != 0)
            taskView.getItems().clear();
        tempList = model.changeView(displayMode, toDoList);
        taskView.getItems().addAll(tempList);
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

        // Generate pop-up window asking what the name of the .txt file should be
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save List");
        FileChooser.ExtensionFilter extFil = new FileChooser.ExtensionFilter("Text File (*.txt)",
                                                                    "*.txt");
        fileChooser.getExtensionFilters().add(extFil);
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
        // If a file was chosen, clear the TableView and ObservableList and add the inputted lists to them
        if (file != null) {
            if (toDoList.size() != 0) {
                toDoList.clear();
                taskView.getItems().clear();
            }
            toDoList = model.loadList(file);
            // Add ObservableList<Task> to taskView
            if (taskView.getItems().size() != 0)
                taskView.getItems().clear();
            tempList = model.sortList(tempList, sortMode);
            tempList = model.changeView(displayMode, toDoList);
            taskView.getItems().addAll(tempList);
        }
    }

    @FXML
    public void viewButtonClicked(ActionEvent actionEvent) {
        // Change the value of displayMode to the value of the viewOptions choicebox
        displayMode = viewOptions.getValue();

        // Pass the toDoList into the changeView function
        ObservableList tempList = FXCollections.observableArrayList();
        if (taskView.getItems().size() != 0)
            taskView.getItems().clear();
        tempList = model.changeView(displayMode, toDoList);
        taskView.getItems().addAll(tempList);
    }

    @FXML
    public void clearButtonClicked(ActionEvent actionEvent) {
        if (listIsEmpty(toDoList))
            return;

        // Clear ObservableList and TableView
        model.clearList(toDoList);
        taskView.getItems().clear();
    }

    @FXML
    public void getInstructions(ActionEvent actionEvent) {
        displayInstructions();
    }

    @FXML
    public void markButtonClicked(ActionEvent actionEvent) {
        selectedTask = taskView.getSelectionModel().getSelectedItem();
        System.out.println(selectedTask.getComplete());

        for (int i = 0; i < toDoList.size(); i++) {
            Task cur_task = toDoList.get(i);
            if (cur_task.getDesc().equals(selectedTask.getDesc())) {
                if (cur_task.getComplete().equals("Yes"))
                    toDoList.get(i).setComplete("No");
                else
                    toDoList.get(i).setComplete("Yes");
            }
        }

        if (taskView.getItems().size() != 0)
            taskView.getItems().clear();
        tempList = model.changeView(displayMode, toDoList);
        taskView.getItems().addAll(tempList);
    }

    @FXML
    public void sortBoxClicked(ActionEvent actionEvent) {
        ObservableList<Task> tempList = FXCollections.observableArrayList();
        if (sortByDateCheckBox.isSelected()) {
            sortMode = true;
            tempList = model.sortList(toDoList, sortMode);
            tempList = model.changeView(displayMode, tempList);
        }
        else {
            sortMode = false;
            tempList = model.changeView(displayMode, toDoList);
        }
        if (taskView.getItems().size() != 0)
            taskView.getItems().clear();
        taskView.getItems().addAll(tempList);
    }
}
