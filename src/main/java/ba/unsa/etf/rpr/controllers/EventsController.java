package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EventSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX controller for updating schedule page
 * @author Emin Džanko
 */
public class EventsController {

    @FXML
    public Button cancelButtonId;
    public ChoiceBox dayOfWeekId;
    public TextField startTimeId;
    public TextField eventNameId;
    public TextField locationId;

    private final String scheduleName;

    private final ObservableList<String> daysOfWeekList = FXCollections.observableArrayList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    );

    public EventsController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() {
        dayOfWeekId.setItems(daysOfWeekList);
    }

    public void addScheduleItems() {
            Event newEvent = new Event();
            Schedule schedule;
            try {
                schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);
                int scheduleId = schedule.getId();
                String dayOfWeek = (String) dayOfWeekId.getValue();
                String startTime = startTimeId.getText();
                String eventName = eventNameId.getText();
                String location = locationId.getText();
                String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

                if(!(startTime.matches(timePattern)))
                    showAlert("Please enter a valid time in a day in format hh:mm.");
                else if (eventName.length() == 0)
                    showAlert("Please enter a valid event name, at least 1 symbol.");
                else if(location.length() == 0)
                    showAlert("Please enter a valid location, at least 1 symbol.");
                else {
                    newEvent.setScheduleId(scheduleId);
                    newEvent.setDayOfWeek(dayOfWeek);
                    newEvent.setStartTime(startTime);
                    newEvent.setEventName(eventName);
                    newEvent.setLocation(location);
                    EventSQLImplementation eventSQLImplementation = EventSQLImplementation.getInstance();
                    eventSQLImplementation.save(newEvent);
                    Stage stage = (Stage) cancelButtonId.getScene().getWindow();
                    stage.close();
               }
            }
            catch (Exception e) {
                System.out.println(scheduleName);
                System.out.println(e.getMessage());
            }
    }

    public void cancelAddingScheduleItems() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }

    public List<String> listData() {
        List<String> lista = new ArrayList<>();
        lista.add((String) dayOfWeekId.getValue());
        lista.add(startTimeId.getText());
        lista.add(eventNameId.getText());
        lista.add(locationId.getText());
        return lista;
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Adding Schedule Item Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
