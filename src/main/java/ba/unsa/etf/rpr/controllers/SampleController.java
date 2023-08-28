package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {
    public Button cancelButtonId;
    public Button okButtonId;
    public TextField usernameId;
    public PasswordField passwordId;

    public void switchToRegistration(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/registration.fxml"));
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}
