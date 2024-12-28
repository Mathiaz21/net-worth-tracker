package SecondaryGraphicComponents;

import CommonConstants.ColorConstants;
import CommonConstants.DimensionConstants;
import LogicComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import LogicComponents.TransactionsHandler;
import SecondaryGraphicComponents.QuarterPanels.TransactionModificationPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        super();
        this.transaction = globalInfo.getTransactions().get(itemIndex);
        this.globalInfo = globalInfo;
        this.itemIndex = itemIndex;
        this.parentTab = parentTab;
        this.setupSwing();
        makeSelectable();
    }

    public TransactionGraphicItem(GlobalInfo globalInfo,
                                  int itemIndex,
                                  TransactionListPanel parentTab,
                                  TransactionModificationPanel transactionModificationPanel) {

        this.transaction = globalInfo.getTransactions().get(itemIndex);
        this.globalInfo = globalInfo;
        this.itemIndex = itemIndex;
        this.parentTab = parentTab;
        this.setupSwing();
        makeSelectableAndUpdateModifPanel(transactionModificationPanel);
    }

    public void updateSelectedState() {
        if (this.globalInfo.transactionsHandler.indexIsSelected(this.itemIndex))
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

        String monthString = TransactionsHandler.printReadableDate(this.transaction.getDate());
        this.dateLabel = new JLabel( monthString );
        String amountString = TransactionsHandler.printReadableAmount(this.transaction.getAmountInCents());
        this.amountLabel = new JLabel(amountString);
        this.setTypeLabel();
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
        this.setMinSizes();
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
        this.dateLabel.setPreferredSize(DimensionConstants.littleTransactionLabelSize);
        this.amountLabel.setPreferredSize(DimensionConstants.littleTransactionLabelSize);
        this.typeLabel.setPreferredSize(DimensionConstants.littleTransactionLabelSize);
        this.primaryAccountLabel.setPreferredSize(DimensionConstants.littleTransactionLabelSize);
        this.multifunctionLabel.setPreferredSize(DimensionConstants.littleTransactionLabelSize);
        this.descriptionLabel.setPreferredSize(DimensionConstants.descriptionLabelSize);
    }

    private void setMinSizes() {
        this.dateLabel.setMinimumSize(DimensionConstants.littleTransactionLabelMinSize);
        this.amountLabel.setMinimumSize(DimensionConstants.littleTransactionLabelMinSize);
        this.typeLabel.setMinimumSize(DimensionConstants.littleTransactionLabelMinSize);
        this.primaryAccountLabel.setMinimumSize(DimensionConstants.littleTransactionLabelMinSize);
        this.multifunctionLabel.setMinimumSize(DimensionConstants.littleTransactionLabelMinSize);
        this.descriptionLabel.setMinimumSize(DimensionConstants.descriptionLabelMinSize);
    }

    private void setTypeLabel() {

        this.typeLabel = new JLabel(this.transaction.getType().toString());
        switch (this.transaction.getType()) {
            case OUTCOME:
                this.typeLabel.setForeground(ColorConstants.outcomeColor);
                break;
            case INCOME:
                this.typeLabel.setForeground(ColorConstants.incomeColor);
                break;
            case INTERNAL:
                this.typeLabel.setForeground(Color.gray);
                break;
        }
    }

    private void makeSelectable() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Classic listener triggered");
                if (e.isShiftDown())
                    globalInfo.transactionsHandler.selectRangeOfItems(itemIndex);
                else
                    globalInfo.transactionsHandler.selectOneItem(itemIndex);
                parentTab.updateSelectedStates();
            }
        });
    }

    private void makeSelectableAndUpdateModifPanel(TransactionModificationPanel transactionModificationPanel) {
        this.removeListener();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Modified listener triggered");
                if (e.isShiftDown())
                    globalInfo.transactionsHandler.selectRangeOfItems(itemIndex);
                else
                    globalInfo.transactionsHandler.selectOneItem(itemIndex);
                parentTab.updateSelectedStates();
                transactionModificationPanel.refreshPanel();
            }
        });
    }

    private void removeListener() {
        MouseListener[] mouseListeners = this.getMouseListeners();
        for (MouseListener mouseListener : mouseListeners)
            this.removeMouseListener(mouseListener);
    }
}
