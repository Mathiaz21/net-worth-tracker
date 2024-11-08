package GraphicComponents;

import FunctionalComponents.Transaction;
import javax.swing.*;
import java.awt.*;

public class TransactionGraphicItem extends JPanel {

    String[] temporaryAccountList = {"Sumeria", "Danske Bank", "Caisse d'Epargne", "Bahamas Offshore"};
    String[] temporaryCategoryList = {"Food", "Leisure", "Transport", "Skiing", "Buying shoes"};

    Transaction transaction;

    GridBagLayout layout;
    GridBagConstraints constraints;

    JLabel dateLabel;
    JLabel amountLabel;
    JLabel typeLabel;
    JLabel primaryAccountLabel;
    JLabel multifunctionLabel;
    JLabel descriptionLabel;

    public TransactionGraphicItem(Transaction transaction) {
        this.transaction = transaction;
        this.setLabels();
        this.setLabelsSize();
        this.setupGridBag();
        this.placeLabels();

    }

    private void setupGridBag() {
        this.layout = new GridBagLayout();
        this.setLayout(this.layout);

        this.constraints = new GridBagConstraints();
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.insets = new Insets(10, 15, 10, 15);
    }

    private void setLabels() {
        this.dateLabel = new JLabel(this.transaction.getDate().toString());
        this.amountLabel = new JLabel(this.transaction.getAmountInCents() + "");
        this.typeLabel = new JLabel(this.transaction.getType().toString());
        this.descriptionLabel = new JLabel(this.transaction.getDescription());
        switch (this.transaction.getType()) {
            case OUTCOME:
                final int outcomeAccountId = this.transaction.getOutcomeAccountId();
                final int outCategoryId = this.transaction.getCategoryId();
                this.primaryAccountLabel = new JLabel(temporaryAccountList[outcomeAccountId]);
                this.multifunctionLabel = new JLabel(temporaryCategoryList[outCategoryId]);
            case INCOME:
                final int incomeAccountId = this.transaction.getIncomeAccountId();
                final int inCategoryId = this.transaction.getCategoryId();
                this.primaryAccountLabel = new JLabel(temporaryAccountList[incomeAccountId]);
                this.multifunctionLabel = new JLabel(temporaryCategoryList[inCategoryId]);
            case INTERNAL:
                final int outcomeAccountId2 = this.transaction.getOutcomeAccountId();
                final int incomeAccountId2 = this.transaction.getIncomeAccountId();
                this.primaryAccountLabel = new JLabel(temporaryAccountList[outcomeAccountId2]);
                this.multifunctionLabel = new JLabel(temporaryAccountList[incomeAccountId2]);
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
