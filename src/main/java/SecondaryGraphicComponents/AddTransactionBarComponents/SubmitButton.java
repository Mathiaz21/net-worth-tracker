package SecondaryGraphicComponents.AddTransactionBarComponents;

import DBConnection.DBTransactionComm;
import FunctionalComponents.Transaction;

import javax.swing.*;

public class SubmitButton extends JButton {

    public SubmitButton(Transaction localTransaction) {

        super("Submit");
        this.addActionListener(e -> DBTransactionComm.addTransactionInDB(localTransaction));
    }
}
