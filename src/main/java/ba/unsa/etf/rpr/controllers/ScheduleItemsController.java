package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ScheduleItemsController {

    @FXML
    public Button addButtonId;
    public Button cancelButtonId;

    @FXML
    public void initalize() {

    }

    public void addScheduleItems() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }

    public void cancelAddingScheduleItems() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }
}
