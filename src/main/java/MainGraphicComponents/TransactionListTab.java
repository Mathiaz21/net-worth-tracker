package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import LogicComponents.TransactionsLogic;
import SecondaryGraphicComponents.TransactionListPanel;

import javax.swing.*;

public class TransactionListTab extends JPanel {

    GlobalInfo globalInfo;
    TransactionsLogic transactionsLogic;
    TransactionListPanel transactionListPanel;
    JScrollPane transactionListScrollPane;
    JButton refreshButton;
    JButton deleteSelectedButton;

    public TransactionListTab(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        transactionsLogic = new TransactionsLogic(globalInfo.getTransactions());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.initComponents();
        this.addGraphicItems();
    }

    private void initComponents() {
        this.transactionListPanel = new TransactionListPanel(globalInfo, transactionsLogic);
        this.transactionListScrollPane = new JScrollPane(transactionListPanel);
        this.refreshButton = new JButton("Refresh");
        this.deleteSelectedButton = new JButton("Delete Selected");
        this.refreshButton.addActionListener(e -> this.transactionListPanel.localRefresh());
        this.deleteSelectedButton.addActionListener(e -> this.transactionListPanel.deleteSelected());
    }


    private void addGraphicItems() {
        this.add(transactionListScrollPane);
        this.add(refreshButton);
        this.add(deleteSelectedButton);
    }
}
