package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.axis.IntegerTickSelector;
import org.jfree.chart3d.axis.NumberAxis3D;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.plot.XYZPlot;

import javax.swing.*;
import java.awt.*;

/**
 * A demo of a 3D line chart.
 */
@SuppressWarnings("serial")
public class XYZLineChart3DDemo1 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public XYZLineChart3DDemo1(String title) {
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

    private static Chart3D createChart(XYZDataset dataset) {
        Chart3D chart = Chart3DFactory.createXYZLineChart("XYZ Line Chart Demo",
                "Orson Charts", dataset, "Day", "Index", "Station");
        chart.setChartBoxColor(new Color(255, 255, 255, 128));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(15, 3, 8));
        NumberAxis3D zAxis = (NumberAxis3D) plot.getZAxis();
        zAxis.setTickSelector(new IntegerTickSelector());
        return chart;
    }

    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     *
     * @return A sample dataset.
     */
    public static XYZDataset<String> createDataset() {
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        for (int s = 1; s < 24; s++) {
            XYZSeries<String> series = new XYZSeries<>("Series " + s);
            double y = 1.0;
            for (int i = 0; i < 3000; i++) {
                y = y * (1.0 + (Math.random() - 0.499) / 10.0);
                series.add(i, y, s);
            }
            dataset.add(series);
        }

        return dataset;
    }


    /**
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        XYZLineChart3DDemo1 app = new XYZLineChart3DDemo1(
                "OrsonCharts: XYZLineChart3DDemo1.java");
        app.pack();
        app.setVisible(true);
    }
}