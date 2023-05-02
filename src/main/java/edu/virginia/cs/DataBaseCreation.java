package edu.virginia.cs;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class DataBaseCreation {
    String STUDENTS;
    static Connection connection;
    public static void main(String[] args){
        //initializeDatabase();
        System.out.print("Hello World");
        connectDatabase();
        //initializeDatabase();
        verifyConnection();
        System.out.print("Hello World");

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
            connection.close();
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
    public void addReviewtoTable(Review review ){
        verifyConnection();


    }




}
