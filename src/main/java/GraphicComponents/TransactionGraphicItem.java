package GraphicComponents;

import FunctionalComponents.GlobalInfo;
import FunctionalComponents.Transaction;
import javax.swing.*;
import java.awt.*;

public class TransactionGraphicItem extends JPanel {

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
        final int accountId = this.transaction.getOutcomeAccountId();
        this.primaryAccountLabel = new JLabel( globalInfo.accountIndexToName(accountId) );

        switch (this.transaction.getType()) {
            case OUTCOME:
            case INCOME:
                final int categoryId = this.transaction.getCategoryId();
                this.multifunctionLabel = new JLabel( globalInfo.categoryIndexToName(categoryId) );
                break;
            case INTERNAL:
                final int accountId2 = this.transaction.getIncomeAccountId();
                this.multifunctionLabel = new JLabel(globalInfo.accountIndexToName(accountId2));
                break;
        }
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
