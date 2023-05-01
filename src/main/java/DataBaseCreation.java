import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DataBaseCreation {
    String STUDENTS;
    Connection connection;
    public void initializeDatabase() {
        String databaseName = "Reviews.sqlite3";
        String databaseUrl = "jdbc:sqlite:" + databaseName;
        try {
            // Check if database file exists
            // Establish connection to database
            // Create tables if they don't exist
            connection = DriverManager.getConnection(databaseUrl);
            //createTables();
            connection.close();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void verifyConnect() {
        try {
            if(connection.isClosed()||connection==null) {
                throw new IllegalStateException("Manager not connected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public void connectDatabase(){
        try {
            if(connection.isClosed()||connection==null) {
                throw new IllegalStateException("Manager is already connected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        String databaseName = "Reviews.sqlite3";
        String url = "jdbc:sqlite:" + databaseName;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTables() {
        try {
            verifyConnect();
            Statement stmt = connection.createStatement();
            String reviewsQuery = "CREATE TABLE IF NOT EXISTS REVIEWS " +
                    "(\n" +
                    "                         ID INT(5),\n" +
                    "                         STUDENTID INT(5),\n" +
                    "                         COURSEID INT(5),\n" +
                    "                         REVIEW_MESSAGE VARCHAR(300),\n" +
                    "                         RATING INT(1),\n" +
                    "                         PRIMARY KEY (ID),\n" +
                    "                         FOREIGN KEY (STUDENTID) REFERENCES STUDENTS(ID),\n" +
                    "                         FOREIGN KEY (COURSEID) REFERENCES COURSES(ID)\n" +
                    ")";
            String studentsQuery = "CREATE TABLE IF NOT EXISTS STUDENTS (ID INT(5),LOGIN VARCHAR(6),PASSWORD VARCHAR(255),PRIMARY KEY (ID))";
            String coursesQuery = "CREATE TABLE IF NOT EXISTS  COURSES (ID INT(5),DEPARTMENT VARCHAR(4),CATALOG_NUMBER INT(4),PRIMARY KEY (ID))";
            stmt.execute(reviewsQuery);
            stmt.execute(studentsQuery);
            stmt.execute(coursesQuery);
            // Close resources
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    /*public void checkTableExistence(){
        if()
    }*/

}
