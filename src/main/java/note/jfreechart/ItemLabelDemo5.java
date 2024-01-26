package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.chart.text.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * This demo shows how to force long labels to be displayed within the bars of a stacked bar chart, even when some
 * labels don't fit completely within each bar.
 */
public class ItemLabelDemo5 extends ApplicationFrame
{

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public ItemLabelDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    public static CategoryDataset createDataset() {
        DefaultCategoryDataset<String, String> categoryDataset = new DefaultCategoryDataset<>();

        categoryDataset.addValue(52.83, "Germany", "Western EU");
        categoryDataset.addValue(20.83, "France", "Western EU");
        categoryDataset.addValue(10.83, "Great Britain", "Western EU");
        categoryDataset.addValue(7.33, "Netherlands", "Western EU");
        categoryDataset.addValue(4.66, "Belgium", "Western EU");
        categoryDataset.addValue(57.14, "Spain", "Southern EU");
        categoryDataset.addValue(14.28, "Greece", "Southern EU");
        categoryDataset.addValue(14.28, "Italy", "Southern EU");
        categoryDataset.addValue(14.28, "Portugal", "Southern EU");
        categoryDataset.addValue(100.0, "Czech Republic", "Eastern EU");
        categoryDataset.addValue(66.66, "Denmark", "Scandinavia");
        categoryDataset.addValue(33.33, "Finland", "Scandinavia");
        categoryDataset.addValue(0, "", "Africa");
        categoryDataset.addValue(100.0, "Israel", "Asia");

        return categoryDataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Item Label Demo 5", null, null, dataset,
                PlotOrientation.VERTICAL, false, true, false);

        chart.setBackgroundPaint(new Color(255, 255, 255));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        MyStackedBarRenderer renderer = new MyStackedBarRenderer();
        plot.setRenderer(renderer);

        ItemLabelPosition position = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER,
                0.0);
        renderer.setPositiveItemLabelPositionFallback(position);
        renderer.setNegativeItemLabelPositionFallback(position);

        StandardCategoryItemLabelGenerator scilg = new StandardCategoryItemLabelGenerator("{0}",
                NumberFormat.getInstance());
        renderer.setDefaultItemLabelGenerator(scilg);
        renderer.setDefaultItemLabelsVisible(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setUpperBound(100);
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

        ItemLabelDemo5 demo = new ItemLabelDemo5("Item Label Demo 5");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

    private static class MyStackedBarRenderer extends StackedBarRenderer
    {

        int oldColumn = -99;

        int count = 0;

        Paint[] list = DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE;

        public Paint getItemPaint(int row, int column) {

            if (this.oldColumn != column) {
                this.count = 0;
                this.oldColumn = column;
            } else
                this.count++;

            return this.list[this.count];
        }
    }
}
