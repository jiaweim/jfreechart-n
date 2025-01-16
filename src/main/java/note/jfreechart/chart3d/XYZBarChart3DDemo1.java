package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.graphics3d.ViewPoint3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;

import javax.swing.*;
import java.awt.*;


/**
 * A demo of a 3D bar chart.
 */
@SuppressWarnings("serial")
public class XYZBarChart3DDemo1 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public XYZBarChart3DDemo1(String title) {
        super(title);
        addWindowListener(new ExitOnClose());
        getContentPane().add(createDemoPanel());
    }

    /**
     * Returns a panel containing the content for the demo.  This method is
     * used across all the individual demo applications to allow aggregation
     * into a single "umbrella" demo (OrsonChartsDemo).
     *
     * @return A panel containing the content for the demo.
     */
    public static JPanel createDemoPanel() {
        DemoPanel content = new DemoPanel(new BorderLayout());
        content.setPreferredSize(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        XYZDataset dataset = createDataset();
        Chart3D chart = createChart(dataset);
        Chart3DPanel chartPanel = new Chart3DPanel(chart);
        content.setChartPanel(chartPanel);
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        content.add(new DisplayPanel3D(chartPanel));
        return content;
    }

    /**
     * Creates an XYZ-bar chart for the demo.
     *
     * @param dataset the dataset.
     * @return An XYZ-bar chart.
     */
    private static Chart3D createChart(XYZDataset dataset) {
        Chart3D chart = Chart3DFactory.createXYZBarChart("XYZBarChart3DDemo1",
                "Chart created with Orson Charts", dataset, "X", "Value", "Z");
        chart.setViewPoint(ViewPoint3D.createAboveRightViewPoint(40));
        return chart;
    }

    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     *
     * @return A sample dataset.
     */
    private static XYZDataset<String> createDataset() {
        XYZSeries<String> series1 = new XYZSeries<>("Series 1");
        series1.add(1.0, 5.0, 1.0);
        XYZSeries<String> series2 = new XYZSeries<>("Series 2");
        series2.add(2.0, 8.0, 2.0);
        XYZSeries<String> series3 = new XYZSeries<>("Series 3");
        series3.add(1.0, 10.0, 2.0);
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        dataset.add(series1);
        dataset.add(series2);
        dataset.add(series3);
        return dataset;
    }

    /**
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        XYZBarChart3DDemo1 app = new XYZBarChart3DDemo1(
                "OrsonCharts: XYZBarChart3DDemo1.java");
        app.pack();
        app.setVisible(true);
    }
}