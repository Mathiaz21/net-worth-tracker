package MainGraphicComponents;

import SecondaryGraphicComponents.QuarterPanels.BalanceCurvePanel;
import SecondaryGraphicComponents.QuadPanel;
import SecondaryGraphicComponents.QuarterPanels.MonthlyBarsPanel;
import SecondaryGraphicComponents.QuarterPanels.TransactionListQuarter;
import SecondaryGraphicComponents.QuarterPanels.TransactionModificationPanel;

import javax.swing.*;

public class OverviewTab extends JPanel {
    QuadPanel overviewQuadPanel;
    JPanel[] overviewPanels;

    public OverviewTab() {
        this.setupOverviewPanels();
        this.overviewQuadPanel = new QuadPanel(overviewPanels);
    }

    private void setupOverviewPanels() {

        overviewPanels = new JPanel[4];
        overviewPanels[0] = new BalanceCurvePanel();
        overviewPanels[1] = new TransactionListQuarter();
        overviewPanels[2] = new MonthlyBarsPanel();
        overviewPanels[3] = new TransactionModificationPanel();
    }
}
