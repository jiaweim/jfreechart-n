package note.jfreechart.chart3d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.Colors;
import org.jfree.chart3d.axis.LabelOrientation;
import org.jfree.chart3d.axis.StandardCategoryAxis3D;
import org.jfree.chart3d.data.category.CategoryDataset3D;
import org.jfree.chart3d.data.category.StandardCategoryDataset3D;
import org.jfree.chart3d.graphics2d.Fit2D;
import org.jfree.chart3d.graphics3d.swing.DisplayPanel3D;
import org.jfree.chart3d.label.StandardCategoryItemLabelGenerator;
import org.jfree.chart3d.label.StandardCategoryLabelGenerator;
import org.jfree.chart3d.plot.CategoryPlot3D;
import org.jfree.chart3d.renderer.category.StackedBarRenderer3D;
import org.jfree.chart3d.table.RectanglePainter;
import org.jfree.chart3d.table.StandardRectanglePainter;

/**
 * A demo of a 3D stacked bar chart.
 */
@SuppressWarnings("serial")
public class StackedBarChart3DDemo3 extends JFrame {

    /**
     * Creates a new test app.
     *
     * @param title  the frame title.
     */
    public StackedBarChart3DDemo3(String title) {
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
     * Creates a stacked bar chart based on the supplied dataset.
     * 
     * @param dataset  the dataset.
     * 
     * @return A stacked bar chart.
     */
    private static Chart3D createChart(CategoryDataset3D dataset) {
        Chart3D chart = Chart3DFactory.createStackedBarChart(
                "The Sinking of the Titanic", 
                "Survival data for 2,201 passengers", 
                dataset, null, 
                "Class", "Passengers");

        URL imageURL = StackedBarChart3DDemo3.class.getResource(
                "iStock_000003105870Small.jpg");
        ImageIcon icon = new ImageIcon(imageURL); 
        RectanglePainter background = new StandardRectanglePainter(Color.WHITE, 
                icon.getImage(), Fit2D.SCALE_TO_FIT_TARGET);
        chart.setBackground(background);
        chart.setChartBoxColor(new Color(255, 255, 255, 155));
        CategoryPlot3D plot = (CategoryPlot3D) chart.getPlot();
        plot.setLegendLabelGenerator(new StandardCategoryLabelGenerator(
                StandardCategoryLabelGenerator.TOTAL_TEMPLATE));
        plot.setToolTipGenerator(new StandardCategoryItemLabelGenerator(
                "%s, %s, %s = %4$.0f"));
        StandardCategoryAxis3D rowAxis 
                = (StandardCategoryAxis3D) plot.getRowAxis();
        rowAxis.setTickLabelGenerator(new StandardCategoryLabelGenerator(
                StandardCategoryLabelGenerator.TOTAL_TEMPLATE));

        StandardCategoryAxis3D columnAxis 
                = (StandardCategoryAxis3D) plot.getColumnAxis();
        columnAxis.setTickLabelGenerator(new StandardCategoryLabelGenerator(
                StandardCategoryLabelGenerator.TOTAL_TEMPLATE));
        columnAxis.setTickLabelOrientation(LabelOrientation.PARALLEL);
        columnAxis.setMaxTickLabelLevels(2);

        StackedBarRenderer3D renderer = (StackedBarRenderer3D) plot.getRenderer();
        renderer.setColors(Colors.createIceCubeColors());
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
        
        dataset.addValue(146, "Survivors", "Women/Children", "1st");
        dataset.addValue(104, "Survivors", "Women/Children", "2nd");
        dataset.addValue(103, "Survivors", "Women/Children", "3rd");
        dataset.addValue(20, "Survivors", "Women/Children", "Crew");

        dataset.addValue(57, "Survivors", "Men", "1st");
        dataset.addValue(14, "Survivors", "Men", "2nd");
        dataset.addValue(75, "Survivors", "Men", "3rd");
        dataset.addValue(192, "Survivors", "Men", "Crew");
 
        dataset.addValue(4, "Victims", "Women/Children", "1st");
        dataset.addValue(13, "Victims", "Women/Children", "2nd");
        dataset.addValue(141, "Victims", "Women/Children", "3rd");
        dataset.addValue(3, "Victims", "Women/Children", "Crew");
        
        dataset.addValue(118, "Victims", "Men", "1st");
        dataset.addValue(154, "Victims", "Men", "2nd");
        dataset.addValue(387, "Victims", "Men", "3rd");
        dataset.addValue(670, "Victims", "Men", "Crew");
          
        return dataset;
    }

  
    /**
     * Starting point for the app.
     *
     * @param args  command line arguments (ignored).
     */
    public static void main(String[] args) {
        StackedBarChart3DDemo3 app = new StackedBarChart3DDemo3(
                "OrsonCharts: StackedBarChart3DDemo3.java");
        app.pack();
        app.setVisible(true);
    }
}

