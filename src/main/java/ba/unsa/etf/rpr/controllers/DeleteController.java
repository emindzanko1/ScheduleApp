package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EventSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class DeleteController {

    private final String scheduleName, username;

    @FXML
    public Label title;
    public Button deleteButton, cancelButton;

    @FXML
    public void initialize() {
        title.setText("Are you you want to delete " + scheduleName + "?");
    }

    public DeleteController(String scheduleName, String username) {
        this.scheduleName = scheduleName;
        this.username = username;
    }

    public void cancelDelete(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void deleteSchedule(ActionEvent event) {
        try {

            Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

            EventSQLImplementation eventSQL = EventSQLImplementation.getInstance();
            List<Event> events = eventSQL.getByScheduleId(schedule.getId());

            if(events.size() > 0) {
                for (Event event1 : events) {
                    eventSQL.delete(event1);
                }
            }

            ScheduleSQLImplementation scheduleSQL = ScheduleSQLImplementation.getInstance();
            scheduleSQL.delete(schedule);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
            ScheduleController controller = new ScheduleController(username, scheduleName);
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (ScheduleException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
