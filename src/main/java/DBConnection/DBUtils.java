package DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String dbUrl = "jdbc:sqlite:/home/mathias/net-worth-tracker.db";
    private static final String incomeCategoriesQuery = "SELECT categoryName FROM categories WHERE categoryType = 'Income'";

    public static void testConnection() {
        // connection string
        try (var conn = DriverManager.getConnection(dbUrl)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getIncomeCategories() {
        try (var conn = DriverManager.getConnection(dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(incomeCategoriesQuery)) {

            while (rs.next()) {
                System.out.println(rs.getInt("categoryName"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
