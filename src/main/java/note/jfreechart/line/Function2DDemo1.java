package note.jfreechart.line;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.function.Function2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class Function2DDemo1 extends ApplicationFrame {
    public Function2DDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Function2DDemo1 ", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        ValueAxis xAxis = plot.getDomainAxis();
        xAxis.setLowerMargin(0.0F);
        xAxis.setUpperMargin(0.0F);
        xAxis.setRange(-2.0F, 2.0F);
        ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setRange(0.0F, 5.0F);
        return chart;
    }

    public static XYDataset createDataset() {
        XYDataset result = DatasetUtils.sampleFunction2D(new X2(), -40.0, 40.0, 400, "f(x)");
        return result;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public static void main(String[] args) {
        Function2DDemo1 demo = new Function2DDemo1("JFreeChart: Function2DDemo1.java");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static class X2 implements Function2D {
        public double getValue(double x) {
            return x * x + (double) 2.0F;
        }
    }
}
