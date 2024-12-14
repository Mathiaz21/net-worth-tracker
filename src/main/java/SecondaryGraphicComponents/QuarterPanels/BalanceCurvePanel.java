package SecondaryGraphicComponents.QuarterPanels;

import LogicComponents.GlobalInfo;
import LogicComponents.TransactionsLogic;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.QuickChart;


import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BalanceCurvePanel extends JPanel {

    ArrayList<Integer> last90DaysBalanceCents;
    double[] last90DaysBalanceEuros;
    double[] range90;
    TransactionsLogic amountMath;

    public BalanceCurvePanel(GlobalInfo globalInfo) {

        this.amountMath = new TransactionsLogic(globalInfo.getTransactions());
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        last90DaysBalanceCents = amountMath.getDailyBalancesBetweenDates(tomorrow.minusDays(90), tomorrow, globalInfo);
        range90 = new double[90];
        last90DaysBalanceEuros = new double[90];
        for(int i = 0; i < 90; i++) {
            range90[i] = (double) i;
            last90DaysBalanceEuros[i] = (double) last90DaysBalanceCents.get(i);
            last90DaysBalanceEuros[i] /= 100;
        }
        XYChart balanceCurveChart = QuickChart.getChart("Global Balance", "Amount (EUR)", "Days", "Amount", range90, last90DaysBalanceEuros);
        JPanel chartPanel = new XChartPanel<>(balanceCurveChart);
        this.add(chartPanel);
    }
}