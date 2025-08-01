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

/**
 * This demo shows a pie chart with an "exploded" section.
 * @since 2025-06-17 ⭐
 */
public class PieChartDemo2 extends ApplicationFrame {

    /**
     * Default constructor.
     *
     * @param title the frame title.
     */
    public PieChartDemo2(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private static PieDataset<String> createDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("One", 43.2);
        dataset.setValue("Two", 10.0);
        dataset.setValue("Three", 27.5);
        dataset.setValue("Four", 17.5);
        dataset.setValue("Five", 11.0);
        dataset.setValue("Six", 19.4);
        return dataset;
    }

    private static JFreeChart createChart(PieDataset<String> dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 2",  // chart title
                dataset,             // dataset
                true,                // include legend
                true, // tooltips
                false
        );
        PiePlot<String> plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("One", new Color(160, 160, 255));
        plot.setSectionPaint("Two", new Color(128, 128, 255 - 32));
        plot.setSectionPaint("Three", new Color(96, 96, 255 - 64));
        plot.setSectionPaint("Four", new Color(64, 64, 255 - 96));
        plot.setSectionPaint("Five", new Color(32, 32, 255 - 128));
        plot.setSectionPaint("Six", new Color(0, 0, 255 - 144));

        plot.setNoDataMessage("No data available");
        plot.setExplodePercent("Two", 0.2);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} ({2} percent)"));
        plot.setLabelBackgroundPaint(new Color(220, 220, 220));

        plot.setLegendLabelToolTipGenerator(
                new StandardPieSectionLabelGenerator(
                        "Tooltip for legend item {0}"));
        plot.setSimpleLabels(true);
        plot.setInteriorGap(0.0);
        return chart;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        PieChartDemo2 demo = new PieChartDemo2("Pie Chart Demo 2");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
