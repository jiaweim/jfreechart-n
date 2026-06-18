//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package note.jfreechart;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYSplineRendererDemo1 extends ApplicationFrame {
    public XYSplineRendererDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        this.getContentPane().add(content);
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        XYSplineRendererDemo1 appFrame = new XYSplineRendererDemo1("JFreeChart: XYSplineRendererDemo1.java");
        appFrame.pack();
        UIUtils.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

    static class MyDemoPanel extends DemoPanel {
        private final XYDataset data1 = this.createSampleData();

        public MyDemoPanel() {
            super(new BorderLayout());
            this.add(this.createContent());
        }

        private XYDataset createSampleData() {
            XYSeries series = new XYSeries("Series 1");
            series.add((double)2.0F, 56.27);
            series.add((double)3.0F, 41.32);
            series.add((double)4.0F, 31.45);
            series.add((double)5.0F, 30.05);
            series.add((double)6.0F, 24.69);
            series.add((double)7.0F, 19.78);
            series.add((double)8.0F, 20.94);
            series.add((double)9.0F, 16.73);
            series.add((double)10.0F, 14.21);
            series.add((double)11.0F, 12.44);
            XYSeriesCollection result = new XYSeriesCollection(series);
            XYSeries series2 = new XYSeries("Series 2");
            series2.add((double)11.0F, 56.27);
            series2.add((double)10.0F, 41.32);
            series2.add((double)9.0F, 31.45);
            series2.add((double)8.0F, 30.05);
            series2.add((double)7.0F, 24.69);
            series2.add((double)6.0F, 19.78);
            series2.add((double)5.0F, 20.94);
            series2.add((double)4.0F, 16.73);
            series2.add((double)3.0F, 14.21);
            series2.add((double)2.0F, 12.44);
            result.addSeries(series2);
            return result;
        }

        private JTabbedPane createContent() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Splines:", this.createChartPanel1());
            tabs.add("Lines:", this.createChartPanel2());
            return tabs;
        }

        private ChartPanel createChartPanel1() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYSplineRenderer renderer1 = new XYSplineRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.LIGHT_GRAY);
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setAxisOffset(new RectangleInsets((double)4.0F, (double)4.0F, (double)4.0F, (double)4.0F));
            JFreeChart chart = new JFreeChart("XYSplineRenderer", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            this.addChart(chart);
            ChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }

        private ChartPanel createChartPanel2() {
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);
            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);
            plot.setBackgroundPaint(Color.LIGHT_GRAY);
            plot.setDomainGridlinePaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.WHITE);
            plot.setAxisOffset(new RectangleInsets((double)4.0F, (double)4.0F, (double)4.0F, (double)4.0F));
            JFreeChart chart = new JFreeChart("XYLineAndShapeRenderer", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            this.addChart(chart);
            ChartUtils.applyCurrentTheme(chart);
            ChartPanel chartPanel = new ChartPanel(chart);
            return chartPanel;
        }
    }
}
