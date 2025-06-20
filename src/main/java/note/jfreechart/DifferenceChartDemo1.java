package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration of the {@link XYDifferenceRenderer}.
 */
public class DifferenceChartDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public DifferenceChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        TimeSeries series1 = new TimeSeries("Random 1");
        TimeSeries series2 = new TimeSeries("Random 2");
        double value1 = 0.0;
        double value2 = 0.0;
        Day day = new Day();
        for (int i = 0; i < 200; i++) {
            value1 = value1 + Math.random() - 0.5;
            value2 = value2 + Math.random() - 0.5;
            series1.add(day, value1);
            series2.add(day, value2);
            day = (Day) day.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Difference Chart Demo 1",
                "Time", "Value",
                dataset,
                true,  // legend
                true,  // tool tips
                false  // URLs
        );
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYDifferenceRenderer r = new XYDifferenceRenderer(Color.green,
                Color.red, false);
        r.setRoundXCoordinates(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairLockedOnData(true);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRenderer(r);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

        ValueAxis domainAxis = new DateAxis("Time");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) {
        DifferenceChartDemo1 demo = new DifferenceChartDemo1(
                "Difference Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
