package NetWorthTracker;

import FunctionalComponents.*;
import GraphicComponents.AddTransactionBarComponents.AmountTextField;
import GraphicComponents.AddTransactionBarComponents.DateTextField;
import GraphicComponents.AddTransactionBarComponents.SubmitButton;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

// TODO : SOLVE SYNCHRONISATION PROBLEMS WITH ACCOUNT ID

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
        this.transactionTypeComboBox = new JComboBox();
        this.accountChoiceComboBox = new JComboBox();
        this.adaptableComboBox = new JComboBox();
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
        setupTransactionTypeComboBox();
        setupAccountChoiceComboBox();
        setupAdaptableComboBox();
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

    private void setupTransactionTypeComboBox() {

        this.transactionTypeComboBox.removeAllItems();
        for(TypeOfTransaction typeOfTransaction : TypeOfTransaction.values())
            this.transactionTypeComboBox.addItem(Transaction.transactionTypeToString(typeOfTransaction));

        this.transactionTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TypeOfTransaction localTransactionType = Transaction.intToTransactionType(transactionTypeComboBox.getSelectedIndex());
                localTransaction.setType(localTransactionType);
                setupAdaptableComboBox();
            }
        });
    }

    private void setupAccountChoiceComboBox() {

        this.accountChoiceComboBox.removeAllItems();
        Vector<Account> accountList = this.globalInfo.getAccounts();
        for (Account account : accountList) {
            this.accountChoiceComboBox.addItem(account.getName());
        }

        this.accountChoiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedAccountIndex = transactionTypeComboBox.getSelectedIndex();
                TypeOfTransaction localTransactionType = localTransaction.getType();
                switch (localTransactionType) {
                    case OUTCOME:
                    case INTERNAL:
                        localTransaction.setOutcomeAccountId(selectedAccountIndex); break;
                    case INCOME:
                        localTransaction.setIncomeAccountId(selectedAccountIndex); break;
                }
            }
        });
    }

    private void setupAdaptableComboBox() {

        ActionListener[] actionListeners = this.adaptableComboBox.getActionListeners();
        for (ActionListener actionListener : actionListeners) {
            this.adaptableComboBox.removeActionListener(actionListener);
        }
        this.adaptableComboBox.removeAllItems();
        switch (localTransaction.getType()){
            case OUTCOME: {

                Vector<Category> outcomeCategories = globalInfo.getOutcomeCategories();
                for (Category category : outcomeCategories) {
                    this.adaptableComboBox.addItem(category.getName());
                }
                this.adaptableComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = adaptableComboBox.getSelectedIndex();
                        int categoryIndex = outcomeCategories.get(selectedIndex).getId();
                        localTransaction.setCategoryId(categoryIndex);
                    }
                });
                break;
            }
            case INCOME: {

                Vector<Category> incomeCategories = globalInfo.getIncomeCategories();
                System.out.println(incomeCategories.size());
                for (Category category : incomeCategories) {
                    this.adaptableComboBox.addItem(category.getName());
                }
                this.adaptableComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = adaptableComboBox.getSelectedIndex();
                        int categoryIndex = incomeCategories.get(selectedIndex).getId();
                        localTransaction.setCategoryId(categoryIndex);
                    }
                });
                break;
            }

            case INTERNAL: {

                Vector<Account> accounts = globalInfo.getAccounts();
                for (Account account : accounts) {
                    this.adaptableComboBox.addItem(account.getName());
                }
                this.adaptableComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = adaptableComboBox.getSelectedIndex();
                        localTransaction.setCategoryId(selectedIndex);
                    }
                });
                break;
            }

        }
    }
}