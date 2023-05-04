package edu.virginia.cs.gui;

import edu.virginia.cs.DataBaseCreation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class SeeReviewController {
    @FXML
    private Label enter;
    @FXML
    private TextField coursename;
    @FXML
    private Label reivewname;
    @FXML
    private TextArea actualreview;
    @FXML
    private Button back;
    @FXML
    private Label error;
    @FXML
    private Label average;
    @FXML
    private TextField avg;

    @FXML
    public void back(ActionEvent e) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("login-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("mainmenu-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void enterCourse(ActionEvent e) throws IOException {
        DataBaseCreation manager = new DataBaseCreation();
        manager.initializeDatabase();
        try {
            if(DataBaseCreation.courseInDatabase(Integer.parseInt(coursename.getText(3, 6)), coursename.getText(0,1))) {
                actualreview.setText(DataBaseCreation.printReviewsForCourse(coursename.getText(0,1)));
                //avg.setText(DataBaseCreation.printAverageReviewScoreForCourse(coursename.getText(0,1)));

            }
            else {
                error.setText("The course you entered has no reviews.");
            }
//        }
//        catch (SQLException l) {
//            l.printStackTrace();
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }
        manager.disconnect();
    }

//    @FXML
//    public void logIn(ActionEvent e) throws IOException {
//        DataBaseCreation manager = new DataBaseCreation();
//        manager.initializeDatabase();
//        try{
//            if (DataBaseCreation.checkPasswordIsCorrect(user.getText(), pass.getText())) {
//                goMain(e);
//            }
//            else{
//                //flash error
//                error.setText("We couldn't log you in! Please try again or create a new account.");
//
//            }
//        }
//        catch (SQLException l){l.printStackTrace();}
//        manager.disconnect();
//    }

}
