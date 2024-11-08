package NetWorthTracker;

import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import GraphicComponents.TransactionGraphicItem;

import javax.swing.*;
import java.time.LocalDate;

public class TransactionListTab extends JPanel {

    public TransactionListTab() {


        Transaction temporaryTransaction = new Transaction(
                                            LocalDate.now(),
                                            255,
                                            TypeOfTransaction.OUTCOME,
                                            2,
                                            3,
                                            4,
                                            "Okay I pull up"
        );
        this.add(new TransactionGraphicItem(temporaryTransaction));
    }
}
