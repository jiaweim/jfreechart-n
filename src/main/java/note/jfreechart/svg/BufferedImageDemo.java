package note.jfreechart.svg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A basic test class for writing to a BufferedImage via Graphics2D. This is used as a reference implementation.
 *
 * @author JiaweiMao
 */
public class BufferedImageDemo
{
    public static void main(String[] args) throws IOException {
        BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ImageIcon icon = new ImageIcon(BufferedImageDemo.class.getResource("jfree_chart_1.jpg"));
        g2.rotate(Math.PI / 12);
        g2.setStroke(new BasicStroke(2.0f));
        g2.setPaint(Color.WHITE);
        g2.fill(new Rectangle(0, 0, 600, 400));
        g2.setPaint(Color.RED);
        g2.draw(new Rectangle(0, 0, 600, 400));
        g2.drawImage(icon.getImage(), 10, 20, null);

        ImageIO.write(image, "png", new File("image-test.png"));
    }
}
