package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * An example of....
 */
public class MultipleAxisDemo2 extends ApplicationFrame {

    /**
     * A demonstration application showing how to create a time series chart
     * with muliple axes.
     *
     * @param title the frame title.
     */
    public MultipleAxisDemo2(String title) {

        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates the demo chart.
     *
     * @return The chart.
     */
    private static JFreeChart createChart() {

        XYDataset dataset1 = createDataset(
                "Series 1", 100.0, new Minute(), 200);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Multiple Axis Demo 2",
                "Time of Day",
                "Primary Range Axis",
                dataset1,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setOrientation(PlotOrientation.VERTICAL);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultPaint(Color.black);

        // DOMAIN AXIS 2
        NumberAxis xAxis2 = new NumberAxis("Domain Axis 2");
        xAxis2.setAutoRangeIncludesZero(false);
        plot.setDomainAxis(1, xAxis2);

        // RANGE AXIS 2
        NumberAxis yAxis2 = new NumberAxis("Range Axis 2");
        plot.setRangeAxis(1, yAxis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);

        XYDataset dataset2 = createDataset(
                "Series 2", 1000.0, new Minute(), 170);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);

        return chart;

    }

    /**
     * Creates a sample dataset.
     *
     * @param name  the dataset name.
     * @param base  the starting value.
     * @param start the starting period.
     * @param count the number of values to generate.
     * @return The dataset.
     */
    private static XYDataset createDataset(String name, double base,
            RegularTimePeriod start, int count) {

        TimeSeries series = new TimeSeries(name);
        RegularTimePeriod period = start;
        double value = base;
        for (int i = 0; i < count; i++) {
            series.add(period, value);
            period = period.next();
            value = value * (1 + (Math.random() - 0.495) / 10.0);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        return dataset;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        MultipleAxisDemo2 demo = new MultipleAxisDemo2("Multiple Axis Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
