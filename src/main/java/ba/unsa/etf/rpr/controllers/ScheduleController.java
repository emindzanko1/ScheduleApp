package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private final String scheduleName;
    private String username;

    private List<Schedule> schedules = new ArrayList<>();

    public ScheduleController(String username) {
        this.username = username;
        this.scheduleName = "";
    }

    public ScheduleController(String scheduleName, String username) {
        this.username = username;
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() throws SQLException, ScheduleException {
        //List<Schedule> schedules = ScheduleSQLImplementation.getInstance().getSchedulesForUser(id);


        //if(!scheduleName.equals(""))
        //hBox.getChildren().add(new Button(scheduleName));



          /*  List<Schedule> schedules = ScheduleSQLImplementation.getInstance().getSchedulesForUser(id);
            for (Schedule schedule : schedules) {
                Button scheduleButton = new Button(schedule.getScheduleName());
                scheduleButton.setOnAction(event -> handleScheduleButtonClick(schedule.getScheduleName()));
                hBox.getChildren().add(scheduleButton);
            }*/
    }

    private void handleScheduleButtonClick(String scheduleName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
            ScheduleFormController controller = new ScheduleFormController(username);
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScheduleItems(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleItems.fxml"));
        ScheduleItemsController scheduleItemsController = new ScheduleItemsController(scheduleName);
        loader.setController(scheduleItemsController);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

        stage.setOnHiding(x -> {
            List<String> lista = scheduleItemsController.vratiPodatke();
            String dayOfWeek = lista.get(0);
            String startTime = lista.get(1);
            String eventName = lista.get(3);
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
        });
    }

    public void createNewSchedule(ActionEvent actionEvent) throws IOException {
        //for(int i = 0; i < actionEvent.hashCode(); i++
        //gridPane.getChildren().add(new Button("test")) ;

      //  gridPane.getChildren().add(hBox);

        //append, get child
        /*for(int i = 0; i < 5; i++) {
                    hBox.getChildren().add(new Button(scheduleName));
on(scheduleName);
            gridPane.add(button, i, 0, 1, 1);
        }*/

        //koliko imam rasporeda, toliko cu imati dugmica ofc


        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
        System.out.println("Username koji saljem " + username);
        ScheduleFormController controller = new ScheduleFormController(username);
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
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
}
