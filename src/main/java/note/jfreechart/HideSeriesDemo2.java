package note.jfreechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A demonstration application showing a chart where you can show and hide
 * individual series.
 */
public class HideSeriesDemo2 extends ApplicationFrame {

    static class DemoPanel extends JPanel implements ActionListener {

        private CategoryItemRenderer renderer;

        /**
         * Creates a new self-contained demo panel.
         */
        public DemoPanel() {
            super(new BorderLayout());
            CategoryDataset dataset = createSampleDataset();
            JFreeChart chart = createChart(dataset);

            ChartPanel chartPanel = new ChartPanel(chart);
            JPanel boxPanel = new JPanel();
            JCheckBox box1 = new JCheckBox("Series 1");
            box1.setActionCommand("S1");
            box1.addActionListener(this);
            box1.setSelected(true);
            JCheckBox box2 = new JCheckBox("Series 2");
            box2.setActionCommand("S2");
            box2.addActionListener(this);
            box2.setSelected(true);
            JCheckBox box3 = new JCheckBox("Series 3");
            box3.setActionCommand("S3");
            box3.addActionListener(this);
            box3.setSelected(true);
            boxPanel.add(box1);
            boxPanel.add(box2);
            boxPanel.add(box3);

            add(chartPanel);
            add(boxPanel, BorderLayout.SOUTH);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        }

        /**
         * Creates a sample dataset.
         *
         * @return A dataset.
         */
        private CategoryDataset createSampleDataset() {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(3.3, "S1", "C1");
            dataset.addValue(4.4, "S1", "C2");
            dataset.addValue(1.7, "S1", "C3");
            dataset.addValue(7.3, "S2", "C1");
            dataset.addValue(6.4, "S2", "C2");
            dataset.addValue(5.7, "S2", "C3");
            dataset.addValue(10.3, "S3", "C1");
            dataset.addValue(11.4, "S3", "C2");
            dataset.addValue(7.7, "S3", "C3");
            return dataset;
        }

        /**
         * Creates a sample chart.
         *
         * @param dataset the dataset.
         * @return A sample chart.
         */
        private JFreeChart createChart(CategoryDataset dataset) {
            JFreeChart result = ChartFactory.createAreaChart(
                    "Hide Series Demo 2",
                    "Category",
                    "Value",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
            CategoryPlot plot = (CategoryPlot) result.getPlot();
            plot.setRenderer(new StatisticalLineAndShapeRenderer());
            plot.setForegroundAlpha(0.5f);
            this.renderer = plot.getRenderer(0);
            return result;
        }

        /**
         * Handles a click on the button by adding new (random) data.
         *
         * @param e the action event.
         */
        public void actionPerformed(ActionEvent e) {
            int series = -1;
            if (e.getActionCommand().equals("S1")) {
                series = 0;
            } else if (e.getActionCommand().equals("S2")) {
                series = 1;
            } else if (e.getActionCommand().equals("S3")) {
                series = 2;
            }
            if (series >= 0) {
                boolean visible = this.renderer.getItemVisible(series, 0);
                this.renderer.setSeriesVisible(series, new Boolean(!visible));
            }
        }

    }

    /**
     * Constructs a new demonstration application.
     *
     * @param title the frame title.
     */
    public HideSeriesDemo2(String title) {
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
        HideSeriesDemo2 demo = new HideSeriesDemo2("Hide Series Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
