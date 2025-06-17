package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * A simple demonstration application showing how to create a stacked 3D bar
 * chart using data from a {@link CategoryDataset}.
 */
public class StackedBarChart3DDemo1 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public StackedBarChart3DDemo1(String title) {
        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates and returns a {@link CategoryDataset} for the demo chart.
     *
     * @return a sample dataset.
     */
    public CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.0, "Series 1", "C1");
        dataset.addValue(5.0, "Series 1", "C2");
        dataset.addValue(6.0, "Series 1", "C3");
        dataset.addValue(7.0, "Series 1", "C4");
        dataset.addValue(8.0, "Series 1", "C5");
        dataset.addValue(9.0, "Series 1", "C6");
        dataset.addValue(10.0, "Series 1", "C7");
        dataset.addValue(11.0, "Series 1", "C8");
        dataset.addValue(3.0, "Series 1", "C9");

        dataset.addValue(4.0, "Series 2", "C1");
        dataset.addValue(7.0, "Series 2", "C2");
        dataset.addValue(17.0, "Series 2", "C3");
        dataset.addValue(15.0, "Series 2", "C4");
        dataset.addValue(6.0, "Series 2", "C5");
        dataset.addValue(8.0, "Series 2", "C6");
        dataset.addValue(9.0, "Series 2", "C7");
        dataset.addValue(13.0, "Series 2", "C8");
        dataset.addValue(7.0, "Series 2", "C9");

        dataset.addValue(15.0, "Series 3", "C1");
        dataset.addValue(14.0, "Series 3", "C2");
        dataset.addValue(12.0, "Series 3", "C3");
        dataset.addValue(11.0, "Series 3", "C4");
        dataset.addValue(10.0, "Series 3", "C5");
        dataset.addValue(0.0, "Series 3", "C6");
        dataset.addValue(7.0, "Series 3", "C7");
        dataset.addValue(9.0, "Series 3", "C8");
        dataset.addValue(11.0, "Series 3", "C9");

        dataset.addValue(14.0, "Series 4", "C1");
        dataset.addValue(3.0, "Series 4", "C2");
        dataset.addValue(7.0, "Series 4", "C3");
        dataset.addValue(0.0, "Series 4", "C4");
        dataset.addValue(9.0, "Series 4", "C5");
        dataset.addValue(6.0, "Series 4", "C6");
        dataset.addValue(7.0, "Series 4", "C7");
        dataset.addValue(9.0, "Series 4", "C8");
        dataset.addValue(10.0, "Series 4", "C9");

        return dataset;

    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Stacked Bar Chart 3D Demo 1",  // chart title
                "Category",                   // domain axis label
                "Value",                      // range axis label
                dataset,                      // data
                PlotOrientation.VERTICAL,     // the plot orientation
                true,                         // include legend
                true,                         // tooltips
                false                         // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setDefaultItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setDefaultNegativeItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        return chart;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        StackedBarChart3DDemo1 demo = new StackedBarChart3DDemo1(
                "Stacked Bar Chart 3D Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
