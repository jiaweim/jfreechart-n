package note.jfreechart;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

public class YIntervalChartDemo2 extends ApplicationFrame {
    public YIntervalChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static void add(YIntervalSeries s, int y, int m, int d, double v, double std) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m, d);
        s.add((double)cal.getTime().getTime(), v, v - std, v + std);
    }

    private static IntervalXYDataset createDataset() {
        YIntervalSeries s1 = new YIntervalSeries("Series 1");
        add(s1, 2005, 4, 16, (double)1309.0F, (double)13.0F);
        add(s1, 2005, 9, 18, (double)1312.0F, (double)12.0F);
        add(s1, 2005, 10, 7, (double)1309.0F, (double)12.0F);
        add(s1, 2006, 0, 12, (double)1311.0F, (double)12.0F);
        add(s1, 2006, 1, 7, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 3, 3, (double)1309.0F, (double)13.0F);
        add(s1, 2006, 3, 4, (double)1307.0F, (double)13.0F);
        add(s1, 2006, 3, 6, (double)1305.0F, (double)13.0F);
        add(s1, 2006, 3, 13, (double)1303.0F, (double)13.0F);
        add(s1, 2006, 3, 25, (double)1308.0F, (double)13.0F);
        add(s1, 2006, 3, 28, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 4, 2, (double)1306.0F, (double)13.0F);
        add(s1, 2006, 4, 15, (double)1303.0F, (double)13.0F);
        add(s1, 2006, 4, 18, (double)1311.0F, (double)13.0F);
        add(s1, 2006, 10, 16, (double)1301.0F, (double)13.0F);
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("YIntervalChartDemo2", "Date", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainAxis(new DateAxis("Date"));
        NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(false);
        XYErrorRenderer renderer = new XYErrorRenderer();
        renderer.setDefaultLinesVisible(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        plot.setRenderer(renderer);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        YIntervalChartDemo2 demo = new YIntervalChartDemo2("JFreeChart: YIntervalChartDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
