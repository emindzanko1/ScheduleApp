package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.Schedule;
import ba.unsa.etf.rpr.ScheduleItemSQLImplementation;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ScheduleController {

    @FXML
    public TextField scheduleNameId;
    public Button addButtonId;
    public Button cancelButtonId;

    public String username;

    public ScheduleController(String username) {
        this.username = username;
    }

    public void cancelSchedule() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }

    public void addSchedule() throws SQLException {
        try {
            String scheduleName = scheduleNameId.getText();
            ScheduleItemSQLImplementation.getInstance().getByEventName(scheduleName);

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
            ScheduleFormController controller = new ScheduleFormController(scheduleName);
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
        catch (IOException | ScheduleException e) {
            System.out.println(e.getMessage());
        }

    }

}
