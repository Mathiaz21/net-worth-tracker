package NetWorthTracker;

import GraphicComponents.AddTransactionBarComponents.AmountTextField;
import FunctionalComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import FunctionalComponents.TypeOfTransaction;
import GraphicComponents.AddTransactionBarComponents.DateTextField;
import GraphicComponents.AddTransactionBarComponents.SubmitButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AddTransactionBar extends JPanel {

    private GlobalInfo globalInfo;

    private Transaction localTransaction;

    private AmountTextField amountTextField;
    private DateTextField dateField;
    private JComboBox transactionTypeComboBox;
    private JComboBox accountChoiceComboBox;
    private JComboBox adaptableComboBox;
    private JTextField descriptionField;
    private SubmitButton submitButton;

    GridBagLayout gridBagLayout;
    GridBagConstraints constraints;

    public AddTransactionBar(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        this.localTransaction = new Transaction(-1, LocalDate.now(), 100, TypeOfTransaction.OUTCOME, 0, 0, 0, "");
        this.setupGridBag();
        this.setupSwingComponents();
        this.addActionListenersToInputs();
    }

    private void setupGridBag() {

        this.gridBagLayout = new GridBagLayout();
        this.setLayout(this.gridBagLayout);

        this.constraints = new GridBagConstraints();
        this.constraints.anchor = GridBagConstraints.LINE_START;
        this.constraints.insets = new Insets(5, 5, 5, 5);
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
    }

    private void setUpLocalTransaction() {
        int defaultAmount = 0;
        LocalDate localDate = LocalDate.now();
        TypeOfTransaction defaultTypeOfTransaction = TypeOfTransaction.OUTCOME;
        int defaultOutcomeAccountId = 0;
        int defaultIncomeAccountId = 0;
        int defaultCategoryId = 0;
        String emptyDescription = "";

        this.localTransaction = new Transaction(localDate, defaultAmount, defaultTypeOfTransaction,
                defaultOutcomeAccountId, defaultIncomeAccountId, defaultCategoryId, emptyDescription);
    }

    private void setupSwingComponents() {

        this.amountTextField = new AmountTextField(this.localTransaction);
        this.dateField = new DateTextField(this.localTransaction);
        String[] transactionTypeList = {"Outcome", "Income", "Internal"};
        this.transactionTypeComboBox = new JComboBox(transactionTypeList);
        Vector<String> accountList = globalInfo.getAccountNames();
        this.accountChoiceComboBox = new JComboBox(accountList);
        Vector<String> categoryList = globalInfo.getOutcomeCategoriesNames();
        this.adaptableComboBox = new JComboBox(categoryList);
        this.descriptionField = new JTextField("");
        this.submitButton = new SubmitButton(this.localTransaction);

        this.add(this.amountTextField, this.constraints);
        this.constraints.gridx = 1;
        this.add(this.dateField, this.constraints);
        this.constraints.gridx = 2;
        this.add(this.transactionTypeComboBox, this.constraints);
        this.constraints.gridx = 3;
        this.add(this.accountChoiceComboBox, this.constraints);
        this.constraints.gridx = 4;
        this.add(this.adaptableComboBox, this.constraints);
        this.constraints.gridx = 5;
        this.add(this.descriptionField, this.constraints);
        this.constraints.gridx = 6;
        this.add(this.submitButton, this.constraints);
    }

    private void addActionListenersToInputs() {
        this.descriptionField.getDocument().addDocumentListener(new DescriptionInputListener());
    }

    private class DescriptionInputListener implements DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            updateDescription();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            updateDescription();
        }
        @Override // Only triggered for styled text
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        public void updateDescription() {
            localTransaction.setDescription(descriptionField.getText());
        }
    }


}
