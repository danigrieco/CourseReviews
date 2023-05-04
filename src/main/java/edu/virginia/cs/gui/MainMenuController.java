package edu.virginia.cs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainMenuController {

    @FXML
    private Button submit;
    @FXML
    private Button see;
    @FXML
    private Button logout;

    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("login-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchtoSubmit(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("submitreview-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void SwitchtoSee(ActionEvent e) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("seereview-view.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);
            stage.setScene(scene);
            stage.show();
    }

}
