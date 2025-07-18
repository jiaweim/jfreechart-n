package note.jfreechart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ThermometerPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * A simple demonstration application showing how to create a thermometer.
 */
public class ThermometerDemo1 extends ApplicationFrame {

    static class ContentPanel extends JPanel implements ChangeListener {

        JSlider slider;

        DefaultValueDataset dataset;

        /**
         * Default constructor.
         */
        public ContentPanel() {
            super(new BorderLayout());
            this.slider = new JSlider(0, 100, 50);
            this.slider.setPaintLabels(true);
            this.slider.setPaintTicks(true);
            this.slider.setMajorTickSpacing(25);
            this.slider.addChangeListener(this);
            add(this.slider, BorderLayout.SOUTH);
            this.dataset = new DefaultValueDataset(this.slider.getValue());
            add(new ChartPanel(createChart(this.dataset)));
        }

        private static JFreeChart createChart(ValueDataset dataset) {
            ThermometerPlot plot = new ThermometerPlot(dataset);
            JFreeChart chart = new JFreeChart(
                    "Thermometer Demo 1",  // chart title
                    JFreeChart.DEFAULT_TITLE_FONT,
                    plot,                  // plot
                    false                  // no legend
            );

            plot.setInsets(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
            plot.setPadding(new RectangleInsets(10.0, 10.0, 10.0, 10.0));
            plot.setThermometerStroke(new BasicStroke(2.0f));
            plot.setThermometerPaint(Color.lightGray);
            plot.setUnits(ThermometerPlot.UNITS_FAHRENHEIT);
            return chart;
        }

        public void stateChanged(ChangeEvent e) {
            this.dataset.setValue(this.slider.getValue());
        }
    }

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public ThermometerDemo1(String title) {
        super(title);
        JPanel chartPanel = createDemoPanel();
        setContentPane(chartPanel);
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createDemoPanel() {
        return new ContentPanel();
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        ThermometerDemo1 demo = new ThermometerDemo1("Thermometer Demo 1");
        demo.pack();
        demo.setVisible(true);
    }

}
