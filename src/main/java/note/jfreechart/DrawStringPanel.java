package note.jfreechart;

import org.jfree.chart.text.TextAnchor;
import org.jfree.chart.text.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


/**
 * A panel used by the {@link DrawStringDemo} class.
 */
public class DrawStringPanel extends JPanel {

    /**
     * The preferred size for the panel.
     */
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 300);

    /**
     * Is the text rotated.
     */
    private boolean rotate;

    /**
     * The text to display.
     */
    private String text = "Hello World";

    /**
     * The text anchor.
     */
    private TextAnchor anchor = TextAnchor.TOP_LEFT;

    /**
     * The rotation anchor.
     */
    private TextAnchor rotationAnchor = TextAnchor.TOP_LEFT;

    /**
     * The font.
     */
    private Font font = new Font("Serif", Font.PLAIN, 12);

    /**
     * The rotation angle.
     */
    private double angle;

    /**
     * Creates a new panel.
     *
     * @param text   the text.
     * @param rotate a flag that controls whether or not the text is rotated.
     */
    public DrawStringPanel(final String text, final boolean rotate) {
        this.text = text;
        this.rotate = rotate;
    }

    /**
     * Returns the preferred size for the panel.
     *
     * @return The preferred size.
     */
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    /**
     * Sets the text anchor.
     *
     * @param anchor the text anchor.
     */
    public void setAnchor(final TextAnchor anchor) {
        this.anchor = anchor;
    }

    /**
     * Sets the rotation anchor.
     *
     * @param anchor the rotation anchor.
     */
    public void setRotationAnchor(final TextAnchor anchor) {
        this.rotationAnchor = anchor;
    }

    /**
     * Sets the rotation angle.
     *
     * @param angle the rotation angle.
     */
    public void setAngle(final double angle) {
        this.angle = angle;
    }

    /**
     * Returns the font.
     *
     * @return The font.
     */
    public Font getFont() {
        return this.font;
    }

    /**
     * Sets the font.
     *
     * @param font the font.
     */
    public void setFont(final Font font) {
        this.font = font;
    }

    /**
     * Paints the panel.
     *
     * @param g the graphics device.
     */
    public void paintComponent(final Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Dimension size = getSize();
        Insets insets = getInsets();
        Rectangle2D available = new Rectangle2D.Double(
                insets.left, insets.top,
                size.getWidth() - insets.left - insets.right,
                size.getHeight() - insets.top - insets.bottom
        );

        double x = available.getCenterX();
        double y = available.getCenterY();

        Line2D line1 = new Line2D.Double(x - 2.0, y + 2.0, x + 2.0, y - 2.0);
        Line2D line2 = new Line2D.Double(x - 2.0, y - 2.0, x + 2.0, y + 2.0);
        g2.setPaint(Color.red);
        g2.draw(line1);
        g2.draw(line2);

        g2.setFont(this.font);
        g2.setPaint(Color.black);
        if (this.rotate) {
            TextUtils.drawRotatedString(
                    this.text, g2, (float) x, (float) y,
                    this.anchor, this.angle, this.rotationAnchor
            );
        } else {
            TextUtils.drawAlignedString(
                    this.text, g2, (float) x, (float) y, this.anchor
            );
        }
    }
}
