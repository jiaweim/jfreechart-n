package note.jfreechart.combinedaxis;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A demo showing two category plots that share a common range axis.
 * @since 2025-06-18⭐
 */
public class CombinedCategoryPlotDemo2 extends ApplicationFrame {

    public CombinedCategoryPlotDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static CategoryDataset createDataset1() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        String series1 = "First";
        String series2 = "Second";

        // column keys...
        String type1 = "Type 1";
        String type2 = "Type 2";
        String type3 = "Type 3";
        String type4 = "Type 4";
        String type5 = "Type 5";
        String type6 = "Type 6";
        String type7 = "Type 7";
        String type8 = "Type 8";

        result.addValue(1.0, series1, type1);
        result.addValue(4.0, series1, type2);
        result.addValue(3.0, series1, type3);
        result.addValue(5.0, series1, type4);
        result.addValue(5.0, series1, type5);
        result.addValue(7.0, series1, type6);
        result.addValue(7.0, series1, type7);
        result.addValue(8.0, series1, type8);

        result.addValue(5.0, series2, type1);
        result.addValue(7.0, series2, type2);
        result.addValue(6.0, series2, type3);
        result.addValue(8.0, series2, type4);
        result.addValue(4.0, series2, type5);
        result.addValue(4.0, series2, type6);
        result.addValue(2.0, series2, type7);
        result.addValue(1.0, series2, type8);

        return result;
    }

    public static CategoryDataset createDataset2() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        // row keys...
        String series1 = "Third";
        String series2 = "Fourth";

        // column keys...
        String sector1 = "Sector 1";
        String sector2 = "Sector 2";
        String sector3 = "Sector 3";
        String sector4 = "Sector 4";

        result.addValue(11.0, series1, sector1);
        result.addValue(14.0, series1, sector2);
        result.addValue(13.0, series1, sector3);
        result.addValue(15.0, series1, sector4);

        result.addValue(15.0, series2, sector1);
        result.addValue(17.0, series2, sector2);
        result.addValue(16.0, series2, sector3);
        result.addValue(18.0, series2, sector4);

        return result;
    }

    private static JFreeChart createChart() {

        CategoryDataset dataset1 = createDataset1();
        CategoryAxis domainAxis1 = new CategoryAxis("Class 1");
        domainAxis1.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis1.setMaximumCategoryLabelWidthRatio(5.0f);
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot1 = new CategoryPlot(dataset1, domainAxis1, null, renderer1);
        subplot1.setDomainGridlinesVisible(true);

        CategoryDataset dataset2 = createDataset2();
        CategoryAxis domainAxis2 = new CategoryAxis("Class 2");
        domainAxis2.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        domainAxis2.setMaximumCategoryLabelWidthRatio(5.0f);
        BarRenderer renderer2 = new BarRenderer();
        renderer2.setDefaultToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot subplot2 = new CategoryPlot(dataset2, domainAxis2, null, renderer2);
        subplot2.setDomainGridlinesVisible(true);

        ValueAxis rangeAxis = new NumberAxis("Value");
        CombinedRangeCategoryPlot plot = new CombinedRangeCategoryPlot(rangeAxis);
        plot.add(subplot1, 3);
        plot.add(subplot2, 2);
        plot.setOrientation(PlotOrientation.HORIZONTAL);

        JFreeChart result = new JFreeChart(
                "Combined Range Category Plot Demo",
                new Font("SansSerif", Font.BOLD, 12),
                plot,
                true
        );
        ChartUtils.applyCurrentTheme(result);
        return result;

    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        String title = "Combined Category Plot Demo 2";
        CombinedCategoryPlotDemo2 demo = new CombinedCategoryPlotDemo2(title);
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
