package MainGraphicComponents;

import DBConnection.DBTransactionComm;
import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import LogicComponents.TransactionsLogic;
import SecondaryGraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.util.ArrayList;

public class TransactionListTab extends JPanel implements TransactionList{

    GlobalInfo globalInfo;
    ArrayList<TransactionGraphicItem> transactionGraphicItems;
    TransactionsLogic transactionsLogic;
    JButton refreshButton;
    JButton deleteSelectedButton;

    public TransactionListTab(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        transactionsLogic = new TransactionsLogic(globalInfo.getTransactions());
        this.transactionGraphicItems = new ArrayList<TransactionGraphicItem>();
        this.setUpSwing();
        this.refreshButton = new JButton("Refresh");
        this.deleteSelectedButton = new JButton("Delete Selected");
        this.refreshButton.addActionListener(e -> this.localRefresh());
        this.deleteSelectedButton.addActionListener(e -> this.deleteSelected());
        this.addGraphicItems();
    }

    private void setUpSwing() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addGraphicItems() {
        for (int i = 0; i < globalInfo.getTransactions().size(); i++) {
            TransactionGraphicItem item = new TransactionGraphicItem(this.globalInfo, i, this);
            this.transactionGraphicItems.add(item);
            this.add(item);
        }
        this.add(refreshButton);
        this.add(deleteSelectedButton);
    }

    public void localRefresh() {
        this.removeAll();
        transactionsLogic.orderTransactionByDate();
        this.addGraphicItems();
        this.revalidate();
    }

    public void deleteSelected() {
        int startIndex = this.globalInfo.getSelectedMinIndex();
        int endIndex = this.globalInfo.getSelectedMaxIndex();
        for (int i = startIndex; i <= endIndex; i++) {
            int transactionId = globalInfo.getTransactions().get(i).getTransactionID();
            DBTransactionComm.deleteTransactionById(transactionId);
        }
        globalInfo.refreshTransactionsFromDB();
        this.localRefresh();
    }

    public void updateSelectedStates() {
        for (TransactionGraphicItem item : transactionGraphicItems)
            item.updateSelectedState();
    }
}
