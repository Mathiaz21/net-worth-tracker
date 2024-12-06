package DBConnection;

import FunctionalComponents.Account;
import FunctionalComponents.Transaction;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAccountComm {

    private static final String accountsQuery = "SELECT accountName, accountId FROM accounts";
    private static final String insertNewAccountQuery = "INSERT INTO transactions(accountName) VALUES (?)";
    private static final String renameAccountByIdQuery =
            "UPDATE accounts SET accountName = ?" +
                    " WHERE accountId = ?";

    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();

        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(accountsQuery)) {
            while (rs.next()) {

                int accountId = rs.getInt("accountId");
                String accountName = rs.getString("accountName");
                accountList.add(new Account(accountId, accountName));
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return accountList;
    }

    public static void insertNewAccount(String accountName) {
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var pstmt = conn.prepareStatement(insertNewAccountQuery)) {

                pstmt.setString(1, accountName);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void renameAccount(int accountId, String newName) {
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var pstmt = conn.prepareStatement(renameAccountByIdQuery)) {
            // set the parameters
            pstmt.setString(1, newName);
            pstmt.setInt(2, accountId);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
