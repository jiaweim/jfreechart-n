package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

/**
 * A simple demo showing the use of the {@link LogarithmicAxis} class
 * with negative values.
 */
public class LogarithmicAxisDemo2 extends ApplicationFrame {

    /**
     * Creates a new instance of the demo.
     *
     * @param title the frame title.
     */
    public LogarithmicAxisDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Logarithmic Axis Demo 2",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        LogarithmicAxis xAxis = new LogarithmicAxis("X");
        //xAxis.setAllowNegativesFlag(true);
        xAxis.setExpTickLabelsFlag(true);
        xAxis.setStrictValuesFlag(false);
        LogarithmicAxis yAxis = new LogarithmicAxis("Y");
        yAxis.setAllowNegativesFlag(true);
        yAxis.setLog10TickLabelsFlag(true);
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        return chart;
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Series 1");
        //series.add(-50000.0, -50000.0);
        //series.add(-5000.0, -5000.0);
        series.add(-500.0, -500.0);
        series.add(-50.0, -50.0);
        series.add(-5.0, -5.0);
        series.add(0.0, 0.0);
        series.add(5.0, 5.0);
        series.add(50.0, 50.0);
        series.add(500.0, 500.0);
        //series.add(5000.0, 5000.0);
        //series.add(50000.0, 50000.0);
        return new XYSeriesCollection(series);
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
    public static void main(String[] args) {
        LogarithmicAxisDemo2 demo = new LogarithmicAxisDemo2(
                "Logarithmic Axis Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
