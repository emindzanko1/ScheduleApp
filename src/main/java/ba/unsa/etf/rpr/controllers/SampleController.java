package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.User;
import ba.unsa.etf.rpr.UserSQLImplementation;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SampleController {
    public TextField usernameId;
    public PasswordField passwordId;
    public Hyperlink hyperlinkId;


    @FXML
    public void initialize() {

    }

    public void switchToRegistration() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/registration.fxml"));
        RegistrationController controller = new RegistrationController(usernameId.getText(), passwordId.getText());
        loader.setController(controller);
        stage.setTitle("ScheduleApp");
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
    }

    public void login() throws IOException, SQLException, ScheduleException {
        String username = usernameId.getText();
        String password = passwordId.getText();
        User user = UserSQLImplementation.getInstance().getByUsername(username);

        if(user.getUsername() == null || !user.getUsername().equals(username))
            showAlert("Invalid username.");

        else if(user.getPassword() == null || !user.getPassword().equals(password))
            showAlert("Invalid password.");

        else {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
            ScheduleController controller = new ScheduleController();
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
