package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleController {

    public Button logoutId;
    public ScheduleController() {
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
}
