package admin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Statistics extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Panel panel_1_1;
    private ChartPanel chartPanel;
    private Thread updateThread;
    private volatile boolean running = true;
    Color textPrimaryColor = new Color(0, 0, 0);
    Color primaryColor = new Color(102, 102, 102);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Statistics frame = new Statistics();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Statistics() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Statistics.class.getResource("/icons/aaa (2).png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    for (double i = 0.1; i <= 1.0; i += 0.1) {
                        float f = (float) i;
                        Statistics.this.setOpacity(f);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                running = false; // Stop the update thread
                if (updateThread != null) {
                    try {
                        updateThread.join(); // Wait for thread to terminate
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 990, 605);
        setLocation(360, 105);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Panel panel = new Panel();
        panel.setBackground(new Color(51, 51, 51));
        panel.setBounds(0, 0, 990, 605);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("x");
        lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                AdminDashboards.panel_4_2.setBackground(primaryColor);
                AdminDashboards.panel_5_2.setBackground(primaryColor);
                AdminDashboards.menu3.setForeground(textPrimaryColor);
                AdminDashboards.dark3.setVisible(true);
                AdminDashboards.light3.setVisible(false);
            }
        });
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblNewLabel.setBounds(948, 0, 42, 47);
        panel.add(lblNewLabel);

        panel_1_1 = new Panel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(51, 51, 51)); // Changed to black
        panel_1_1.setBounds(0, 69, 990, 536);
        panel.add(panel_1_1);

        JLabel lblThngK = new JLabel("Biểu đồ doanh thu");
        lblThngK.setHorizontalAlignment(SwingConstants.CENTER);
        lblThngK.setForeground(Color.WHITE);
        lblThngK.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblThngK.setBounds(50, 11, 881, 52);
        panel.add(lblThngK);

        // Initialize the chart
        initChart();
        startChartUpdateThread();
    }

    private void initChart() {
        // Create initial dataset
        DefaultCategoryDataset dataset = createDataset();

        // Create the chart
        JFreeChart chart = ChartFactory.createBarChart(
            "", // Chart title (empty as we have a label above)
            "Tháng", // X-axis label
            "Doanh thu (VND)", // Y-axis label
            dataset // Dataset
        );

        // Set the chart's outer background to black
        chart.setBackgroundPaint(new Color(51, 51, 51));

        // Customize the chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(51, 51, 51)); // Black plot background
        plot.setDomainGridlinePaint(new Color(200, 200, 200)); // Light gray gridlines
        plot.setRangeGridlinePaint(new Color(200, 200, 200)); // Light gray gridlines

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        domainAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 14));
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        domainAxis.setLabelPaint(Color.WHITE); // White axis label
        domainAxis.setTickLabelPaint(Color.WHITE); // White tick labels

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 14));
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        rangeAxis.setLabelPaint(Color.WHITE); // White axis label
        rangeAxis.setTickLabelPaint(Color.WHITE); // White tick labels

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
       
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, new Color(209, 49, 27)); // Blue bars
        renderer.setMaximumBarWidth(0.08); // Adjust bar width
        
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        renderer.setDefaultItemLabelPaint(Color.WHITE); // White labels
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(
            ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER
        ));
        // Create chart panel and add to panel_1_1
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(0, 0, 0)); // Set ChartPanel background to black
        chartPanel.setBounds(50, 20, 890, 496);
        panel_1_1.add(chartPanel);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<Integer, Integer> monthlyRevenue = StatisticsDAO.getInstance().getMonthlyRevenue2025();

        // Add data for each month (1 to 12)
        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        for (int month = 1; month <= 12; month++) {
            int revenue = monthlyRevenue.getOrDefault(month, 0);
            dataset.addValue(revenue, "Doanh thu", months[month - 1]);
        }

        return dataset;
    }

    private void startChartUpdateThread() {
        updateThread = new Thread(() -> {
            while (running) {
                try {
                    // Update the dataset
                    DefaultCategoryDataset newDataset = createDataset();
                    EventQueue.invokeLater(() -> {
                        if (chartPanel != null) {
                            chartPanel.getChart().getCategoryPlot().setDataset(newDataset);
                        }
                    });
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.setDaemon(true); // Make thread daemon so it terminates with the application
        updateThread.start();
    }
}