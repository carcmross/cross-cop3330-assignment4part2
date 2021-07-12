package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Marc-Anthony Cross
 */

public class EditTaskManagerController implements Initializable {

    public static String tempDesc;

    public static String tempDueDate;

    @FXML
    private Button submitButton;

    @FXML
    private TextField editedDesc;

    @FXML
    private TextField editedDueDate;

    public void openEditWindow(String desc, String dueDate) throws IOException {
        // Generate pop-up window with current values of the currently selected task
        Parent root2 = FXMLLoader.load(getClass().getResource("EditTaskManager.fxml"));

        Stage secondStage = new Stage();
        Scene scene2 = new Scene(root2);
        secondStage.setTitle("Edit Task");
        secondStage.setScene(scene2);
        secondStage.showAndWait();
    }

    @FXML
    public void submitChangesButtonClicked(ActionEvent actionEvent) {
        // Get text from the edited text fields
        tempDesc = editedDesc.getText();
        tempDueDate = editedDueDate.getText();

        // Close the window
        Stage tempStage = (Stage) submitButton.getScene().getWindow();
        tempStage.close();

        // Make curDesc and curDueDate holders empty again
        ToDoListsManagerController.curDesc = "";
        ToDoListsManagerController.curDueDate = "";
        return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set text field to contain the selected task's old values to be edited for reference
        editedDesc.setText(ToDoListsManagerController.curDesc);
        editedDueDate.setText(ToDoListsManagerController.curDueDate);
    }
}
