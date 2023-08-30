package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    public Label subtitleId;

    private final String username;

    public MainController(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        subtitleId.setText("Welcome " + username);
    }
}
