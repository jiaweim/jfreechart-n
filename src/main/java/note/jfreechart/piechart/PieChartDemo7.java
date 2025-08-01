package note.jfreechart.piechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

/**
 * A simple demonstration application showing how to create a pie chart using
 * data from a {@link DefaultPieDataset}.  This chart has a lot of labels and
 * rotates, so it is useful for testing the label distribution algorithm.
 */
public class PieChartDemo7 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title the frame title.
     */
    public PieChartDemo7(final String title) {
        super(title);
        setContentPane(createDemoPanel());
    }

    /**
     * Creates a sample dataset.
     *
     * @param sections the number of sections.
     * @return A sample dataset.
     */
    private static PieDataset createDataset(int sections) {
        DefaultPieDataset result = new DefaultPieDataset();
        for (int i = 0; i < sections; i++) {
            double value = 100.0 * Math.random();
            result.setValue("Section " + i, value);
        }
        return result;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        PieDataset dataset = createDataset(14);

        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 7",  // chart title
                dataset,             // dataset
                false,               // include legend
                true,
                false
        );

        // set the background color for the chart...
        chart.setBackgroundPaint(new Color(222, 222, 255));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setCircular(true);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}",
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance()));
        plot.setNoDataMessage("No data available");

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        Rotator rotator = new Rotator(plot);
        rotator.start();
        return chartPanel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        PieChartDemo7 demo = new PieChartDemo7("Pie Chart Demo 7");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    /**
     * A timer that makes the plot rotate.
     */
    static class Rotator extends Timer implements ActionListener {

        /**
         * The plot.
         */
        private PiePlot plot;

        /**
         * The angle.
         */
        private int angle = 270;

        /**
         * Constructor.
         *
         * @param plot the plot.
         */
        Rotator(PiePlot plot) {
            super(100, null);
            this.plot = plot;
            addActionListener(this);
        }

        /**
         * Modifies the starting angle.
         *
         * @param event the action event.
         */
        public void actionPerformed(ActionEvent event) {
            this.plot.setStartAngle(this.angle);
            this.angle = this.angle + 1;
            if (this.angle == 360) {
                this.angle = 0;
            }
        }

    }

}
