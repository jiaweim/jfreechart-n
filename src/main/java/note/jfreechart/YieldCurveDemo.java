package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTick;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.*;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * A yield curve demo.  This illustrates how to override the refreshTicks()
 * method in the DateAxis class to get the axis to display a custom set of
 * tick labels.
 */
public class YieldCurveDemo extends ApplicationFrame {

    /**
     * A custom date axis.
     */
    static class CustomDateAxis extends DateAxis {

        private Date base;

        /**
         * Creates a new axis.
         *
         * @param label the axis label.
         * @param base  the base date.
         */
        public CustomDateAxis(String label, Date base) {
            super(label);
            this.base = base;
        }

        /**
         * Return a custom list of ticks.  This is a crude implementation, it
         * doesn't take into account the plot orientation or the axis location
         * (assumes the bottom of the chart) or attempt to change the number
         * of tick labels to avoid overlapping (we've omitted the 3m and 6m
         * labels manually, but it would be possible to write code to compute
         * this).
         *
         * @param g2       the graphics target.
         * @param state    the axis state.
         * @param dataArea the data area.
         * @param edge     the edge along which the axis lies.
         * @return A list of ticks.
         */
        public List refreshTicks(Graphics2D g2, AxisState state,
                Rectangle2D dataArea, RectangleEdge edge) {
            List result = new ArrayList();
            // start with base
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(this.base);
            cal.add(Calendar.MONTH, 1);
            result.add(new DateTick(cal.getTime(), "1M", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.MONTH, 5);
            //result.add(new DateTick(cal.getTime(), "6M", 
            //    TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0));
            cal.add(Calendar.MONTH, 6);
            result.add(new DateTick(cal.getTime(), "1Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.YEAR, 1);
            result.add(new DateTick(cal.getTime(), "2Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.YEAR, 1);
            result.add(new DateTick(cal.getTime(), "3Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.YEAR, 2);
            result.add(new DateTick(cal.getTime(), "5Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.YEAR, 5);
            result.add(new DateTick(cal.getTime(), "10Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            cal.add(Calendar.YEAR, 10);
            result.add(new DateTick(cal.getTime(), "20Y", TextAnchor.TOP_CENTER,
                    TextAnchor.CENTER, 0.0));
            return result;
        }
    }

    /**
     * A demonstration application showing how to create a simple time series
     * chart.  This example uses monthly data.
     *
     * @param title the frame title.
     */
    public YieldCurveDemo(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart.
     *
     * @param dataset a dataset.
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "US$ Treasury Yields",  // title
                "Date",             // x-axis label
                "Yield",            // y-axis label
                dataset,            // data
                false,               // create legend?
                true,               // generate tooltips?
                false               // generate URLs?
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        GregorianCalendar cal = new GregorianCalendar(2005, Calendar.NOVEMBER,
                15);
        plot.setDomainAxis(new CustomDateAxis("Date", cal.getTime()));
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        chart.addSubtitle(new TextTitle("November 2005"));
        TextTitle source = new TextTitle(
                "Source: http://www.econstats.com/r/r_am1.htm");
        source.setFont(new Font("Dialog", Font.PLAIN, 9));
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);
        return chart;

    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    private static XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries("US$ Treasury Yields");
        Day m1 = new Day(1, 12, 2005);
        Day m3 = new Day(1, 2, 2006);
        Day m6 = new Day(1, 5, 2006);
        Day y1 = new Day(1, 12, 2006);
        Day y2 = new Day(1, 12, 2007);
        Day y3 = new Day(1, 12, 2008);
        Day y5 = new Day(1, 12, 2010);
        Day y7 = new Day(1, 12, 2012);
        Day y10 = new Day(1, 12, 2015);
        Day y20 = new Day(1, 12, 2025);

        s1.add(m1, 3.79);
        s1.add(m3, 3.995);
        s1.add(m6, 4.26);
        s1.add(y1, 4.3225);
        s1.add(y2, 4.4475);
        s1.add(y3, 4.475);
        s1.add(y5, 4.52);
        s1.add(y7, 4.56);
        s1.add(y10, 4.625);
        s1.add(y20, 4.905);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

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
        YieldCurveDemo demo = new YieldCurveDemo("Yield Curve Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
