package DBConnection;

import FunctionalComponents.Category;
import FunctionalComponents.TypeOfTransaction;

import java.security.InvalidParameterException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBCategoryComm {


    private static final String incomeCategoriesQuery = "SELECT categoryId, categoryName, categoryType FROM categories WHERE categoryType = 'Income'";
    private static final String outcomeCategoriesQuery = "SELECT categoryId, categoryName, categoryType FROM categories WHERE categoryType = 'Outcome'";

    public static ArrayList<Category> getIncomeCategories() {
        return getCategoriesByType("Income");
    }

    public static ArrayList<Category> getOutcomeCategories() {
        return getCategoriesByType("Outcome");
    }


    private static ArrayList<Category> getCategoriesByType(String type) {
        ArrayList<Category> categoryList = new ArrayList<>();
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
        try (var conn = DriverManager.getConnection(DBUtils.dbUrl);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(categoriesQuery)) {

            while (rs.next()) {
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");
                TypeOfTransaction categoryType = stringToTypeOfTransaction(rs.getString("categoryType"));
                categoryList.add(new Category(categoryId, categoryName, categoryType));
            }
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return categoryList;
    }

    private static TypeOfTransaction stringToTypeOfTransaction(String typeOfTransactionString) {
        return switch (typeOfTransactionString) {
            case "Income" -> TypeOfTransaction.INCOME;
            case "Outcome" -> TypeOfTransaction.OUTCOME;
            default -> throw new InvalidParameterException("Category type must be \"Income\" or \"Outcome\"");
        };
    }

    public static void insertNewCategory(Category category) {

    }
}
