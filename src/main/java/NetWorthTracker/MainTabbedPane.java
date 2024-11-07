package NetWorthTracker;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import GraphicComponents.QuadPanel;

import javax.swing.*;
import java.time.LocalDate;

public class MainTabbedPane extends JTabbedPane {

    public MainTabbedPane() {
        
        super();
        JPanel examplePanel = new JPanel();
        JPanel transactionListTab = new TransactionListTab();
        JPanel thirdPanel = new JPanel();
        JPanel fourthPanel = new JPanel();

        this.add("Overview", examplePanel);
        this.add("Transaction List", transactionListTab);
        this.add("Stats", thirdPanel);
        this.add("Accounts and Categories", fourthPanel);
    }
}
