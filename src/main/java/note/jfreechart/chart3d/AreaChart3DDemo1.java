package note.jfreechart.chart3d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.plot.CategoryPlot3D;
import org.jfree.chart3d.renderer.category.AreaRenderer3D;

/**
 * A demo of a 3D area chart in Swing.
 */
public class AreaChart3DDemo1 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public AreaChart3DDemo1(String title) {
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
    public static DemoPanel createDemoPanel() {
        DemoPanel content = new DemoPanel(new BorderLayout());
        content.setPreferredSize(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        CategoryDataset3D<String, String, String> dataset = SampleData.createCompanyRevenueDataset();
        Chart3D chart = createChart(dataset);
        Chart3DPanel chartPanel = new Chart3DPanel(chart);
        content.setChartPanel(chartPanel);
        content.add(new DisplayPanel3D(chartPanel));
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        return content;
    }

    /**
     * Creates an area chart using the specified {@code dataset}.
     *
     * @param dataset the dataset.
     * @return An area chart.
     */
    private static Chart3D createChart(CategoryDataset3D<String, String, String> dataset) {
        Chart3D chart = Chart3DFactory.createAreaChart(
                "Reported Revenues By Quarter",
                "Large companies in the IT industry", dataset, "Company",
                "Quarter", "Value");
        chart.setChartBoxColor(new Color(255, 255, 255, 128));
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.getRowAxis().setVisible(false);
        plot.setGridlineStrokeForValues(new BasicStroke(0.5f,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 4.0f,
                new float[]{2f, 2f}, 0f));
        AreaRenderer3D renderer = (AreaRenderer3D) plot.getRenderer();
        renderer.setBaseColor(Color.GRAY);
        return chart;
    }


    /**
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        AreaChart3DDemo1 app = new AreaChart3DDemo1(
                "OrsonCharts: AreaChart3DDemo1.java");
        app.pack();
        app.setVisible(true);
    }

}