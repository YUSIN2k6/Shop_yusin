package user;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;




import ITEM.detailitem;
import ITEM.infobuy;
import ITEM.temp;
import admin.StatisticsDAO;
import dao.CategoryDAO;
import dao.DetailDAO;
import dao.ManagerPurchaseDAO;
import dao.PurchaseDetailDAO;

public class PurchaseDetail extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PurchaseDetail.class.getName());
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_2;
    private DetailDAO s;
    private Color textPrimaryColor = new Color(0, 0, 0);
    private Color primaryColor = new Color(102, 102, 102);
    private PurchaseDetailDAO p = new PurchaseDetailDAO();
    private ArrayList<ITEM.item> items = new ArrayList<>();
    private ManagerPurchaseDAO mp = new ManagerPurchaseDAO();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private String date = df.format(new Date());
    private JTable table;
    private String[] value = new String[5];
    private JLabel lbday;
    private JRadioButton rdo0persent;
    private JRadioButton rdo10persent;
    private JRadioButton rdo20persent;
    private JLabel lbidhd;
    private JLabel lbtotalprice;
    private JLabel lbhour;
    private JLabel lbtotalprice2;
	CategoryDAO s2;
	ManagerPurchaseDAO s3;
	StatisticsDAO s4;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PurchaseDetail frame = new PurchaseDetail();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setDefault() {
        UserDashboard.panel_4_1.setBackground(primaryColor);
        UserDashboard.panel_5_1.setBackground(primaryColor);
        UserDashboard.menu2.setForeground(textPrimaryColor);
        UserDashboard.dark2.setVisible(true);
        UserDashboard.light2.setVisible(false);
    }

    public void LoadData2() {
        ArrayList<detailitem> list = s.loaddetailitem();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (detailitem e : list) {
            model.addRow(new Object[] {
                e.getId(),
                e.getName(),
                e.getQty(),
                e.getTotalprice()
            });
        }
    }

    public void export() {
    	try {
    	value = s.getInfoBuyValue();
    	MessageFormat header = new MessageFormat("Shop Quần Áo\t Hoá đơn: "+ value[1]+ " Tổng cộng: "+value[2]);
    	MessageFormat footer = new MessageFormat("Page {0, number, integer}");
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			e.printStackTrace();
		}
    }
            
    

    public void showvalue() {
        value = s.getInfoBuyValue();
        lbidhd.setText(value[1]);
        lbtotalprice.setText(String.format("%,d", Integer.parseInt(value[2])) + " VNĐ");
        lbtotalprice2.setText(String.format("%,d", Integer.parseInt(value[2])) + " VNĐ");
        lbhour.setText(value[3]);
        lbday.setText(value[4]);
    }

    private void init() {
        this.s = DetailDAO.getInstance();
        this.s2= CategoryDAO.getInstance();
        this.s3 = ManagerPurchaseDAO.getInstance();
        this.s4= StatisticsDAO.getInstance();
        showvalue();
        LoadData2();
    }

    public PurchaseDetail() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    for (double i = 0.1; i <= 1.0; i += 0.1) {
                        float f = (float) i;
                        PurchaseDetail.this.setOpacity(f);
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
        setBounds(500, 200, 990, 605);
        setLocation(360, 105);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Panel panel = new Panel();
        panel.setBounds(0, 0, 990, 605);
        panel.setBackground(new Color(51, 51, 51));
        contentPane.add(panel);
        panel.setLayout(null);

        lblNewLabel_2 = new JLabel("Chi tiết mua hàng");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 35));
        lblNewLabel_2.setBounds(351, 1, 295, 52);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_12 = new JLabel("x");
        lblNewLabel_12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                setDefault();
            }
        });
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12.setForeground(Color.WHITE);
        lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblNewLabel_12.setBounds(948, 0, 42, 47);
        panel.add(lblNewLabel_12);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 255, 255), 4));
        panel_1.setBounds(10, 64, 486, 191);
        panel.add(panel_1);
        panel_1.setLayout(null);

        lblNewLabel = new JLabel("Mã hoá đơn:");
        lblNewLabel.setBounds(10, 11, 180, 30);
        panel_1.add(lblNewLabel);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblTngHon = new JLabel("Tổng tiền:");
        lblTngHon.setBounds(10, 52, 180, 30);
        panel_1.add(lblTngHon);
        lblTngHon.setForeground(new Color(0, 0, 0));
        lblTngHon.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblGiMua = new JLabel("Giờ thanh toán:");
        lblGiMua.setBounds(10, 93, 180, 30);
        panel_1.add(lblGiMua);
        lblGiMua.setForeground(new Color(0, 0, 0));
        lblGiMua.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblNgyMua = new JLabel("Ngày mua:");
        lblNgyMua.setBounds(10, 134, 180, 30);
        panel_1.add(lblNgyMua);
        lblNgyMua.setForeground(new Color(0, 0, 0));
        lblNgyMua.setFont(new Font("Segoe UI", Font.BOLD, 20));

        lbidhd = new JLabel("0");
        lbidhd.setForeground(Color.BLACK);
        lbidhd.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbidhd.setBounds(200, 11, 276, 30);
        panel_1.add(lbidhd);

        lbtotalprice = new JLabel("0");
        lbtotalprice.setForeground(Color.BLACK);
        lbtotalprice.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbtotalprice.setBounds(200, 52, 276, 30);
        panel_1.add(lbtotalprice);

        lbhour = new JLabel("0");
        lbhour.setForeground(Color.BLACK);
        lbhour.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbhour.setBounds(200, 93, 276, 30);
        panel_1.add(lbhour);

        lbday = new JLabel("0");
        lbday.setForeground(Color.BLACK);
        lbday.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbday.setBounds(200, 134, 276, 30);
        panel_1.add(lbday);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 277, 661, 305);
        panel.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.BOLD, 20));
        table.setRowHeight(40);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setModel(new DefaultTableModel(
            new Object[][] { { null, null, null, null } },
            new String[] { "stt", "Tên món hàng", "Số lượng", "Thành tiền" }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(233);
        scrollPane.setViewportView(table);

        JButton btnXcNhnHo = new JButton("Xác nhận hoá đơn");
        btnXcNhnHo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 int totalqty = s.gettotalqty();
        		 value = s.getInfoBuyValue();
        		 String idhd = value[1];
        		 int totalprice1 = Integer.parseInt(value[2]);
        		 String hour = value[3];
        		 String dayStr = value[4];
        		 java.sql.Date day = null;
        	        try {
        	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	            java.util.Date parsedDate = sdf.parse(dayStr);
        	            day = new java.sql.Date(parsedDate.getTime());
        	        } catch (Exception ex) {
        	            ex.printStackTrace();
        	            JOptionPane.showMessageDialog(null, "Invalid date format!", "Error", JOptionPane.ERROR_MESSAGE);
        	            return;
        	        }
        		 
        		 s3.insertPurchase(idhd, totalprice1, totalqty, hour, day);
        		 s4.insertTotalprice(totalprice1, day);
        		
        		ArrayList<detailitem> List = s.loaddetailitem();
                 for (detailitem tempItem : List) {
                     String name = tempItem.getName();
                     int qty = tempItem.getQty();
                     int totalprice2 = tempItem.getTotalprice();
                     int price = totalprice2/qty;
                     s3.insertdetail(idhd, name, qty, price, totalprice2);
                     
                 }
                
                 s.Deleteall();
                 s.updateresetinfo();
                 showvalue();
                 LoadData2();
                 JOptionPane.showMessageDialog(null, "Xác nhận hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        btnXcNhnHo.setForeground(new Color(52, 219, 4));
        btnXcNhnHo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnXcNhnHo.setBackground(Color.WHITE);
        btnXcNhnHo.setBounds(726, 295, 218, 50);
        panel.add(btnXcNhnHo);

        JButton btnXutHon = new JButton("Huỷ hoá đơn");
        btnXutHon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ArrayList<detailitem> List = s.loaddetailitem();
                 for (detailitem tempItem : List) {
                     String name = tempItem.getName();
                     int qtyTemp = tempItem.getQty();
                     int qtyCategory = s2.getqtyatCategory(name);
                     s2.updateProduct2(name, qtyCategory + qtyTemp);
                 }
                 s.Deleteall();
                 s.updateresetinfo();
                 showvalue();
                 lbday.setText(value[4]);
                 LoadData2();
                 JOptionPane.showMessageDialog(null, "Huỷ hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        btnXutHon.setForeground(new Color(213, 0, 0));
        btnXutHon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnXutHon.setBackground(Color.WHITE);
        btnXutHon.setBounds(726, 368, 218, 50);
        panel.add(btnXutHon);

        JButton btnXutHon_1 = new JButton("Xuất hoá đơn");
        btnXutHon_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                export();
            }
        });
        btnXutHon_1.setForeground(new Color(255, 128, 0));
        btnXutHon_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnXutHon_1.setBackground(Color.WHITE);
        btnXutHon_1.setBounds(726, 440, 218, 50);
        panel.add(btnXutHon_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBorder(new LineBorder(new Color(0, 255, 255), 4));
        panel_1_1.setBounds(506, 64, 474, 191);
        panel.add(panel_1_1);

        JLabel lblTngTinXc = new JLabel("Tổng tiền xác nhận:");
        lblTngTinXc.setForeground(Color.BLACK);
        lblTngTinXc.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTngTinXc.setBounds(10, 139, 210, 30);
        panel_1_1.add(lblTngTinXc);

        rdo0persent = new JRadioButton("");
        rdo0persent.setSelected(true);
        rdo0persent.setBounds(196, 21, 20, 20);
        panel_1_1.add(rdo0persent);

        JLabel lblGim = new JLabel("Giảm 0%");
        lblGim.setForeground(Color.BLACK);
        lblGim.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblGim.setBounds(10, 21, 180, 20);
        panel_1_1.add(lblGim);

        JLabel lblGim_3 = new JLabel("Giảm 10%");
        lblGim_3.setForeground(Color.BLACK);
        lblGim_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblGim_3.setBounds(10, 63, 180, 20);
        panel_1_1.add(lblGim_3);

        rdo10persent = new JRadioButton("");
        rdo10persent.setBounds(196, 63, 20, 20);
        panel_1_1.add(rdo10persent);

        JLabel lblGim_1 = new JLabel("Giảm 20%");
        lblGim_1.setForeground(Color.BLACK);
        lblGim_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblGim_1.setBounds(10, 102, 180, 20);
        panel_1_1.add(lblGim_1);

        rdo20persent = new JRadioButton("");
        rdo20persent.setBounds(196, 102, 20, 20);
        panel_1_1.add(rdo20persent);

        lbtotalprice2 = new JLabel("0");
        lbtotalprice2.setForeground(Color.BLACK);
        lbtotalprice2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbtotalprice2.setBounds(215, 139, 233, 30);
        panel_1_1.add(lbtotalprice2);

        JButton btnbuyp_2_1 = new JButton("Làm mới");
        btnbuyp_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadData2();
                showvalue();
            }
        });
        btnbuyp_2_1.setForeground(new Color(208, 208, 0));
        btnbuyp_2_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnbuyp_2_1.setBackground(Color.WHITE);
        btnbuyp_2_1.setBounds(726, 512, 218, 50);
        panel.add(btnbuyp_2_1);

        // Nhóm các JRadioButton vào ButtonGroup
        ButtonGroup discountGroup = new ButtonGroup();
        discountGroup.add(rdo0persent);
        discountGroup.add(rdo10persent);
        discountGroup.add(rdo20persent);

        // Xử lý sự kiện cho JRadioButton
        rdo0persent.addActionListener(e -> {
            int totalPrice = s.gettotalprice();
            int roundedPrice = (int) (Math.round(totalPrice / 1000.0) * 1000);
            lbtotalprice2.setText(String.format("%,d", roundedPrice) + " VNĐ");
            s.updatetotalprice(new infobuy(1, lbidhd.getText(), roundedPrice, lbhour.getText(), java.sql.Date.valueOf(lbday.getText())));
        });

        rdo10persent.addActionListener(e -> {
            int totalPrice = s.gettotalprice();
            double discountedPrice = totalPrice * 0.9;
            int roundedPrice = (int) (Math.round(discountedPrice / 1000.0) * 1000);
            lbtotalprice2.setText(String.format("%,d", roundedPrice) + " VNĐ");
            s.updatetotalprice(new infobuy(1, lbidhd.getText(), roundedPrice, lbhour.getText(), java.sql.Date.valueOf(lbday.getText())));
        });

        rdo20persent.addActionListener(e -> {
            int totalPrice = s.gettotalprice();
            double discountedPrice = totalPrice * 0.8;
            int roundedPrice = (int) (Math.round(discountedPrice / 1000.0) * 1000);
            lbtotalprice2.setText(String.format("%,d", roundedPrice) + " VNĐ");
            s.updatetotalprice(new infobuy(1, lbidhd.getText(), roundedPrice, lbhour.getText(), java.sql.Date.valueOf(lbday.getText())));
        });

        init();
    }

   
}