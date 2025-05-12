package admin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import ITEM.category;
import ITEM.managepurchase;
import dao.DetailDAO;
import dao.ManagerPurchaseDAO;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Cursor;
import java.awt.Toolkit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
public class ManagePurchase extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfidhd;
    private JTextField search;
    ManagerPurchaseDAO s2;
    Color textPrimaryColor = new Color(0, 0, 0);
    Color primaryColor = new Color(102, 102, 102);
    int selectedRow;
    StatisticsDAO s = new StatisticsDAO();
    private JTextField tftotal;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagePurchase frame = new ManagePurchase();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clear() {
        tfidhd.setText("######");
        tftotal.setText("0");
        loadtable();
    }

    private void loadtable() {
        ArrayList<managepurchase> list = s2.loadPurchased();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (managepurchase e : list) {
            model.addRow(new Object[] { e.getStt(), e.getIdhd(), e.getTotalprice(), e.getTotalqty(), e.getHour(), e.getDay() });
        }
    }

    private void init() {
        this.s2 = ManagerPurchaseDAO.getInstance();
        loadtable();
    }

    private void exportTableToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Quản Lý Mua Áo");

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();

        Row headerRow = sheet.createRow(0);
        for (int j = 0; j < columnCount; j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(model.getColumnName(j));
        }

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.createCell(j);
                Object value = model.getValueAt(i, j);
                if (value != null) {
                    cell.setCellValue(value.toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }

        for (int j = 0; j < columnCount; j++) {
            sheet.autoSizeColumn(j);
        }

        java.awt.FileDialog fileDialog = new java.awt.FileDialog((java.awt.Frame) null, "Chọn nơi lưu file Excel", java.awt.FileDialog.SAVE);
        fileDialog.setFile("QuanLyMuaAo.xlsx");
        fileDialog.setVisible(true);

        String fileName = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (fileName != null) {
            if (!fileName.toLowerCase().endsWith(".xlsx")) {
                fileName += ".xlsx";
            }
            String filePath = directory + fileName;
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công tại: " + filePath, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Hủy xuất file Excel.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

        try {
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ManagePurchase() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ManagePurchase.class.getResource("/icons/aaa (2).png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    for (double i = 0.1; i <= 1.0; i += 0.1) {
                        float f = (float) i;
                        ManagePurchase.this.setOpacity(f);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel lblQunLMua = new JLabel("Quản lý mua áo");
        lblQunLMua.setHorizontalAlignment(SwingConstants.CENTER);
        lblQunLMua.setForeground(Color.WHITE);
        lblQunLMua.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblQunLMua.setBounds(0, 0, 917, 52);
        panel.add(lblQunLMua);

        JLabel lblNewLabel_4_1_1 = new JLabel("Id hoá đơn");
        lblNewLabel_4_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_4_1_1.setBounds(39, 58, 119, 25);
        panel.add(lblNewLabel_4_1_1);

        tfidhd = new JTextField();
        tfidhd.setEditable(false);
        tfidhd.setHorizontalAlignment(SwingConstants.CENTER);
        tfidhd.setFont(new Font("Segoe UI", Font.BOLD, 25));
        tfidhd.setText("######");
        tfidhd.setColumns(10);
        tfidhd.setBounds(40, 86, 205, 31);
        panel.add(tfidhd);

        JLabel lblNewLabel_1 = new JLabel("Tìm : ");
        lblNewLabel_1.setIcon(new ImageIcon(ManagePurchase.class.getResource("/icons/search_Light.png")));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblNewLabel_1.setBounds(10, 178, 73, 40);
        panel.add(lblNewLabel_1);

        search = new JTextField();
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
            	String Search = search.getText();
            	s2.getManagepurchaseValue(table, Search);
            }
        });
        search.setFont(new Font("Segoe UI", Font.BOLD, 25));
        search.setColumns(10);
        search.setBounds(93, 178, 310, 39);
        panel.add(search);

        JButton btxn = new JButton("Xuất Excel");
        btxn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btxn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btxn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportTableToExcel();
            }
        });
        btxn.setBackground(new Color(255, 255, 255));
        btxn.setForeground(new Color(0, 142, 0));
        btxn.setBounds(524, 73, 180, 41);
        panel.add(btxn);

        JButton btnclear = new JButton("Làm mới");
        btnclear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnclear.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        btnclear.setForeground(new Color(208, 208, 0));
        btnclear.setBackground(new Color(255, 255, 255));
        btnclear.setBounds(737, 73, 180, 41);
        panel.add(btnclear);

        JButton btndelete = new JButton("Xoá");
        btndelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btndelete.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btndelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfidhd.getText().equalsIgnoreCase("########")) {
                    JOptionPane.showMessageDialog(null, "Không có lựa chọn hoá đơn nào cần xoá. Yêu cầu lựa chọn hoá đơn để xoá", "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    String idhd = tfidhd.getText();
                    s2.deletedetail(idhd);
                    s2.deletepurchase(idhd);
                    clear();
                }
            }
        });
        btndelete.setForeground(new Color(230, 0, 0));
        btndelete.setBackground(new Color(255, 255, 255));
        btndelete.setBounds(737, 144, 180, 41);
        panel.add(btndelete);

        JButton btexit = new JButton("X");
        btexit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btexit.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btexit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdminDashboards.panel_4_1.setBackground(primaryColor);
                AdminDashboards.panel_5_1.setBackground(primaryColor);
                AdminDashboards.menu2.setForeground(textPrimaryColor);
                AdminDashboards.dark2.setVisible(true);
                AdminDashboards.light2.setVisible(false);
            }
        });
        btexit.setForeground(Color.WHITE);
        btexit.setBackground(new Color(255, 0, 0));
        btexit.setBounds(924, 0, 66, 41);
        panel.add(btexit);

        JLabel lblNewLabel_4_1_1_1 = new JLabel("Tổng tiền hoá đơn");
        lblNewLabel_4_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_4_1_1_1.setBounds(255, 58, 119, 25);
        panel.add(lblNewLabel_4_1_1_1);

        tftotal = new JTextField();
        tftotal.setEditable(false);
        tftotal.setText("0");
        tftotal.setHorizontalAlignment(SwingConstants.CENTER);
        tftotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tftotal.setColumns(10);
        tftotal.setBounds(256, 86, 205, 31);
        panel.add(tftotal);

        JButton btnNewButton = new JButton("Chi tiết");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                Detail detail = new Detail(dialog);
                dialog.getContentPane().setLayout(null);
                dialog.setSize(1200, 800);
                dialog.setLocationRelativeTo(null);
                dialog.getContentPane().add(detail);
                dialog.setVisible(true);
            }
        });
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setForeground(new Color(0, 128, 192));
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnNewButton.setBounds(524, 144, 180, 40);
        panel.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 233, 949, 361);
        panel.add(scrollPane);

        table = new JTable();
        table.setRowHeight(40);
        table.setFont(new Font("Segoe UI", Font.BOLD, 25));
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null},
            },
            new String[] {
                "Stt", "Mã hoá đơn", "Tổng cộng", "Tổng số lượng", "Giờ mua", "Ngày mua"
            }
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object idObj = table.getValueAt(selectedRow, 1);
                        tfidhd.setText(idObj != null ? idObj.toString() : "########");
                        Object nameObj = table.getValueAt(selectedRow, 2);
                        tftotal.setText(nameObj != null ? nameObj.toString() + " VNĐ" : "0");
                    }
                }
            }
        });
        init();
    }
}