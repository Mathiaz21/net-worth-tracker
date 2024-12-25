package SecondaryGraphicComponents.QuarterPanels;

import CommonConstants.ColorConstants;
import LogicComponents.GlobalInfo;
import LogicComponents.PosNegTotals;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;
import java.util.ArrayList;

import static MainGraphicComponents.ChartMethods.purifyCategoryChart;

public class MonthlyBarsPanel extends JPanel {

    GlobalInfo globalInfo;
    PosNegTotals posNegTotals;
    public MonthlyBarsPanel(GlobalInfo globalInfo) {
        this.globalInfo = globalInfo;
        this.posNegTotals = globalInfo.transactionsHandler.deriveMonthlyPosNegTotals(YearMonth.now().minusMonths(11), YearMonth.now());

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
        purifyCategoryChart(chart);
        setChartColors(chart);
        chart.addSeries("Income", this.posNegTotals.labels, this.posNegTotals.positiveTotal);
        chart.addSeries("Outcome", emptyLabels, this.posNegTotals.negativeTotal);
        return chart;
    }

    private static void setChartColors(CategoryChart chart) {
        chart.getStyler().setChartFontColor(Color.lightGray);
        Color[] barColors = new Color[] {ColorConstants.incomeColor, ColorConstants.outcomeColor};
        chart.getStyler().setSeriesColors(barColors);
    }
}
