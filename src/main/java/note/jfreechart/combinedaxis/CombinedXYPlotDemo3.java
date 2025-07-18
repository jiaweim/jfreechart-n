package note.jfreechart.combinedaxis;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.date.MonthConstants;
import org.jfree.chart.plot.CombinedRangeXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * A demonstration application showing how to create a combined chart using...
 */
public class CombinedXYPlotDemo3 extends ApplicationFrame {

    /**
     * Constructs a new demonstration application.
     *
     * @param title the frame title.
     */
    public CombinedXYPlotDemo3(String title) {

        super(title);
        JFreeChart chart = createCombinedChart();
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
    }

    /**
     * Creates a combined XYPlot chart.
     *
     * @return the combined chart.
     */
    private static JFreeChart createCombinedChart() {

        // create a default chart based on some sample data...
        TimeSeriesCollection dataset0 = new TimeSeriesCollection();
        TimeSeries eur = createEURTimeSeries();
        dataset0.addSeries(eur);

        TimeSeriesCollection dataset1 = new TimeSeriesCollection();
        TimeSeries mav = MovingAverage.createMovingAverage(
                eur, "EUR/GBP (30 Day MA)", 30, 30
        );
        dataset1.addSeries(eur);
        dataset1.addSeries(mav);

        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(eur);

        JFreeChart chart = null;

        // make a common vertical axis for all the sub-plots
        NumberAxis valueAxis = new NumberAxis("Value");
        valueAxis.setAutoRangeIncludesZero(false);  // override default

        // make a horizontally combined plot
        CombinedRangeXYPlot parent = new CombinedRangeXYPlot(valueAxis);

        // add subplot 1...
        XYPlot subplot1 = new XYPlot(dataset0, new DateAxis("Date 1"), null,
                new StandardXYItemRenderer());
        parent.add(subplot1, 1);

        // add subplot 2...
        XYPlot subplot2 = new XYPlot(dataset1, new DateAxis("Date 2"), null,
                new StandardXYItemRenderer());
        parent.add(subplot2, 1);

        // add subplot 3...
        XYPlot subplot3 = new XYPlot(dataset2, new DateAxis("Date 3"),
                null, new XYBarRenderer(0.20));
        parent.add(subplot3, 1);

        // now make the top level JFreeChart
        chart = new JFreeChart("Demo Chart", JFreeChart.DEFAULT_TITLE_FONT, parent, true);

        // then customise it a little...
        TextTitle subtitle = new TextTitle("This is a subtitle",
                new Font("SansSerif", Font.BOLD, 12));
        chart.addSubtitle(subtitle);
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));
        return chart;

    }

    /**
     * Returns a time series of the daily EUR/GBP exchange rates in 2001 (to date), for use in
     * the JFreeChart demonstration application.
     * <p>
     * You wouldn't normally create a time series in this way.  Typically, values would
     * be read from a database.
     *
     * @return A time series.
     */
    public static TimeSeries createEURTimeSeries() {

        TimeSeries t1 = new TimeSeries("EUR/GBP");
        try {
            t1.add(new Day(2, MonthConstants.JANUARY, 2001), 1.5788);
            t1.add(new Day(3, MonthConstants.JANUARY, 2001), 1.5913);
            t1.add(new Day(4, MonthConstants.JANUARY, 2001), 1.5807);
            t1.add(new Day(5, MonthConstants.JANUARY, 2001), 1.5711);
            t1.add(new Day(8, MonthConstants.JANUARY, 2001), 1.5778);
            t1.add(new Day(9, MonthConstants.JANUARY, 2001), 1.5851);
            t1.add(new Day(10, MonthConstants.JANUARY, 2001), 1.5846);
            t1.add(new Day(11, MonthConstants.JANUARY, 2001), 1.5727);
            t1.add(new Day(12, MonthConstants.JANUARY, 2001), 1.5585);
            t1.add(new Day(15, MonthConstants.JANUARY, 2001), 1.5694);
            t1.add(new Day(16, MonthConstants.JANUARY, 2001), 1.5629);
            t1.add(new Day(17, MonthConstants.JANUARY, 2001), 1.5831);
            t1.add(new Day(18, MonthConstants.JANUARY, 2001), 1.5624);
            t1.add(new Day(19, MonthConstants.JANUARY, 2001), 1.5694);
            t1.add(new Day(22, MonthConstants.JANUARY, 2001), 1.5615);
            t1.add(new Day(23, MonthConstants.JANUARY, 2001), 1.5656);
            t1.add(new Day(24, MonthConstants.JANUARY, 2001), 1.5795);
            t1.add(new Day(25, MonthConstants.JANUARY, 2001), 1.5852);
            t1.add(new Day(26, MonthConstants.JANUARY, 2001), 1.5797);
            t1.add(new Day(29, MonthConstants.JANUARY, 2001), 1.5862);
            t1.add(new Day(30, MonthConstants.JANUARY, 2001), 1.5803);
            t1.add(new Day(31, MonthConstants.JANUARY, 2001), 1.5714);
            t1.add(new Day(1, MonthConstants.FEBRUARY, 2001), 1.5717);
            t1.add(new Day(2, MonthConstants.FEBRUARY, 2001), 1.5735);
            t1.add(new Day(5, MonthConstants.FEBRUARY, 2001), 1.5691);
            t1.add(new Day(6, MonthConstants.FEBRUARY, 2001), 1.5676);
            t1.add(new Day(7, MonthConstants.FEBRUARY, 2001), 1.5677);
            t1.add(new Day(8, MonthConstants.FEBRUARY, 2001), 1.5737);
            t1.add(new Day(9, MonthConstants.FEBRUARY, 2001), 1.5654);
            t1.add(new Day(12, MonthConstants.FEBRUARY, 2001), 1.5621);
            t1.add(new Day(13, MonthConstants.FEBRUARY, 2001), 1.5761);
            t1.add(new Day(14, MonthConstants.FEBRUARY, 2001), 1.5898);
            t1.add(new Day(15, MonthConstants.FEBRUARY, 2001), 1.6045);
            t1.add(new Day(16, MonthConstants.FEBRUARY, 2001), 1.5852);
            t1.add(new Day(19, MonthConstants.FEBRUARY, 2001), 1.5704);
            t1.add(new Day(20, MonthConstants.FEBRUARY, 2001), 1.5892);
            t1.add(new Day(21, MonthConstants.FEBRUARY, 2001), 1.5844);
            t1.add(new Day(22, MonthConstants.FEBRUARY, 2001), 1.5934);
            t1.add(new Day(23, MonthConstants.FEBRUARY, 2001), 1.5951);
            t1.add(new Day(26, MonthConstants.FEBRUARY, 2001), 1.5848);
            t1.add(new Day(27, MonthConstants.FEBRUARY, 2001), 1.5706);
            t1.add(new Day(28, MonthConstants.FEBRUARY, 2001), 1.5680);
            t1.add(new Day(1, MonthConstants.MARCH, 2001), 1.5645);
            t1.add(new Day(2, MonthConstants.MARCH, 2001), 1.5754);
            t1.add(new Day(5, MonthConstants.MARCH, 2001), 1.5808);
            t1.add(new Day(6, MonthConstants.MARCH, 2001), 1.5766);
            t1.add(new Day(7, MonthConstants.MARCH, 2001), 1.5756);
            t1.add(new Day(8, MonthConstants.MARCH, 2001), 1.5760);
            t1.add(new Day(9, MonthConstants.MARCH, 2001), 1.5748);
            t1.add(new Day(12, MonthConstants.MARCH, 2001), 1.5779);
            t1.add(new Day(13, MonthConstants.MARCH, 2001), 1.5837);
            t1.add(new Day(14, MonthConstants.MARCH, 2001), 1.5886);
            t1.add(new Day(15, MonthConstants.MARCH, 2001), 1.5931);
            t1.add(new Day(16, MonthConstants.MARCH, 2001), 1.5945);
            t1.add(new Day(19, MonthConstants.MARCH, 2001), 1.5880);
            t1.add(new Day(20, MonthConstants.MARCH, 2001), 1.5817);
            t1.add(new Day(21, MonthConstants.MARCH, 2001), 1.5927);
            t1.add(new Day(22, MonthConstants.MARCH, 2001), 1.6065);
            t1.add(new Day(23, MonthConstants.MARCH, 2001), 1.6006);
            t1.add(new Day(26, MonthConstants.MARCH, 2001), 1.6007);
            t1.add(new Day(27, MonthConstants.MARCH, 2001), 1.5989);
            t1.add(new Day(28, MonthConstants.MARCH, 2001), 1.6135);
            t1.add(new Day(29, MonthConstants.MARCH, 2001), 1.6282);
            t1.add(new Day(30, MonthConstants.MARCH, 2001), 1.6090);
            t1.add(new Day(2, MonthConstants.APRIL, 2001), 1.6107);
            t1.add(new Day(3, MonthConstants.APRIL, 2001), 1.6093);
            t1.add(new Day(4, MonthConstants.APRIL, 2001), 1.5880);
            t1.add(new Day(5, MonthConstants.APRIL, 2001), 1.5931);
            t1.add(new Day(6, MonthConstants.APRIL, 2001), 1.5968);
            t1.add(new Day(9, MonthConstants.APRIL, 2001), 1.6072);
            t1.add(new Day(10, MonthConstants.APRIL, 2001), 1.6167);
            t1.add(new Day(11, MonthConstants.APRIL, 2001), 1.6214);
            t1.add(new Day(12, MonthConstants.APRIL, 2001), 1.6120);
            t1.add(new Day(17, MonthConstants.APRIL, 2001), 1.6229);
            t1.add(new Day(18, MonthConstants.APRIL, 2001), 1.6298);
            t1.add(new Day(19, MonthConstants.APRIL, 2001), 1.6159);
            t1.add(new Day(20, MonthConstants.APRIL, 2001), 1.5996);
            t1.add(new Day(23, MonthConstants.APRIL, 2001), 1.6042);
            t1.add(new Day(24, MonthConstants.APRIL, 2001), 1.6061);
            t1.add(new Day(25, MonthConstants.APRIL, 2001), 1.6045);
            t1.add(new Day(26, MonthConstants.APRIL, 2001), 1.5970);
            t1.add(new Day(27, MonthConstants.APRIL, 2001), 1.6095);
            t1.add(new Day(30, MonthConstants.APRIL, 2001), 1.6141);
            t1.add(new Day(1, MonthConstants.MAY, 2001), 1.6076);
            t1.add(new Day(2, MonthConstants.MAY, 2001), 1.6077);
            t1.add(new Day(3, MonthConstants.MAY, 2001), 1.6035);
            t1.add(new Day(4, MonthConstants.MAY, 2001), 1.6060);
            t1.add(new Day(8, MonthConstants.MAY, 2001), 1.6178);
            t1.add(new Day(9, MonthConstants.MAY, 2001), 1.6083);
            t1.add(new Day(10, MonthConstants.MAY, 2001), 1.6107);
            t1.add(new Day(11, MonthConstants.MAY, 2001), 1.6209);
            t1.add(new Day(14, MonthConstants.MAY, 2001), 1.6228);
            t1.add(new Day(15, MonthConstants.MAY, 2001), 1.6184);
            t1.add(new Day(16, MonthConstants.MAY, 2001), 1.6167);
            t1.add(new Day(17, MonthConstants.MAY, 2001), 1.6223);
            t1.add(new Day(18, MonthConstants.MAY, 2001), 1.6305);
            t1.add(new Day(21, MonthConstants.MAY, 2001), 1.6420);
            t1.add(new Day(22, MonthConstants.MAY, 2001), 1.6484);
            t1.add(new Day(23, MonthConstants.MAY, 2001), 1.6547);
            t1.add(new Day(24, MonthConstants.MAY, 2001), 1.6444);
            t1.add(new Day(25, MonthConstants.MAY, 2001), 1.6577);
            t1.add(new Day(29, MonthConstants.MAY, 2001), 1.6606);
            t1.add(new Day(30, MonthConstants.MAY, 2001), 1.6604);
            t1.add(new Day(31, MonthConstants.MAY, 2001), 1.6772);
            t1.add(new Day(1, MonthConstants.JUNE, 2001), 1.6717);
            t1.add(new Day(4, MonthConstants.JUNE, 2001), 1.6685);
            t1.add(new Day(5, MonthConstants.JUNE, 2001), 1.6621);
            t1.add(new Day(6, MonthConstants.JUNE, 2001), 1.6460);
            t1.add(new Day(7, MonthConstants.JUNE, 2001), 1.6333);
            t1.add(new Day(8, MonthConstants.JUNE, 2001), 1.6265);
            t1.add(new Day(11, MonthConstants.JUNE, 2001), 1.6311);
            t1.add(new Day(12, MonthConstants.JUNE, 2001), 1.6238);
            t1.add(new Day(13, MonthConstants.JUNE, 2001), 1.6300);
            t1.add(new Day(14, MonthConstants.JUNE, 2001), 1.6289);
            t1.add(new Day(15, MonthConstants.JUNE, 2001), 1.6276);
            t1.add(new Day(18, MonthConstants.JUNE, 2001), 1.6299);
            t1.add(new Day(19, MonthConstants.JUNE, 2001), 1.6353);
            t1.add(new Day(20, MonthConstants.JUNE, 2001), 1.6378);
            t1.add(new Day(21, MonthConstants.JUNE, 2001), 1.6567);
            t1.add(new Day(22, MonthConstants.JUNE, 2001), 1.6523);
            t1.add(new Day(25, MonthConstants.JUNE, 2001), 1.6418);
            t1.add(new Day(26, MonthConstants.JUNE, 2001), 1.6429);
            t1.add(new Day(27, MonthConstants.JUNE, 2001), 1.6439);
            t1.add(new Day(28, MonthConstants.JUNE, 2001), 1.6605);
            t1.add(new Day(29, MonthConstants.JUNE, 2001), 1.6599);
            t1.add(new Day(2, MonthConstants.JULY, 2001), 1.6727);
            t1.add(new Day(3, MonthConstants.JULY, 2001), 1.6620);
            t1.add(new Day(4, MonthConstants.JULY, 2001), 1.6628);
            t1.add(new Day(5, MonthConstants.JULY, 2001), 1.6730);
            t1.add(new Day(6, MonthConstants.JULY, 2001), 1.6649);
            t1.add(new Day(9, MonthConstants.JULY, 2001), 1.6603);
            t1.add(new Day(10, MonthConstants.JULY, 2001), 1.6489);
            t1.add(new Day(11, MonthConstants.JULY, 2001), 1.6421);
            t1.add(new Day(12, MonthConstants.JULY, 2001), 1.6498);
            t1.add(new Day(13, MonthConstants.JULY, 2001), 1.6447);
            t1.add(new Day(16, MonthConstants.JULY, 2001), 1.6373);
            t1.add(new Day(17, MonthConstants.JULY, 2001), 1.6443);
            t1.add(new Day(18, MonthConstants.JULY, 2001), 1.6246);
            t1.add(new Day(19, MonthConstants.JULY, 2001), 1.6295);
            t1.add(new Day(20, MonthConstants.JULY, 2001), 1.6362);
            t1.add(new Day(23, MonthConstants.JULY, 2001), 1.6348);
            t1.add(new Day(24, MonthConstants.JULY, 2001), 1.6242);
            t1.add(new Day(25, MonthConstants.JULY, 2001), 1.6241);
            t1.add(new Day(26, MonthConstants.JULY, 2001), 1.6281);
            t1.add(new Day(27, MonthConstants.JULY, 2001), 1.6296);
            t1.add(new Day(30, MonthConstants.JULY, 2001), 1.6279);
            t1.add(new Day(31, MonthConstants.JULY, 2001), 1.6300);
            t1.add(new Day(1, MonthConstants.AUGUST, 2001), 1.6290);
            t1.add(new Day(2, MonthConstants.AUGUST, 2001), 1.6237);
            t1.add(new Day(3, MonthConstants.AUGUST, 2001), 1.6138);
            t1.add(new Day(6, MonthConstants.AUGUST, 2001), 1.6121);
            t1.add(new Day(7, MonthConstants.AUGUST, 2001), 1.6170);
            t1.add(new Day(8, MonthConstants.AUGUST, 2001), 1.6135);
            t1.add(new Day(9, MonthConstants.AUGUST, 2001), 1.5996);
            t1.add(new Day(10, MonthConstants.AUGUST, 2001), 1.5931);
            t1.add(new Day(13, MonthConstants.AUGUST, 2001), 1.5828);
            t1.add(new Day(14, MonthConstants.AUGUST, 2001), 1.5824);
            t1.add(new Day(15, MonthConstants.AUGUST, 2001), 1.5783);
            t1.add(new Day(16, MonthConstants.AUGUST, 2001), 1.5810);
            t1.add(new Day(17, MonthConstants.AUGUST, 2001), 1.5761);
            t1.add(new Day(20, MonthConstants.AUGUST, 2001), 1.5831);
            t1.add(new Day(21, MonthConstants.AUGUST, 2001), 1.5870);
            t1.add(new Day(22, MonthConstants.AUGUST, 2001), 1.5808);
            t1.add(new Day(23, MonthConstants.AUGUST, 2001), 1.5845);
            t1.add(new Day(24, MonthConstants.AUGUST, 2001), 1.5844);
            t1.add(new Day(28, MonthConstants.AUGUST, 2001), 1.5924);
            t1.add(new Day(29, MonthConstants.AUGUST, 2001), 1.5950);
            t1.add(new Day(30, MonthConstants.AUGUST, 2001), 1.5941);
            t1.add(new Day(31, MonthConstants.AUGUST, 2001), 1.5968);
            t1.add(new Day(3, MonthConstants.SEPTEMBER, 2001), 1.6020);
            t1.add(new Day(4, MonthConstants.SEPTEMBER, 2001), 1.6236);
            t1.add(new Day(5, MonthConstants.SEPTEMBER, 2001), 1.6352);
            t1.add(new Day(6, MonthConstants.SEPTEMBER, 2001), 1.6302);
            t1.add(new Day(7, MonthConstants.SEPTEMBER, 2001), 1.6180);
            t1.add(new Day(10, MonthConstants.SEPTEMBER, 2001), 1.6218);
            t1.add(new Day(11, MonthConstants.SEPTEMBER, 2001), 1.6182);
            t1.add(new Day(12, MonthConstants.SEPTEMBER, 2001), 1.6157);
            t1.add(new Day(13, MonthConstants.SEPTEMBER, 2001), 1.6171);
            t1.add(new Day(14, MonthConstants.SEPTEMBER, 2001), 1.5960);
            t1.add(new Day(17, MonthConstants.SEPTEMBER, 2001), 1.5952);
            t1.add(new Day(18, MonthConstants.SEPTEMBER, 2001), 1.5863);
            t1.add(new Day(19, MonthConstants.SEPTEMBER, 2001), 1.5790);
            t1.add(new Day(20, MonthConstants.SEPTEMBER, 2001), 1.5811);
            t1.add(new Day(21, MonthConstants.SEPTEMBER, 2001), 1.5917);
            t1.add(new Day(24, MonthConstants.SEPTEMBER, 2001), 1.6005);
            t1.add(new Day(25, MonthConstants.SEPTEMBER, 2001), 1.5915);
            t1.add(new Day(26, MonthConstants.SEPTEMBER, 2001), 1.6012);
            t1.add(new Day(27, MonthConstants.SEPTEMBER, 2001), 1.6032);
            t1.add(new Day(28, MonthConstants.SEPTEMBER, 2001), 1.6133);
            t1.add(new Day(1, MonthConstants.OCTOBER, 2001), 1.6147);
            t1.add(new Day(2, MonthConstants.OCTOBER, 2001), 1.6002);
            t1.add(new Day(3, MonthConstants.OCTOBER, 2001), 1.6041);
            t1.add(new Day(4, MonthConstants.OCTOBER, 2001), 1.6172);
            t1.add(new Day(5, MonthConstants.OCTOBER, 2001), 1.6121);
            t1.add(new Day(8, MonthConstants.OCTOBER, 2001), 1.6044);
            t1.add(new Day(9, MonthConstants.OCTOBER, 2001), 1.5974);
            t1.add(new Day(10, MonthConstants.OCTOBER, 2001), 1.5915);
            t1.add(new Day(11, MonthConstants.OCTOBER, 2001), 1.6022);
            t1.add(new Day(12, MonthConstants.OCTOBER, 2001), 1.6014);
            t1.add(new Day(15, MonthConstants.OCTOBER, 2001), 1.5942);
            t1.add(new Day(16, MonthConstants.OCTOBER, 2001), 1.5925);
            t1.add(new Day(17, MonthConstants.OCTOBER, 2001), 1.6007);
            t1.add(new Day(18, MonthConstants.OCTOBER, 2001), 1.6000);
            t1.add(new Day(19, MonthConstants.OCTOBER, 2001), 1.6030);
            t1.add(new Day(22, MonthConstants.OCTOBER, 2001), 1.6014);
            t1.add(new Day(23, MonthConstants.OCTOBER, 2001), 1.5995);
            t1.add(new Day(24, MonthConstants.OCTOBER, 2001), 1.5951);
            t1.add(new Day(25, MonthConstants.OCTOBER, 2001), 1.5953);
            t1.add(new Day(26, MonthConstants.OCTOBER, 2001), 1.6057);
            t1.add(new Day(29, MonthConstants.OCTOBER, 2001), 1.6051);
            t1.add(new Day(30, MonthConstants.OCTOBER, 2001), 1.6027);
            t1.add(new Day(31, MonthConstants.OCTOBER, 2001), 1.6144);
            t1.add(new Day(1, MonthConstants.NOVEMBER, 2001), 1.6139);
            t1.add(new Day(2, MonthConstants.NOVEMBER, 2001), 1.6189);
            t1.add(new Day(5, MonthConstants.NOVEMBER, 2001), 1.6248);
            t1.add(new Day(6, MonthConstants.NOVEMBER, 2001), 1.6267);
            t1.add(new Day(7, MonthConstants.NOVEMBER, 2001), 1.6281);
            t1.add(new Day(8, MonthConstants.NOVEMBER, 2001), 1.6310);
            t1.add(new Day(9, MonthConstants.NOVEMBER, 2001), 1.6313);
            t1.add(new Day(12, MonthConstants.NOVEMBER, 2001), 1.6272);
            t1.add(new Day(13, MonthConstants.NOVEMBER, 2001), 1.6361);
            t1.add(new Day(14, MonthConstants.NOVEMBER, 2001), 1.6323);
            t1.add(new Day(15, MonthConstants.NOVEMBER, 2001), 1.6252);
            t1.add(new Day(16, MonthConstants.NOVEMBER, 2001), 1.6141);
            t1.add(new Day(19, MonthConstants.NOVEMBER, 2001), 1.6086);
            t1.add(new Day(20, MonthConstants.NOVEMBER, 2001), 1.6055);
            t1.add(new Day(21, MonthConstants.NOVEMBER, 2001), 1.6132);
            t1.add(new Day(22, MonthConstants.NOVEMBER, 2001), 1.6074);
            t1.add(new Day(23, MonthConstants.NOVEMBER, 2001), 1.6065);
            t1.add(new Day(26, MonthConstants.NOVEMBER, 2001), 1.6061);
            t1.add(new Day(27, MonthConstants.NOVEMBER, 2001), 1.6039);
            t1.add(new Day(28, MonthConstants.NOVEMBER, 2001), 1.6069);
            t1.add(new Day(29, MonthConstants.NOVEMBER, 2001), 1.6044);
            t1.add(new Day(30, MonthConstants.NOVEMBER, 2001), 1.5928);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return t1;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createCombinedChart();
        return new ChartPanel(chart);
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        CombinedXYPlotDemo3 demo = new CombinedXYPlotDemo3("Combined XY Plot Demo 3");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
