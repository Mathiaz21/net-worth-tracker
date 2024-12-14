package SecondaryGraphicComponents;

import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import MainGraphicComponents.TransactionListTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransactionGraphicItem extends JPanel {

    GlobalInfo globalInfo;
    Transaction transaction;
    int itemIndex;
    TransactionListTab parentTab;

    GridBagLayout layout;
    GridBagConstraints constraints;

    Color selectedBackground = new Color(100, 160, 240, 64);

    JLabel dateLabel;
    JLabel amountLabel;
    JLabel typeLabel;
    JLabel primaryAccountLabel;
    JLabel multifunctionLabel;
    JLabel descriptionLabel;

    public TransactionGraphicItem(GlobalInfo globalInfo, int itemIndex, TransactionListTab parentTab) {
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
        this.setLabels();
        this.setupGridBag();
        this.placeLabels();
    }

    private void setupGridBag() {
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);

        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.insets = new Insets(0, 15, 0, 15);
    }

    private void setLabels() {

        this.dateLabel = new JLabel(this.transaction.getDate().toString());
        this.amountLabel = new JLabel(this.transaction.getAmountInCents() + "");
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
