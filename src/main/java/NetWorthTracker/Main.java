package NetWorthTracker;

import DBConnection.DBCategoryComm;
import DBConnection.DBUtils;
import FunctionalComponents.Category;
import com.formdev.flatlaf.FlatDarkLaf;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        MainFrame mainFrame = new MainFrame();
    }
}
