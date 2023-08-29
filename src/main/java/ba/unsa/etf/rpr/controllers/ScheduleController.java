package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ScheduleController {

    @FXML
    public Button logoutId;

    public void logout() {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
    public void addSchedule() {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
}
