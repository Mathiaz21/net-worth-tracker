package NetWorthTracker;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import GraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.time.LocalDate;

public class TransactionListTab extends JPanel {

    JLabel temporaryLabel;
    Transaction temporaryTransaction;
    public TransactionListTab() {

        this.temporaryLabel = new JLabel("TODO : Transaction List");
        Transaction temporaryTransaction = new Transaction(
                                            LocalDate.now(),
                                            0,
                                            TypeOfTransaction.OUTCOME,
                                            2,
                                            3,
                                            4,
                                            "Okay I pull up"
        );
        this.add(temporaryLabel);
        this.add(new TransactionGraphicItem(temporaryTransaction));
    }
}
