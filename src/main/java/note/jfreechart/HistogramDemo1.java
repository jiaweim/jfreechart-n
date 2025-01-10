package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;

import javax.swing.*;
import java.util.Random;

/**
 * <b>HistogramDemo1</b>
 * <p>
 * A simple histogram illustrating the use of the
 * <code>HistogramDataset</code> class.  Mouse-wheel zooming
 * has been enabled for this chart, as well as panning (via CTRL-mouse-drag).
 *
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 29 Apr 2024, 7:27 PM
 */
public class HistogramDemo1 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public HistogramDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static IntervalXYDataset createDataset() {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = new double[1000];
        Random random = new Random(12345678L);
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 5;
        }
        dataset.addSeries("H1", values, 100, 2.0, 8.0);

        values = new double[1000];
        for (int i = 0; i < 1000; i++) {
            values[i] = random.nextGaussian() + 7;
        }
        dataset.addSeries("H2", values, 100, 4.0, 10.0);
        return dataset;
    }


    /**
     * Creates a chart.
     *
     * @param dataset a dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogram Demo",
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setForegroundAlpha(0.85f);
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
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

    public static void main(String[] args) {
        HistogramDemo1 demo = new HistogramDemo1("Histogram Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
