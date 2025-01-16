package note.jfreechart.chart3d;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.axis.ValueAxis3D;
import org.jfree.chart3d.data.Range;
import org.jfree.chart3d.data.function.Function3D;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.chart3d.renderer.GradientColorScale;
import org.jfree.chart3d.renderer.xyz.SurfaceRenderer;

/**
 * A demo of a surface chart.
 */
@SuppressWarnings("serial")
public class SurfaceRendererDemo1 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title  the frame title.
     */
    public SurfaceRendererDemo1(String title) {
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
        Chart3D chart = createChart();
        Chart3DPanel chartPanel = new Chart3DPanel(chart);
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        content.setChartPanel(chartPanel);
        content.add(new DisplayPanel3D(chartPanel));
        return content;
    }
        
    /**
     * Creates a surface chart for the demo.
     * 
     * @return A surface chart. 
     */
    private static Chart3D createChart() {
        Function3D function = (x, z) -> Math.cos(x) * Math.sin(z);
        
        Chart3D chart = Chart3DFactory.createSurfaceChart(
                "SurfaceRendererDemo1", 
                "y = cos(x) * sin(z)", 
                function, "X", "Y", "Z");
 
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10, 5, 10));
        ValueAxis3D xAxis = plot.getXAxis();
        xAxis.setRange(-Math.PI, Math.PI);
        ValueAxis3D zAxis = plot.getZAxis();
        zAxis.setRange(-Math.PI, Math.PI);
        SurfaceRenderer renderer = (SurfaceRenderer) plot.getRenderer();
        renderer.setDrawFaceOutlines(false);
        renderer.setColorScale(new GradientColorScale(new Range(-1.0, 1.0), 
                Color.RED, Color.YELLOW));
        return chart;    
    }
    
    /**
     * Starting point for the app.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {
        SurfaceRendererDemo1 app = new SurfaceRendererDemo1(
                "OrsonCharts: SurfaceRendererDemo1.java");
        app.pack();
        app.setVisible(true);
    }
}

