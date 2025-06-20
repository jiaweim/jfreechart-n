package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * A simple demonstration application showing how to create a stacked area
 * chart using data from a {@link CategoryDataset}.
 */
public class StackedAreaChartDemo extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public StackedAreaChartDemo(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "S1", "C1");
        dataset.addValue(2.0, "S1", "C2");
        dataset.addValue(3.0, "S1", "C3");
        dataset.addValue(4.0, "S1", "C4");
        dataset.addValue(5.0, "S1", "C5");
        dataset.addValue(6.0, "S1", "C6");
        dataset.addValue(7.0, "S1", "C7");
        dataset.addValue(8.0, "S1", "C8");
        dataset.addValue(3.0, "S2", "C1");
        dataset.addValue(3.0, "S2", "C2");
        dataset.addValue(-3.0, "S2", "C3");
        dataset.addValue(3.0, "S2", "C4");
        dataset.addValue(3.0, "S2", "C5");
        dataset.addValue(3.0, "S2", "C6");
        dataset.addValue(3.0, "S2", "C7");
        dataset.addValue(3.0, "S2", "C8");
        dataset.addValue(8.0, "S3", "C1");
        dataset.addValue(7.0, "S3", "C2");
        dataset.addValue(-6.0, "S3", "C3");
        dataset.addValue(5.0, "S3", "C4");
        dataset.addValue(4.0, "S3", "C5");
        dataset.addValue(3.0, "S3", "C6");
        dataset.addValue(2.0, "S3", "C7");
        dataset.addValue(1.0, "S3", "C8");

        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return A sample chart.
     */
    public JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedAreaChart(
                "Stacked Area Chart",      // chart title
                "Category",                // domain axis label
                "Value",                   // range axis label
                dataset,                   // data
                PlotOrientation.VERTICAL,  // orientation
                true,                      // include legend
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setForegroundAlpha(0.85f);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        domainAxis.setCategoryMargin(0.0);

        // change the auto tick unit selection to integer units only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);

        return chart;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        StackedAreaChartDemo demo = new StackedAreaChartDemo("Stacked Area Chart Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
