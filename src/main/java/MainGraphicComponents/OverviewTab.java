package MainGraphicComponents;

import LogicComponents.GlobalInfo;
import SecondaryGraphicComponents.QuarterPanels.BalanceCurvePanel;
import SecondaryGraphicComponents.QuadPanel;
import SecondaryGraphicComponents.QuarterPanels.MonthlyBarsPanel;
import SecondaryGraphicComponents.QuarterPanels.TransactionModificationPanel;

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

        overviewPanels = new JPanel[4];
        overviewPanels[0] = new BalanceCurvePanel(globalInfo);
        overviewPanels[1] = new TransactionListTab(globalInfo);
        overviewPanels[2] = new MonthlyBarsPanel(globalInfo);
        overviewPanels[3] = new TransactionModificationPanel();
    }
}
