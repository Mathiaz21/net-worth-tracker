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

    private void initLabels() {
        amountLabel = new JLabel("Amount : ");
        dateLabel = new JLabel("Date : ");
        typeLabel = new JLabel("Type : ");
        primaryAccountLabel = new JLabel("Primary Account : ");
        multifunctionLabel = new JLabel("Multifunction : ");
        descriptionLabel = new JLabel("Description : ");
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

        this.add(this.amountLabel, GBC);

        this.GBC.gridx=1;
        this.add(this.amountField, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=1;
        this.add(this.dateLabel, GBC);
        this.GBC.gridx=1;
        this.add(this.dateField, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=2;
        this.add(this.typeLabel, GBC);
        this.GBC.gridx=1;
        this.add(this.typeComboBox, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=3;
        this.add(this.primaryAccountLabel, GBC);
        this.GBC.gridx=1;
        this.add(this.primaryAccountComboBox, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=4;
        this.add(this.multifunctionLabel, GBC);
        this.GBC.gridx=1;
        this.add(this.multifunctionComboBox, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=5;
        this.add(this.descriptionLabel, GBC);
        this.GBC.gridx=1;
        this.add(this.descriptionField, GBC);

        this.GBC.gridx=0;
        this.GBC.gridy=6;
        this.GBC.gridwidth=2;
        this.add(this.submitButton, GBC);


    }
}
