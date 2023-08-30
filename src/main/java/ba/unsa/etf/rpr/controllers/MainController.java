package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    public Label subtitleId;
    public Button logoutId;

    private final String username;

    public MainController(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        subtitleId.setText("Welcome " + username);
    }

    public void logout() {
        Stage stage = (Stage) logoutId.getScene().getWindow();
        stage.close();
    }
}
