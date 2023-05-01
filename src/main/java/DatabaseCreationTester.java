import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class DatabaseCreationTester {
    @BeforeEach
    public void setup() throws SQLException {
        DataBaseCreation dataBaseCreation = new DataBaseCreation();
    }
    @Test
    public void testCheckTableExistence(){
        String url = "jdbc:sqlite:doesnotexist.sqlite3";
        assertThrows(IllegalWordException.class, ()-> );    }

}
