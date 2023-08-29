package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.User;
import ba.unsa.etf.rpr.UserSQLImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegistrationController {

    public TextField firstNameId;

    public Label invalidFirstNameId;

    public TextField lastNameId;

    public Label invalidLastNameId;

    public TextField usernameId;

    public Label invalidUsernameId;

    public PasswordField passwordId;

    public Label invalidPasswordId;

    private String username, password;
   public RegistrationController(String username, String password) {
       this.username = username;
       this.password = password;
   }

   @FXML
    public void initialize() {
       usernameId.setText(username);
       passwordId.setText(password);

       usernameId.textProperty().addListener((obs, oldValue, newValue) -> {
           if(newValue.length() >= 5)
               invalidUsernameId.setText(" ");
           else
               invalidUsernameId.setText("Username must have at least 5 characters");
       });

       passwordId.textProperty().addListener((obs, oldValue, newValue) -> {
           if(newValue.length() >= 5)
               invalidPasswordId.setText(" ");
           else
               invalidPasswordId.setText("Password must have at least 5 characters");
       });
   }

    public void register(ActionEvent actionEvent) throws IOException, SQLException {
        String firstName = firstNameId.getText();
        String lastName = lastNameId.getText();
        String username = usernameId.getText();
        String password = passwordId.getText();

        User user = UserSQLImplementation.getInstance().getByUsername(username);

        if(firstName.length() < 1)
            showAlert("Registration Error", "Enter your first name.");

        else if(lastName.length() < 1)
            showAlert("Registration Error", "Enter your last name.");

        else if (user.getUsername() != null) {
            showAlert("Registration Error", "Username already exists.");
        }
        else if (username.length() < 5) {
            showAlert("Registration Error", "Username is too short.");
        }

        else if (password.length() < 5) {
            showAlert("Registration Error", "Password is too short.");
        }  else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);

            UserSQLImplementation userSQL = UserSQLImplementation.getInstance();
            userSQL.save(newUser);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/schedule.fxml"));
            ScheduleController controller = new ScheduleController();
            loader.setController(controller);
            stage.setTitle("ScheduleApp");
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
        }
    }

    public void switchToLogin(ActionEvent actionEvent) throws IOException {
       Stage stage = (Stage) usernameId.getScene().getWindow();
       stage.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
