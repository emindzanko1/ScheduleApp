package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    public TextField nameId;

    public Label invalidName;

    public TextField usernameId;

    public Label invalidUsername;

    public PasswordField passwordId;

    public Label invalidPassword;

    private String username, password;
   public RegistrationController(String username, String password) {
       this.username = username;
       this.password = password;
   }

   @FXML
    public void initialize() {
       usernameId.setText(username);
       passwordId.setText(password);
   }
}
