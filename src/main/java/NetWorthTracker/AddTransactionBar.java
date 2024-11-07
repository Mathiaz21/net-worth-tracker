package NetWorthTracker;

import DBConnection.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AddTransactionBar extends JPanel {

    private JTextField amountField;
    private JTextField dateField;
    private JComboBox transactionTypeComboBox;
    private JComboBox accountChoiceComboBox;
    private JComboBox adaptableComboBox;
    private JTextField descriptionField;
    private JButton submitButton;

    GridBagLayout gridBagLayout;
    GridBagConstraints constraints;

    public AddTransactionBar() {

        this.setupGridBag();
        this.setupSwingComponents();
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

    private void setupSwingComponents() {

        this.amountField = new JTextField("");
        this.dateField = new JTextField(getTodaysDate());
        String[] transactionTypeList = {"Outcome", "Income", "Internal"};
        this.transactionTypeComboBox = new JComboBox(transactionTypeList);
        String[] accountList = {"Lydia", "Danske Bank", "Crédit Agricole"};
        this.accountChoiceComboBox = new JComboBox(accountList);
        Vector<String> categoryList = DBUtils.getOutcomeCategories();
        this.adaptableComboBox = new JComboBox(categoryList);
        this.descriptionField = new JTextField("Buying a Banana");
        this.submitButton = new JButton("Submit");

        this.add(this.amountField, this.constraints);
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

    private static String getTodaysDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }
}
