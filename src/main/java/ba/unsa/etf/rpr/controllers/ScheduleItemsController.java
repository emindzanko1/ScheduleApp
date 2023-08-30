package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ScheduleItemsController {

    @FXML
    public Button addButtonId;
    public Button cancelButtonId;
    public TextField dayOfWeekId;
    public TextField startTimeId;
    public TextField endTimeId;
    public TextField eventNameId;
    public TextField locationId;

    private String scheduleName;

    public ScheduleItemsController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initalize() {

    }

    public void addScheduleItems() {
        try {
            ScheduleItem newScheduleItem = new ScheduleItem();

            Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

            int scheduleId = schedule.getId();
            String dayOfWeek = dayOfWeekId.getText().toLowerCase();

            List<String> workDays = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday");

            if(!workDays.contains(dayOfWeek))
                showAlert("Please enter a valid work day.");



            else {

                newScheduleItem.setScheduleId(scheduleId);
                newScheduleItem.setDayOfWeek(dayOfWeekId.getText());
                newScheduleItem.setStartTime(startTimeId.getText());
                newScheduleItem.setEndTime(endTimeId.getText());
                newScheduleItem.setEventName(eventNameId.getText());
                newScheduleItem.setLocation(locationId.getText());

                ScheduleItemSQLImplementation scheduleItemSQLImplementation = ScheduleItemSQLImplementation.getInstance();
                scheduleItemSQLImplementation.save(newScheduleItem);
                Stage stage = (Stage) cancelButtonId.getScene().getWindow();
                stage.close();
            }

        }
        catch (ScheduleException | SQLException e) {
            System.out.println(e.getMessage());
        }

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

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
