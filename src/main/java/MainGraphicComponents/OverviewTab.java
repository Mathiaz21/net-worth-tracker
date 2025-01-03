package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import SecondaryGraphicComponents.QuarterPanels.BalanceCurvePanel;
import SecondaryGraphicComponents.QuadPanel;
import SecondaryGraphicComponents.QuarterPanels.MonthlyBarsPanel;
import SecondaryGraphicComponents.QuarterPanels.TransactionListQuarterPanel;
import SecondaryGraphicComponents.QuarterPanels.TransactionModificationPanel;
import SecondaryGraphicComponents.TransactionListPanel;

import javax.swing.*;

public class OverviewTab extends JPanel {
    GlobalInfo globalInfo;
    QuadPanel overviewQuadPanel;
    JPanel[] overviewPanels;

    public OverviewTab(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        this.setupOverviewPanels();
        this.overviewQuadPanel = new QuadPanel(overviewPanels);
        this.add(this.overviewQuadPanel);
    }

    private void setupOverviewPanels() {

        TransactionModificationPanel transactionModificationPanel = new TransactionModificationPanel(globalInfo);

        overviewPanels = new JPanel[4];
        overviewPanels[0] = new BalanceCurvePanel(globalInfo);
        overviewPanels[1] = new TransactionListQuarterPanel(globalInfo, transactionModificationPanel);
        overviewPanels[2] = new MonthlyBarsPanel(globalInfo);
        overviewPanels[3] = transactionModificationPanel;
    }
}
