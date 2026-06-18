package note.jfreechart.time;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class JTimeSeriesDemo1 extends ApplicationFrame {
    public JTimeSeriesDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(false);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(false);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return chart;
    }

    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("L&G European Index Trust");
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), (double) 137.0F);
        s1.add(new Month(7, 2002), 132.8);
        s1.add(new Month(8, 2002), 110.3);
        s1.add(new Month(9, 2002), (double) 110.5F);
        s1.add(new Month(10, 2002), 94.11);
        s1.add(new Month(11, 2002), (double) 102.5F);
        s1.add(new Month(12, 2002), 112.3);
        s1.add(new Month(1, 2003), (double) 104.0F);
        s1.add(new Month(2, 2003), 98.53);
        s1.add(new Month(3, 2003), 97.15);
        s1.add(new Month(4, 2003), 94.9);
        s1.add(new Month(5, 2003), 107.8);
        s1.add(new Month(6, 2003), 113.7);
        s1.add(new Month(7, 2003), (double) 112.5F);
        s1.add(new Month(8, 2003), 118.6);
        s1.add(new Month(9, 2003), 123.8);
        s1.add(new Month(10, 2003), 117.2);
        s1.add(new Month(11, 2003), (double) 123.0F);
        s1.add(new Month(12, 2003), (double) 127.0F);
        s1.add(new Month(1, 2004), 132.7);
        s1.add(new Month(2, 2004), 132.4);
        s1.add(new Month(3, 2004), 131.7);
        s1.add(new Month(4, 2004), (double) 128.0F);
        s1.add(new Month(5, 2004), 131.8);
        s1.add(new Month(6, 2004), 127.4);
        s1.add(new Month(7, 2004), (double) 133.5F);
        s1.add(new Month(8, 2004), (double) 126.0F);
        s1.add(new Month(9, 2004), (double) 129.5F);
        s1.add(new Month(10, 2004), 135.3);
        s1.add(new Month(11, 2004), (double) 138.0F);
        s1.add(new Month(12, 2004), 141.3);
        s1.add(new Month(1, 2005), 148.8);
        s1.add(new Month(2, 2005), 147.1);
        s1.add(new Month(3, 2005), 150.7);
        s1.add(new Month(4, 2005), (double) 150.0F);
        s1.add(new Month(5, 2005), 145.7);
        s1.add(new Month(6, 2005), (double) 152.0F);
        s1.add(new Month(7, 2005), 157.2);
        s1.add(new Month(8, 2005), (double) 167.0F);
        s1.add(new Month(9, 2005), (double) 165.0F);
        s1.add(new Month(10, 2005), 171.6);
        s1.add(new Month(11, 2005), 166.2);
        s1.add(new Month(12, 2005), 174.3);
        s1.add(new Month(1, 2006), 183.8);
        s1.add(new Month(2, 2006), (double) 187.0F);
        s1.add(new Month(3, 2006), 191.3);
        s1.add(new Month(4, 2006), (double) 202.5F);
        s1.add(new Month(5, 2006), 200.6);
        s1.add(new Month(6, 2006), 187.3);
        s1.add(new Month(7, 2006), 192.2);
        s1.add(new Month(8, 2006), 190.8);
        s1.add(new Month(9, 2006), 194.7);
        s1.add(new Month(10, 2006), 201.3);
        s1.add(new Month(11, 2006), 205.1);
        s1.add(new Month(12, 2006), 206.7);
        s1.add(new Month(1, 2007), 216.8);
        s1.add(new Month(2, 2007), (double) 218.0F);
        s1.add(new Month(3, 2007), 215.4);
        s1.add(new Month(4, 2007), (double) 223.0F);
        s1.add(new Month(5, 2007), 235.1);
        s1.add(new Month(6, 2007), (double) 242.0F);
        s1.add(new Month(7, 2007), 237.8);
        TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), (double) 116.5F);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), (double) 101.5F);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), (double) 111.0F);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        s2.add(new Month(8, 2002), 90.95);
        s2.add(new Month(9, 2002), 91.02);
        s2.add(new Month(10, 2002), 82.37);
        s2.add(new Month(11, 2002), 86.32);
        s2.add(new Month(12, 2002), (double) 91.0F);
        s2.add(new Month(1, 2003), (double) 86.0F);
        s2.add(new Month(2, 2003), 80.04);
        s2.add(new Month(3, 2003), 80.4);
        s2.add(new Month(4, 2003), 80.28);
        s2.add(new Month(5, 2003), 86.42);
        s2.add(new Month(6, 2003), 91.4);
        s2.add(new Month(7, 2003), 90.52);
        s2.add(new Month(8, 2003), 93.11);
        s2.add(new Month(9, 2003), 96.8);
        s2.add(new Month(10, 2003), 94.78);
        s2.add(new Month(11, 2003), 99.56);
        s2.add(new Month(12, 2003), 100.8);
        s2.add(new Month(1, 2004), 103.4);
        s2.add(new Month(2, 2004), 102.1);
        s2.add(new Month(3, 2004), 105.3);
        s2.add(new Month(4, 2004), 103.7);
        s2.add(new Month(5, 2004), 105.2);
        s2.add(new Month(6, 2004), 103.7);
        s2.add(new Month(7, 2004), 105.7);
        s2.add(new Month(8, 2004), 103.6);
        s2.add(new Month(9, 2004), 106.1);
        s2.add(new Month(10, 2004), 109.3);
        s2.add(new Month(11, 2004), 110.3);
        s2.add(new Month(12, 2004), 112.6);
        s2.add(new Month(1, 2005), (double) 116.0F);
        s2.add(new Month(2, 2005), 117.3);
        s2.add(new Month(3, 2005), 120.1);
        s2.add(new Month(4, 2005), 119.3);
        s2.add(new Month(5, 2005), 116.2);
        s2.add(new Month(6, 2005), 120.8);
        s2.add(new Month(7, 2005), 125.2);
        s2.add(new Month(8, 2005), 127.7);
        s2.add(new Month(9, 2005), 130.8);
        s2.add(new Month(10, 2005), (double) 131.0F);
        s2.add(new Month(11, 2005), 135.3);
        s2.add(new Month(12, 2005), 141.2);
        s2.add(new Month(1, 2006), 144.7);
        s2.add(new Month(2, 2006), 146.4);
        s2.add(new Month(3, 2006), 151.9);
        s2.add(new Month(4, 2006), (double) 153.5F);
        s2.add(new Month(5, 2006), (double) 144.5F);
        s2.add(new Month(6, 2006), 150.1);
        s2.add(new Month(7, 2006), 148.7);
        s2.add(new Month(8, 2006), 150.1);
        s2.add(new Month(9, 2006), 151.6);
        s2.add(new Month(10, 2006), 153.4);
        s2.add(new Month(11, 2006), 158.3);
        s2.add(new Month(12, 2006), 157.6);
        s2.add(new Month(1, 2007), 163.9);
        s2.add(new Month(2, 2007), 163.8);
        s2.add(new Month(3, 2007), (double) 162.0F);
        s2.add(new Month(4, 2007), 167.1);
        s2.add(new Month(5, 2007), (double) 170.0F);
        s2.add(new Month(6, 2007), 175.7);
        s2.add(new Month(7, 2007), 171.9);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        JTimeSeriesDemo1 demo = new JTimeSeriesDemo1("Time Series Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
