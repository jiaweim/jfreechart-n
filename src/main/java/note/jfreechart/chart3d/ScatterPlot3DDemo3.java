package note.jfreechart.chart3d;

import org.jfree.chart3d.*;
import org.jfree.chart3d.data.DataUtils;
import org.jfree.chart3d.data.JSONUtils;
import org.jfree.chart3d.data.KeyedValues3D;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.graphics3d.ViewPoint3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.legend.LegendAnchor;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.chart3d.renderer.xyz.ScatterXYZRenderer;
import org.jfree.chart3d.style.StandardChartStyle;
import org.jfree.chart3d.table.TextElement;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * A demonstration of a scatter plot in 3D.
 */
@SuppressWarnings("serial")
public class ScatterPlot3DDemo3 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title the frame title.
     */
    public ScatterPlot3DDemo3(String title) {
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
        DemoPanel content = new DemoPanel(new GridLayout(2, 2));
        //content.setMinimumSize(new Dimension(40, 40));

        XYZDataset[] datasets = createDatasets();
        Chart3D chart1 = createChart("Iris Dataset : Combination 1",
                datasets[0], "Sepal Length", "Sepal Width", "Petal Length");
        Chart3DPanel chartPanel1 = new Chart3DPanel(chart1);
        chartPanel1.setPreferredSize(new Dimension(10, 10));
        chartPanel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 0)),
                BorderFactory.createLineBorder(Color.DARK_GRAY)));
        chartPanel1.setMargin(0.35);

        Chart3D chart2 = createChart("Iris Dataset : Combination 2",
                datasets[1], "Sepal Length", "Sepal Width", "Petal Width");
        Chart3DPanel chartPanel2 = new Chart3DPanel(chart2);
        chartPanel2.setPreferredSize(new Dimension(10, 10));
        chartPanel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 0)),
                BorderFactory.createLineBorder(Color.DARK_GRAY)));
        chartPanel2.setMargin(0.35);

        Chart3D chart3 = createChart("Iris Dataset : Combination 3",
                datasets[2], "Sepal Length", "Petal Width", "Petal Length");
        Chart3DPanel chartPanel3 = new Chart3DPanel(chart3);
        chartPanel3.setPreferredSize(new Dimension(10, 10));
        chartPanel3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 0)),
                BorderFactory.createLineBorder(Color.DARK_GRAY)));
        chartPanel3.setMargin(0.35);

        Chart3D chart4 = createChart(
                "Iris Dataset : Combination 4", datasets[3],
                "Sepal Width", "Petal Width", "Petal Length");
        Chart3DPanel chartPanel4 = new Chart3DPanel(chart4);
        chartPanel4.setPreferredSize(new Dimension(10, 10));
        chartPanel4.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 0)),
                BorderFactory.createLineBorder(Color.DARK_GRAY)));
        chartPanel4.setMargin(0.35);

        content.add(new DisplayPanel3D(chartPanel1, false, true));
        content.addChartPanel(chartPanel1);
        content.add(new DisplayPanel3D(chartPanel2, false, true));
        content.addChartPanel(chartPanel2);
        content.add(new DisplayPanel3D(chartPanel3, false, true));
        content.addChartPanel(chartPanel3);
        content.add(new DisplayPanel3D(chartPanel4, false, true));
        content.addChartPanel(chartPanel4);
        content.setPreferredSize(new Dimension(400, 400));
        return content;
    }

    private static XYZDataset[] createDatasets() {
        XYZDataset[] datasets = new XYZDataset[4];
        datasets[0] = createDataset("sepal length", "sepal width",
                "petal length");
        datasets[1] = createDataset("sepal length", "sepal width",
                "petal width");
        datasets[2] = createDataset("sepal length", "petal width",
                "petal length");
        datasets[3] = createDataset("sepal width", "petal width",
                "petal length");
        return datasets;
    }

    private static Chart3D createChart(String title, XYZDataset dataset,
            String xLabel, String yLabel, String zLabel) {
        Chart3D chart = Chart3DFactory.createScatterChart(null, null,
                dataset, xLabel, yLabel, zLabel);
        TextElement title1 = new TextElement(title);
        title1.setFont(StandardChartStyle.createDefaultFont(Font.PLAIN, 16));
        chart.setTitle(title1);
        chart.setLegendAnchor(LegendAnchor.BOTTOM_LEFT);
        chart.setLegendOrientation(Orientation.VERTICAL);
        XYZPlot plot = (XYZPlot) chart.getPlot();
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(Colors.createIntenseColors());
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(40));
        return chart;
    }

    /**
     * Reads a dataset from the file iris.txt.
     *
     * @return A sample dataset.
     */
    private static XYZDataset<String> createDataset(String xKey, String yKey,
            String zKey) {
        Reader in = new InputStreamReader(
                ScatterPlot3DDemo3.class.getResourceAsStream("iris.txt"));
        KeyedValues3D<String, String, String, Number> data;
        try {
            data = JSONUtils.readKeyedValues3D(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return DataUtils.extractXYZDatasetFromColumns(data, xKey, yKey, zKey);
    }

    /**
     * Starting point for the app.
     *
     * @param args command line arguments (ignored).
     */
    public static void main(String[] args) {
        ScatterPlot3DDemo3 app = new ScatterPlot3DDemo3(
                "OrsonCharts : ScatterPlot3DDemo3.java");
        app.pack();
        app.setVisible(true);
    }
}