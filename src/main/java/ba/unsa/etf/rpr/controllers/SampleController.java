package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.User;
import ba.unsa.etf.rpr.UserSQLImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SampleController {
    public Button cancelButtonId;
    public Button okButtonId;
    public TextField usernameId;
    public PasswordField passwordId;
    public Hyperlink hyperlinkId;


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

    public void login(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameId.getText();
        List<User> user = UserSQLImplementation.getInstance().getByUsername(username);

        if(!user.isEmpty()) {
            System.out.println("username " + username + " .");
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
            ScheduleController controller = new ScheduleController();
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
        else {
            showAlert("Login Error", "Invalid username.");
        }

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
