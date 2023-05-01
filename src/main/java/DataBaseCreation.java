import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DataBaseCreation {
    Connection connection;
    public static void initializeDatabase() {
        try {
            // Check if database file exists
            File file = new File("Reviews.sqlite3");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Establish connection to database
            String url = "jdbc:sqlite:Reviews.sqlite3";
            Connection conn = DriverManager.getConnection(url);

            // Create tables if they don't exist
            Statement stmt = conn.createStatement();
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
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            if(connection != null) {
                throw new IllegalStateException();
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/edu/virginia/cs/hw7/Reviews.sqlite3");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    /*public void checkTableExistence(){
        if()
    }*/

}
