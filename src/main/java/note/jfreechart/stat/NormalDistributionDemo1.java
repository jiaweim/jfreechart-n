package note.jfreechart.stat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

/**
 * A line chart displaying a standard normal distribution.
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 03 Jul 2025, 1:28 PM
 */
public class NormalDistributionDemo1 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public NormalDistributionDemo1(String title) {
        super(title);
        JPanel jPanel = createDemoPanel();
        jPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jPanel);
    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }

    public static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Normal Distribution", "X", "Y", dataset);
        return chart;
    }

    public static XYDataset createDataset() {
        NormalDistributionFunction2D normalDistributionFunction2D = new NormalDistributionFunction2D(0.0, 1.0);
        return DatasetUtils.sampleFunction2D(normalDistributionFunction2D, -5.0, 5.0, 100, "Normal");
    }

    public static void main(String[] args) {
        NormalDistributionDemo1 distributionDemo1 = new NormalDistributionDemo1("Normal Distribution");
        distributionDemo1.pack();
        UIUtils.centerFrameOnScreen(distributionDemo1);
        distributionDemo1.setVisible(true);
    }
}
