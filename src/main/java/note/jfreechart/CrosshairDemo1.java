package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.api.RectangleInsets;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressEventType;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.NumberCellRenderer;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.Range;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An example of a crosshair being controlled by an external UI component.
 */
public class CrosshairDemo1 extends ApplicationFrame {

    private static class DemoPanel extends JPanel
            implements ChangeListener,
            ChartProgressListener {

        private TimeSeries series;

        private ChartPanel chartPanel;

        private DemoTableModel model;

        private JFreeChart chart;

        private JSlider slider;

        /**
         * Creates a new demo panel.
         */
        public DemoPanel() {
            super(new BorderLayout());
            this.chart = createChart();
            this.chart.addProgressListener(this);
            this.chartPanel = new ChartPanel(this.chart);
            this.chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
            this.chartPanel.setDomainZoomable(true);
            this.chartPanel.setRangeZoomable(true);
            Border border = BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(4, 4, 4, 4),
                    BorderFactory.createEtchedBorder());
            this.chartPanel.setBorder(border);
            add(this.chartPanel);

            JPanel dashboard = new JPanel(new BorderLayout());
            dashboard.setPreferredSize(new Dimension(400, 60));
            dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));

            this.model = new DemoTableModel(3);
            XYPlot plot = (XYPlot) this.chart.getPlot();
            this.model.setValueAt(plot.getDataset().getSeriesKey(0), 0, 0);
            this.model.setValueAt(new Double("0.00"), 0, 1);
            this.model.setValueAt(new Double("0.00"), 0, 2);
            this.model.setValueAt(new Double("0.00"), 0, 3);
            this.model.setValueAt(new Double("0.00"), 0, 4);
            this.model.setValueAt(new Double("0.00"), 0, 5);
            this.model.setValueAt(new Double("0.00"), 0, 6);
            JTable table = new JTable(this.model);
            TableCellRenderer renderer1 = new DateCellRenderer(
                    new SimpleDateFormat("HH:mm"));
            TableCellRenderer renderer2 = new NumberCellRenderer();
            table.getColumnModel().getColumn(1).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(2).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(3).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(4).setCellRenderer(renderer2);
            table.getColumnModel().getColumn(5).setCellRenderer(renderer1);
            table.getColumnModel().getColumn(6).setCellRenderer(renderer2);
            dashboard.add(new JScrollPane(table));

            this.slider = new JSlider(0, 100, 50);
            this.slider.addChangeListener(this);
            dashboard.add(this.slider, BorderLayout.SOUTH);
            add(dashboard, BorderLayout.SOUTH);
        }

        /**
         * Creates the demo chart.
         *
         * @return The chart.
         */
        private JFreeChart createChart() {

            XYDataset dataset1 = createDataset("Random 1", 100.0, new Minute(),
                    200);

            JFreeChart chart1 = ChartFactory.createTimeSeriesChart(
                    "Crosshair Demo 1",
                    "Time of Day",
                    "Value",
                    dataset1,
                    true,
                    true,
                    false
            );

            chart1.setBackgroundPaint(Color.white);
            XYPlot plot = (XYPlot) chart1.getPlot();
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

            plot.setDomainCrosshairVisible(true);
            plot.setDomainCrosshairLockedOnData(false);
            plot.setRangeCrosshairVisible(false);
            XYItemRenderer renderer = plot.getRenderer();
            renderer.setDefaultPaint(Color.black);

            return chart1;
        }


        /**
         * Creates a sample dataset.
         *
         * @param name  the dataset name.
         * @param base  the starting value.
         * @param start the starting period.
         * @param count the number of values to generate.
         * @return The dataset.
         */
        private XYDataset createDataset(String name, double base,
                RegularTimePeriod start, int count) {

            this.series = new TimeSeries(name);
            RegularTimePeriod period = start;
            double value = base;
            for (int i = 0; i < count; i++) {
                this.series.add(period, value);
                period = period.next();
                value = value * (1 + (Math.random() - 0.495) / 10.0);
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(this.series);

            return dataset;

        }

        /**
         * Handles a state change event.
         *
         * @param event the event.
         */
        public void stateChanged(ChangeEvent event) {
            int value = this.slider.getValue();
            XYPlot plot = (XYPlot) this.chart.getPlot();
            ValueAxis domainAxis = plot.getDomainAxis();
            Range range = domainAxis.getRange();
            double c = domainAxis.getLowerBound()
                    + (value / 100.0) * range.getLength();
            plot.setDomainCrosshairValue(c);
        }

        /**
         * Handles a chart progress event.
         *
         * @param event the event.
         */
        public void chartProgress(ChartProgressEvent event) {
            if (event.getType() != ChartProgressEventType.DRAWING_FINISHED) {
                return;
            }
            if (this.chartPanel != null) {
                JFreeChart c = this.chartPanel.getChart();
                if (c != null) {
                    XYPlot plot = (XYPlot) c.getPlot();
                    XYDataset dataset = plot.getDataset();
                    Comparable seriesKey = dataset.getSeriesKey(0);
                    double xx = plot.getDomainCrosshairValue();

                    // update the table...
                    this.model.setValueAt(seriesKey, 0, 0);
                    long millis = (long) xx;
                    this.model.setValueAt(new Long(millis), 0, 1);
                    int itemIndex = this.series.getIndex(new Minute(
                            new Date(millis)));
                    if (itemIndex >= 0) {
                        TimeSeriesDataItem item = this.series.getDataItem(
                                Math.min(199, Math.max(0, itemIndex)));
                        TimeSeriesDataItem prevItem = this.series.getDataItem(
                                Math.max(0, itemIndex - 1));
                        TimeSeriesDataItem nextItem = this.series.getDataItem(
                                Math.min(199, itemIndex + 1));
                        long x = item.getPeriod().getMiddleMillisecond();
                        double y = item.getValue().doubleValue();
                        long prevX
                                = prevItem.getPeriod().getMiddleMillisecond();
                        double prevY = prevItem.getValue().doubleValue();
                        long nextX
                                = nextItem.getPeriod().getMiddleMillisecond();
                        double nextY = nextItem.getValue().doubleValue();
                        this.model.setValueAt(new Long(x), 0, 1);
                        this.model.setValueAt(new Double(y), 0, 2);
                        this.model.setValueAt(new Long(prevX), 0, 3);
                        this.model.setValueAt(new Double(prevY), 0, 4);
                        this.model.setValueAt(new Long(nextX), 0, 5);
                        this.model.setValueAt(new Double(nextY), 0, 6);
                    }

                }
            }
        }

    }

    /**
     * A demonstration application showing how to control a crosshair using an
     * external UI component.
     *
     * @param title the frame title.
     */
    public CrosshairDemo1(String title) {
        super(title);
        setContentPane(new DemoPanel());
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        CrosshairDemo1 demo = new CrosshairDemo1("Crosshair Demo 1");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

    /**
     * A demo table model.
     */
    static class DemoTableModel extends AbstractTableModel
            implements TableModel {

        private Object[][] data;

        /**
         * Creates a new demo table model.
         *
         * @param rows the row count.
         */
        public DemoTableModel(int rows) {
            this.data = new Object[rows][7];
        }

        /**
         * Returns the number of columns.
         *
         * @return 7.
         */
        public int getColumnCount() {
            return 7;
        }

        /**
         * Returns the row count.
         *
         * @return 1.
         */
        public int getRowCount() {
            return 1;
        }

        /**
         * Returns the value at the specified cell in the table.
         *
         * @param row    the row index.
         * @param column the column index.
         * @return The value.
         */
        public Object getValueAt(int row, int column) {
            return this.data[row][column];
        }

        /**
         * Sets the value at the specified cell.
         *
         * @param value  the value.
         * @param row    the row index.
         * @param column the column index.
         */
        public void setValueAt(Object value, int row, int column) {
            this.data[row][column] = value;
            fireTableDataChanged();
        }

        /**
         * Returns the column name.
         *
         * @param column the column index.
         * @return The column name.
         */
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Series Name:";
                case 1:
                    return "X:";
                case 2:
                    return "Y:";
                case 3:
                    return "X (prev)";
                case 4:
                    return "Y (prev):";
                case 5:
                    return "X (next):";
                case 6:
                    return "Y (next):";
            }
            return null;
        }

    }

}

