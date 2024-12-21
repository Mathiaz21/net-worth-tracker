package SecondaryGraphicComponents;

import DBConnection.DBTransactionComm;
import LogicComponents.GlobalInfo;
import LogicComponents.TransactionsLogic;

import javax.swing.*;
import java.util.ArrayList;
public class TransactionListPanel extends JPanel {
    GlobalInfo globalInfo;
    ArrayList<TransactionGraphicItem> transactionGraphicItems;
    TransactionsLogic transactionsLogic;

    public TransactionListPanel(GlobalInfo globalInfo, TransactionsLogic transactionsLogic) {
        super();
        this.globalInfo = globalInfo;
        this.transactionsLogic = transactionsLogic;
        transactionGraphicItems = new ArrayList<>();
        int nbTransactions = this.globalInfo.getTransactions().size();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addGraphicItems();
    }

    private void addGraphicItems() {
        for (int i = 0; i < globalInfo.getTransactions().size(); i++) {
            TransactionGraphicItem item = new TransactionGraphicItem(this.globalInfo, i, this);
            this.transactionGraphicItems.add(item);
            this.add(item);
        }
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
