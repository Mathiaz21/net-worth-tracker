package DBConnection;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class DBUtils {

    static final String dbUrl = "jdbc:sqlite:/home/mathias/.net-worth-tracker/net-worth-tracker.db";


    public static void testConnection() {
        // connection string
        try (var conn = DriverManager.getConnection(dbUrl)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
