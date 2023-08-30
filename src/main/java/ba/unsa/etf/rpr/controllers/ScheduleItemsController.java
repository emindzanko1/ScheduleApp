package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleItemsController {

    @FXML
    public Button addButtonId;
    public Button cancelButtonId;
    public TextField dayOfWeekId;
    public TextField startTimeId;
    public TextField endTimeId;
    public TextField eventNameId;
    public TextField locationId;

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

    public List<String> vratiPodatke() {
        List<String> lista = new ArrayList<>();
        lista.add(dayOfWeekId.getText());
        lista.add(startTimeId.getText());
        lista.add(endTimeId.getText());
        lista.add(eventNameId.getText());
        lista.add(locationId.getText());
        return lista;
    }
}
