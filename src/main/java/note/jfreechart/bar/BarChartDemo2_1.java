package note.jfreechart.bar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration application showing how to create a horizontal bar
 * chart.
 */
public class BarChartDemo2_1 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public BarChartDemo2_1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private static CategoryDataset createDataset() {
        double[][] data = new double[][]{
                {1.0, 43.0, 35.0, 58.0, 54.0, 77.0, 71.0, 89.0},
                {54.0, 75.0, 63.0, 83.0, 43.0, 46.0, 27.0, 13.0},
                {41.0, 33.0, 22.0, 34.0, 62.0, 32.0, 42.0, 34.0}
        };
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int r = 0; r < data.length; r++) {
            String rowKey = "Series " + (r + 1);
            for (int c = 0; c < data[r].length; c++) {
                String columnKey = "Factor" + (c + 1);
                dataset.addValue(data[r][c], rowKey, columnKey);
            }
        }

        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return A chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Demo 2",         // chart title
                "Category",                 // domain axis label
                "Score (%)",                // range axis label
                dataset,                    // data
                PlotOrientation.HORIZONTAL, // orientation
                true,                       // include legend
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setLegendItemToolTipGenerator(
                new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        // OPTIONAL CUSTOMISATION COMPLETED.

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
    public static void main(String[] args) {
        BarChartDemo2 demo = new BarChartDemo2("Bar Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
