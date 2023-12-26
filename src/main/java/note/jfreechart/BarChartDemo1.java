package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.legend.LegendTitle;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 25 Dec 2023, 10:27 PM
 */
public class BarChartDemo1 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public BarChartDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(720, 480));
        this.setContentPane(chartPanel);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String series1 = "Males";
        String series2 = "Females";
        String category1 = "18 to 39";
        String category2 = "40 - 59";
        String category3 = "60 and over";
        dataset.addValue(5.5, series1, category1);
        dataset.addValue(10.3, series2, category1);
        dataset.addValue(8.4, series1, category2);
        dataset.addValue(20.1, series2, category2);
        dataset.addValue(12.8, series1, category3);
        dataset.addValue(24.3, series2, category3);
        return dataset;
    }

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Antidepressant Medication Usage", "Age Category", "Percent", dataset);
        LegendTitle legend = chart.getLegend();
        chart.removeLegend();
        chart.addSubtitle(new TextTitle("Percentage of adults aged 18 and over who used antidepressant medication over past 30 days, by age and sex: United States, 2015-2018"));
        TextTitle source = new TextTitle("Source: https://www.cdc.gov/nchs/products/databriefs/db377.htm");
        source.setFont(new Font("SansSerif", 0, 10));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        chart.addSubtitle(legend);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairPaint(Color.BLUE);
        plot.getDomainAxis().setCategoryMargin(0.2);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setItemMargin(0.06);
        renderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
        return chart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        BarChartDemo1 demo = new BarChartDemo1("JFreeChart: BarChartDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
