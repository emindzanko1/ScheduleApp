package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    public TextField nameId;

    public Label invalidNameId;

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
               passwordId.setText(" ");
           else
               passwordId.setText("Password must have at least 5 characters");
       });

   }
}
