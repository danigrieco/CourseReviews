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
        Review r = new Review(12121,"tgh8wp","3140","sucks balls",1);
        Review r2 = new Review(12121,"yassqueen","3140","sucks less balls",4);

        addReviewtoTable(r);
        addReviewtoTable(r2);

        printReviewsForCourse("3140");
        printAverageReviewScoreForCourse("3140");

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
            stmt.execute(studentsQuery);
            stmt.execute(coursesQuery);
            stmt.execute(reviewsQuery);
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
    public static boolean courseInDatabase(int catalog, String dept){
        verifyConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from COURSES where DEPARTMENT = ? and CATALOG_NUMBER = ?");
            statement.setString(1, dept);
            statement.setInt(2, catalog);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addCourseToTable(Course course){
        verifyConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO COURSES (ID, DEPARTMENT, CATALOG_NUMBER) VALUES (?, ?, ?)");
            statement.setInt(1, course.getID());
            statement.setString(2, course.getDepartment());
            statement.setInt(3, course.getCatalog());
            statement.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void addStudentToTable(Student student){
        verifyConnection();
        try {
            PreparedStatement checkStmt = connection.prepareStatement("SELECT * FROM STUDENTS WHERE ID = ?");
            checkStmt.setInt(1, student.getID());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                System.out.println("A student with the same ID already exists in the database.");
                return;
            }
            PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO STUDENTS (ID, LOGIN, PASSWORD) VALUES (?, ?, ?)");
            insertStmt.setInt(1, student.getID());
            insertStmt.setString(2, student.getLogin());
            insertStmt.setString(3, student.getPassword());
            insertStmt.executeUpdate();
            System.out.println("Student added to database.");
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static boolean alreadyReviewed(int courseID, int studentID){
        verifyConnection();
        try {
            PreparedStatement checkStmt = connection.prepareStatement("SELECT * FROM REVIEWS WHERE STUDENTID = ?");
            checkStmt.setInt(1, studentID);
            ResultSet rs = checkStmt.executeQuery();
            while (rs.next()) {
                if(rs.getInt("courseID")==courseID){
                    return true;
                }
            }
            return false;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static boolean courseAlreadyHasReviews(String catalog, String dept){
        verifyConnection();
        try {
            String x = courseID(catalog,dept);
            PreparedStatement checkStmt = connection.prepareStatement("SELECT * FROM REVIEWS WHERE COURSEID = ?");
            checkStmt.setString(1, x);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                    return true;
            }
            return false;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static String courseID(String catalog, String dept){
        verifyConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM COURSES WHERE DEPARTMENT = ? and CATALOG_NUMBER = ?");
            statement.setString(1, dept);
            statement.setString(2, catalog);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                    return rs.getString("ID");
            }
            return null;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void printReviewsForCourse(String courseID) {
        verifyConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT REVIEW_MESSAGE FROM REVIEWS WHERE COURSEID = ?"
            );
            statement.setString(1, courseID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("REVIEW_MESSAGE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printAverageReviewScoreForCourse(String courseID) {
        verifyConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT AVG(RATING) AS AVERAGE_SCORE FROM REVIEWS WHERE COURSEID = ?"
            );
            statement.setString(1, courseID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                double averageScore = rs.getDouble("AVERAGE_SCORE");
                System.out.printf("Course Average %.2f/5%n", averageScore);
            } else {
                System.out.println("No reviews found for this course.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }













}