package note.jfreechart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.compass.CompassPlot;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration application showing how to...
 */
public class CompassDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo application.
     *
     * @param title the frame title.
     */
    public CompassDemo1(String title) {

        super(title);

        ValueDataset dataset = new DefaultValueDataset(new Double(45.0));
        JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(ValueDataset dataset) {

        CompassPlot plot = new CompassPlot(dataset);
        plot.setSeriesNeedle(7);
        plot.setSeriesPaint(0, Color.black);
        plot.setSeriesOutlinePaint(0, Color.black);
        plot.setRosePaint(Color.red);
        plot.setRoseHighlightPaint(Color.gray);
        plot.setRoseCenterPaint(Color.white);
        plot.setDrawBorder(false);
        JFreeChart chart = new JFreeChart(plot);
        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(new DefaultValueDataset(new Double(45.0)));
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        CompassDemo1 demo = new CompassDemo1("Compass Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
