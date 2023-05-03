package edu.virginia.cs.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class NewUserController {
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;

    public void Cursor(KeyEvent event) {
        if (!event.getText().isBlank()) {
            user.setStyle("-fx-text-inner-color: #000000; -fx-background-color: #FFFFFF;");
            user.setEditable(true);
            pass.setStyle("-fx-text-inner-color: #000000; -fx-background-color: #FFFFFF;");
            pass.setEditable(true);
        }
    }
}
