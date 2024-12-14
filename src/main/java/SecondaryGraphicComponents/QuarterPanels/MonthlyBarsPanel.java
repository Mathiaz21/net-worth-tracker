package SecondaryGraphicComponents.QuarterPanels;

import LogicComponents.GlobalInfo;
import LogicComponents.PosNegTotals;
import LogicComponents.TransactionsLogic;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class MonthlyBarsPanel extends JPanel {

    GlobalInfo globalInfo;
    TransactionsLogic transactionsLogic;
    PosNegTotals posNegTotals;
    public MonthlyBarsPanel(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        this.transactionsLogic = new TransactionsLogic(this.globalInfo.getTransactions());
        this.posNegTotals = transactionsLogic.deriveMonthlyPosNegTotals(YearMonth.now().minusMonths(11), YearMonth.now());

        CategoryChart monthlyBarChart = getChart();
        JPanel histogramPanel = new XChartPanel(monthlyBarChart);
        this.add(histogramPanel);
    }

    private CategoryChart getChart() {

        CategoryChart chart = new CategoryChartBuilder().width(600).height(400).title("Monthly Globals").xAxisTitle("Month").yAxisTitle("Amount").build();
        ArrayList<String> emptyLabels = new ArrayList<>();
        for(int i = 0; i  <this.posNegTotals.labels.size(); i++)
            emptyLabels.add("");
        chart.getStyler().setOverlapped(true);
        chart.addSeries("Income", this.posNegTotals.labels, this.posNegTotals.positiveTotal);
        chart.addSeries("Outcome", emptyLabels, this.posNegTotals.negativeTotal);
        return chart;
    }
}
