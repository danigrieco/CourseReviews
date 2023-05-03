package edu.virginia.cs.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label welcome;
    @FXML
    private Label login;
    @FXML
    private Button exisiting;
    @FXML
    private Button newuser;

    public void Cursor(KeyEvent e) {

    }

    public void SwitchtoExisting(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("existinguser-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();

        //Parent root = new FXMLLoader(CourseReviewApplication.class.getResource("login-view.fxml"));
        //Parent root = FXMLLoader.load(CourseReviewApplication.class.getResource(newuser-view.fxml));


//        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("login-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
//        stage.setTitle("Course Review");
//        stage.setScene(scene);
//        stage.show();

    }

    public void SwitchtoNew(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CourseReviewApplication.class.getResource("newuser-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }




//    public void exitTheGame(ActionEvent event){
//        System.exit(0);
//    }
//    //if player guess is correct, end game
//
//    public void Cursor(KeyEvent event) {
//        TextField field = (TextField) getNode(gridPane, c, r);
//        field.setStyle("-fx-text-inner-color: #000000; -fx-background-color: #FFFFFF;");
//        if (event.getText().isBlank()) {
//            TextField x = (TextField) getNode(gridPane,4,r);
//            String blank = "";
//            x.setText(blank);
//            if (c == 0) {
//                field.setText("");
//            }
//            if (c > 0) {
//                Node n2 = getNode(gridPane, c - 1, r);
//                TextField newField2 = (TextField) n2;
//                newField2.requestFocus();
//                newField2.setText(blank);
//                c--;
//                int b = c;
//                guess = guess.substring(0, b);
//
//            }
//        } else {
//            field.setEditable(true);
//            field.setText(event.getText());
//            guess += event.getText();
//            if (c < 4) {
//                field = (TextField)getNode(gridPane,c+1,r);
//                field.requestFocus();
//                c++;
//                illegalWord.setVisible(false);
//            }
//            if(c==4){
//                field.requestFocus();
//            }
//            if (guess.length() == 5) {
//                try {
//                    LetterResult[] answer = wordle.submitGuess(guess);
//                    for (int i = 0; i < 5; i++) {
//                        Node node1 = getNode(gridPane, i, r);
//                        TextField field1 = (TextField) node1;
//                        if (answer[i] == LetterResult.YELLOW) {
//                            field1.setStyle("-fx-background-color: #c8b653; -fx-text-inner-color: #FFFFFF;");
//                        } else if (answer[i] == LetterResult.GREEN) {
//                            field1.setStyle("-fx-background-color: #228B22; -fx-text-inner-color: #FFFFFF;");
//                        } else if (answer[i] == LetterResult.GRAY) {
//                            field1.setStyle("-fx-background-color: #808080; -fx-text-inner-color: #FFFFFF;");
//                        }
//                    }
//                    if (wordle.isGameOver()) {
//                        guess = "";
//                        stopPlaying();
//                        r = 0;
//                        c = 0;
//                        Node n1 = getNode(gridPane, c, r);
//                        TextField newField = (TextField) n1;
//                        newField.requestFocus();
//                        //newField.setDisable(true);
//                        if (wordle.isWin()) {
//                            YouWin.setText("You win!");
//                            YouWin.setVisible(true);
//                        } else if (wordle.isLoss()) {
//                            YouLose.setText("You Lose! The correct answer was: "+ wordle.getAnswer());
//                            YouLose.setVisible(true);
//                        }
//                        playAgain.setVisible(true);
//                        buttonsVisible();
//                    } else {
//                        guess = "";
//                        c = 0;
//                        r++;
//                        Node n1 = getNode(gridPane, c, r);
//                        TextField newField1 = (TextField) n1;
//                        newField1.requestFocus();
//                    }
//                } catch (IllegalWordException newWord) {
//                    illegalWord.setText("Not a word!");
//                    illegalWord.setVisible(true);
//                    c = 4;
//                    TextField newField2 = (TextField) getNode(gridPane, c, r);
//                    newField2.requestFocus();
//                }
//            }
//        }
//    }
//
//    public void buttonsVisible(){
//        playAgain.setVisible(true);
//        Yes.setVisible(true);
//        No.setVisible(true);
//    }
//
//    public void moveToTextField(MouseEvent mouseEvent){
//        EventType<MouseEvent> event = MouseEvent.MOUSE_PRESSED;
//        if(mouseEvent.getEventType().equals(event)){
//            TextField current = (TextField) getNode(gridPane,c,r);
//            TextField t = (TextField) mouseEvent.getSource();
//            if(!current.equals(t)){
//                current.requestFocus();
//            }
//        }
//    }
//
//    public void stopPlaying(){
//        int gridPaneWidth = 5;
//        int gridPaneHeight = 6;
//        for(int i =0;i<gridPaneWidth;i++){
//            for(int j = 0;j<gridPaneHeight;j++){
//                Node n1 = getNode(gridPane,i,j);
//                TextField newField = (TextField) n1;
//                newField.setEditable(false);
//                newField.setDisable(true);
//            }
//        }
//    }
//
//    public Node getNode(GridPane gridPane,int column, int row){
//        int x = 0;
//        for(Node child: gridPane.getChildren()){
//            if(gridPane.getColumnIndex(child)==null&&x==0){
//                gridPane.setColumnIndex(child,0);
//                x = 0;
//            }
//            if(gridPane.getRowIndex(child)==null&&x==0){
//                gridPane.setRowIndex(child,0);
//                x = 0;
//            }
//            if(RowandColumnCheck(child, column, row)){
//                return child;
//            }
//        }
//        return null;
//    }
//
//    public boolean RowandColumnCheck(Node child,int column, int row){
//        if(gridPane.getRowIndex(child)==row && gridPane.getColumnIndex(child)==column){
//            return true;
//        }
//        return false;
//    }
//
//    public void playAgain(ActionEvent event){
//        int gpwidth = 5;
//        int gpheight = 6;
//        wordle = new edu.virginia.cs.wordle.WordleImplementation();
//        answer = wordle.getAnswer();
//        for(int i=0; i<gpwidth; i++) {
//            for(int j=0; j<gpheight; j++) {
//                Node n1 = getNode(gridPane, i, j);
//                TextField newField = (TextField) n1;
//                newField.setText("");
//                newField.setStyle("-fx-background-color: #FFFFFF");
//                newField.setEditable(true);
//                newField.setDisable(false);
//            }
//        }
//        playAgain.setVisible(false);
//        YouWin.setVisible(false);
//        YouLose.setVisible(false);
//        Yes.setVisible(false);
//        No.setVisible(false);
//        r = 0;
//        c = 0;
//        Node n2 = getNode(gridPane, c, r);
//        TextField curr = (TextField) n2;
//        curr.requestFocus();
//    }
//
}