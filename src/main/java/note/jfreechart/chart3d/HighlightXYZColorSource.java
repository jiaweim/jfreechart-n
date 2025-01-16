package note.jfreechart.chart3d;

import java.awt.Color;
import org.jfree.chart3d.data.Range;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.renderer.xyz.StandardXYZColorSource;
import org.jfree.chart3d.renderer.xyz.XYZColorSource;

/**
 * A custom implementation of the {@link XYZColorSource} interface.
 */
@SuppressWarnings("serial")
public class HighlightXYZColorSource extends StandardXYZColorSource {
    
    private final XYZDataset dataset;
    
    /** The range of x-values for the highlight region. */
    private final Range xRange;
    
    /** The range of y-values for the highlight region. */
    private final Range yRange;
    
    /** The range of z-values for the highlight region. */
    private final Range zRange;
    
    /** The highlight color. */
    private final Color highlightColor;
    
    /**
     * Creates a new instance.
     * 
     * @param dataset  the dataset.
     * @param highlightColor
     * @param xRange
     * @param yRange
     * @param zRange
     * @param colors 
     */
    public HighlightXYZColorSource(XYZDataset dataset, Color highlightColor, 
            Range xRange, Range yRange, Range zRange, Color... colors) {
        super(colors);
        this.dataset = dataset;
        this.xRange = xRange;
        this.yRange = yRange;
        this.zRange = zRange;
        this.highlightColor = highlightColor;
    }

    @Override
    public Color getColor(int series, int item) {
        double x = this.dataset.getX(series, item);
        double y = this.dataset.getY(series, item);
        double z = this.dataset.getZ(series, item);
        if (this.xRange.contains(x) && this.yRange.contains(y) 
                && this.zRange.contains(z)) {
            return this.highlightColor; 
        } else {
            return super.getColor(series, item);
        }
    }

}