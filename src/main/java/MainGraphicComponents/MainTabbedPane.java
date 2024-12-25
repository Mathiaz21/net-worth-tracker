package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import SecondaryGraphicComponents.QuarterPanels.TransactionListQuarterPanel;
import SecondaryGraphicComponents.TransactionListPanel;

import javax.swing.*;

public class MainTabbedPane extends JTabbedPane {

    GlobalInfo globalInfo;

    public MainTabbedPane(GlobalInfo globalInfo) {
        
        super();
        this.globalInfo = globalInfo;
        JPanel overviewTab = new OverviewTab(globalInfo);
        JPanel transactionListTab = new TransactionListQuarterPanel(globalInfo, new TransactionListPanel(globalInfo));
        JPanel statsTab = new JPanel();
        statsTab.add(new JLabel("TODO : Stats tab"));
        JPanel accountNCategoryTab = new JPanel();
        accountNCategoryTab.add(new JLabel("TODO : Account N Category Tab"));

        

        this.add("Overview", overviewTab);
        this.add("Transaction List", transactionListTab);
        this.add("Stats", statsTab);
        this.add("Accounts and Categories", accountNCategoryTab);
    }
}
