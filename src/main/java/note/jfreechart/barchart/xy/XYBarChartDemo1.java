package note.jfreechart.barchart.xy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.IntervalXYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * A bar chart created using data from a <code>TimeSeriesCollection</code>.  The
 * chart uses a subtitle to cite the data source.
 *
 * @since 2025-06-17⭐
 */
public class XYBarChartDemo1 extends ApplicationFrame {

    /**
     * Constructs the demo application.
     *
     * @param title the frame title.
     */
    public XYBarChartDemo1(String title) {

        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    private static JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart(
                "State Executions - USA",
                "Year",
                true,
                "Number of People",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        // then customise it a little...
        chart.addSubtitle(new TextTitle(
                "Source: http://www.amnestyusa.org/abolish/listbyyear.do",
                new Font("Dialog", Font.ITALIC, 10)));
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator(
                "{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
        renderer.setDefaultToolTipGenerator(generator);
        renderer.setMargin(0.1);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        axis.setLowerMargin(0.01);
        axis.setUpperMargin(0.01);
        ChartUtils.applyCurrentTheme(chart);
        return chart;
    }

    private static IntervalXYDataset createDataset() {

        TimeSeries t1 = new TimeSeries("Executions", "Year", "Count");
        try {
            t1.add(new Year(1976), 0.0);
            t1.add(new Year(1977), 1.0);
            t1.add(new Year(1978), 0.0);
            t1.add(new Year(1979), 2.0);
            t1.add(new Year(1980), 0.0);
            t1.add(new Year(1981), 1.0);
            t1.add(new Year(1982), 2.0);
            t1.add(new Year(1983), 5.0);
            t1.add(new Year(1984), 21.0);
            t1.add(new Year(1985), 18.0);
            t1.add(new Year(1986), 18.0);
            t1.add(new Year(1987), 25.0);
            t1.add(new Year(1988), 11.0);
            t1.add(new Year(1989), 16.0);
            t1.add(new Year(1990), 23.0);
            t1.add(new Year(1991), 14.0);
            t1.add(new Year(1992), 31.0);
            t1.add(new Year(1993), 38.0);
            t1.add(new Year(1994), 31.0);
            t1.add(new Year(1995), 56.0);
            t1.add(new Year(1996), 45.0);
            t1.add(new Year(1997), 74.0);
            t1.add(new Year(1998), 68.0);
            t1.add(new Year(1999), 98.0);
            t1.add(new Year(2000), 85.0);
            t1.add(new Year(2001), 66.0);
            t1.add(new Year(2002), 71.0);
            t1.add(new Year(2003), 65.0);
            t1.add(new Year(2004), 59.0);
            t1.add(new Year(2005), 60.0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new TimeSeriesCollection(t1);
    }

    public static JPanel createDemoPanel() {
        return new ChartPanel(createChart(createDataset()), false);
    }

    public static void main(String[] args) {
        XYBarChartDemo1 demo = new XYBarChartDemo1("State Executions - USA");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
