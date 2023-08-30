package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ScheduleController {

    @FXML
    public TextField scheduleNameId;
    public Button addButtonId;
    public Button cancelButtonId;


    public void cancelSchedule() {
        Stage stage = (Stage) cancelButtonId.getScene().getWindow();
        stage.close();
    }


    public void addSchedule()  {
        try {
            String scheduleName = scheduleNameId.getText();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scheduleForm.fxml"));
            ScheduleFormController controller = new ScheduleFormController(scheduleName);
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
