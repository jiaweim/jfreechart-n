package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DefaultXYItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A demonstration application showing a time series chart where you can
 * dynamically add (random) data by clicking on a button.
 */
public class DynamicDataDemo2 extends ApplicationFrame {

    static class DemoPanel extends JPanel implements ActionListener {

        /**
         * Series 1.
         */
        private TimeSeries series1;

        /**
         * Series 2.
         */
        private TimeSeries series2;

        /**
         * The most recent value added to series 1.
         */
        private double lastValue1 = 100.0;

        /**
         * The most recent value added to series 2.
         */
        private double lastValue2 = 500.0;

        /**
         * Creates a new self-contained demo panel.
         */
        public DemoPanel() {
            super(new BorderLayout());
            this.series1 = new TimeSeries("Random 1");
            this.series2 = new TimeSeries("Random 2");
            TimeSeriesCollection dataset1 = new TimeSeriesCollection(
                    this.series1);
            TimeSeriesCollection dataset2 = new TimeSeriesCollection(
                    this.series2);
            JFreeChart chart = ChartFactory.createTimeSeriesChart(
                    "Dynamic Data Demo 2", "Time", "Value", dataset1,
                    true, true, false);
            chart.setBackgroundPaint(Color.white);

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(Color.lightGray);
            plot.setDomainGridlinePaint(Color.white);
            plot.setRangeGridlinePaint(Color.white);
            plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));
            ValueAxis axis = plot.getDomainAxis();
            axis.setAutoRange(true);
            //axis.setFixedAutoRange(60000.0);  // 60 seconds

            plot.setDataset(1, dataset2);
            NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
            rangeAxis2.setAutoRangeIncludesZero(false);
            plot.setRenderer(1, new DefaultXYItemRenderer());
            plot.setRangeAxis(1, rangeAxis2);
            plot.mapDatasetToRangeAxis(1, 1);

            ChartPanel chartPanel = new ChartPanel(chart);
            add(chartPanel);

            JButton button1 = new JButton("Add To Series 1");
            button1.setActionCommand("ADD_DATA_1");
            button1.addActionListener(this);

            JButton button2 = new JButton("Add To Series 2");
            button2.setActionCommand("ADD_DATA_2");
            button2.addActionListener(this);

            JButton button3 = new JButton("Add To Both");
            button3.setActionCommand("ADD_BOTH");
            button3.addActionListener(this);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setBackground(Color.white);
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            buttonPanel.add(button3);

            add(buttonPanel, BorderLayout.SOUTH);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        }

        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e the action event.
         */
        public void actionPerformed(ActionEvent e) {
            boolean add1 = false;
            boolean add2 = false;
            if (e.getActionCommand().equals("ADD_DATA_1")) {
                add1 = true;
            } else if (e.getActionCommand().equals("ADD_DATA_2")) {
                add2 = true;
            } else if (e.getActionCommand().equals("ADD_BOTH")) {
                add1 = true;
                add2 = true;
            }
            if (add1) {
                double factor = 0.90 + 0.2 * Math.random();
                this.lastValue1 = this.lastValue1 * factor;
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                this.series1.add(new Millisecond(), this.lastValue1);
            }
            if (add2) {
                double factor = 0.90 + 0.2 * Math.random();
                this.lastValue2 = this.lastValue2 * factor;
                Millisecond now = new Millisecond();
                System.out.println("Now = " + now.toString());
                this.series2.add(new Millisecond(), this.lastValue2);
            }
        }

    }


    /**
     * Constructs a new demonstration application.
     *
     * @param title the frame title.
     */
    public DynamicDataDemo2(String title) {
        super(title);
        setContentPane(new DemoPanel());
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new DynamicDataDemo2.DemoPanel();
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        DynamicDataDemo2 demo = new DynamicDataDemo2("Dynamic Data Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
