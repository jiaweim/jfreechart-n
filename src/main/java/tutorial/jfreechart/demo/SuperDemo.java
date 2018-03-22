/* --------------
 * SuperDemo.java
 * --------------
 * (C) Copyright 2004-2007, by Object Refinery Limited.
 *
 */

package tutorial.jfreechart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * A demo for JFreeChart.
 */
public class SuperDemo extends ApplicationFrame 
                       implements ActionListener, TreeSelectionListener {

    /** Exit action command. */
    public static final String EXIT_COMMAND = "EXIT";

    private JPanel displayPanel;
    
    private JPanel chartContainer;
    
    private JPanel descriptionContainer;
    
    private JTextPane descriptionPane;
    
    /**
     * Creates a new demo instance.
     * 
     * @param title  the frame title.
     */
    public SuperDemo(String title) {
        super(title);
        setContentPane(createContent());
        setJMenuBar(createMenuBar());
    }

    /**
     * Creates a panel for the main window.
     * 
     * @return A panel.
     */
    private JComponent createContent() {
        JPanel content = new JPanel(new BorderLayout());
        
        JTabbedPane tabs = new JTabbedPane();
        JPanel content1 = new JPanel(new BorderLayout());
        content1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JTree tree = new JTree(createTreeModel());
        tree.addTreeSelectionListener(this);
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(300, 100));
        splitter.setLeftComponent(treePane);
        splitter.setRightComponent(createChartDisplayPanel());
        content1.add(splitter);
        tabs.add("Demos", content1);
        MemoryUsageDemo memUse = new MemoryUsageDemo(300000);
        memUse.new DataGenerator(1000).start();
        tabs.add("Memory Usage", memUse);
        tabs.add("Source Code", createSourceCodePanel());
        tabs.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        content.add(tabs);
        return content;
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // first the file menu
        JMenu fileMenu = new JMenu("File", true);
        fileMenu.setMnemonic('F');

        JMenuItem exportToPDF = new JMenuItem("Export to PDF...", 'p');
        exportToPDF.setActionCommand("EXPORT_TO_PDF");
        exportToPDF.addActionListener(this);
        fileMenu.add(exportToPDF);
        
        fileMenu.addSeparator();
        
        JMenuItem exitItem = new JMenuItem("Exit", 'x');
        exitItem.setActionCommand(EXIT_COMMAND);
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);

        // finally, glue together the menu and return it
        menuBar.add(fileMenu);

        return menuBar;
    }
    
    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL sourceURL = SuperDemo.class.getResource("source.html");
        if (sourceURL != null) {
            try {
                editorPane.setPage(sourceURL);
            } 
            catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + sourceURL);
            }
        } 
        else {
            System.err.println("Couldn't find file: source.html");
        }

        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        
        panel.add(editorScrollPane);
        return panel;
    }
    
    /**
     * Handles menu selections by passing control to an appropriate method.
     *
     * @param event  the event.
     */
    public void actionPerformed(ActionEvent event) {

        String command = event.getActionCommand();
        if (command.equals("EXPORT_TO_PDF")) {
            exportToPDF();
        }
        else if (command.equals(EXIT_COMMAND)) {
            attemptExit();
        }
    }
    
    /**
     * Opens a "Save As..." dialog, inviting the user to save the selected 
     * chart to a file in PDF format.
     */
    private void exportToPDF() {
        Component c = this.chartContainer.getComponent(0);
        if (c instanceof ChartPanel) {
            JFileChooser fc = new JFileChooser();
            fc.setName("untitled.pdf");
            fc.setFileFilter(new FileFilter() {

                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".pdf");
                }

                public String getDescription() {
                    return "Portable Document Format (PDF)";
                }});
            int result = fc.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                ChartPanel cp = (ChartPanel) c;
                try {
                    JFreeChart chart = (JFreeChart) cp.getChart().clone();
                    PDFExportTask t = new PDFExportTask(chart, cp.getWidth(), 
                            cp.getHeight(), fc.getSelectedFile());
                    SwingUtilities.invokeLater(t);
                }
                catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            String message = "Unable to export the selected item.  There is ";
            message += "either no chart selected,\nor else the chart is not ";
            message += "at the expected location in the component hierarchy\n";
            message += "(future versions of the demo may include code to ";
            message += "handle these special cases).";
            JOptionPane.showMessageDialog(this, message, "PDF Export", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    static class PDFExportTask implements Runnable {

        JFreeChart chart;
        
        int width;
        
        int height;
        
        File file;
        
        /**
         * A task that exports a chart to a file in PDF format using iText.
         * 
         * @param chart  the chart.
         * @param width  the width.
         * @param height  the height.
         * @param file  the file.
         */
        public PDFExportTask(JFreeChart chart, int width, int height, 
                File file) {
            this.chart = chart;
            this.file = file;
            this.width = width;
            this.height = height;
            chart.setBorderVisible(true);
            chart.setPadding(new RectangleInsets(2, 2, 2, 2));
        }
        
        public void run() {
            try {
                SuperDemo.saveChartAsPDF(this.file, this.chart, this.width, 
                        this.height, new DefaultFontMapper());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    /**
     * Writes a chart to an output stream in PDF format using iText.
     * 
     * @param out  the output stream.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static void writeChartAsPDF(OutputStream out,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        Rectangle pagesize = new Rectangle(width, height);
        Document document = new Document(pagesize, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.addAuthor("JFreeChart");
            document.addSubject("Demonstration");
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(width, height);
            Graphics2D g2 = tp.createGraphics(width, height, mapper);
            Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(g2, r2D);
            g2.dispose();
            cb.addTemplate(tp, 0, 0);
        }
        catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

    /**
     * Saves a chart to a file in PDF format using iText.
     * 
     * @param file  the file.
     * @param chart  the chart.
     * @param width  the chart width.
     * @param height  the chart height.
     * @param mapper  the font mapper.
     * 
     * @throws IOException if there is an I/O problem.
     */
    public static void saveChartAsPDF(File file,
            JFreeChart chart,
            int width,
            int height,
            FontMapper mapper) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        writeChartAsPDF(out, chart, width, height, mapper);
        out.close();
    }

    /**
     * Exits the application, but only if the user agrees.
     */
    private void attemptExit() {

        String title = "Confirm";
        String message = "Are you sure you want to exit the demo?";
        int result = JOptionPane.showConfirmDialog(
            this, message, title, JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }


    private JPanel createChartDisplayPanel() {
         
        this.displayPanel = new JPanel(new BorderLayout());
        this.chartContainer = new JPanel(new BorderLayout());
        this.chartContainer.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(Color.black)
            )
        );
        this.chartContainer.add(createNoDemoSelectedPanel());
        this.descriptionContainer = new JPanel(new BorderLayout());
        this.descriptionContainer.setBorder(
            BorderFactory.createEmptyBorder(4, 4, 4, 4)
        );
        this.descriptionContainer.setPreferredSize(new Dimension(600, 140));
        this.descriptionPane = new JTextPane();
        this.descriptionPane.setEditable(false);
        JScrollPane scroller = new JScrollPane(
            this.descriptionPane, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        this.descriptionContainer.add(scroller);
        displayDescription("select.html");
        JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitter.setTopComponent(this.chartContainer);
        splitter.setBottomComponent(this.descriptionContainer);
        this.displayPanel.add(splitter);
        splitter.setDividerLocation(0.75);
        return this.displayPanel;
    }
    
    /**
     * Creates a <code>TreeModel</code> with references to all the individual 
     * demo applications.  This is an ugly piece of hard-coding but, hey, it's
     * just a demo!
     * 
     * @return A TreeModel.
     */
    private TreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JFreeChart");
        root.add(createAreaChartsNode());
        root.add(createBarChartsNode());
        root.add(createCombinedAxisChartsNode());
        root.add(createFinancialChartsNode());
        root.add(createGanttChartsNode());
        root.add(createLineChartsNode());
        root.add(createMeterChartsNode());
        root.add(createMultipleAxisChartsNode());
        root.add(createOverlaidChartsNode());
        root.add(createPieChartsNode());
        root.add(createStatisticalChartsNode());
        root.add(createTimeSeriesChartsNode());
        root.add(createXYChartsNode());
        root.add(createMiscellaneousChartsNode());
        root.add(createExperimentalNode());
        return new DefaultTreeModel(root);
    }
    
    /**
     * Creates the tree node for the pie chart demos.
     * 
     * @return A populated tree node.
     */
    private MutableTreeNode createPieChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Pie Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo1", 
                        "PieChartDemo1.java"));  
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo2", 
                        "PieChartDemo2.java"));            
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo3", 
                        "PieChartDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo4", 
                        "PieChartDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo5", 
                        "PieChartDemo5.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo6", 
                        "PieChartDemo6.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo7", 
                        "PieChartDemo7.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo8", 
                        "PieChartDemo8.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo1", 
                        "PieChart3DDemo1.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo2", 
                        "PieChart3DDemo2.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo3", 
                        "PieChart3DDemo3.java"));  
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultiplePieChartDemo1", 
                        "MultiplePieChartDemo1.java"));
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
            new DemoDescription("demo.RingChartDemo1", "RingChartDemo1.java"));
                
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        return root;
    }
    
    /**
     * Creates the tree node for the overlaid chart demos.
     * 
     * @return A populated tree node.
     */
    private MutableTreeNode createOverlaidChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Overlaid Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidBarChartDemo1", 
                        "OverlaidBarChartDemo1.java"));  
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidBarChartDemo2", 
                        "OverlaidBarChartDemo2.java"));            
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidXYPlotDemo1", 
                        "OverlaidXYPlotDemo1.java"));            
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidXYPlotDemo2", 
                        "OverlaidXYPlotDemo2.java"));            
                
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);

        return root;
    }

    /**
     * Creates a tree node containing sample bar charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts");            
        root.add(createCategoryBarChartsNode());
        root.add(createXYBarChartsNode());
        return root;        
    }
    
    /**
     * Creates a tree node containing bar charts based on the 
     * {@link CategoryPlot} class.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createCategoryBarChartsNode() {
        DefaultMutableTreeNode root 
            = new DefaultMutableTreeNode("CategoryPlot");
        
        MutableTreeNode n1 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo1", "BarChartDemo1.java"));                
        MutableTreeNode n2 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo2", "BarChartDemo2.java"));                
        MutableTreeNode n3 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo3", "BarChartDemo3.java"));                
        MutableTreeNode n4 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo4", "BarChartDemo4.java"));                
        MutableTreeNode n5 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo5", "BarChartDemo5.java"));                
        MutableTreeNode n6 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo6", "BarChartDemo6.java"));                
        MutableTreeNode n7 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo7", "BarChartDemo7.java"));                
        MutableTreeNode n8 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo8", "BarChartDemo8.java"));                
        MutableTreeNode n9 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo9", "BarChartDemo9.java"));                
        MutableTreeNode n10 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo10", "BarChartDemo10.java"));                
        MutableTreeNode n11 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo1", "BarChart3DDemo1.java"));                
        MutableTreeNode n12 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo2", "BarChart3DDemo2.java"));                
        MutableTreeNode n13 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo3", "BarChart3DDemo3.java"));
        MutableTreeNode n14 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo4", "BarChart3DDemo4.java"));
        MutableTreeNode n15 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.CylinderChartDemo1", "CylinderChartDemo1.java"));
        MutableTreeNode n16 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.CylinderChartDemo2", "CylinderChartDemo2.java"));
        MutableTreeNode n17 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.IntervalBarChartDemo1", "IntervalBarChartDemo1.java"));
        MutableTreeNode n18 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.LayeredBarChartDemo1", "LayeredBarChartDemo1.java"));
        MutableTreeNode n19 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.LayeredBarChartDemo2", "LayeredBarChartDemo2.java"));
        MutableTreeNode n20 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo1", "StackedBarChartDemo1.java"));
        MutableTreeNode n21 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo2", "StackedBarChartDemo2.java"));
        MutableTreeNode n22 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo3", "StackedBarChartDemo3.java"));
        MutableTreeNode n23 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo4", "StackedBarChartDemo4.java"));
        MutableTreeNode n24 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo5", "StackedBarChartDemo5.java"));
        MutableTreeNode n25 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo6", "StackedBarChartDemo6.java"));
        MutableTreeNode n26 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo7", "StackedBarChartDemo7.java"));
        MutableTreeNode n27 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StatisticalBarChartDemo1", 
                "StatisticalBarChartDemo1.java"));
        MutableTreeNode n28 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.SurveyResultsDemo1", "SurveyResultsDemo1.java"));
        MutableTreeNode n29 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.SurveyResultsDemo2", "SurveyResultsDemo2.java"));
        MutableTreeNode n30 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.SurveyResultsDemo3", "SurveyResultsDemo3.java"));
        MutableTreeNode n31 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.WaterfallChartDemo1", "WaterfallChartDemo1.java"));
            
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        root.add(n17);
        root.add(n18);
        root.add(n19);
        root.add(n20);
        root.add(n21);
        root.add(n22);
        root.add(n23);
        root.add(n24);
        root.add(n25);
        root.add(n26);
        root.add(n27);
        root.add(n28);
        root.add(n29);
        root.add(n30);
        root.add(n31);
        return root;        
    }
    
    private MutableTreeNode createXYBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XYPlot");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo1", 
                        "XYBarChartDemo1.java"));                
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo2", 
                        "XYBarChartDemo2.java"));                
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo3", 
                        "XYBarChartDemo3.java"));                
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo4", 
                        "XYBarChartDemo4.java"));                
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo5", 
                        "XYBarChartDemo5.java"));                
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo6", 
                        "XYBarChartDemo6.java"));                
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StackedXYBarChartDemo1", 
                        "StackedXYBarChartDemo1.java"));                
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StackedXYBarChartDemo2", 
                        "StackedXYBarChartDemo2.java"));                
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StackedXYBarChartDemo3", 
                        "StackedXYBarChartDemo3.java"));                
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.RelativeDateFormatDemo1", 
                        "RelativeDateFormatDemo1.java"));                
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.RelativeDateFormatDemo2", 
                        "RelativeDateFormatDemo2.java"));                
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        return root;
    }
    
    /**
     * Creates a tree node containing line chart items.
     * 
     * @return A tree node.
     */
    private MutableTreeNode createLineChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Line Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo1", 
                "AnnotationDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo1", 
                "LineChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo2", 
                "LineChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo3", 
                "LineChartDemo3.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo4", 
                "LineChartDemo4.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo5", 
                "LineChartDemo5.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo6", 
                "LineChartDemo6.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo7", 
                "LineChartDemo7.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo8", 
                "LineChartDemo8.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StatisticalLineChartDemo1", 
                "StatisticalLineChartDemo1.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo1", 
                "XYStepRendererDemo1.java"));
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo2", 
                "XYStepRendererDemo2.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        
        return root;
    }
    
    /**
     * A node for various area charts.
     * 
     * @return The node.
     */
    private MutableTreeNode createAreaChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Area Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.AreaChartDemo1", "AreaChartDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.StackedXYAreaChartDemo1", 
                "StackedXYAreaChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription("demo.StackedXYAreaChartDemo2", 
                "StackedXYAreaChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription("demo.XYAreaChartDemo1", 
                "XYAreaChartDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription("demo.XYAreaChartDemo2", 
                "XYAreaChartDemo2.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepAreaRendererDemo1", 
                    "XYStepAreaRendererDemo1.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        
        return root;
    }
    
    private MutableTreeNode createStatisticalChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Statistical Charts");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BoxAndWhiskerChartDemo1", 
                "BoxAndWhiskerChartDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HistogramDemo1", 
                "HistogramDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.NormalDistributionDemo1", 
                "NormalDistributionDemo1.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.RegressionDemo1", 
                "RegressionDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo1", 
                "ScatterPlotDemo1.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo2", 
                "ScatterPlotDemo2.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo3", 
                "ScatterPlotDemo3.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo4", 
                "ScatterPlotDemo4.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYErrorRendererDemo1", 
                "XYErrorRendererDemo1.java"));
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        return root;
    }
    
    /**
     * Creates a sub-tree for the time series charts.
     * 
     * @return The root node for the subtree.
     */
    private MutableTreeNode createTimeSeriesChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Time Series Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PeriodAxisDemo1", 
                "PeriodAxisDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PeriodAxisDemo2", 
                "PeriodAxisDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.RelativeDateFormatDemo1", 
                "RelativeDateFormatDemo1.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.TimeSeriesDemo1", 
                "TimeSeriesDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo2", "TimeSeriesDemo2.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo3", "TimeSeriesDemo3.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo4", "TimeSeriesDemo4.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo5", "TimeSeriesDemo5.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo6", "TimeSeriesDemo6.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo7", "TimeSeriesDemo7.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo8", "TimeSeriesDemo8.java"));
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo9", "TimeSeriesDemo9.java"));
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo10", 
                    "TimeSeriesDemo10.java"));
        DefaultMutableTreeNode n14 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo11", 
                    "TimeSeriesDemo11.java"));
        DefaultMutableTreeNode n15 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo12", 
                    "TimeSeriesDemo12.java"));
        DefaultMutableTreeNode n16 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo13", 
                    "TimeSeriesDemo13.java"));
      
        DefaultMutableTreeNode n17 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DeviationRendererDemo1", 
                        "DeviationRendererDemo1.java"));
        DefaultMutableTreeNode n18 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DeviationRendererDemo2", 
                        "DeviationRendererDemo2.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        root.add(n17);
        root.add(n18);
        
        return root;
    }
    
    /**
     * Creates a node for the tree model that contains financial charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createFinancialChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Financial Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CandlestickChartDemo1", 
                "CandlestickChartDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HighLowChartDemo1", 
                "HighLowChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HighLowChartDemo2", 
                "HighLowChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PriceVolumeDemo1", 
                "PriceVolumeDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.YieldCurveDemo", 
                "YieldCurveDemo.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        return root;
    }

    private MutableTreeNode createXYChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XY Charts");
        
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo1", 
                "ScatterPlotDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo2", 
                "ScatterPlotDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo3", 
                "ScatterPlotDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBlockChartDemo1", 
                "XYBlockChartDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBlockChartDemo2", 
                "XYBlockChartDemo2.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBlockChartDemo3", 
                "XYBlockChartDemo3.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYLineAndShapeRendererDemo1", 
                "XYLineAndShapeRendererDemo1.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo1", 
                "XYSeriesDemo1.java"));                
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo2", 
                "XYSeriesDemo2.java"));                
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo3", 
                "XYSeriesDemo3.java"));        
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.WindChartDemo1", 
                "WindChartDemo1.java"));                
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        
        return root;
    }

    /**
     * Creates a node for the tree model that contains "meter" charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createMeterChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Meter Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo1", 
                "MeterChartDemo1.java"));
        
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo2", 
                "MeterChartDemo2.java"));
        
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo3", 
                "MeterChartDemo3.java"));
        
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ThermometerDemo1", 
                "ThermometerDemo1.java"));
       
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        return root;
    }

    private MutableTreeNode createMultipleAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Multiple Axis Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo1", 
                        "DualAxisDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo2", 
                        "DualAxisDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo3", 
                        "DualAxisDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo4", 
                        "DualAxisDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo5", 
                        "DualAxisDemo5.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultipleAxisDemo1", 
                        "MultipleAxisDemo1.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultipleAxisDemo2", 
                        "MultipleAxisDemo2.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultipleAxisDemo3", 
                        "MultipleAxisDemo3.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ParetoChartDemo1", 
                        "ParetoChartDemo1.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        
        return root;
    }
    
    private MutableTreeNode createCombinedAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Combined Axis Charts"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedCategoryPlotDemo1", 
                "CombinedCategoryPlotDemo1.java"
            )
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedCategoryPlotDemo2", 
                "CombinedCategoryPlotDemo2.java"
            )
        );
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedTimeSeriesDemo1", 
                "CombinedTimeSeriesDemo1.java"
            )
        );
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo1", 
                "CombinedXYPlotDemo1.java"
            )
        );
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo2", 
                "CombinedXYPlotDemo2.java"
            )
        );
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo3", 
                "CombinedXYPlotDemo3.java"
            )
        );
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo4", 
                "CombinedXYPlotDemo4.java"
            )
        );

        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        
        return root;
    }

    private MutableTreeNode createGanttChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Gantt Charts"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.GanttDemo1", "GanttDemo1.java")
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.GanttDemo2", "GanttDemo2.java")
        );
        
        root.add(n1);
        root.add(n2);
        
        return root;
    }
    
    /**
     * Creates the subtree containing the miscellaneous chart types.
     * 
     * @return A subtree.
     */
    private MutableTreeNode createMiscellaneousChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Miscellaneous");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BubbleChartDemo1", 
                "BubbleChartDemo1.java"));
        
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BubbleChartDemo2", 
                "BubbleChartDemo2.java"));

        DefaultMutableTreeNode n2b = new DefaultMutableTreeNode(
                new DemoDescription("demo.CategoryLabelPositionsDemo1", 
                "CategoryLabelPositionsDemo1.java"));

        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CategoryStepChartDemo1", 
                "CategoryStepChartDemo1.java"));
        
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassDemo1", "CompassDemo1.java"));
        
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassFormatDemo1", 
                "CompassFormatDemo1.java"));
        
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassFormatDemo2", 
                "CompassFormatDemo2.java"));
        
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DifferenceChartDemo1", 
                "DifferenceChartDemo1.java"));
        
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DifferenceChartDemo2", 
                "DifferenceChartDemo2.java"));
        
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.EventFrequencyDemo1", 
                "EventFrequencyDemo1.java"));

        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.GridBandDemo1", 
                "GridBandDemo1.java"));
        
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HideSeriesDemo1", 
                "HideSeriesDemo1.java"));

        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultipleDatasetDemo1", 
                "MultipleDatasetDemo1.java"));

        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PolarChartDemo1", 
                "PolarChartDemo1.java"));

        DefaultMutableTreeNode n14 = new DefaultMutableTreeNode(
                new DemoDescription("demo.SpiderWebChartDemo1", 
                "SpiderWebChartDemo1.java"));
        
        DefaultMutableTreeNode n15 = new DefaultMutableTreeNode(
                new DemoDescription("demo.SymbolAxisDemo1", 
                "SymbolAxisDemo1.java"));
        
        DefaultMutableTreeNode n16 = new DefaultMutableTreeNode(
                new DemoDescription("demo.TranslateDemo1", 
                "TranslateDemo1.java"));
        
        DefaultMutableTreeNode n17 = new DefaultMutableTreeNode(
                new DemoDescription("demo.YIntervalChartDemo1", 
                "YIntervalChartDemo1.java"));

        root.add(createAnnotationsNode());
        root.add(createCrosshairChartsNode());
        root.add(createDynamicChartsNode());
        root.add(createItemLabelsNode());
        root.add(createLegendNode());
        root.add(createMarkersNode());
        root.add(createOrientationNode());
        root.add(n1);
        root.add(n2);
        root.add(n2b);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        root.add(n17);
        
        return root;
    }
    
    private MutableTreeNode createAnnotationsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Annotations");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo1", 
                "AnnotationDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo2", 
                "AnnotationDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CategoryPointerAnnotationDemo1", 
                "CategoryPointerAnnotationDemo1.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBoxAnnotationDemo1", 
                "XYBoxAnnotationDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYPolygonAnnotationDemo1", 
                "XYPolygonAnnotationDemo1.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        return root;
    }

    private MutableTreeNode createCrosshairChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Crosshairs");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo1", 
                "CrosshairDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo2", 
                "CrosshairDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo3", 
                "CrosshairDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo4", 
                "CrosshairDemo4.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        
        return root;
    }
    
    private MutableTreeNode createDynamicChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Dynamic Charts");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo1", 
                "DynamicDataDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo2", 
                "DynamicDataDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo3", 
                "DynamicDataDemo3.java"));
        root.add(n1);
        root.add(n2);
        root.add(n3);
        return root;
    }
    
    private MutableTreeNode createItemLabelsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Item Labels");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo1", 
                "ItemLabelDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo2", 
                "ItemLabelDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo3", 
                "ItemLabelDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo4", 
                "ItemLabelDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo5", 
                "ItemLabelDemo5.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        
        return root;
    }
    
    private MutableTreeNode createLegendNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Legends");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LegendWrapperDemo1", 
                        "LegendWrapperDemo1.java"));
        
        root.add(n1);
        
        return root;
    }
    
    private MutableTreeNode createMarkersNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Markers");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.CategoryMarkerDemo1", 
            "CategoryMarkerDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.CategoryMarkerDemo2", 
            "CategoryMarkerDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription("demo.MarkerDemo1", "MarkerDemo1.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription("demo.MarkerDemo2", "MarkerDemo2.java"));
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        return root;
    }
    
    private MutableTreeNode createOrientationNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Plot Orientation"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.PlotOrientationDemo1", "PlotOrientationDemo1.java"
            )
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.PlotOrientationDemo2", "PlotOrientationDemo2.java"
            )
        );
       
        root.add(n1);
        root.add(n2);
        
        return root;
    }
    
    private MutableTreeNode createExperimentalNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Experimental");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.DialDemo1", 
                "DialDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.DialDemo2", 
                "DialDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.DialDemo3", 
                "DialDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.DialDemo4", 
                "DialDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.DialDemo5", 
                "DialDemo5.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.LogAxisDemo1", 
                "LogAxisDemo1.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.VectorPlotDemo1", 
                "VectorPlotDemo1.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.experimental.XYTitleAnnotationDemo1", 
                "XYTitleAnnotationDemo1.java"));
        
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);

        return root;
    }

    
    private void displayDescription(String fileName) {
        java.net.URL descriptionURL = SuperDemo.class.getResource(fileName);
        if (descriptionURL != null) {
            try {
                this.descriptionPane.setPage(descriptionURL);
            } 
            catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " 
                        + descriptionURL);
            }
        } 
        else {
            System.err.println("Couldn't find file: " + fileName);
        }
         
    }
    
    /**
     * Receives notification of tree selection events and updates the demo 
     * display accordingly.
     * 
     * @param event  the event.
     */
    public void valueChanged(TreeSelectionEvent event) {
        TreePath path = event.getPath();
        Object obj = path.getLastPathComponent();
        if (obj != null) {
            DefaultMutableTreeNode n = (DefaultMutableTreeNode) obj;
            Object userObj = n.getUserObject();
            if (userObj instanceof DemoDescription) {
                DemoDescription dd = (DemoDescription) userObj;
                SwingUtilities.invokeLater(new DisplayDemo(this, dd));
            }
            else {
                this.chartContainer.removeAll();
                this.chartContainer.add(createNoDemoSelectedPanel());
                this.displayPanel.validate();
                displayDescription("select.html");
            }
        }
        System.out.println(obj);
    }
    
    private JPanel createNoDemoSelectedPanel() {
        JPanel panel = new JPanel(new FlowLayout()) {

            /* (non-Javadoc)
             * @see javax.swing.JComponent#getToolTipText()
             */
            public String getToolTipText() {
                // TODO Auto-generated method stub
                return "(" + getWidth() + ", " + getHeight() + ")";
            }
            
        };
        ToolTipManager.sharedInstance().registerComponent(panel);
        panel.add(new JLabel("No demo selected"));
        panel.setPreferredSize(new Dimension(600, 400));
        return panel;
    }
    
    /**
     * Starting point for the JFreeChart Demo Collection.
     * 
     * @param args  ignored.
     */
    public static void main(String[] args) {
        SuperDemo demo = new SuperDemo("JFreeChart 1.0.5 Demo Collection");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    static class DisplayDemo implements Runnable {
        
        private SuperDemo app;
        
        private DemoDescription demoDescription;
        
        /**
         * Creates a new runnable.
         * 
         * @param app  the application.
         * @param d  the demo description.
         */
        public DisplayDemo(SuperDemo app, DemoDescription d) {
            this.app = app;
            this.demoDescription = d;    
        }
        
        /**
         * Runs the task.
         */
        public void run() {
            try {
                Class c = Class.forName(this.demoDescription.getClassName());
                Method m = c.getDeclaredMethod("createDemoPanel", null);
                JPanel panel = (JPanel) m.invoke(null, null);
                this.app.chartContainer.removeAll();
                this.app.chartContainer.add(panel);
                this.app.displayPanel.validate();
                String className = c.getName();
                String fileName = className;
                int i = className.lastIndexOf('.');
                if (i > 0) {
                    fileName = className.substring(i + 1);
                }
                fileName = fileName + ".html";
                this.app.displayDescription(fileName);
                
            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }
            catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            catch (IllegalAccessException e4) {
                e4.printStackTrace();
            }
               
        }
        
    }
}
