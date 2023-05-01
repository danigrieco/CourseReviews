
package src;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DataBaseCreation {
    Connection connection;

    public void connect() {
        try {
            if(connection != null) {
                throw new IllegalStateException();
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/edu/virginia/cs/hw7/bus_stops.sqlite3");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
