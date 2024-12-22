package SecondaryGraphicComponents;

import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionGraphicItem extends JPanel {

    GlobalInfo globalInfo;
    Transaction transaction;
    int itemIndex;
    TransactionListPanel parentTab;

    GridBagLayout layout;
    GridBagConstraints constraints;

    Color selectedBackground = new Color(100, 160, 240, 64);

    JLabel dateLabel;
    JLabel amountLabel;
    JLabel typeLabel;
    JLabel primaryAccountLabel;
    JLabel multifunctionLabel;
    JLabel descriptionLabel;

    public TransactionGraphicItem(GlobalInfo globalInfo, int itemIndex, TransactionListPanel parentTab) {
        this.transaction = globalInfo.getTransactions().get(itemIndex);
        this.globalInfo = globalInfo;
        this.itemIndex = itemIndex;
        this.parentTab = parentTab;
        this.setupSwing();
        makeSelectable();
    }

    public void updateSelectedState() {
        if (this.globalInfo.indexIsSelected(this.itemIndex))
            this.setBackground(this.selectedBackground);
        else
            this.setBackground(null);
        this.revalidate();
    }


    private void setupSwing() {
        this.setPreferredSize(new Dimension(100, 50));
        this.setLabels();
        this.setupGridBag();
        this.placeLabels();
    }

    private void setupGridBag() {
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);

        this.constraints = new GridBagConstraints();
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
    }

    private void setLabels() {

        this.dateLabel = new JLabel(this.transaction.getDate().toString());
        String amountString = printReadableAmount(this.transaction.getAmountInCents());
        this.amountLabel = new JLabel(amountString);
        this.typeLabel = new JLabel(this.transaction.getType().toString());
        this.descriptionLabel = new JLabel(this.transaction.getDescription());
        int accountId = 0;
        int categoryId;

        switch (this.transaction.getType()) {
            case OUTCOME:
                accountId = this.transaction.getOutcomeAccountId();
                categoryId = this.transaction.getCategoryId();
                this.multifunctionLabel = new JLabel( globalInfo.categoryIndexToName(categoryId) );
                break;
            case INCOME:
                accountId = this.transaction.getIncomeAccountId();
                categoryId = this.transaction.getCategoryId();
                this.multifunctionLabel = new JLabel( globalInfo.categoryIndexToName(categoryId) );
                break;
            case INTERNAL:
                accountId = this.transaction.getOutcomeAccountId();
                final int accountId2 = this.transaction.getIncomeAccountId();
                this.multifunctionLabel = new JLabel(globalInfo.accountIndexToName(accountId2));
                break;
        }

        this.primaryAccountLabel = new JLabel( globalInfo.accountIndexToName(accountId) );
        this.setPrefSizes();
    }


    private void placeLabels() {
        this.add(dateLabel, this.constraints);
        this.constraints.gridx = 1;
        this.add(amountLabel, this.constraints);
        this.constraints.gridx = 2;
        this.add(typeLabel, this.constraints);
        this.constraints.gridx = 3;
        this.add(primaryAccountLabel, this.constraints);
        this.constraints.gridx = 4;
        this.add(multifunctionLabel, this.constraints);
        this.constraints.gridx = 5;
        this.add(descriptionLabel, this.constraints);
    }

    private void setPrefSizes() {
        Dimension littleLabelDimension = new Dimension(150,30);
        Dimension descriptionLabelDimension = new Dimension(200,30);
        this.dateLabel.setPreferredSize(littleLabelDimension);
        this.amountLabel.setPreferredSize(littleLabelDimension);
        this.typeLabel.setPreferredSize(littleLabelDimension);
        this.primaryAccountLabel.setPreferredSize(littleLabelDimension);
        this.multifunctionLabel.setPreferredSize(littleLabelDimension);
        this.descriptionLabel.setPreferredSize(descriptionLabelDimension);
    }

    private String printReadableAmount(int amountInCents) {
        return amountInCents/100 + "." + amountInCents%100;
    }

    private void makeSelectable() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isShiftDown())
                    globalInfo.selectRangeOfItems(itemIndex);
                else
                    globalInfo.selectOneItem(itemIndex);
                parentTab.updateSelectedStates();
            }
        });

    }

}
