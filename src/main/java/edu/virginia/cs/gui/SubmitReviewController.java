package edu.virginia.cs.gui;

import edu.virginia.cs.Course;
import edu.virginia.cs.DataBaseCreation;
import edu.virginia.cs.Review;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SubmitReviewController {
    @FXML
    private TextField courseName;
    @FXML
    private TextArea message;
    @FXML
    private TextField rating;
    @FXML
    private Label error;
    @FXML
    private Button submit;

    @FXML
    public void submission(ActionEvent e) throws IOException {
        DataBaseCreation manager = new DataBaseCreation();
        manager.initializeDatabase();
        String[] course = courseName.getText().split(" ");
        String dept = course[0];
        String num = course[1];
        if (!validCourse(dept, num)) {error.setText("Invalid Course Name");}
        else{
            String courseID = DataBaseCreation.courseID(num, dept);
            if (courseID == null){
                DataBaseCreation.addCourseToTable(new Course(dept, num));
                courseID = DataBaseCreation.courseID(num, dept);
            }
            String studentID;
            String reviewMessage = message.getText();
            String reviewRating = rating.getText();
            DataBaseCreation.addReviewtoTable(new Review(studentID, courseID, reviewMessage, reviewRating));

        }
        manager.disconnect();
    }

    private boolean validCourse(String dept, String num){
        boolean result = true;
        if (dept.length() > 4 || num.length() > 4){result= false;}
        for (int i = 0; i < dept.length(); i++){
            if (Character.isUpperCase(dept.charAt(i))){result = false;}
        }
        return result;
    }
}
