package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration application showing an {@link XYBarRenderer}
 * plotting data from a {@link DefaultIntervalXYDataset}.
 */
public class XYBarChartDemo6 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title the frame title.
     */
    public XYBarChartDemo6(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                "XYBarChartDemo6",
                "X",
                false,
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setUseYInterval(true);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        return chart;
    }

    /**
     * Creates a sample dataset.
     */
    private static IntervalXYDataset createDataset() {
        DefaultIntervalXYDataset dataset = new DefaultIntervalXYDataset();
        double[] x = {1.0, 2.0, 3.0, 4.0};
        double[] startx = {0.9, 1.8, 2.7, 3.6};
        double[] endx = {1.1, 2.2, 3.3, 4.4};
        double[] y = {1.0, 2.0, 3.0, 4.0};
        double[] starty = {0.9, 1.8, 2.7, 3.6};
        double[] endy = {1.1, 2.2, 3.3, 4.4};
        double[][] data = new double[][]{x, startx, endx, y, starty, endy};
        dataset.addSeries("Series 1", data);
        return dataset;
    }

    /**
     * Creates a panel for the demo.
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()));
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        XYBarChartDemo6 demo = new XYBarChartDemo6("JFreeChart : XYBarChartDemo6");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
