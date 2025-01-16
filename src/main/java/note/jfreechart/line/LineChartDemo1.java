package note.jfreechart.line;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

/**
 * A simple demonstration application showing how to create a line chart using
 * data from a {@link CategoryDataset}.
 *
 * @author Jiawei Mao
 * @version 0.1.0 ⭐
 * @since 26 Dec 2023, 10:35 AM
 */
public class LineChartDemo1 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public LineChartDemo1(String title) {
        super(title);

        CategoryDataset<String, String> dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset<String, String> createDataset() {
        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(212, "Classes", "JDK 1.0");
        dataset.addValue(504, "Classes", "JDK 1.1");
        dataset.addValue(1520, "Classes", "SDK 1.2");
        dataset.addValue(1842, "Classes", "SDK 1.3");
        dataset.addValue(2991, "Classes", "SDK 1.4");
        dataset.addValue(3500, "Classes", "SDK 1.5");
        return dataset;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset a dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createLineChart(
                "Java Standard Class Library", // chart title
                "Release", // domain axis label
                "Class Count", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips
                false // urls
        );
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));

        TextTitle source = new TextTitle("Source: Java In A Nutshell (4th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", Font.PLAIN, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot<String, String> plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinesVisible(false);
        URL imageURL = LineChartDemo1.class.getClassLoader().getResource("OnBridge11small.png");
        if (imageURL != null) {
            ImageIcon temp = new ImageIcon(imageURL);
            // use ImageIcon because it waits for the image to load...
            chart.setBackgroundImage(temp.getImage());
            plot.setBackgroundPaint(new Color(0, 0, 0, 0));
        }

        // customise the range axis...
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // customise the renderer...
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0));
        return chart;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LineChartDemo1 demo1 = new LineChartDemo1("Line Chart Demo");
            demo1.pack();
            UIUtils.centerFrameOnScreen(demo1);
            demo1.setVisible(true);
        });
    }
}
