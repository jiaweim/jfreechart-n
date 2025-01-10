package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A simple demonstration application showing how to create a box-and-whisker chart (on a CategoryPlot).
 */
public class BoxAndWhiskerChartDemo1 extends ApplicationFrame {

    public BoxAndWhiskerChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static BoxAndWhiskerCategoryDataset<String, String> createDataset() {
        int SERIES_COUNT = 3;
        int CATEGORY_COUNT = 5;
        int VALUE_COUNT = 20;
        DefaultBoxAndWhiskerCategoryDataset<String, String> result
                = new DefaultBoxAndWhiskerCategoryDataset<>();
        for (int s = 0; s < SERIES_COUNT; s++) {
            for (int c = 0; c < CATEGORY_COUNT; c++) {
                List<Double> values = createValueList(0, 20.0, VALUE_COUNT);
                result.add(values, "Series " + s, "Category " + c);
            }
        }
        return result;
    }

    private static List<Double> createValueList(double lowerBound, double upperBound,
            int count) {
        List<Double> result = new java.util.ArrayList<>();
        for (int i = 0; i < count; i++) {
            double v = lowerBound + (Math.random() * (upperBound - lowerBound));
            result.add(v);
        }
        return result;
    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return The chart.
     */
    private static JFreeChart createChart(BoxAndWhiskerCategoryDataset<String, String> dataset) {
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                "Box and Whisker Chart Demo 1", "Category", "Value", dataset,
                true);
        CategoryPlot<String, String> plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        chart.setBackgroundPaint(Color.white);

        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }

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

        BoxAndWhiskerChartDemo1 demo = new BoxAndWhiskerChartDemo1(
                "Box-and-Whisker Chart Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
