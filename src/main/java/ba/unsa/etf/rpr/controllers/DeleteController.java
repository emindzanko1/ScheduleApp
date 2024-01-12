package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DeleteController {

    private final String scheduleName;

    @FXML
    public Label title;
    public Button deleteButton, cancelButton;

    @FXML
    public void initialize() {
        title.setText("Are you you want to delete " + scheduleName + " ?");
    }

    public DeleteController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public void cancelDelete(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
