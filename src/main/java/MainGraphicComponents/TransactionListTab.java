package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import SecondaryGraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.util.ArrayList;

public class TransactionListTab extends JPanel {

    GlobalInfo globalInfo;
    ArrayList<Transaction> transactions;
    public TransactionListTab(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        this.transactions = globalInfo.getTransactions();
        this.setUpSwing();
        this.addAllTransactionsItems();
    }

    private void setUpSwing() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addAllTransactionsItems() {
        for (Transaction transaction : transactions) {
            this.add(new TransactionGraphicItem(transaction, globalInfo));
        }
    }
}
