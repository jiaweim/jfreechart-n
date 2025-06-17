package note.jfreechart.line;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A chart showing the number of classes in each major release of the Java Development Kit (JDK).
 * You can hover the mouse pointer over a data point to see a tooltip that displays the exact data value.
 *
 * @since 2025-06-17 ⭐
 */
public class LineChartDemo1 extends ApplicationFrame {

    public LineChartDemo1(String title) {
        super(title);

        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(768, 512));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(212.0D, "Classes", "JDK 1.0");
        dataset.addValue(504.0D, "Classes", "JDK 1.1");
        dataset.addValue(1520.0D, "Classes", "JDK 1.2");
        dataset.addValue(1842.0D, "Classes", "JDK 1.3");
        dataset.addValue(2991.0D, "Classes", "JDK 1.4");
        dataset.addValue(3500.0D, "Classes", "JDK 1.5");
        dataset.addValue(3793.0D, "Classes", "JDK 1.6");
        dataset.addValue(4024.0D, "Classes", "JDK 1.7");
        dataset.addValue(4240.0D, "Classes", "JDK 8");
        dataset.addValue(6005.0D, "Classes", "JDK 9");
        dataset.addValue(6002.0D, "Classes", "JDK 10");
        dataset.addValue(4411.0D, "Classes", "JDK 11");
        dataset.addValue(4433.0D, "Classes", "JDK 12");
        dataset.addValue(4545.0D, "Classes", "JDK 13");
        dataset.addValue(4569.0D, "Classes", "JDK 14");
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart("Java Standard Class Library", null, "Class Count", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.addSubtitle(new TextTitle("Number of Classes By Release"));

        TextTitle source = new TextTitle("Source: https://stackoverflow.com/q/3112882 and Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(false);
        try {
            Image image = ImageIO.read(LineChartDemo1.class.getClassLoader().getResourceAsStream("OnBridge11small.png"));
            chart.setBackgroundImage(image);
            plot.setBackgroundPaint(null);
        } catch (Exception e) {
            System.err.println("Error loading background image.");
        }

        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartUtils.applyCurrentTheme(chart);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDrawOutlines(true);
        renderer.setUseFillPaint(true);
        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F));
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D));
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LineChartDemo1 demo1 = new LineChartDemo1("Line Chart Demo1");
            demo1.pack();
            UIUtils.centerFrameOnScreen(demo1);
            demo1.setVisible(true);
        });
    }
}
