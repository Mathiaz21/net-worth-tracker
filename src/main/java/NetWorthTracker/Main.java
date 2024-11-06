package NetWorthTracker;

import DBConnection.DBUtils;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        DBUtils.testConnection();
        DBUtils.getIncomeCategories();
        //MainFrame mainFrame = new MainFrame();
    }
}
