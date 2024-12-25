package MainGraphicComponents;

import CommonConstants.ColorConstants;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XYChart;

import java.awt.*;

public class ChartMethods {

    public static void purifyXChart(XYChart chart) {
        Color transparent = ColorConstants.transparent;
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(transparent);
        chart.getStyler().setPlotBackgroundColor(transparent);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setAxisTickLabelsColor(Color.lightGray);
        chart.getStyler().setAxisTitlesVisible(false);
    }

    public static void purifyCategoryChart(CategoryChart chart) {
        Color transparent = ColorConstants.transparent;

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartBackgroundColor(transparent);
        chart.getStyler().setPlotBackgroundColor(transparent);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        chart.getStyler().setPlotGridLinesStroke(new BasicStroke(1.0f));
        chart.getStyler().setPlotGridLinesColor(Color.GRAY);

        chart.getStyler().setAxisTickLabelsColor(Color.lightGray);
        chart.getStyler().setAxisTitlesVisible(false);
    }
}
