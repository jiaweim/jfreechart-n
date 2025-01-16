package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class DemoDisplayer implements Runnable {

    private final OrsonChartsDemoComponent demoComp;

    private final DemoDescription demoDescription;

    /**
     * Creates a new runnable.
     *
     * @param demoComp the demo component.
     * @param d        the demo description.
     */
    public DemoDisplayer(OrsonChartsDemoComponent demoComp, DemoDescription d) {
        this.demoComp = demoComp;
        this.demoDescription = d;
    }

    /**
     * Runs the task.
     */
    @Override
    public void run() {
        try {
            Class<?> c = Class.forName(this.demoDescription.getClassName());
            Method m = c.getDeclaredMethod("createDemoPanel", (Class[]) null);
            JPanel panel = (JPanel) m.invoke(null, (Object[]) null);
            panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(4, 4, 4, 4),
                    BorderFactory.createLineBorder(Color.BLACK)));
            this.demoComp.getChartContainer().removeAll();
            this.demoComp.getChartContainer().add(panel);
            this.demoComp.getChartContainer().validate();
            if (panel instanceof DemoPanel) {
                DemoPanel demoPanel = (DemoPanel) panel;
                for (Chart3DPanel cp3d : demoPanel.getChartPanels()) {
                    if (demoComp.getChartStyle() != null) {
                        Chart3D chart = (Chart3D) cp3d.getDrawable();
                        chart.setStyle(demoComp.getChartStyle());
                    }
                    cp3d.zoomToFit();
                }
            }
            String f = this.demoDescription.getDescriptionFileName();
            URL descriptionURL = OrsonChartsDemo.class.getResource(f);
            if (descriptionURL != null) {
                try {
                    this.demoComp.getChartDescriptionPane().setPage(descriptionURL);
                } catch (IOException e) {
                    System.err.println("Attempted to read a bad URL: "
                            + descriptionURL);
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e1) {
            e1.printStackTrace();
        }

    }

}