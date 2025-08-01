package note.jfreechart.chart3d;

import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DPanel;
import org.jfree.chart3d.style.ChartStyle;
import org.jfree.chart3d.style.ChartStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * A demo application for Orson Charts.  This aggregates all the individual
 * demos which can also be run independently.
 */
@SuppressWarnings("serial")
public class OrsonChartsDemo extends JFrame implements ActionListener {

    /**
     * Default size for the content panel in the demo applications.
     */
    public static final Dimension DEFAULT_CONTENT_SIZE
            = new Dimension(892, 640);

    private OrsonChartsDemoComponent demoComponent;

    /**
     * Creates a new demo instance with the specified frame title.
     *
     * @param title the title.
     */
    public OrsonChartsDemo(String title) {
        super(title);
        addWindowListener(new ExitOnClose());
        setJMenuBar(createMenuBar());
        add(createContent());
    }

    /**
     * Creates the menu bar.
     *
     * @return The menu bar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu("File"));
        menuBar.add(createStyleMenu("Style"));
        return menuBar;
    }

    /**
     * Creates the file menu.
     *
     * @param title the menu title.
     * @return The menu.
     */
    private JMenu createFileMenu(String title) {
        JMenu fileMenu = new JMenu(title);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("EXIT");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu createStyleMenu(String title) {
        JMenu styleMenu = new JMenu(title);
        JMenuItem noStyleMenuItem = new JRadioButtonMenuItem("No Style (style as coded)");
        noStyleMenuItem.setActionCommand("NO_STYLE");
        noStyleMenuItem.addActionListener(this);

        JMenuItem orson1StyleMenuItem = new JRadioButtonMenuItem("Orson 1 Style");
        orson1StyleMenuItem.setActionCommand("ORSON1_STYLE");
        orson1StyleMenuItem.addActionListener(this);

        JMenuItem orson2StyleMenuItem = new JRadioButtonMenuItem("Orson 2 Style");
        orson2StyleMenuItem.setActionCommand("ORSON2_STYLE");
        orson2StyleMenuItem.addActionListener(this);

        JMenuItem iceCubeStyleMenuItem = new JRadioButtonMenuItem("Ice Cube Style");
        iceCubeStyleMenuItem.setActionCommand("ICE_CUBE_STYLE");
        iceCubeStyleMenuItem.addActionListener(this);

        JMenuItem pastelStyleMenuItem = new JRadioButtonMenuItem("Pastel");
        pastelStyleMenuItem.setActionCommand("PASTEL_STYLE");
        pastelStyleMenuItem.addActionListener(this);

        JMenuItem logicalFontStyleMenuItem = new JRadioButtonMenuItem("Logical Font Style");
        logicalFontStyleMenuItem.setActionCommand("LOGICAL_FONT_STYLE");
        logicalFontStyleMenuItem.addActionListener(this);

        styleMenu.add(noStyleMenuItem);
        styleMenu.add(orson1StyleMenuItem);
        styleMenu.add(orson2StyleMenuItem);
        styleMenu.add(iceCubeStyleMenuItem);
        styleMenu.add(pastelStyleMenuItem);
        styleMenu.add(logicalFontStyleMenuItem);

        // set radio button group and default selection
        ButtonGroup group = new ButtonGroup();
        group.add(noStyleMenuItem);
        group.add(orson1StyleMenuItem);
        group.add(orson2StyleMenuItem);
        group.add(iceCubeStyleMenuItem);
        group.add(pastelStyleMenuItem);
        group.add(logicalFontStyleMenuItem);
        noStyleMenuItem.setSelected(true);

        return styleMenu;
    }

    /**
     * Creates the main content of the demo application, a tabbed pane with
     * one tab showing the demo charts and another showing general information
     * about Orson Charts.
     *
     * @return The content component.
     */
    private JComponent createContent() {
        JTabbedPane tabs = new JTabbedPane();
        this.demoComponent = new OrsonChartsDemoComponent();
        tabs.add("Demos", this.demoComponent);
        tabs.add("About", createAboutPanel());
        return tabs;
    }

    /**
     * Creates a panel containing information about Orson Charts.
     *
     * @return A panel containing information about Orson Charts.
     */
    private JPanel createAboutPanel() {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setPreferredSize(new Dimension(800, 400));
        URL descriptionURL = OrsonChartsDemo.class.getResource(
                "/note/jfreechart/chart3d/about.html");
        try {
            textPane.setPage(descriptionURL);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JScrollPane scroller = new JScrollPane(textPane);
        result.add(scroller);
        return result;
    }

    private void applyStyleToChartsInPanels(List<Chart3DPanel> panels,
            ChartStyle style) {
        for (Chart3DPanel panel : panels) {
            Chart3D chart = (Chart3D) panel.getDrawable();
            ChartStyle s = style.clone();
            chart.setStyle(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("EXIT".equals(e.getActionCommand())) {
            System.exit(0);
        }
        Component c = this.demoComponent.getChartContainer().getComponent(0);
        if (c instanceof DemoPanel) {
            DemoPanel dp = (DemoPanel) c;
            List<Chart3DPanel> chartPanels = dp.getChartPanels();
            if ("NO_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(null);
            }

            if ("ORSON1_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(ChartStyles.createOrson1Style());
                applyStyleToChartsInPanels(chartPanels, ChartStyles.createOrson1Style());
            }

            if ("ORSON2_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(ChartStyles.createOrson2Style());
                applyStyleToChartsInPanels(chartPanels, ChartStyles.createOrson2Style());
            }

            if ("ICE_CUBE_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(ChartStyles.createIceCubeStyle());
                applyStyleToChartsInPanels(chartPanels, ChartStyles.createIceCubeStyle());
            }

            if ("PASTEL_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(ChartStyles.createPastelStyle());
                applyStyleToChartsInPanels(chartPanels, ChartStyles.createPastelStyle());
            }

            if ("LOGICAL_FONT_STYLE".equals(e.getActionCommand())) {
                this.demoComponent.setChartStyle(ChartStyles.createLogicalFontStyle());
                applyStyleToChartsInPanels(chartPanels, ChartStyles.createLogicalFontStyle());
            }
        }

    }

    /**
     * Starting point for the demo application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrsonChartsDemo app = new OrsonChartsDemo("Orson Charts Demo 2.1");
            app.pack();
            app.setVisible(true);
        });
    }

}