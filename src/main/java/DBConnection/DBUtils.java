package DBConnection;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Vector;

public class DBUtils {

    static final String dbUrl = "jdbc:sqlite:/home/mathias/net-worth-tracker.db";


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

    static TypeOfTransaction intToTypeOfTransaction(int transactionTypeId) {
        return switch (transactionTypeId) {
            case 0 -> TypeOfTransaction.INCOME;
            case 1 -> TypeOfTransaction.OUTCOME;
            case 2 -> TypeOfTransaction.INTERNAL;
            default -> throw new InvalidParameterException("Type of Transaction Id must be 0, 1 or 2");
        };
    }
}
