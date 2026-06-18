package note.jfreechart.line;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYShapeRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

import javax.swing.*;
import java.awt.*;

public class XYShapeRendererDemo1 extends ApplicationFrame {

    public XYShapeRendererDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("X");
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis("Y");
        yAxis.setAutoRangeIncludesZero(false);

        XYShapeRenderer renderer = new XYShapeRenderer();
        LookupPaintScale ps = new LookupPaintScale(1.0F, 4.0F, new Color(0, 0, 255));
        ps.add(2.0F, new Color(100, 100, 255));
        ps.add(3.0F, new Color(200, 200, 255));
        renderer.setPaintScale(ps);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainCrosshairVisible(true);
        plot.setDomainCrosshairLockedOnData(true);
        plot.setRangeCrosshairVisible(true);
        plot.setRangeCrosshairLockedOnData(true);

        JFreeChart chart = new JFreeChart("XYShapeRendererDemo1", plot);
        chart.removeLegend();
        NumberAxis zAxis = new NumberAxis("Score");
        zAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        PaintScaleLegend psl = new PaintScaleLegend(ps, zAxis);
        psl.setPosition(RectangleEdge.RIGHT);
        psl.setMargin((double) 4.0F, (double) 4.0F, (double) 40.0F, (double) 4.0F);
        psl.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        chart.addSubtitle(psl);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    public static XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] x = new double[]{2.1, 2.3, 2.3, 2.2, 2.2, 1.8, 1.8, 1.9, 2.3, 2.8};
        double[] y = new double[]{14.1, 17.1, (double) 10.0F, 8.8, 8.7, 8.4, 5.4, 4.1, 4.1, (double) 25.0F};
        double[] z = new double[]{2.4, 2.7, 1.7, 2.2, 1.3, 2.2, 2.1, 3.2, 1.6, 3.4};
        double[][] series = new double[][]{x, y, z};
        dataset.addSeries("Series 1", series);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        return chartPanel;
    }

    public static void main(String[] args) {
        XYShapeRendererDemo1 demo = new XYShapeRendererDemo1("JFreeChart: XYShapeRendererDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
