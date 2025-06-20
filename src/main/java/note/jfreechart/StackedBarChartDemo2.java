package note.jfreechart;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A stacked bar chart with a horizontal orientation.
 */
public class StackedBarChartDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public StackedBarChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(81.0, "Against all torture", "Italy");
        dataset.addValue(72.0, "Against all torture", "Great Britain");
        dataset.addValue(58.0, "Against all torture", "USA");
        dataset.addValue(48.0, "Against all torture", "Israel");
        dataset.addValue(43.0, "Against all torture", "Russia");
        dataset.addValue(23.0, "Against all torture", "India");
        dataset.addValue(59.0, "Against all torture", "Average (*)");

        dataset.addValue(5.0, "-", "Italy");
        dataset.addValue(4.0, "-", "Great Britain");
        dataset.addValue(6.0, "-", "USA");
        dataset.addValue(9.0, "-", "Israel");
        dataset.addValue(20.0, "-", "Russia");
        dataset.addValue(45.0, "-", "India");
        dataset.addValue(12.0, "-", "Average (*)");

        dataset.addValue(14.0, "Some degree permissible", "Italy");
        dataset.addValue(24.0, "Some degree permissible", "Great Britain");
        dataset.addValue(36.0, "Some degree permissible", "USA");
        dataset.addValue(43.0, "Some degree permissible", "Israel");
        dataset.addValue(37.0, "Some degree permissible", "Russia");
        dataset.addValue(32.0, "Some degree permissible", "India");
        dataset.addValue(29.0, "Some degree permissible", "Average (*)");

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
        JFreeChart chart = ChartFactory.createStackedBarChart(
                "Public Opinion : Torture of Prisoners",
                "Country",                    // domain axis label
                "%",                 // range axis label
                dataset,                      // data
                PlotOrientation.HORIZONTAL,   // orientation
                false,                        // include legend
                true,                         // tooltips?
                false                         // URLs?
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);
        chart.getTitle().setMargin(2.0, 0.0, 0.0, 0.0);

        TextTitle tt = new TextTitle(
                "Source: http://news.bbc.co.uk/1/hi/world/6063386.stm",
                new Font("Dialog", Font.PLAIN, 11));
        tt.setPosition(RectangleEdge.BOTTOM);
        tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        tt.setMargin(0.0, 0.0, 4.0, 4.0);
        chart.addSubtitle(tt);

        TextTitle t = new TextTitle(
                "(*) Across 27,000 respondents in 25 countries",
                new Font("Dialog", Font.PLAIN, 11));
        t.setPosition(RectangleEdge.BOTTOM);
        t.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        t.setMargin(4.0, 0.0, 2.0, 4.0);
        chart.addSubtitle(t);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        LegendItemCollection items = new LegendItemCollection();
        items.add(new LegendItem("Against all torture", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.green));
        items.add(new LegendItem("Some degree permissible", null, null, null,
                new Rectangle2D.Double(-6.0, -3.0, 12.0, 6.0), Color.red));
        plot.setFixedLegendItems(items);
        plot.setInsets(new RectangleInsets(5, 5, 5, 20));
        LegendTitle legend = new LegendTitle(plot);
        legend.setPosition(RectangleEdge.BOTTOM);
        chart.addSubtitle(legend);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.0);

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.green,
                0.0f, 0.0f, new Color(0, 64, 0));
        Paint gp1 = new Color(0, 0, 0, 0);
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
                0.0f, 0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

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
        StackedBarChartDemo2 demo = new StackedBarChartDemo2(
                "Stacked Bar Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}

