package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {

    @FXML
    public Button logoutId;
    public Button addId;

    public void logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
    public void addSchedule(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
}
