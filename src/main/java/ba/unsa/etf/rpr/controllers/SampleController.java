package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SampleController {
    public Button cancelButtonId;
    public Button okButtonId;
    public TextField usernameId;
    public PasswordField passwordId;


    @FXML
    public void initialize() {

    }

    public void switchToRegistration(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/registration.fxml"));
        RegistrationController controller = new RegistrationController(usernameId.getText(), passwordId.getText());
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void login(ActionEvent actionEvent) {
    }
}
