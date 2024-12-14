package SecondaryGraphicComponents.QuarterPanels;

import LogicComponents.GlobalInfo;
import LogicComponents.TransactionsLogic;
import MainGraphicComponents.TransactionList;
import SecondaryGraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.util.ArrayList;

public class TransactionListQuarter extends JPanel implements TransactionList {

    GlobalInfo globalInfo;
    TransactionsLogic transactionsLogic;
    ArrayList<TransactionGraphicItem> transactionGraphicItems;

    public TransactionListQuarter(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        this.transactionsLogic = new TransactionsLogic(globalInfo.getTransactions());
//        setupTransactionGraphicItems();

    }

    public void setupTransactionGraphicItems() {
        int numberOfTransaction = globalInfo.getTransactions().size();
        transactionGraphicItems = new ArrayList<>();
        for (int i = 0; i < numberOfTransaction; i++){
            transactionGraphicItems.add(new TransactionGraphicItem(globalInfo, i, this));
            this.add(transactionGraphicItems.get(i));
        }

    }

    public void updateSelectedStates() {
        for (TransactionGraphicItem item : transactionGraphicItems)
            item.updateSelectedState();
    }
}
