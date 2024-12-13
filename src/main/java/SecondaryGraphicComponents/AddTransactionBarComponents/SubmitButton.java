package SecondaryGraphicComponents.AddTransactionBarComponents;

import DBConnection.DBTransactionComm;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import LogicComponents.GlobalInfo;
import MainGraphicComponents.AddTransactionBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButton extends JButton {

    public SubmitButton(Transaction localTransaction, GlobalInfo globalInfo, AddTransactionBar addTransactionBar) {

        super("Submit");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                globalInfo.addSyncedTransaction(localTransaction);
                addTransactionBar.resetTextFields();
            }
        });
    }
}
