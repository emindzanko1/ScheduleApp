package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for schedule page
 * @author Emin Džanko
 */
public class ScheduleFormController {

    private final String scheduleName;
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

    public ScheduleFormController(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @FXML
    public void initialize() {
        title.setText(scheduleName);
    }

    public void addScheduleItems(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleItems.fxml"));
        ScheduleItemsController scheduleItemsController= new ScheduleItemsController(scheduleName);
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
                case "Monday" -> mondayListId.getItems().add(formattedData);
                case "Tuesday" -> tuesdayListId.getItems().add(formattedData);
                case "Wednesday" -> wednesdayListId.getItems().add(formattedData);
                case "Thursday" -> thursdayListId.getItems().add(formattedData);
                case "Friday" -> fridayListId.getItems().add(formattedData);
                default -> {
                }
            }
        });
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelId.getScene().getWindow();
        stage.close();
    }
}
