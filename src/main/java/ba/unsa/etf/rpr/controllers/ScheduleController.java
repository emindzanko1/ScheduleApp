package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EventSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for schedule page
 *
 * @author Emin Džanko
 */
public class ScheduleController {
    @FXML
    private Label title;
    @FXML
    private Button cancelId;
    @FXML
    private ListView<Hyperlink> mondayListId;
    @FXML
    private ListView<Hyperlink> tuesdayListId;
    @FXML
    private ListView<Hyperlink> wednesdayListId;
    @FXML
    private ListView<Hyperlink> thursdayListId;
    @FXML
    private ListView<Hyperlink> fridayListId;
  /* @FXML
   private ListView<String> mondayListId;
    @FXML
    private ListView<String> tuesdayListId;
    @FXML
    private ListView<String> wednesdayListId;
    @FXML
    private ListView<String> thursdayListId;
    @FXML
    private ListView<String> fridayListId;*/
    @FXML
    private HBox hBox;
    @FXML
    private HBox mondayBox;
    @FXML
    private HBox tuesdayBox;
    @FXML
    private HBox wednesdayBox;
    @FXML
    private HBox thursdayBox;
    @FXML
    private HBox fridayBox;

    private String scheduleName;
    private final String username;

    public ScheduleController(String username) {
        this.username = username;
        this.scheduleName = "";
    }

    public ScheduleController(String username, String scheduleName) {
        this.username = username;
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() throws SQLException, ScheduleException {
        User user = UserSQLImplementation.getInstance().getByUsername(username);
        int userId = user.getId();
        List<Schedule> schedules = ScheduleSQLImplementation.getInstance().getSchedulesByUserId(userId);

        if (!schedules.isEmpty()) {
            this.scheduleName = schedules.get(0).getScheduleName();
            displaySchedule(this.scheduleName);
        }

        for (Schedule schedule : schedules) {
            Button scheduleButton = new Button(schedule.getScheduleName());
            scheduleButton.setOnAction(event -> {
                try {
                    handleScheduleButtonClick(schedule.getScheduleName());
                } catch (SQLException | ScheduleException e) {
                    e.printStackTrace();
                }
            });
            hBox.getChildren().add(scheduleButton);
        }
    }

    private void handleScheduleButtonClick(String scheduleName) throws SQLException, ScheduleException {
        this.scheduleName = scheduleName;
        Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

        List<Event> events = EventSQLImplementation.getInstance().getByScheduleId(schedule.getId());
        displayEventsInListViews(events);
    }

    private void displaySchedule(String scheduleName) throws SQLException, ScheduleException {
        this.scheduleName = scheduleName;

        Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

        List<Event> events = EventSQLImplementation.getInstance().getByScheduleId(schedule.getId());

        displayEventsInListViews(events);
    }

    private void displayEventsInListViews(List<Event> events) {
        Collections.sort(events);

        mondayListId.getItems().clear();
        tuesdayListId.getItems().clear();
        wednesdayListId.getItems().clear();
        thursdayListId.getItems().clear();
        fridayListId.getItems().clear();

        for (Event event : events) {
            Hyperlink hyperlink = new Hyperlink(event.getStartTime().substring(0, event.getStartTime().length() - 3) + " " + event.getEventName());
            hyperlink.setOnAction(e -> {
                try {
                    handleEventClick(event);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

           /* hyperlink.setOnMouseClicked(event1 -> {

                try {
                    handleEventClick(event1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });*/
            switch (event.getDayOfWeek()) {
                case "Monday" -> mondayListId.getItems().add(hyperlink);
                case "Tuesday" -> tuesdayListId.getItems().add(hyperlink);
                case "Wednesday" -> wednesdayListId.getItems().add(hyperlink);
                case "Thursday" -> thursdayListId.getItems().add(hyperlink);
                case "Friday" -> fridayListId.getItems().add(hyperlink);
                default -> {
                }
            }
        }
    }

    private void handleEventClick(Event event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/eventForm.fxml"));

        EventFormController eventFormController = new EventFormController();

        loader.setController(eventFormController);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

    }


    public void addEvent(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/event.fxml"));
        EventsController eventsController = new EventsController(scheduleName);
        loader.setController(eventsController);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void createNewSchedule(ActionEvent actionEvent) throws IOException, SQLException, ScheduleException {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();

        User user = UserSQLImplementation.getInstance().getByUsername(username);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));

        ScheduleFormController controller = new ScheduleFormController(scheduleName, username, "Add");
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

    }

    public void deleteSchedule(ActionEvent actionEvent) throws IOException, SQLException, ScheduleException {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();

        User user = UserSQLImplementation.getInstance().getByUsername(username);
        int userId = user.getId();

        int numberOfSchedules = ScheduleSQLImplementation.getInstance().getNumberOfSchedules((userId));

        if (numberOfSchedules != 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/delete.fxml"));
            DeleteController controller = new DeleteController(scheduleName, username);
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } else {
            showDeletingAlert();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();
    }

    private void showDeletingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deleting Schedule Error");
        alert.setHeaderText(null);
        alert.setContentText("You don't have any schedules, so we can not perform deleting.");
        alert.showAndWait();
    }
}
