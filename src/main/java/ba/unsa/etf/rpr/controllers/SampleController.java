package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController {
    public Button cancelButtonId;
    public Button okButtonId;
    public TextField usernameId;
    public PasswordField passwordId;

    public void probaDugmeta(ActionEvent actionEvent) {
        usernameId.setText("uspjesna proba");
    }
}
