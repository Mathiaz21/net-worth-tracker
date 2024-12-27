package SecondaryGraphicComponents.QuarterPanels;

import FunctionalComponents.Transaction;
import LogicComponents.GlobalInfo;

import javax.swing.*;
import java.awt.*;

public class TransactionModificationPanel extends JPanel {

    GlobalInfo globalInfo;
    GridBagLayout GBL;
    GridBagConstraints GBC;

    JLabel amountLabel;
    JLabel dateLabel;
    JLabel typeLabel;
    JLabel primaryAccountLabel;
    JLabel multifunctionLabel;
    JLabel descriptionLabel;

    JTextField amountField;
    JTextField dateField;
    JComboBox<String> typeComboBox;
    JComboBox<String> primaryAccountComboBox;
    JComboBox<String> multifunctionComboBox;
    JTextField descriptionField;
    JButton submitButton;


    public TransactionModificationPanel(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        initLabels();
        initInputs();
        addComponents();
    }

    public void refreshFields() {
        try {
            Transaction selectedTransaction = this.getSelectedTransaction();
            this.amountField.setText(selectedTransaction.getAmountInCents() + ""); // TODO : finish the handling of field updates
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initLabels() {
        amountLabel = new JLabel("Amount");
        dateLabel = new JLabel("Date");
        typeLabel = new JLabel("Type");
        primaryAccountLabel = new JLabel("Primary Account");
        multifunctionLabel = new JLabel("Multifunction");
        descriptionLabel = new JLabel("Description");
    }

    private void initInputs() {
        amountField = new JTextField();
        dateField = new JTextField();
        typeComboBox = new JComboBox<>();
        primaryAccountComboBox = new JComboBox<>();
        multifunctionComboBox = new JComboBox<>();
        descriptionField = new JTextField();
        submitButton = new JButton("Submit");
    }

    private Transaction getSelectedTransaction() throws Exception {
        int minIndex = globalInfo.transactionsHandler.getSelectedMinIndex();
        int maxIndex = globalInfo.transactionsHandler.getSelectedMaxIndex();
        if (minIndex == maxIndex)
            return globalInfo.transactionsHandler.listOfTransactions.get(minIndex);
        else
            throw new Exception("Several transactions selected");
    }




    private void addComponents() {
        GBL = new GridBagLayout();
        GBC = new GridBagConstraints();
        this.setLayout(GBL);

        GBC.anchor = GridBagConstraints.EAST;
        GBC.ipady = 10;
        GBC.insets = new Insets(8, 10, 8, 8);
        this.add(this.amountLabel, GBC);
        this.GBC.gridy=1;
        this.add(this.dateLabel, GBC);
        this.GBC.gridy=2;
        this.add(this.typeLabel, GBC);
        this.GBC.gridy=3;
        this.add(this.primaryAccountLabel, GBC);
        this.GBC.gridy=4;
        this.add(this.multifunctionLabel, GBC);
        this.GBC.gridx=2;
        this.GBC.gridy=0;
        this.add(this.descriptionLabel, GBC);
        this.GBC.gridy=3;
        this.GBC.gridwidth=2;
        GBC.gridheight=2;
        this.GBC.anchor = GridBagConstraints.CENTER;
        GBC.ipadx = 20;
        GBC.ipady = 30;
        this.add(this.submitButton, GBC);

        GBC.gridx=1;
        GBC.gridy=0;
        GBC.gridwidth=1;
        GBC.gridheight=1;
        GBC.anchor = GridBagConstraints.WEST;
        GBC.ipadx=0;
        GBC.ipady=10;
        this.add(this.amountField, GBC);
        GBC.gridy=1;
        this.add(this.dateField, GBC);
        GBC.gridy=2;
        this.add(this.typeComboBox, GBC);
        GBC.gridy=3;
        this.add(this.primaryAccountComboBox, GBC);
        GBC.gridy=4;
        this.add(this.multifunctionComboBox, GBC);
        GBC.gridx=3;
        GBC.gridy=0;
        this.add(this.descriptionField, GBC);
    }
}
