package note.jfreechart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.function.PowerFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * A demo showing one way to fit regression lines to XY data.
 */
public class RegressionDemo1 extends ApplicationFrame {

    static class DemoPanel extends JPanel {

        /**
         * Dataset 1.
         */
        private XYDataset data1;

        /**
         * Creates a new instance.
         */
        public DemoPanel() {
            super(new BorderLayout());
            this.data1 = createSampleData1();
            add(createContent());
        }

        /**
         * Creates and returns a sample dataset.  The data was randomly generated.
         *
         * @return a sample dataset.
         */
        private XYDataset createSampleData1() {
            XYSeries series = new XYSeries("Series 1");
            series.add(2.0, 56.27);
            series.add(3.0, 41.32);
            series.add(4.0, 31.45);
            series.add(5.0, 30.05);
            series.add(6.0, 24.69);
            series.add(7.0, 19.78);
            series.add(8.0, 20.94);
            series.add(9.0, 16.73);
            series.add(10.0, 14.21);
            series.add(11.0, 12.44);
            XYDataset result = new XYSeriesCollection(series);
            return result;
        }

        /**
         * Creates a tabbed pane for displaying sample charts.
         *
         * @return the tabbed pane.
         */
        private JTabbedPane createContent() {
            JTabbedPane tabs = new JTabbedPane();
            tabs.add("Linear", createChartPanel1());
            tabs.add("Power", createChartPanel2());
            return tabs;
        }

        /**
         * Creates a chart based on the first dataset, with a fitted linear regression line.
         *
         * @return the chart panel.
         */
        private ChartPanel createChartPanel1() {

            // create plot...
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);

            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
                    true);
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);

            // calculate the regression and create subplot 2...
            double[] coefficients = Regression.getOLSRegression(this.data1, 0);
            Function2D curve = new LineFunction2D(coefficients[0], coefficients[1]);
            XYDataset regressionData = DatasetUtils.sampleFunction2D(curve,
                    2.0, 11.0, 100, "Fitted Regression Line");

            plot.setDataset(1, regressionData);
            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true,
                    false);
            renderer2.setSeriesPaint(0, Color.blue);
            plot.setRenderer(1, renderer2);

            // create and return the chart panel...
            JFreeChart chart = new JFreeChart("Linear Regression",
                    JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            return chartPanel;

        }

        /**
         * Creates a chart based on the second dataset, with a fitted power
         * regression line.
         *
         * @return the chart panel.
         */
        private ChartPanel createChartPanel2() {

            // create subplot 1...
            NumberAxis xAxis = new NumberAxis("X");
            xAxis.setAutoRangeIncludesZero(false);
            NumberAxis yAxis = new NumberAxis("Y");
            yAxis.setAutoRangeIncludesZero(false);

            XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
                    true);
            XYPlot plot = new XYPlot(this.data1, xAxis, yAxis, renderer1);

            // calculate the regression and create subplot 2...
            double[] coefficients = Regression.getPowerRegression(this.data1, 0);
            Function2D curve = new PowerFunction2D(coefficients[0],
                    coefficients[1]);
            XYDataset regressionData = DatasetUtils.sampleFunction2D(curve,
                    2.0, 11.0, 100, "Fitted Regression Line");

            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true,
                    false);
            renderer2.setSeriesPaint(0, Color.blue);
            plot.setDataset(1, regressionData);
            plot.setRenderer(1, renderer2);

            // create and return the chart panel...
            JFreeChart chart = new JFreeChart("Power Regression",
                    JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            return chartPanel;

        }

    }

    /**
     * Creates a new instance of the demo application.
     *
     * @param title the frame title.
     */
    public RegressionDemo1(String title) {
        super(title);
        JPanel content = createDemoPanel();
        getContentPane().add(content);
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
     * The starting point for the regression demo.
     *
     * @param args ignored.
     */
    public static void main(String args[]) {
        RegressionDemo1 appFrame = new RegressionDemo1("Regression Demo 1");
        appFrame.pack();
        UIUtils.centerFrameOnScreen(appFrame);
        appFrame.setVisible(true);
    }

}
