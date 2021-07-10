package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        tempDesc = editedDesc.getText();
        tempDueDate = editedDueDate.getText();
        Stage tempStage = (Stage) submitButton.getScene().getWindow();
        tempStage.close();
        ToDoListsManagerController.curDesc = "";
        ToDoListsManagerController.curDueDate = "";
        return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editedDesc.setText(ToDoListsManagerController.curDesc);
        editedDueDate.setText(ToDoListsManagerController.curDueDate);
    }
}
