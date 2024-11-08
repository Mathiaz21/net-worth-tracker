package DBConnection;

import FunctionalComponents.Account;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DBAccountComm {

    private static final String accountsQuery = "SELECT accountName, accountId FROM accounts";
    private static final String insertNewAccountQuery = "INSERT INTO transactions(accountName) VALUES (?)";

    public static Vector<Account> getAccounts() {
        Vector<Account> accountVector = new Vector<>();

        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(accountsQuery)) {
            while (rs.next()) {

                int accountId = rs.getInt("accountId");
                String accountName = rs.getString("accountName");
                accountVector.add(new Account(accountId, accountName));
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return accountVector;
    }

    public static void insertNewAccount(String accountName) {
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var pstmt = conn.prepareStatement(insertNewAccountQuery)) {

                pstmt.setString(1, accountName);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //TODO : public static void renameAccount
}
