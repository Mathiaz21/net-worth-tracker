package SecondaryGraphicComponents;

import DBConnection.DBTransactionComm;
import LogicComponents.GlobalInfo;
import LogicComponents.TransactionsHandler;
import MainGraphicComponents.TransactionListTab;
import SecondaryGraphicComponents.QuarterPanels.TransactionModificationPanel;

import javax.swing.*;
import java.util.ArrayList;
public class TransactionListPanel extends JPanel {
    GlobalInfo globalInfo;
    ArrayList<TransactionGraphicItem> transactionGraphicItems;

    public TransactionListPanel(GlobalInfo globalInfo) {
        super();
        this.globalInfo = globalInfo;
        transactionGraphicItems = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addGraphicItems();
    }

    public TransactionListPanel(GlobalInfo globalInfo, TransactionModificationPanel transactionModificationPanel) {
        super();
        this.globalInfo = globalInfo;
        transactionGraphicItems = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addGraphicItemsWithModifPanel(transactionModificationPanel);
    }

    private void addGraphicItems() {
        for (int i = 0; i < globalInfo.getTransactions().size(); i++) {
            TransactionGraphicItem item = new TransactionGraphicItem(this.globalInfo, i, this);
            this.transactionGraphicItems.add(item);
            this.add(item);
        }
    }

    private void addGraphicItemsWithModifPanel(TransactionModificationPanel transactionModificationPanel) {
        for (int i = 0; i < globalInfo.getTransactions().size(); i++) {
            TransactionGraphicItem item = new TransactionGraphicItem(this.globalInfo, i, this, transactionModificationPanel);
            this.transactionGraphicItems.add(item);
            this.add(item);
        }
    }

    public void localRefresh() {
        this.removeAll();
        this.transactionGraphicItems = new ArrayList<>();
        globalInfo.transactionsHandler.orderTransactionByDate();
        this.addGraphicItems();
        this.revalidate();
    }

    public void deleteSelected() {
        int startIndex = this.globalInfo.transactionsHandler.getSelectedMinIndex();
        int endIndex = this.globalInfo.transactionsHandler.getSelectedMaxIndex();
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
