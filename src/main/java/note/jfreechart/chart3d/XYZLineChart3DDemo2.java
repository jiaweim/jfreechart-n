package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.plot.XYZPlot;

import javax.swing.*;
import java.awt.*;

/**
 * A demo of a 3D line chart.
 */
@SuppressWarnings("serial")
public class XYZLineChart3DDemo2 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public XYZLineChart3DDemo2(String title) {
        super(title);
        addWindowListener(new ExitOnClose());
        getContentPane().add(createDemoPanel());
    }

    private static XYZDataset createDataset() {
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        XYZSeries<String> series = new XYZSeries<>("Series 1");
        series.add(5, 5, 5);
        series.add(15, 5, 5);
        series.add(15, 15, 5);
        series.add(5, 15, 5);
        series.add(5, 5, 5);
        series.add(5, 5, 15);
        series.add(15, 5, 15);
        series.add(15, 15, 15);
        series.add(5, 15, 15);
        series.add(5, 5, 15);
        series.add(5, 15, 15);
        series.add(5, 15, 5);
        series.add(15, 15, 5);
        series.add(15, 15, 15);
        series.add(15, 5, 15);
        series.add(15, 5, 5);
        dataset.add(series);


        return dataset;
    }

    private static Chart3D createChart(XYZDataset dataset) {
        Chart3D chart = Chart3DFactory.createXYZLineChart("XYZ Line Chart Demo",
                "Orson Charts", dataset, "X", "Y", "Z");
        chart.setChartBoxColor(new Color(255, 255, 255, 128));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.getXAxis().setRange(0, 20);
        plot.getYAxis().setRange(0, 20);
        plot.getZAxis().setRange(0, 20);
        return chart;
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
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        XYZLineChart3DDemo2 app = new XYZLineChart3DDemo2(
                "OrsonCharts: XYZLineChart3DDemo2.java");
        app.pack();
        app.setVisible(true);
    }
}