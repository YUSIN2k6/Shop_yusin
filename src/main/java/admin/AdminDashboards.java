package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.Login;
import user.UserDashboard;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.Cursor;

public class AdminDashboards extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JLabel dark1;
	public static JLabel Light1;
	public static Panel panel_4;
	public static Panel panel_5;
	public static JLabel menu1;
	
	
	public static JLabel dark2;
	public static JLabel light2;
	public static Panel panel_4_1;
	public static Panel panel_5_1;
	public static JLabel menu2;
	
	
	public static JLabel dark3;
	public static JLabel light3;
	public static Panel panel_4_2;
	public static Panel panel_5_2;
	public static JLabel menu3;
	
	public static JLabel dark4;
	public static JLabel Light4;
	public static Panel panel_4_3;
	public static Panel panel_5_4;
	public static JLabel menu4;
	
	public static JLabel nummoney;
	public static JLabel numshirt;
	public static JLabel numtk;
	Color selectionColor = new Color(34,48,62); 
	Color sideColor = new Color(255, 33, 33);
	Color textSelectionColor = new Color(255,255,255);
	Color backsize = new Color(102,102,102);
	Color black = new Color(0,0,0);
	public static JLabel adminemail;
	StatisticsDAO s;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboards frame = new AdminDashboards();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 private void Loaddata() {
	        new Thread(() -> {
	            while (true) {
	                SwingUtilities.invokeLater(() -> {
	                   
	                    nummoney.setText(String.format("%,d", s.getTotalmoney()));
	                    numtk.setText(String.format("%,d", s.getTotalshirt()));
	                    numshirt.setText(String.format("%,d", s.getTotalqtysold()));

	                   
	                });

	                try {
	                    Thread.sleep(5000); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();
	    }
	private void init() {
		icons();
		this.s = StatisticsDAO.getInstance();
		Loaddata();
	}
	
	private void icons() {
		dark1.setVisible(true);
		Light1.setVisible(false);
		dark2.setVisible(true);
		light2.setVisible(false);
		dark3.setVisible(true);
		light3.setVisible(false);
		dark4.setVisible(true);
		Light4.setVisible(false);
	}
	
	public AdminDashboards() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 710);
		setLocation(70, 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1280, 710);
		contentPane.add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(241, 190, 40));
		panel_1.setBounds(0, 0, 1280, 105);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/Logo home.png")));
		lblNewLabel.setBounds(57, 11, 64, 64);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel(" Đăng xuất");
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon icon = new ImageIcon(AdminDashboards.class.getResource("/icons/aa.png"));
				int a = JOptionPane.showOptionDialog(AdminDashboards.this, "Bạn có muốn đăng xuất bây giờ không?","Đăng xuất",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon,null,null);
				if (a == JOptionPane.YES_OPTION) {
					new Login().setVisible(true);
					AdminDashboards.this.dispose();
				}
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/logout.png")));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(1117, 25, 153, 40);
		panel_1.add(lblNewLabel_2);
		
		adminemail = new JLabel("admin@gmail.com");
		adminemail.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/user.png")));
		adminemail.setForeground(Color.WHITE);
		adminemail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		adminemail.setBounds(770, 33, 268, 30);
		panel_1.add(adminemail);
		
		Panel panel_2 = new Panel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(102, 102, 102));
		panel_2.setBounds(0, 105, 290, 605);
		panel.add(panel_2);
		
		Panel panel_3 = new Panel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(255, 33, 33));
		panel_3.setBounds(0, 37, 290, 60);
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Bảng điều khiển");
		lblNewLabel_4.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/dashboard.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 27));
		lblNewLabel_4.setBounds(10, 0, 280, 60);
		panel_3.add(lblNewLabel_4);
		
		panel_4 = new Panel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(102, 102, 102));
		panel_4.setBounds(0, 194, 290, 50);
		panel_2.add(panel_4);
		
		menu1 = new JLabel("  Quản lý nhân viên");
		menu1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4.setBackground(selectionColor);
				panel_5.setBackground(sideColor);
				menu1.setForeground(textSelectionColor);
				dark1.setVisible(false);
				Light1.setVisible(true);
				new ManageUser().setVisible(true);
				
				panel_4_1.setBackground(backsize);
				panel_5_1.setBackground(backsize);
				menu2.setForeground(black);
				dark2.setVisible(true);
				light2.setVisible(false);
				new ManagePurchase().setVisible(false);;
				new ManagePurchase().dispose();
				
				panel_4_2.setBackground(backsize);
				panel_5_2.setBackground(backsize);
				menu3.setForeground(black);
				dark3.setVisible(true);
				light3.setVisible(false);
				new Statistics().setVisible(false);
				new Statistics().dispose();
				
			
				
				panel_4_3.setBackground(backsize);
				panel_5_4.setBackground(backsize);
				menu4.setForeground(black);
				dark4.setVisible(true);
				Light4.setVisible(false);
				new Changeshirt().setVisible(false);
				new Changeshirt().dispose();
			}
		});
		menu1.setForeground(new Color(0, 0, 0));
		menu1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu1.setBounds(74, 0, 216, 50);
		panel_4.add(menu1);
		
		panel_5 = new Panel();
		panel_5.setBackground(new Color(102, 102, 102));
		panel_5.setBounds(0, 0, 16, 50);
		panel_4.add(panel_5);
		
		dark1 = new JLabel("");
		dark1.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/user_Darkk.png")));
		dark1.setBounds(44, 0, 24, 50);
		panel_4.add(dark1);
		
		Light1 = new JLabel("");
		Light1.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/user_Light.png")));
		Light1.setBounds(44, 0, 24, 50);
		panel_4.add(Light1);
		
		panel_4_1 = new Panel();
		panel_4_1.setLayout(null);
		panel_4_1.setBackground(new Color(102, 102, 102));
		panel_4_1.setBounds(0, 244, 290, 50);
		panel_2.add(panel_4_1);
		
		menu2 = new JLabel("  Quản lý áo đã bán");
		menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4_1.setBackground(selectionColor);
				panel_5_1.setBackground(sideColor);
				menu2.setForeground(textSelectionColor);
				dark2.setVisible(false);
				light2.setVisible(true);
				new ManagePurchase().setVisible(true);
				
				panel_4.setBackground(backsize);
				panel_5.setBackground(backsize);
				menu1.setForeground(black);
				dark1.setVisible(true);
				Light1.setVisible(false);
				new ManageUser().setVisible(false);
				new ManageUser().dispose();
				
				panel_4_2.setBackground(backsize);
				panel_5_2.setBackground(backsize);
				menu3.setForeground(black);
				dark3.setVisible(true);
				light3.setVisible(false);
				new Statistics().setVisible(false);
				new Statistics().dispose();
				
				panel_4_3.setBackground(backsize);
				panel_5_4.setBackground(backsize);
				menu4.setForeground(black);
				dark4.setVisible(true);
				Light4.setVisible(false);
				new Changeshirt().setVisible(false);
				new Changeshirt().dispose();
			}
		});
		menu2.setForeground(new Color(0, 0, 0));
		menu2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu2.setBounds(74, 0, 216, 50);
		panel_4_1.add(menu2);
		
		panel_5_1 = new Panel();
		panel_5_1.setBackground(new Color(102, 102, 102));
		panel_5_1.setBounds(0, 0, 16, 50);
		panel_4_1.add(panel_5_1);
		
		dark2 = new JLabel("");
		dark2.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/transaction_Dark.png")));
		dark2.setBounds(44, 0, 24, 50);
		panel_4_1.add(dark2);

		light2 = new JLabel("");
		light2.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/transaction_Light.png")));
		light2.setBounds(44, 0, 24, 50);
		panel_4_1.add(light2);
		
		panel_4_2 = new Panel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(new Color(102, 102, 102));
		panel_4_2.setBounds(0, 294, 290, 50);
		panel_2.add(panel_4_2);
		
		menu3 = new JLabel("  Biểu đồ doanh thu");
		menu3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4_2.setBackground(selectionColor);
				panel_5_2.setBackground(sideColor);
				menu3.setForeground(textSelectionColor);
				dark3.setVisible(false);
				light3.setVisible(true);
				new Statistics().setVisible(true);
				
				panel_4.setBackground(backsize);
				panel_5.setBackground(backsize);
				menu1.setForeground(black);
				dark1.setVisible(true);
				Light1.setVisible(false);
				new ManageUser().setVisible(false);
				new ManageUser().dispose();
				
				panel_4_1.setBackground(backsize);
				panel_5_1.setBackground(backsize);
				menu2.setForeground(black);
				dark2.setVisible(true);
				light2.setVisible(false);
				new ManagePurchase().setVisible(false);
				new ManagePurchase().dispose();
				
				panel_4_3.setBackground(backsize);
				panel_5_4.setBackground(backsize);
				menu4.setForeground(black);
				dark4.setVisible(true);
				Light4.setVisible(false);
				new Changeshirt().setVisible(false);
				new Changeshirt().dispose();
			}
		});
		menu3.setForeground(new Color(0, 0, 0));
		menu3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu3.setBounds(74, 0, 216, 50);
		panel_4_2.add(menu3);
		
		panel_5_2 = new Panel();
		panel_5_2.setBackground(new Color(102, 102, 102));
		panel_5_2.setBounds(0, 0, 16, 50);
		panel_4_2.add(panel_5_2);
		
		dark3 = new JLabel("");
		dark3.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/category_Dark.png")));
		dark3.setBounds(44, 0, 24, 50);
		panel_4_2.add(dark3);

		light3 = new JLabel("");
		light3.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/category_Light.png")));
		light3.setBounds(44, 0, 24, 50);
		panel_4_2.add(light3);
		
		Panel panel_11 = new Panel();
		panel_11.setBounds(0, 344, 290, 50);
		panel_2.add(panel_11);
		panel_11.setLayout(null);
		
		Panel panel_5_2_1 = new Panel();
		panel_5_2_1.setBackground(new Color(102, 102, 102));
		panel_5_2_1.setBounds(0, 0, 16, 50);
		panel_11.add(panel_5_2_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/logout (1).png")));
		lblNewLabel_5.setBounds(46, 11, 24, 24);
		panel_11.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("  Thoát");
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_6.setBounds(78, 0, 212, 50);
		panel_11.add(lblNewLabel_6);
		
		panel_4_3 = new Panel();
		panel_4_3.setLayout(null);
		panel_4_3.setBounds(0, 144, 290, 50);
		panel_2.add(panel_4_3);
		
		panel_5_4 = new Panel();
		panel_5_4.setBackground(new Color(102, 102, 102));
		panel_5_4.setBounds(0, 0, 16, 50);
		panel_4_3.add(panel_5_4);
		
		dark4 = new JLabel("");
		dark4.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/tshirt_dark.png")));
		dark4.setBounds(46, 11, 24, 24);
		panel_4_3.add(dark4);
		
		menu4 = new JLabel("  Quản lý kho áo");
		menu4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4_3.setBackground(selectionColor);
				panel_5_4.setBackground(sideColor);
				menu4.setForeground(textSelectionColor);
				dark4.setVisible(false);
				Light4.setVisible(true);
				new Changeshirt().setVisible(true);
				
				panel_4.setBackground(backsize);
				panel_5.setBackground(backsize);
				menu1.setForeground(black);
				dark1.setVisible(true);
				Light1.setVisible(false);
				new ManageUser().setVisible(false);
				new ManageUser().dispose();
				
				panel_4_1.setBackground(backsize);
				panel_5_1.setBackground(backsize);
				menu2.setForeground(black);
				dark2.setVisible(true);
				light2.setVisible(false);
				new ManagePurchase().setVisible(false);
				new ManagePurchase().dispose();
				
				panel_4_2.setBackground(backsize);
				panel_5_2.setBackground(backsize);
				menu3.setForeground(black);
				dark3.setVisible(true);
				light3.setVisible(false);
				new Statistics().setVisible(false);
				new Statistics().dispose();
				
			}
		});
		menu4.setForeground(Color.BLACK);
		menu4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu4.setBounds(78, 0, 212, 50);
		panel_4_3.add(menu4);
		
		Light4 = new JLabel("");
		Light4.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/tshirt_light.png")));
		Light4.setBounds(46, 11, 24, 24);
		panel_4_3.add(Light4);
		
		Panel panel_6 = new Panel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(51, 51, 51));
		panel_6.setBounds(290, 105, 990, 605);
		panel.add(panel_6);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(64, 0, 64), 2));
		panel_8.setBackground(new Color(0, 215, 54));
		panel_8.setBounds(340, 190, 300, 200);
		panel_6.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/money.png")));
		lblNewLabel_3_1.setBounds(10, 72, 72, 72);
		panel_8.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("Tổng doanh thu đã bán");
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_7_1.setBounds(0, 19, 300, 45);
		panel_8.add(lblNewLabel_7_1);
		
		nummoney = new JLabel("0");
		nummoney.setHorizontalAlignment(SwingConstants.CENTER);
		nummoney.setForeground(Color.WHITE);
		nummoney.setFont(new Font("Segoe UI", Font.BOLD, 28));
		nummoney.setBounds(92, 84, 198, 50);
		panel_8.add(nummoney);
		
		JPanel panel_8_1 = new JPanel();
		panel_8_1.setBorder(new LineBorder(new Color(64, 0, 64), 2));
		panel_8_1.setLayout(null);
		panel_8_1.setBackground(new Color(255, 128, 0));
		panel_8_1.setBounds(20, 190, 300, 200);
		panel_6.add(panel_8_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/icon account.png")));
		lblNewLabel_3.setBounds(10, 72, 72, 72);
		panel_8_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Tổng số áo trong kho");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_7.setBounds(0, 21, 300, 45);
		panel_8_1.add(lblNewLabel_7);
		
		numtk = new JLabel("0");
		numtk.setHorizontalAlignment(SwingConstants.CENTER);
		numtk.setForeground(new Color(255, 255, 255));
		numtk.setFont(new Font("Segoe UI", Font.BOLD, 40));
		numtk.setBounds(121, 86, 97, 50);
		panel_8_1.add(numtk);
		
		JPanel panel_8_2 = new JPanel();
		panel_8_2.setBorder(new LineBorder(new Color(64, 0, 64), 2));
		panel_8_2.setLayout(null);
		panel_8_2.setBackground(new Color(255, 0, 128));
		panel_8_2.setBounds(660, 190, 300, 200);
		panel_6.add(panel_8_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setIcon(new ImageIcon(AdminDashboards.class.getResource("/icons/Shirt.png")));
		lblNewLabel_3_2.setBounds(10, 72, 72, 72);
		panel_8_2.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_7_2 = new JLabel("Tổng số áo đã bán");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setForeground(Color.WHITE);
		lblNewLabel_7_2.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_7_2.setBounds(0, 21, 300, 45);
		panel_8_2.add(lblNewLabel_7_2);
		
		numshirt = new JLabel("0");
		numshirt.setHorizontalAlignment(SwingConstants.CENTER);
		numshirt.setForeground(Color.WHITE);
		numshirt.setFont(new Font("Segoe UI", Font.BOLD, 40));
		numshirt.setBounds(121, 86, 108, 50);
		panel_8_2.add(numshirt);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 255, 255));
		panel_7.setForeground(new Color(0, 0, 255));
		panel_7.setBounds(10, 400, 960, 5);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setLayout(null);
		panel_7_1.setForeground(Color.BLUE);
		panel_7_1.setBackground(Color.CYAN);
		panel_7_1.setBounds(10, 174, 960, 5);
		panel_6.add(panel_7_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 255, 255));
		panel_9.setForeground(new Color(0, 255, 255));
		panel_9.setBounds(970, 175, 5, 230);
		panel_6.add(panel_9);
		
		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(new Color(0, 255, 255));
		panel_9_1.setBounds(5, 175, 5, 230);
		panel_6.add(panel_9_1);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(51, 51, 51));
		panel_10.setBorder(new LineBorder(new Color(0, 255, 255), 4, true));
		panel_10.setBounds(320, 50, 340, 61);
		panel_6.add(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Số liệu thống kê");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_8.setBounds(0, 0, 340, 61);
		panel_10.add(lblNewLabel_8);
		init();
	}
}
