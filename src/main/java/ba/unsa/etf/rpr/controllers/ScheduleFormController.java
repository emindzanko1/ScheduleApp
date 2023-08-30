package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScheduleFormController {

    private String scheduleName;
    @FXML
    private Label title;
    @FXML
    private Button cancelId;

    public ScheduleFormController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() {
        title.setText(scheduleName);
    }

    public void addScheduleItems(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();
    }
}
