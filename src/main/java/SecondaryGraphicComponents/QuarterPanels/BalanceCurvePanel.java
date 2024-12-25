package SecondaryGraphicComponents.QuarterPanels;

import CommonConstants.ColorConstants;
import LogicComponents.GlobalInfo;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.QuickChart;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


import static MainGraphicComponents.ChartMethods.purifyXChart;

public class BalanceCurvePanel extends JPanel {

    ArrayList<Integer> last90DaysBalanceCents;
    double[] last90DaysBalanceEuros;
    double[] range90;
    GlobalInfo globalInfo;

    public BalanceCurvePanel(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        last90DaysBalanceCents = this.globalInfo.transactionsHandler.getDailyBalancesBetweenDates(tomorrow.minusDays(90), tomorrow, globalInfo);
        range90 = new double[90];
        last90DaysBalanceEuros = new double[90];
        for(int i = 0; i < 90; i++) {
            range90[i] = (double) i;
            last90DaysBalanceEuros[i] = (double) last90DaysBalanceCents.get(i);
            last90DaysBalanceEuros[i] /= 100;
        }
        String chartTitle = "Current Net Worth : " + last90DaysBalanceCents.get(89)/100 + " €";
        XYChart balanceCurveChart = QuickChart.getChart(chartTitle, "Amount (EUR)", "Days", "Amount", range90, last90DaysBalanceEuros);
        purifyXChart(balanceCurveChart);
        boolean isIncreasing = last90DaysBalanceCents.get(89) > last90DaysBalanceCents.get(0);
        setChartColors(balanceCurveChart, isIncreasing);
        JPanel chartPanel = new XChartPanel<>(balanceCurveChart);
        this.add(chartPanel);
    }

    private static void setChartColors(XYChart chart, boolean isIncreasing) {
        chart.getStyler().setChartFontColor(Color.lightGray);
        if (isIncreasing)
            chart.getStyler().setSeriesColors(new Color[]{ColorConstants.incomeColor});
        else
            chart.getStyler().setSeriesColors(new Color[]{ColorConstants.outcomeColor});
    }

}