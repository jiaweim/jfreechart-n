package note.jfreechart;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * In this demo, a meter chart is saved to a scaled image file.
 */
public class MeterChartDemo4 {

    /**
     * Default constructor.
     */
    public MeterChartDemo4() {
        super();
    }

    /**
     * Starting point for the demo.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {

        ValueDataset dataset = new DefaultValueDataset(75.0);
        MeterPlot plot = new MeterPlot(dataset);
        JFreeChart chart = new JFreeChart("Scaled Image Test", plot);

        // save it to an image
        try {
            File file1 = new File("meterchart100.png");
            OutputStream out = new BufferedOutputStream(new FileOutputStream(file1));
            BufferedImage image = chart.createBufferedImage(200, 200, 400, 400, null);
            ChartUtils.writeBufferedImageAsPNG(out, image);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

}
