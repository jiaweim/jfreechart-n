package note.jfreechart.piechart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A pie chart with controls for reordering the items in the dataset.
 */
public class PieChartDemo4 extends ApplicationFrame {

    private static class DemoPanel extends JPanel implements ActionListener {

        JFreeChart chart;

        DefaultPieDataset dataset;

        boolean ascendingByKey = false;

        boolean ascendingByValue = false;

        /**
         * Creates a new demo panel.
         *
         * @param dataset
         */
        public DemoPanel(DefaultPieDataset dataset) {
            super(new BorderLayout());
            this.dataset = dataset;
            this.chart = createChart(dataset);
            ChartPanel chartPanel = new ChartPanel(this.chart);
            add(chartPanel);
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton button1 = new JButton("By Key");
            button1.setActionCommand("BY_KEY");
            button1.addActionListener(this);
            JButton button2 = new JButton("By Value");
            button2.setActionCommand("BY_VALUE");
            button2.addActionListener(this);
            JButton button3 = new JButton("Random");
            button3.setActionCommand("RANDOM");
            button3.addActionListener(this);
            buttonPanel.add(button1);
            buttonPanel.add(button2);
            buttonPanel.add(button3);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if ("BY_KEY".equals(cmd)) {
                if (!this.ascendingByKey) {
                    this.dataset.sortByKeys(SortOrder.ASCENDING);
                    this.ascendingByKey = true;
                } else {
                    this.dataset.sortByKeys(SortOrder.DESCENDING);
                    this.ascendingByKey = false;
                }
            } else if ("BY_VALUE".equals(cmd)) {
                if (!this.ascendingByValue) {
                    this.dataset.sortByValues(SortOrder.ASCENDING);
                    this.ascendingByValue = true;
                } else {
                    this.dataset.sortByValues(SortOrder.DESCENDING);
                    this.ascendingByValue = false;
                }
            } else if ("RANDOM".equals(cmd)) {
                // we create a new dataset here - that's a bit wasteful,
                // but the DefaultPieDataset will need new methods before we
                // can shuffle it 'in place'...
                List keys = new ArrayList(this.dataset.getKeys());
                Collections.shuffle(keys);
                DefaultPieDataset pd = new DefaultPieDataset();
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    Comparable key = (Comparable) iterator.next();
                    pd.setValue(key, this.dataset.getValue(key));
                }
                PiePlot plot = (PiePlot) this.chart.getPlot();
                plot.setDataset(pd);
                this.dataset = pd;
            }
        }

    }

    /**
     * Default constructor.
     *
     * @param title the frame title.
     */
    public PieChartDemo4(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private static DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Section A", 43.2);
        dataset.setValue("Section B", 10.0);
        dataset.setValue("Section C", 27.5);
        dataset.setValue("Section D", 17.5);
        dataset.setValue("Section E", 11.0);
        dataset.setValue("Section F", 19.4);
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset the dataset.
     * @return a chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 4",  // chart title
                dataset,             // data
                true,                // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setExplodePercent("Section D", 0.50);
        return chart;

    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new DemoPanel(createDataset());
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        PieChartDemo4 demo = new PieChartDemo4("Pie Chart Demo 4");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
