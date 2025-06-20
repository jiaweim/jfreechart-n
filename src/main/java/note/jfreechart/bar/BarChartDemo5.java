package note.jfreechart.bar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A simple demonstration application showing how to create a horizontal bar
 * chart.
 */
public class BarChartDemo5 extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public BarChartDemo5(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return A sample dataset.
     */
    private static CategoryDataset createDataset() {

        // The following data is obtained from:
        //
        // http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf
        //
        // An arbitrary selection of countries has been included in this
        // dataset, please refer to the original report for the complete
        // set.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String seriesKey = "Prison Population Rates";
        dataset.addValue(59, seriesKey, "Norway");
        dataset.addValue(69, seriesKey, "Switzerland");
        dataset.addValue(85, seriesKey, "France");
        dataset.addValue(93, seriesKey, "Syria");
        dataset.addValue(96, seriesKey, "Germany");
        dataset.addValue(111, seriesKey, "China");
        dataset.addValue(116, seriesKey, "Australia");
        dataset.addValue(121, seriesKey, "Egypt");
        dataset.addValue(129, seriesKey, "England & Wales");
        dataset.addValue(157, seriesKey, "New Zealand");
        dataset.addValue(205, seriesKey, "Chile");
        dataset.addValue(229, seriesKey, "Iran");
        dataset.addValue(359, seriesKey, "Singapore");
        dataset.addValue(404, seriesKey, "South Africa");
        dataset.addValue(406, seriesKey, "Ukraine");
        dataset.addValue(686, seriesKey, "USA");
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return A chart.
     */
    private static JFreeChart createChart(final CategoryDataset dataset) {
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
                "Prison Population Rates - Selected Countries",  // chart title
                "Country",                  // domain axis label
                "Prisoners Per 100,000 National Population",              // range axis label
                dataset,                     // data
                PlotOrientation.HORIZONTAL,  // orientation
                true,                        // include legend
                true,
                false
        );
        chart.addSubtitle(new TextTitle(
                "Source: http://www.homeoffice.gov.uk/rds/pdfs2/r188.pdf",
                new Font("Dialog", Font.ITALIC, 10)));
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.0);
        categoryAxis.setUpperMargin(0.02);
        categoryAxis.setLowerMargin(0.02);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(0.10);

        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
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

        BarChartDemo5 demo = new BarChartDemo5("Bar Chart Demo 5");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
