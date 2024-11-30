package NetWorthTracker;

import DBConnection.DBTransactionComm;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import GraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Vector;

public class TransactionListTab extends JPanel {

    Vector<Transaction> transactions;
    public TransactionListTab() {
        this.loadAllTransactions();
        this.setUpSwing();
        this.addAllTransactionsItems();
    }

    public void loadAllTransactions() {
        this.transactions = DBTransactionComm.getAllTransactions();
    }

    private void setUpSwing() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addAllTransactionsItems() {
        for (Transaction transaction : transactions) {
            this.add(new TransactionGraphicItem(transaction));
        }
    }
}
