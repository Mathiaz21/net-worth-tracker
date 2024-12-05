package GraphicComponents;

import FunctionalComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import javax.swing.*;
import java.awt.*;

public class TransactionGraphicItem extends JPanel {

    String[] temporaryAccountList = {"Sumeria", "Danske Bank", "Caisse d'Epargne", "Bahamas Offshore"};
    String[] temporaryCategoryList = {"Food", "Leisure", "Transport", "Skiing", "Buying shoes"};

    GlobalInfo globalInfo;

    Transaction transaction;

    GridBagLayout layout;
    GridBagConstraints constraints;

    JLabel dateLabel;
    JLabel amountLabel;
    JLabel typeLabel;
    JLabel primaryAccountLabel;
    JLabel multifunctionLabel;
    JLabel descriptionLabel;

    public TransactionGraphicItem(Transaction transaction, GlobalInfo globalInfo) {
        this.transaction = transaction;
        this.globalInfo = globalInfo;
        this.setupSwing();

    }

    private void setupSwing() {
//        this.setBackground(Color.WHITE);
        this.setLabels();
//        this.setLabelsSize();
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
        final int accountId = this.transaction.getOutcomeAccountId();
        this.primaryAccountLabel = new JLabel( globalInfo.AccountIndexToName(accountId) );

        switch (this.transaction.getType()) {
            case OUTCOME:
            case INCOME:
                final int categoryId = this.transaction.getCategoryId();
                this.multifunctionLabel = new JLabel( globalInfo.categoryIndexToName(categoryId) );
                break;
            case INTERNAL:
                final int accountId2 = this.transaction.getIncomeAccountId();
                this.multifunctionLabel = new JLabel(globalInfo.categoryIndexToName(accountId2));
                break;
        }
    }


    private void setLabelsSize() {
        this.dateLabel.setSize(100, 15);
        this.amountLabel.setSize(100, 15);
        this.typeLabel.setSize(100, 15);
        this.primaryAccountLabel.setSize(100, 15);
        this.multifunctionLabel.setSize(100, 15);
        this.descriptionLabel.setSize(200, 15);
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
}
