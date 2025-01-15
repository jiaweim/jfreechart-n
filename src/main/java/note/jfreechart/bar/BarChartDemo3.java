package note.jfreechart.bar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.swing.UIUtils;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 25 Dec 2023, 10:51 PM
 */
public class BarChartDemo3 extends ApplicationFrame {

    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public BarChartDemo3(String title) {
        super(title);

        DefaultCategoryDataset<String, String> dataset = new DefaultCategoryDataset<>();
        dataset.addValue(1.0, "Row 1", "Column 1");
        dataset.addValue(5.0, "Row 1", "Column 2");
        dataset.addValue(3.0, "Row 1", "Column 3");
        dataset.addValue(2.0, "Row 2", "Column 1");
        dataset.addValue(3.0, "Row 2", "Column 2");
        dataset.addValue(2.0, "Row 2", "Column 3");
        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart Demo", // 标题
                "Category", // X 轴标题
                "Value", // y 轴标题
                dataset, // 数据
                PlotOrientation.VERTICAL, // 方向
                true, // legend
                true, // tooltip
                false // url
        );
        chart.setBackgroundPaint(Color.WHITE);

        CategoryPlot<String, String> plot = (CategoryPlot<String, String>) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.WHITE);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.GRAY);
        renderer.setSeriesPaint(1, Color.ORANGE);
        renderer.setDrawBarOutline(false);

        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BarChartDemo3 demo = new BarChartDemo3("Bar Demo 1");
                demo.pack();
                UIUtils.centerFrameOnScreen(demo);
                demo.setVisible(true);
            }
        });
    }

}
