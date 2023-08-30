package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScheduleFormController {

    private String scheduleName;
    @FXML
    private Label title;

    public ScheduleFormController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() {
        title.setText(scheduleName);
    }

    public void addSchedule(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }
}
