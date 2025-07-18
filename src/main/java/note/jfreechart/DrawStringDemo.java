package note.jfreechart;

import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.FontChooserPanel;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.ui.UIUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A demo of some of the string drawing methods in the JCommon class library.
 */
public class DrawStringDemo extends ApplicationFrame
        implements ActionListener, ChangeListener {
    /**
     * The alignment anchor for the first panel.
     */
    private JComboBox<String> combo1;

    /**
     * The alignment anchor for the second panel.
     */
    private JComboBox<String> combo2;

    /**
     * The rotation anchor for the second panel.
     */
    private JComboBox<String> combo3;

    /**
     * A spinner for the second panel.
     */
    private JSlider rotation;

    /**
     * String panel 1.
     */
    private DrawStringPanel drawStringPanel1;

    /**
     * String panel 2.
     */
    private DrawStringPanel drawStringPanel2;

    /**
     * Creates a new demo instance.
     *
     * @param title the frame title.
     */
    public DrawStringDemo(final String title) {
        super(title);
        setContentPane(createContentPane());
    }

    /**
     * Receives action events.
     *
     * @param event the event.
     */
    public void actionPerformed(final ActionEvent event) {

        if (event.getActionCommand().equals("fontButton.clicked")) {
            displayFontDialog();
        }

        if (event.getActionCommand().equals("combo1.changed")) {
            handleCombo1Change();
        }
        if (event.getActionCommand().equals("combo2.changed")) {
            handleCombo2Change();
        }
        if (event.getActionCommand().equals("combo3.changed")) {
            handleCombo3Change();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        final int r = this.rotation.getValue();
        final double angle = Math.PI * 2.0 * (r / 360.0);
        this.drawStringPanel2.setAngle(angle);
        this.drawStringPanel2.invalidate();
        this.drawStringPanel2.repaint();
    }

    /**
     * Updates the display when combo 1 is updated.
     */
    private void handleCombo1Change() {
        final String text = (String) this.combo1.getSelectedItem();
        this.drawStringPanel1.setAnchor(convertStringToAnchor(text));
        this.drawStringPanel1.invalidate();
        this.drawStringPanel1.repaint();
    }

    /**
     * Updates the display when combo 2 is updated.
     */
    private void handleCombo2Change() {
        final String text = this.combo2.getSelectedItem().toString();
        this.drawStringPanel2.setAnchor(convertStringToAnchor(text));
        this.drawStringPanel2.invalidate();
        this.drawStringPanel2.repaint();
    }

    /**
     * Updates the display when combo 3 is updated.
     */
    private void handleCombo3Change() {
        final String text = this.combo3.getSelectedItem().toString();
        this.drawStringPanel2.setRotationAnchor(convertStringToAnchor(text));
        this.drawStringPanel2.invalidate();
        this.drawStringPanel2.repaint();
    }

    /**
     * Creates the content pane for the demo frame.
     *
     * @return The content pane.
     */
    private JPanel createContentPane() {

        final JPanel content = new JPanel(new BorderLayout());
        final JTabbedPane tabs = new JTabbedPane();

        tabs.add("Alignment", createTab1Content());
        tabs.add("Rotation", createTab2Content());

        content.add(tabs);
        return content;
    }

    /**
     * Creates the content for tab 1.
     *
     * @return The content panel.
     */
    private JPanel createTab1Content() {
        final JPanel content = new JPanel(new BorderLayout());
        this.combo1 = new JComboBox<>();
        this.combo1.setActionCommand("combo1.changed");
        populateTextAnchorCombo(this.combo1);
        this.combo1.addActionListener(this);

        final JPanel controls = new JPanel();
        controls.add(this.combo1);

        final JButton fontButton = new JButton("Select Font...");
        fontButton.setActionCommand("fontButton.clicked");
        fontButton.addActionListener(this);
        controls.add(fontButton);
        content.add(controls, BorderLayout.NORTH);
        this.drawStringPanel1 = new DrawStringPanel("0123456789", false);
        content.add(this.drawStringPanel1);
        return content;
    }

    /**
     * Creates the content for tab 2.
     *
     * @return The content panel.
     */
    private JPanel createTab2Content() {
        final JPanel content = new JPanel(new BorderLayout());
        final JPanel controls = new JPanel();
        controls.add(new JLabel("Text anchor: "));

        this.combo2 = new JComboBox<>();
        populateTextAnchorCombo(this.combo2);
        this.combo2.setActionCommand("combo2.changed");
        this.combo2.addActionListener(this);
        controls.add(this.combo2);
        controls.add(new JLabel("Rotation anchor: "));
        this.combo3 = new JComboBox<>();
        populateTextAnchorCombo(this.combo3);
        this.combo3.setActionCommand("combo3.changed");
        this.combo3.addActionListener(this);
        controls.add(this.combo3);

        this.rotation = new JSlider(JSlider.HORIZONTAL);
        this.rotation.addChangeListener(this);
        controls.add(this.rotation);
        content.add(controls, BorderLayout.NORTH);
        this.drawStringPanel2 = new DrawStringPanel("Rotated Text", true);
        content.add(this.drawStringPanel2);
        return content;
    }

    /**
     * Displays a primitive font chooser dialog to allow the user to change the font.
     */
    private void displayFontDialog() {

        final FontChooserPanel panel = new FontChooserPanel(this.drawStringPanel1.getFont());
        final int result = JOptionPane.showConfirmDialog(this, panel, "Font Selection",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            this.drawStringPanel1.setFont(panel.getSelectedFont());
            this.drawStringPanel2.setFont(panel.getSelectedFont());
        }
    }

    /**
     * Populates a combo box with the available {@link TextAnchor} options.
     *
     * @param combo the combo box.
     */
    private void populateTextAnchorCombo(final JComboBox<String> combo) {
        combo.addItem("TextAnchor.TOP_LEFT");
        combo.addItem("TextAnchor.TOP_CENTER");
        combo.addItem("TextAnchor.TOP_RIGHT");
        combo.addItem("TextAnchor.HALF_ASCENT_LEFT");
        combo.addItem("TextAnchor.HALF_ASCENT_CENTER");
        combo.addItem("TextAnchor.HALF_ASCENT_RIGHT");
        combo.addItem("TextAnchor.CENTER_LEFT");
        combo.addItem("TextAnchor.CENTER");
        combo.addItem("TextAnchor.CENTER_RIGHT");
        combo.addItem("TextAnchor.BASELINE_LEFT");
        combo.addItem("TextAnchor.BASELINE_CENTER");
        combo.addItem("TextAnchor.BASELINE_RIGHT");
        combo.addItem("TextAnchor.BOTTOM_LEFT");
        combo.addItem("TextAnchor.BOTTOM_CENTER");
        combo.addItem("TextAnchor.BOTTOM_RIGHT");
    }

    /**
     * Converts a string to a corresponding {@link TextAnchor} instance.
     *
     * @param text the text.
     * @return The anchor.
     */
    private TextAnchor convertStringToAnchor(final String text) {

        if (text.equals("TextAnchor.TOP_LEFT")) {
            return TextAnchor.TOP_LEFT;
        } else if (text.equals("TextAnchor.TOP_CENTER")) {
            return TextAnchor.TOP_CENTER;
        } else if (text.equals("TextAnchor.TOP_RIGHT")) {
            return TextAnchor.TOP_RIGHT;
        } else if (text.equals("TextAnchor.CENTER_LEFT")) {
            return TextAnchor.CENTER_LEFT;
        } else if (text.equals("TextAnchor.CENTER")) {
            return TextAnchor.CENTER;
        } else if (text.equals("TextAnchor.CENTER_RIGHT")) {
            return TextAnchor.CENTER_RIGHT;
        } else if (text.equals("TextAnchor.HALF_ASCENT_LEFT")) {
            return TextAnchor.HALF_ASCENT_LEFT;
        } else if (text.equals("TextAnchor.HALF_ASCENT_CENTER")) {
            return TextAnchor.HALF_ASCENT_CENTER;
        } else if (text.equals("TextAnchor.HALF_ASCENT_RIGHT")) {
            return TextAnchor.HALF_ASCENT_RIGHT;
        } else if (text.equals("TextAnchor.BASELINE_LEFT")) {
            return TextAnchor.BASELINE_LEFT;
        } else if (text.equals("TextAnchor.BASELINE_CENTER")) {
            return TextAnchor.BASELINE_CENTER;
        } else if (text.equals("TextAnchor.BASELINE_RIGHT")) {
            return TextAnchor.BASELINE_RIGHT;
        } else if (text.equals("TextAnchor.BOTTOM_LEFT")) {
            return TextAnchor.BOTTOM_LEFT;
        } else if (text.equals("TextAnchor.BOTTOM_CENTER")) {
            return TextAnchor.BOTTOM_CENTER;
        } else if (text.equals("TextAnchor.BOTTOM_RIGHT")) {
            return TextAnchor.BOTTOM_RIGHT;
        } else {
            return null;
        }

    }

    /**
     * The starting point for the demo.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) {

        final DrawStringDemo demo = new DrawStringDemo("DrawString Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
