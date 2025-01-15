package note.jfreechart.bar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
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
 * A bar chart showing licence statistics for open source projects listed
 * at Freshmeat.
 */
public class BarChartDemo11 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public BarChartDemo11(String title) {

        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(23192, "S1", "GNU General Public Licence");
        dataset.addValue(3157, "S1", "GNU Lesser General Public Licence");
        dataset.addValue(1506, "S1", "BSD Licence (Original)");
        dataset.addValue(1283, "S1", "BSD Licence (Revised)");
        dataset.addValue(738, "S1", "MIT/X Consortium Licence");
        dataset.addValue(630, "S1", "Artistic Licence");
        dataset.addValue(585, "S1", "Public Domain");
        dataset.addValue(349, "S1", "Apache Licence 2.0");
        dataset.addValue(317, "S1", "Apache Licence");
        dataset.addValue(309, "S1", "Mozilla Public Licence");
        dataset.addValue(918, "S1", "Other");

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {

        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Open Source Projects By Licence",         // chart title
                "Licence",               // domain axis label
                "Project Count",                  // range axis label
                dataset,                  // data
                PlotOrientation.HORIZONTAL, // orientation
                false,                     // include legend
                true,                     // tooltips?
                false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(0.4f);
        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, new Color(0, 0, 64));
        renderer.setSeriesPaint(0, gp0);

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

        BarChartDemo11 demo = new BarChartDemo11("Bar Chart Demo 11");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
