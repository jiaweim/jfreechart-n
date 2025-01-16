package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.legend.LegendAnchor;
import org.jfree.chart3d.plot.CategoryPlot3D;

import javax.swing.*;
import java.awt.*;

/**
 * A demo of a 3D bar chart.
 * <p>
 * http://www.theverge.com/2013/7/23/4549094/apple-microsoft-google-profit-revenue-margins-q2-2013-chart
 */
@SuppressWarnings("serial")
public class BarChart3DDemo1 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public BarChart3DDemo1(String title) {
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
        CategoryDataset3D dataset = SampleData.createCompanyRevenueDataset();
        Chart3D chart = createChart(dataset);
        Chart3DPanel chartPanel = new Chart3DPanel(chart);
        chartPanel.setMargin(0.30);
        content.setChartPanel(chartPanel);
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        content.add(new DisplayPanel3D(chartPanel));
        return content;
    }

    /**
     * Creates a bar chart with the supplied dataset.
     *
     * @param dataset the dataset.
     * @return A bar chart.
     */
    public static Chart3D createChart(CategoryDataset3D dataset) {
        Chart3D chart = Chart3DFactory.createBarChart("Quarterly Revenues",
                "For some large IT companies", dataset, null, "Quarter",
                "$billion Revenues");
        chart.setChartBoxColor(new Color(255, 255, 255, 127));
        chart.setLegendAnchor(LegendAnchor.BOTTOM_RIGHT);
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.setGridlinePaintForValues(Color.BLACK);
        return chart;
    }

    /**
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        BarChart3DDemo1 app = new BarChart3DDemo1(
                "OrsonCharts: BarChart3DDemo1.java");
        app.pack();
        app.setVisible(true);
    }
}
