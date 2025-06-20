package note.jfreechart;

import org.jfree.chart.*;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.UIUtils;
import org.jfree.data.general.DefaultPieDataset;

/**
 * A simple demonstration application showing chart mouse events on a pie chart.
 */
public class MouseListenerDemo1 extends ApplicationFrame
        implements ChartMouseListener {

    /**
     * Creates a new demo.
     *
     * @param title the frame title.
     */
    public MouseListenerDemo1(String title) {

        super(title);

        // create a dataset...
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Java", 43.2);
        data.setValue("Visual Basic", 0.0);
        data.setValue("C/C++", 17.5);

        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart Demo 1",  // chart title
                data,                // data
                true,                // include legend
                true,
                false
        );

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(chart, false, false, false,
                false, false);
        chartPanel.addChartMouseListener(this);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Receives chart mouse click events.
     *
     * @param event the event.
     */
    public void chartMouseClicked(ChartMouseEvent event) {

        ChartEntity entity = event.getEntity();
        if (entity != null) {
            System.out.println("Mouse clicked: " + entity.toString());
        } else {
            System.out.println("Mouse clicked: null entity.");
        }

    }

    /**
     * Receives chart mouse moved events.
     *
     * @param event the event.
     */
    public void chartMouseMoved(ChartMouseEvent event) {

        int x = event.getTrigger().getX();
        int y = event.getTrigger().getY();
        ChartEntity entity = event.getEntity();
        if (entity != null) {
            System.out.println(
                    "Mouse moved: " + x + ", " + y + ": " + entity.toString()
            );
        } else {
            System.out.println(
                    "Mouse moved: " + x + ", " + y + ": null entity."
            );
        }

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        MouseListenerDemo1 demo = new MouseListenerDemo1("Mouse Listener Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
