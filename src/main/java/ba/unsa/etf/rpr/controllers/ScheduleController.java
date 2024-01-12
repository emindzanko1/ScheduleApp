package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EventSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for schedule page
 * @author Emin Džanko
 */
public class ScheduleController {

    @FXML
    private Label title;
    @FXML
    private Button cancelId;
    @FXML
    private ListView<String> mondayListId;
    @FXML
    private ListView<String> tuesdayListId;
    @FXML
    private ListView<String> wednesdayListId;
    @FXML
    private ListView<String> thursdayListId;
    @FXML
    private ListView<String> fridayListId;
    @FXML
    private Button newScheduleId;
    @FXML
    private GridPane gridPane;
    @FXML
    private HBox hBox;
    @FXML
    private Button button1;

    private int id;
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
        if (!hBox.getChildren().isEmpty()) {
            //simulateButtonClick(0);
        }
    }

    private void handleScheduleButtonClick(String scheduleName) throws SQLException, ScheduleException {
        this.scheduleName = scheduleName;

        Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

        List<Event> events = EventSQLImplementation.getInstance().getByScheduleId(schedule.getId());

        for(Event event : events)
            System.out.println(event.getEventName());


        displayEventsInListViews(events);


        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
            ScheduleFormController controller = new ScheduleFormController(username, scheduleName, "Add");
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void displaySchedule(String scheduleName) throws SQLException, ScheduleException {
        this.scheduleName = scheduleName;

        Schedule schedule = ScheduleSQLImplementation.getInstance().getByScheduleName(scheduleName);

        List<Event> events = EventSQLImplementation.getInstance().getByScheduleId(schedule.getId());

        displayEventsInListViews(events);
    }

    private void simulateButtonClick(int index) {
        Button clickedButton = (Button) hBox.getChildren().get(index);
        clickedButton.setStyle("-fx-background-color: lightblue; -fx-border-color: blue; -fx-border-width: 2px;");

        // Trigger the action associated with the button click
        clickedButton.fire(); // Simulate the button click event
    }

    private void displayEventsInListViews(List<Event> events) {
        mondayListId.getItems().clear();
        tuesdayListId.getItems().clear();
        wednesdayListId.getItems().clear();
        thursdayListId.getItems().clear();
        fridayListId.getItems().clear();

        for (Event event : events) {
            String formattedData = event.getStartTime().substring(0, event.getStartTime().length() - 3) + " " + event.getEventName();
            switch (event.getDayOfWeek()) {
                case "Monday":
                    mondayListId.getItems().add(formattedData);
                    break;
                case "Tuesday":
                    tuesdayListId.getItems().add(formattedData);
                    break;
                case "Wednesday":
                    wednesdayListId.getItems().add(formattedData);
                    break;
                case "Thursday":
                    thursdayListId.getItems().add(formattedData);
                    break;
                case "Friday":
                    fridayListId.getItems().add(formattedData);
                    break;
                default:
                    break;
            }
        }
    }


    public void addEvent(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/event.fxml"));
        EventsController eventsController = new EventsController(scheduleName);
        loader.setController(eventsController);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

        /*stage.setOnHiding(x -> {
            List<String> list = eventsController.listData();
            String dayOfWeek = list.get(0);
            String startTime = list.get(1);
            String eventName = list.get(2);
            String formattedData = startTime + " " + eventName;

            switch (dayOfWeek) {
                case "Monday" -> addSortedItem(mondayListId, formattedData);
                case "Tuesday" -> addSortedItem(tuesdayListId, formattedData);
                case "Wednesday" -> addSortedItem(wednesdayListId, formattedData);
                case "Thursday" -> addSortedItem(thursdayListId, formattedData);
                case "Friday" -> addSortedItem(fridayListId, formattedData);
                default -> {
                }
            }
        });*/
    }

    public void createNewSchedule(ActionEvent actionEvent) throws IOException, SQLException, ScheduleException {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();

        User user = UserSQLImplementation.getInstance().getByUsername(username);
        int userId = user.getId();

        int numberOfSchedules = ScheduleSQLImplementation.getInstance().getNumberOfSchedules((userId));

        if(numberOfSchedules < 7) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));

            ScheduleFormController controller = new ScheduleFormController(scheduleName, username, "Add");
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
        else {
            showCreatingAlert();
        }
    }

    public void deleteSchedule(ActionEvent actionEvent) throws IOException, SQLException, ScheduleException {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();

        User user = UserSQLImplementation.getInstance().getByUsername(username);
        int userId = user.getId();

        int numberOfSchedules = ScheduleSQLImplementation.getInstance().getNumberOfSchedules((userId));

        if(numberOfSchedules != 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/delete.fxml"));

            //ScheduleFormController controller = new ScheduleFormController(scheduleName, username, "Delete");
            DeleteController controller = new DeleteController(scheduleName, username);
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
        else {
            showDeletingAlert();
        }

    }

    private void addSortedItem(ListView<String> listView, String item) {
        ObservableList<String> items = listView.getItems();
        int index = 0;
        while (index < items.size() && compareItems(item, items.get(index)) > 0) {
            index++;
        }
        final int finalIndex = index;
        Platform.runLater(() -> items.add(finalIndex, item));
    }

    private int compareItems(String item1, String item2) {
        String timePattern = "\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(timePattern);

        Matcher matcher1 = pattern.matcher(item1);
        Matcher matcher2 = pattern.matcher(item2);

        if (matcher1.find() && matcher2.find()) {
            String time1 = matcher1.group();
            String time2 = matcher2.group();

            LocalTime localTime1 = LocalTime.parse(time1);
            LocalTime localTime2 = LocalTime.parse(time2);

            return localTime1.compareTo(localTime2);
        } else {
            return 0;
        }
    }
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();
    }

    private void showCreatingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Creating Schedule Error");
        alert.setHeaderText(null);
        alert.setContentText("Maximum number of schedules is 7.");
        alert.showAndWait();
    }

    private void showDeletingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deleting Schedule Error");
        alert.setHeaderText(null);
        alert.setContentText("You don't have any schedules, so we can not perform deleting.");
        alert.showAndWait();
    }
}
