package note.jfreechart.chart3d;

import java.awt.Color;
import java.util.Objects;
import org.jfree.chart3d.Colors;
import org.jfree.chart3d.renderer.category.CategoryColorSource;
import org.jfree.chart3d.renderer.category.StandardCategoryColorSource;

/**
 * A custom implementation of the {@link CategoryColorSource} interface.
 */
@SuppressWarnings("serial")
public class HighlightCategoryColorSource extends StandardCategoryColorSource {
    
    /** The row index of an item to highlight. */
    private int highlightRowIndex;
    
    /** The column index of an item to highlight. */
    private int highlightColumnIndex;
    
    /** The highlight color. */
    private Color highlightColor;

    /**
     * Creates a new instance with default colors.
     */
    public HighlightCategoryColorSource() {
        this(-1, -1, Color.RED, Colors.getDefaultColors());
    }
    
    /**
     * Creates a new instance with the supplied sequence of colors.  The
     * supplied array must have at least one entry, and all entries must be
     * non-{@code null}.
     * 
     * @param row  the row index of the item to highlight (or -1).
     * @param column  the column index of the item to highlight (or -1).
     * @param highlightColor  the highlight color ({@code null} not 
     *     permitted).
     * @param colors  the colors ({@code null} not permitted). 
     */
    public HighlightCategoryColorSource(int row, int column, 
            Color highlightColor, Color... colors) {
        super(colors);
        this.highlightRowIndex = row;
        this.highlightColumnIndex = column;
        this.highlightColor = highlightColor;
    }

    /**
     * Returns the row index of the item to be highlighted.  The default 
     * value is {@code -1}.
     * 
     * @return The row index. 
     */
    public int getHighlightRowIndex() {
        return highlightRowIndex;
    }

    /**
     * Sets the row index of the item to highlight (you can set this to -1 to
     * have no item highlighted).
     * 
     * @param index  the row index. 
     */
    public void setHighlightRowIndex(int index) {
        this.highlightRowIndex = index;
    }

    /**
     * Returns the column index of the item to be highlighted.  The default 
     * value is {@code -1}.
     * 
     * @return The row index. 
     */
    public int getHighlightColumnIndex() {
        return highlightColumnIndex;
    }

    /**
     * Sets the column index of the item to highlight (you can set this to -1 to
     * have no item highlighted).
     * 
     * @param index  the row index. 
     */
    public void setHighlightColumnIndex(int index) {
        this.highlightColumnIndex = index;
    }

    /**
     * Returns the highlight color.  The default value is 
     * {@code Color.RED}.
     * 
     * @return The highlight color (never {@code null}). 
     */
    public Color getHighlightColor() {
        return highlightColor;
    }

    /**
     * Sets the highlight color.
     * 
     * @param color  the color ({@code null} not permitted). 
     */
    public void setHighlightColor(Color color) {
        Objects.requireNonNull(color, "color");
        this.highlightColor = color;
    }
 
    /**
     * Returns the color to use for the specified item.
     * 
     * @param series  the series index.
     * @param row  the row index.
     * @param column  the column index.
     * 
     * @return The color (never {@code null}). 
     */
    @Override
    public Color getColor(int series, int row, int column) {
        Color result = super.getColor(series, row, column);
        if (row == this.highlightRowIndex && column 
                == this.highlightColumnIndex) {
            result = this.highlightColor;
        }
        return result;
    }
    
    /**
     * Tests this color source for equality with an arbitrary object.
     * 
     * @param obj  the object ({@code null} permitted).
     * 
     * @return A boolean. 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HighlightCategoryColorSource)) {
            return false;
        }
        HighlightCategoryColorSource that 
                = (HighlightCategoryColorSource) obj;
        if (this.highlightColumnIndex != that.highlightColumnIndex) {
            return false;
        }
        if (this.highlightRowIndex != that.highlightRowIndex) {
            return false;
        }
        if (!this.highlightColor.equals(that.highlightColor)) {
            return false;
        }
        return super.equals(obj);
    }

}