package DBConnection;

import FunctionalComponents.Account;
import FunctionalComponents.Transaction;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAccountComm {

    private static final String accountsQuery = "SELECT accountName, accountId, initialAmount FROM accounts";
    private static final String insertNewAccountQuery = "INSERT INTO accounts(accountName, initialAmount) VALUES (?, ?)";
    private static final String renameAccountByIdQuery =
            "UPDATE accounts SET accountName = ?" +
                    " WHERE accountId = ?";
    private static final String redefineInitialAmountByIdQuery =
            "UPDATE accounts SET initialAmount = ?" +
                    " WHERE accountId = ?";

    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();

        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(accountsQuery)) {
            while (rs.next()) {

                int accountId = rs.getInt("accountId");
                String accountName = rs.getString("accountName");
                int initialAmount = rs.getInt("initialAmount");
                accountList.add(new Account(accountId, accountName, initialAmount));
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return accountList;
    }

    public static void insertNewAccount(String accountName, int initialAmount) {
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var pstmt = conn.prepareStatement(insertNewAccountQuery)) {

                pstmt.setString(1, accountName);
                pstmt.setInt(2, initialAmount);
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

    public static void redefInitialAmount(int accountId, int initialAmount) {
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var pstmt = conn.prepareStatement(redefineInitialAmountByIdQuery)) {
            // set the parameters
            pstmt.setInt(1, initialAmount);
            pstmt.setInt(2, accountId);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
