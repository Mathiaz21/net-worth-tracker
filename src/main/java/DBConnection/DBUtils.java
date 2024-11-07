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

    private static final String dbUrl = "jdbc:sqlite:/home/mathias/net-worth-tracker.db";
    private static final String incomeCategoriesQuery = "SELECT categoryName FROM categories WHERE categoryType = 'Income'";
    private static final String outcomeCategoriesQuery = "SELECT categoryName FROM categories WHERE categoryType = 'Outcome'";

    private static final String allTransactionsQuery = "SELECT * FROM transactions";
    private static final String insertNewTransactionQuery =
            "INSERT INTO transactions(amountInCents, typeOfTransaction, outcomeAccountId, incomeAccountId, categoryId, description, transactionDate) " +
                    "VALUES (?,?,?,?,?,?,?)";
    private static final String deleteTransactionByIdQuery = "DELETE FROM transactions WHERE transactionId = ?";
    private static final String updateTransactionByIdQuery =
            "UPDATE transactions SET amountInCents = ?," +
                    "typeOfTransaction = ?," +
                    "outcomeAccountId = ?," +
                    "incomeAccountId = ?," +
                    "categoryId = ?," +
                    "description = ?," +
                    "transactionDate = ?" +
                    " WHERE transactionId = ?";

    public static void testConnection() {
        // connection string
        try (var conn = DriverManager.getConnection(dbUrl)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Vector<String> getIncomeCategories() {
        return getCategoriesByType("Income");
    }

    public static Vector<String> getOutcomeCategories() {
        return getCategoriesByType("Outcome");
    }

    public static Vector<Transaction> getAllTransactions() {
        Vector<Transaction> transactions = new Vector<>();

        try (var conn = DriverManager.getConnection(dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(allTransactionsQuery)) {
            while (rs.next()) {
                Transaction newTransaction = new Transaction(

                        rs.getInt("transactionId"),
                        LocalDate.now(),//dateToLocalDate(rs.getDate("transactionDate")), //TODO : Finish the conversion of the timestamp
                        rs.getInt("amountInCents"),
                        intToTypeOfTransaction(rs.getInt("typeOfTransaction")),
                        rs.getInt("outcomeAccountId"),
                        rs.getInt("incomeAccountId"),
                        rs.getInt("categoryId"),
                        rs.getString("description")
                );
                transactions.add(newTransaction);
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return transactions;
    }

    public static void addTransactionInDB(Transaction transaction) {

        final int amountInCents = transaction.getAmountInCents();
        final int typeOfTransaction = Transaction.transactionTypeToInt(transaction.getType());
        final int outcomeAccountId = transaction.getOutcomeAccountId();
        final int incomeAccountId = transaction.getIncomeAccountId();
        final int categoryId = transaction.getCategoryId();
        final String description = transaction.getDescription();
        final String date = transaction.getDate().toString();

        try (var conn = DriverManager.getConnection(dbUrl);
             var pstmt = conn.prepareStatement(insertNewTransactionQuery)) {

                pstmt.setInt(1, amountInCents);
                pstmt.setInt(2, typeOfTransaction);
                pstmt.setInt(3, outcomeAccountId);
                pstmt.setInt(4, incomeAccountId);
                pstmt.setInt(5, categoryId);
                pstmt.setString(6, description);
                pstmt.setString(7, date);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteTransactionById(int id) {
        try (var conn = DriverManager.getConnection(dbUrl);
             var pstmt = conn.prepareStatement(deleteTransactionByIdQuery)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updateTransactionInDB(int transactionId, Transaction transaction) {
        try (var conn = DriverManager.getConnection(dbUrl);
             var pstmt = conn.prepareStatement(updateTransactionByIdQuery)) {
            // set the parameters
            pstmt.setInt(1, transaction.getAmountInCents());
            final int typeOfTransaction = Transaction.transactionTypeToInt(transaction.getType());
            pstmt.setInt(2, typeOfTransaction);
            pstmt.setInt(3, transaction.getOutcomeAccountId());
            pstmt.setInt(4, transaction.getIncomeAccountId());
            pstmt.setInt(5, transaction.getCategoryId());
            pstmt.setString(6, transaction.getDescription());
            pstmt.setString(7, transaction.getDate().toString());
            pstmt.setInt(8, transactionId);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    private static Vector<String> getCategoriesByType(String type) {
        Vector<String> categoriesVector = new Vector<>();
        if ( !(type.equals("Income") || type.equals("Outcome")) ) {
            throw new InvalidParameterException("Type of Category must be \"Income\" or \"Outcome\"");
        }
        String categoriesQuery = "";
        if(type.equals("Income")) {
            categoriesQuery = incomeCategoriesQuery;
        }
        if(type.equals("Outcome")) {
            categoriesQuery = outcomeCategoriesQuery;
        }
        try (var conn = DriverManager.getConnection(dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(categoriesQuery)) {

            while (rs.next()) { categoriesVector.add(rs.getString("categoryName")); }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return categoriesVector;
    }

    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static TypeOfTransaction intToTypeOfTransaction(int transactionTypeId) {
        return switch (transactionTypeId) {
            case 0 -> TypeOfTransaction.INCOME;
            case 1 -> TypeOfTransaction.OUTCOME;
            case 2 -> TypeOfTransaction.INTERNAL;
            default -> throw new InvalidParameterException("Type of Transaction Id must be 0, 1 or 2");
        };
    }
}
