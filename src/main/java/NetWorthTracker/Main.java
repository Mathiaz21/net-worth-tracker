package NetWorthTracker;

import DBConnection.DBUtils;
import com.formdev.flatlaf.FlatDarkLaf;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        DBUtils.testConnection();
        Vector<String> incomeCategories = DBUtils.getIncomeCategories();
        System.out.println(incomeCategories);
        MainFrame mainFrame = new MainFrame();
    }
}
