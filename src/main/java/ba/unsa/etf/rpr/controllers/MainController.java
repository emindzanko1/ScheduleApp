package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * JavaFX controller for User main page
 * @author Emin Džanko
 */
public class MainController {

    public Label subtitleId;
    public Button logoutId;

    private final String username;

    public MainController(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        subtitleId.setText("Welcome " + username);
    }

    public void logout() {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }

    public void checkSchedules() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
        ScheduleController controller = new ScheduleController(username);
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();

        /*Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
        ScheduleController controller = new ScheduleController(username);
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();*/
    }
}
