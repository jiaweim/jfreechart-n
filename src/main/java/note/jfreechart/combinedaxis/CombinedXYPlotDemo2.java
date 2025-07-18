package note.jfreechart.combinedaxis;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.date.MonthConstants;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A demo showing two plots that share a common range axis.
 * @since 2025-06-18⭐
 */
public class CombinedXYPlotDemo2 extends ApplicationFrame {

    public CombinedXYPlotDemo2(String title) {
        super(title);
        JFreeChart chart = createCombinedChart();
        ChartPanel panel = new ChartPanel(chart, true, true, true, true, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    private static JFreeChart createCombinedChart() {

        // create subplot 1...
        IntervalXYDataset data1 = createDataset1();
        XYBarRenderer renderer1 = new XYBarRenderer(0.20);
        renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0,000.0")));
        XYPlot subplot1 = new XYPlot(data1, new DateAxis("Date"), null, renderer1);

        // create subplot 2...
        XYDataset data2 = createDataset2();
        StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy"),
                new DecimalFormat("0,000.0")));
        XYPlot subplot2 = new XYPlot(data2, new DateAxis("Date"), null, renderer2);

        // create a parent plot...
        NumberAxis sharedAxis = new NumberAxis("Value");
        sharedAxis.setTickMarkInsideLength(3.0f);

        CombinedRangeXYPlot plot = new CombinedRangeXYPlot(sharedAxis);
        plot.add(subplot1, 1);
        plot.add(subplot2, 1);

        // return a new chart containing the overlaid plot...
        return new JFreeChart("Combined (Range) XY Plot",
                JFreeChart.DEFAULT_TITLE_FONT, plot, true);
    }

    private static IntervalXYDataset createDataset1() {
        TimeSeries series1 = new TimeSeries("Series 1");
        series1.add(new Day(1, MonthConstants.MARCH, 2002), 12353.3);
        series1.add(new Day(2, MonthConstants.MARCH, 2002), 13734.4);
        series1.add(new Day(3, MonthConstants.MARCH, 2002), 14525.3);
        series1.add(new Day(4, MonthConstants.MARCH, 2002), 13984.3);
        series1.add(new Day(5, MonthConstants.MARCH, 2002), 12999.4);
        series1.add(new Day(6, MonthConstants.MARCH, 2002), 14274.3);
        series1.add(new Day(7, MonthConstants.MARCH, 2002), 15943.5);
        series1.add(new Day(8, MonthConstants.MARCH, 2002), 14845.3);
        series1.add(new Day(9, MonthConstants.MARCH, 2002), 14645.4);
        series1.add(new Day(10, MonthConstants.MARCH, 2002), 16234.6);
        series1.add(new Day(11, MonthConstants.MARCH, 2002), 17232.3);
        series1.add(new Day(12, MonthConstants.MARCH, 2002), 14232.2);
        series1.add(new Day(13, MonthConstants.MARCH, 2002), 13102.2);
        series1.add(new Day(14, MonthConstants.MARCH, 2002), 14230.2);
        series1.add(new Day(15, MonthConstants.MARCH, 2002), 11235.2);

        return new TimeSeriesCollection(series1);
    }

    private static XYDataset createDataset2() {
        TimeSeries series2 = new TimeSeries("Series 2");

        series2.add(new Day(3, MonthConstants.MARCH, 2002), 6853.2);
        series2.add(new Day(4, MonthConstants.MARCH, 2002), 9642.3);
        series2.add(new Day(5, MonthConstants.MARCH, 2002), 8253.5);
        series2.add(new Day(6, MonthConstants.MARCH, 2002), 5352.3);
        series2.add(new Day(7, MonthConstants.MARCH, 2002), 3532.0);
        series2.add(new Day(8, MonthConstants.MARCH, 2002), 2635.3);
        series2.add(new Day(9, MonthConstants.MARCH, 2002), 3998.2);
        series2.add(new Day(10, MonthConstants.MARCH, 2002), 1943.2);
        series2.add(new Day(11, MonthConstants.MARCH, 2002), 6943.9);
        series2.add(new Day(12, MonthConstants.MARCH, 2002), 7843.2);
        series2.add(new Day(13, MonthConstants.MARCH, 2002), 6495.3);
        series2.add(new Day(14, MonthConstants.MARCH, 2002), 7943.6);
        series2.add(new Day(15, MonthConstants.MARCH, 2002), 8500.7);
        series2.add(new Day(16, MonthConstants.MARCH, 2002), 9595.9);

        return new TimeSeriesCollection(series2);
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createCombinedChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        CombinedXYPlotDemo2 demo = new CombinedXYPlotDemo2("Combined XY Plot Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
