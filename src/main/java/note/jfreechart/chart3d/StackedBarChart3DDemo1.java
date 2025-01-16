package note.jfreechart.chart3d;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.data.DefaultKeyedValues;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.data.category.StandardCategoryDataset3D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;

/**
 * A demo of a 3D stacked bar chart.
 */
@SuppressWarnings("serial")
public class StackedBarChart3DDemo1 extends JFrame {

	/**
     * Creates a new test app.
     *
     * @param title  the frame title.
     */
    public StackedBarChart3DDemo1(String title) {
        super(title);
        addWindowListener(new ExitOnClose());
        getContentPane().add(createDemoPanel());
    }

    /**
     * Creates a stacked bar chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A stacked bar chart. 
     */
    private static Chart3D createChart(CategoryDataset3D dataset) {
        Chart3D chart = Chart3DFactory.createStackedBarChart(
                "Stacked Bar Chart", "Put the data source here", dataset, null, 
                null, "Value");
        return chart;    
    }
    
    /**
     * Creates a sample dataset (hard-coded for the purpose of keeping the
     * demo self-contained - in practice you would normally read your data
     * from a file, database or other source).
     * 
     * @return A sample dataset.
     */
    private static CategoryDataset3D createDataset() {
        
        StandardCategoryDataset3D<String, String, String> dataset 
                = new StandardCategoryDataset3D<>();

        DefaultKeyedValues<String, Double> s1 = new DefaultKeyedValues<>();
        s1.put("A", 4.0);
        s1.put("B", 2.0);
        s1.put("C", 3.0);
        s1.put("D", 5.0);
        s1.put("E", 2.0);
        s1.put("F", 1.0);
        DefaultKeyedValues<String, Double> s2 = new DefaultKeyedValues<>();
        s2.put("A", 1.0);
        s2.put("B", 2.0);
        s2.put("C", 3.0);
        s2.put("D", 2.0);
        s2.put("E", 3.0);
        s2.put("F", 1.0);
        DefaultKeyedValues<String, Double> s3 = new DefaultKeyedValues<>();
        s3.put("A", 6.0);
        s3.put("B", 6.0);
        s3.put("C", 6.0);
        s3.put("D", 4.0);
        s3.put("E", 4.0);
        s3.put("F", 4.0);
        DefaultKeyedValues<String, Double> s4 = new DefaultKeyedValues<>();
        s4.put("A", 9.0);
        s4.put("B", 8.0);
        s4.put("C", 7.0);
        s4.put("D", 6.0);
        s4.put("D", 3.0);
        s4.put("E", 4.0);
        s4.put("F", 6.0);
        DefaultKeyedValues<String, Double> s5 = new DefaultKeyedValues<>();
        s5.put("A", 9.0);
        s5.put("B", 8.0);
        s5.put("C", 7.0);
        s5.put("D", 6.0);
        s5.put("E", 7.0);
        s5.put("F", 9.0);

        dataset.addSeriesAsRow("Series 1", "Row 1", s1);
        dataset.addSeriesAsRow("Series 2", "Row 2", s2);
        dataset.addSeriesAsRow("Series 3", "Row 2", s3);
        dataset.addSeriesAsRow("Series 4", "Row 3", s4);
        dataset.addSeriesAsRow("Series 5", "Row 3", s5);
        return dataset;
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
        CategoryDataset3D dataset = createDataset();
        Chart3D chart = createChart(dataset);
        Chart3DPanel chartPanel = new Chart3DPanel(chart);
        chartPanel.setMargin(0.33);
        content.setChartPanel(chartPanel);
        chartPanel.zoomToFit(OrsonChartsDemo.DEFAULT_CONTENT_SIZE);
        content.add(new DisplayPanel3D(chartPanel));
        return content;
    }
  
    /**
     * Starting point for the app.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {
        StackedBarChart3DDemo1 app = new StackedBarChart3DDemo1(
                "OrsonCharts: StackedBarChart3DDemo2.java");
        app.pack();
        app.setVisible(true);
    }
}

