package SecondaryGraphicComponents.QuarterPanels;

import LogicComponents.GlobalInfo;
import SecondaryGraphicComponents.TransactionListPanel;

import javax.swing.*;
import java.awt.*;

public class TransactionListQuarterPanel extends JPanel {

    GlobalInfo globalInfo;
    TransactionListPanel transactionListPanel;
    JScrollPane transactionListScrollPane;

    public TransactionListQuarterPanel(GlobalInfo globalInfo, TransactionListPanel transactionListPanel) {
        this.globalInfo = globalInfo;
        this.transactionListPanel = transactionListPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.initComponents();
        this.add(transactionListScrollPane);
    }

    private void initComponents() {
        this.transactionListPanel = new TransactionListPanel(globalInfo);
        this.transactionListScrollPane = new JScrollPane(transactionListPanel);
        this.transactionListScrollPane.getVerticalScrollBar().setUnitIncrement(20);
    }

}
