package note.jfreechart.stat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * A line chart displaying several normal distribution functions.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jul 2025, 1:27 PM
 */
public class NormalDistributionDemo2 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public NormalDistributionDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Normal Distributions", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setLowerMargin(0.0D);
        xAxis.setUpperMargin(0.0D);
        XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) plot.getRenderer();
        r.setDrawSeriesLineAsPath(true);
        r.setSeriesStroke(0, new BasicStroke(1.5F));
        r.setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{6.0F, 4.0F}, 0.0F));
        r.setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{6.0F, 4.0F, 3.0F, 3.0F}, 0.0F));
        r.setSeriesStroke(3, new BasicStroke(2.0F, 1, 1, 1.0F, new float[]{4.0F, 4.0F}, 0.0F));
        XYPointerAnnotation a1 = new XYPointerAnnotation("μ = -2.0, σ² = 0.5", -2.0D, 0.564D, 3.9269908169872414D);
        a1.setLabelOffset(4.0D);
        a1.setTextAnchor(TextAnchor.BOTTOM_RIGHT);
        a1.setBackgroundPaint(Color.YELLOW);
        plot.addAnnotation(a1);
        XYPointerAnnotation a2 = new XYPointerAnnotation("μ = 0.0, σ²= 0.2", 0.225D, 0.8D, 0.0D);
        a2.setLabelOffset(4.0D);
        a2.setTextAnchor(TextAnchor.CENTER_LEFT);
        a2.setBackgroundPaint(new Color(0, 0, 255, 63));
        plot.addAnnotation(a2);
        XYPointerAnnotation a3 = new XYPointerAnnotation("μ = 0.0, σ² = 1.0", 0.75D, 0.3D, 5.497787143782138D);
        a3.setLabelOffset(4.0D);
        a3.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);
        a3.setBackgroundPaint(new Color(255, 0, 0, 63));
        plot.addAnnotation(a3);
        XYPointerAnnotation a4 = new XYPointerAnnotation("μ = 0.0, σ² = 5.0", 3.0D, 0.075D, 4.71238898038469D);
        a4.setLabelOffset(4.0D);
        a4.setTextAnchor(TextAnchor.BOTTOM_CENTER);
        a4.setBackgroundPaint(new Color(0, 255, 0, 63));
        plot.addAnnotation(a4);

//        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    public static XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        NormalDistributionFunction2D f1 = new NormalDistributionFunction2D(0.0, 1.0);
        XYSeries s1 = DatasetUtils.sampleFunction2DToSeries(f1, -5.1, 5.1, 121, "N1");
        dataset.addSeries(s1);
        NormalDistributionFunction2D f2 = new NormalDistributionFunction2D(0.0, Math.sqrt(0.2));
        XYSeries s2 = DatasetUtils.sampleFunction2DToSeries(f2, -5.1, 5.1, 121, "N2");
        dataset.addSeries(s2);
        NormalDistributionFunction2D f3 = new NormalDistributionFunction2D(0.0, Math.sqrt(5.0));
        XYSeries s3 = DatasetUtils.sampleFunction2DToSeries(f3, -5.1, 5.1, 121, "N3");
        dataset.addSeries(s3);
        NormalDistributionFunction2D f4 = new NormalDistributionFunction2D(-2.0, Math.sqrt(0.5));
        XYSeries s4 = DatasetUtils.sampleFunction2DToSeries(f4, -5.1, 5.1, 121, "N4");
        dataset.addSeries(s4);

        return dataset;
    }

    public static void main(String[] args) {
        NormalDistributionDemo2 demo = new NormalDistributionDemo2("JFreeChart: NormalDistributionDemo2.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
