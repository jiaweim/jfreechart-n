package note.jfreechart;

import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CrosshairOverlayDemo2 extends JFrame {

    public CrosshairOverlayDemo2(String title) {
        super(title);
        this.setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new MyDemoPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CrosshairOverlayDemo2 app = new CrosshairOverlayDemo2("JFreeChart: CrosshairOverlayDemo2.java");
                app.pack();
                app.setVisible(true);
            }
        });
    }

    static class MyDemoPanel extends JPanel implements ChartMouseListener {

        private static final int SERIES_COUNT = 4;

        private ChartPanel chartPanel;
        private Crosshair xCrosshair;
        private Crosshair[] yCrosshairs;

        public MyDemoPanel() {
            super(new BorderLayout());
            JFreeChart chart = this.createChart(this.createDataset());
            this.chartPanel = new ChartPanel(chart);
            this.chartPanel.addChartMouseListener(this);
            CrosshairOverlay crosshairOverlay = new CrosshairOverlay();
            this.xCrosshair = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
            this.xCrosshair.setLabelVisible(true);
            crosshairOverlay.addDomainCrosshair(this.xCrosshair);
            this.yCrosshairs = new Crosshair[4];

            for (int i = 0; i < SERIES_COUNT; ++i) {
                this.yCrosshairs[i] = new Crosshair(Double.NaN, Color.GRAY, new BasicStroke(0.5F));
                this.yCrosshairs[i].setLabelVisible(true);
                if (i % 2 != 0) {
                    this.yCrosshairs[i].setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                }

                crosshairOverlay.addRangeCrosshair(this.yCrosshairs[i]);
            }

            this.chartPanel.addOverlay(crosshairOverlay);
            this.add(this.chartPanel);
        }

        private JFreeChart createChart(XYDataset dataset) {
            JFreeChart chart = ChartFactory.createXYLineChart("CrosshairOverlayDemo2", "X", "Y", dataset);
            return chart;
        }

        private XYDataset createDataset() {
            XYSeriesCollection dataset = new XYSeriesCollection();
            for (int i = 0; i < 4; ++i) {
                XYSeries series = new XYSeries("S" + i);
                for (int x = 0; x < 10; ++x) {
                    series.add(x, x + Math.random() * 4.0);
                }
                dataset.addSeries(series);
            }

            return dataset;
        }

        public void chartMouseClicked(ChartMouseEvent event) {
        }

        public void chartMouseMoved(ChartMouseEvent event) {
            Rectangle2D dataArea = this.chartPanel.getScreenDataArea();
            JFreeChart chart = event.getChart();
            XYPlot plot = (XYPlot) chart.getPlot();
            ValueAxis xAxis = plot.getDomainAxis();
            double x = xAxis.java2DToValue(event.getTrigger().getX(), dataArea, RectangleEdge.BOTTOM);
            this.xCrosshair.setValue(x);

            for (int i = 0; i < 4; ++i) {
                double y = DatasetUtils.findYValue(plot.getDataset(), i, x);
                this.yCrosshairs[i].setValue(y);
            }
        }
    }
}
