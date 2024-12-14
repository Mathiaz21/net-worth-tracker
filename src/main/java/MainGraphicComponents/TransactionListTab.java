package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import LogicComponents.TransactionsLogic;
import SecondaryGraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.util.ArrayList;

public class TransactionListTab extends JPanel {

    GlobalInfo globalInfo;
    ArrayList<Transaction> transactions;
    ArrayList<TransactionGraphicItem> transactionGraphicItems;
    TransactionsLogic transactionsLogic;
    JButton refreshButton;

    public TransactionListTab(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        this.transactions = globalInfo.getTransactions();
        transactionsLogic = new TransactionsLogic(transactions);
        this.transactionGraphicItems = new ArrayList<TransactionGraphicItem>();
        this.setUpSwing();
        this.refreshButton = new JButton("Refresh");
        this.refreshButton.addActionListener(e -> this.refresh());
        this.addGraphicItems();
    }

    private void setUpSwing() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addGraphicItems() {
        for (int i = 0; i < transactions.size(); i++) {
            TransactionGraphicItem item = new TransactionGraphicItem(this.globalInfo, i, this);
            this.transactionGraphicItems.add(item);
            this.add(item);
        }
        this.add(refreshButton);
    }

    public void refresh() {
        this.removeAll();
        transactionsLogic.orderTransactionByDate();
        this.addGraphicItems();
        this.revalidate();
    }

    public void updateSelectedStates() {
        for (TransactionGraphicItem item : transactionGraphicItems)
            item.updateSelectedState();
    }
}
