package edu.virginia.cs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class NewUserController {
    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;

    @FXML
    private Button back;

    public void Cursor(KeyEvent event) {
        if (!event.getText().isBlank()) {
            user.setStyle("-fx-text-inner-color: #000000; -fx-background-color: #FFFFFF;");
            user.setEditable(true);
            pass.setStyle("-fx-text-inner-color: #000000; -fx-background-color: #FFFFFF;");
            pass.setEditable(true);
        }
    }

    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("login-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }
}
