package NetWorthTracker;

import GraphicComponents.QuadPanel;

import javax.swing.*;

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
