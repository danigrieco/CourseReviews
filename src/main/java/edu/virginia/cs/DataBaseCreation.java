package edu.virginia.cs;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DataBaseCreation {
    String STUDENTS;
    static Connection connection;
    public static void main(String[] args){
        initializeDatabase();
        Review nReview = new Review(123,"tgh8wp","hello","yessir",4);
        addReviewtoTable(nReview);
        verifyConnection();
        System.out.print(nReview);

    }
    public static void initializeDatabase() {
        String databaseName = "src/Reviews.sqlite3";
        String databaseUrl = "jdbc:sqlite:" + databaseName;
        try {
            // Check if database file exists
            // Establish connection to database
            // Create tables if they don't exist
            connection = DriverManager.getConnection(databaseUrl);
            //createTables();
            //connection.close();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void verifyConnection() {
        try {
            if(connection.isClosed()||connection==null) {
                throw new IllegalStateException("Manager not connected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public static void connectDatabase(){
        try {
            if(connection.isClosed()||connection==null) {
                throw new IllegalStateException("Manager is already connected!");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        String databaseName = "src/Reviews.sqlite3";
        String url = "jdbc:sqlite:" + databaseName;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createTables() {
        try {
            //verifyConnection();
            Statement stmt = connection.createStatement();
            String reviewsQuery = "CREATE TABLE IF NOT EXISTS REVIEWS " +
                    "(\n" +
                    "                         ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "                         STUDENTID INT NOT NULL,\n" +
                    "                         COURSEID VARCHAR(255) not null,\n" +
                    "                         REVIEW_MESSAGE VARCHAR(300) NOT NULL,\n" +
                    "                         RATING INT(1) NOT NULL check(1<=RATING<=5),\n" +
                    "                         ,\n" +
                    "                         FOREIGN KEY (STUDENTID) REFERENCES STUDENTS(ID) on delete cascade,\n" +
                    "                         FOREIGN KEY (COURSEID) REFERENCES COURSES(ID) on delete cascade\n" +
                    ")";
            String studentsQuery = "CREATE TABLE IF NOT EXISTS STUDENTS " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,LOGIN VARCHAR(255) not null unique,PASSWORD VARCHAR(255) not null)";
            String coursesQuery = "CREATE TABLE IF NOT EXISTS  COURSES " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT,DEPARTMENT VARCHAR(255) not null,CATALOG_NUMBER INT(4) not null)";
            stmt.execute(reviewsQuery);
            stmt.execute(studentsQuery);
            stmt.execute(coursesQuery);
            // Close resources
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public boolean checkPasswordIsCorrect(String password, String userName) throws SQLException {
        if (connection.isClosed()) {
            throw new IllegalStateException("Connection is closed right.");
        }
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from STUDENTS where ID = userName";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                if (password.equals(rs.getString("PASSWORD"))) {
                    return true;
                }
                return false;
            }
            return false;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void addReviewtoTable(Review review){
        verifyConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO REVIEWS (STUDENTID, COURSEID, REVIEW_MESSAGE, RATING) " +
                    "SELECT ?, ?, ?, ? " +
                    "WHERE NOT EXISTS " +
                    "(SELECT 1 FROM REVIEWS WHERE STUDENTID = ? AND COURSEID = ?);";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, review.getStudentID());
            statement1.setString(2, review.getCourseID());
            statement1.setString(3, review.getReview_message());
            statement1.setInt(4, review.getRating());
            statement1.setString(5, review.getStudentID());
            statement1.setString(6, review.getCourseID());
            statement1.executeUpdate();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }


    }




}
