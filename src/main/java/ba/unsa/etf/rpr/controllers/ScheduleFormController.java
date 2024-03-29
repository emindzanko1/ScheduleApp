package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for creating new schedule
 * @author Emin Džanko
 */
public class ScheduleFormController {

    @FXML
    public TextField scheduleNameId;
    public Button cancelButtonId;

    public String scheduleName, username, option;

    public ScheduleFormController(String scheduleName, String username, String option) {
        this.scheduleName = scheduleName;
        this.username = username;
        this.option = option;
    }

    @FXML
    public void initialize() throws SQLException {}

    public void cancelSchedule() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }

    public void addSchedule() throws SQLException {
        try {
            String scheduleName = scheduleNameId.getText();
            if(scheduleName.length() == 0)
                showCreatingAlert();
            else {
                User user = UserSQLImplementation.getInstance().getByUsername(username);
                int userId = user.getId();

                Schedule newSchedule = new Schedule();
                newSchedule.setScheduleName(scheduleName);
                newSchedule.setUserId(userId);
                ScheduleSQLImplementation scheduleSQL = ScheduleSQLImplementation.getInstance();
                scheduleSQL.save(newSchedule);
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
                ScheduleController controller = new ScheduleController(username, scheduleName);
                loader.setController(controller);
                stage.setTitle("ScheduleApp");
                stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.show();
            }
        }
         catch (IOException | ScheduleException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showCreatingAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Creating Schedule Error");
        alert.setHeaderText(null);
        alert.setContentText("Schedule name is too short.");
        alert.showAndWait();
    }
}
