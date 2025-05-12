package user;

import java.awt.EventQueue;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
public class UserDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel JTxTDate; 
	private JLabel JTxTTime;
	public static JLabel dark1;
	public static JLabel light1;
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
	
	Color selectionColor = new Color(34,48,62);
	Color sideColor = new Color(255, 33, 33);
	Color textSelectionColor = new Color(255,255,255);
	public static JLabel useremail;
	private Panel panel_8;
	private Panel panel_5_2_1;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	Color backsize = new Color(102,102,102);
	Color black = new Color(0,0,0);
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserDashboard frame = new UserDashboard();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	private void init() {
		icons();
	}
	
	private void icons() {
		dark1.setVisible(true);
		light1.setVisible(false);
		dark2.setVisible(true);
		light2.setVisible(false);
		dark3.setVisible(true);
		light3.setVisible(false);
	}
	
	public void setTime() {
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
					String time = tf.format(date);
					JTxTTime.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
					JTxTDate.setText(df.format(date));
				}
			}
		}).start();;
    }
	
	public UserDashboard() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				new Thread(() -> {
		            for (double i = 0.1; i <= 1.0; i += 0.1) {
		                float f = (float) i;
		                UserDashboard.this.setOpacity(f); 
		                try {
		                    Thread.sleep(40);
		                } catch (InterruptedException e1) {
		                    e1.printStackTrace();
		                }
		            }
		        }).start();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserDashboard.class.getResource("/icons/aaa (2).png")));
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 710);
		setLocation(70, 0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(0, 0, 1280, 710);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(241, 190, 40));
		panel_1.setBounds(0, 0, 1280, 105);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel(" Đăng xuất");
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon icon = new ImageIcon(UserDashboard.class.getResource("/icons/aa.png"));
				int a = JOptionPane.showOptionDialog(UserDashboard.this, "Bạn có muốn đăng xuất bây giờ không?","Đăng xuất",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon,null,null);
				if (a == JOptionPane.YES_OPTION) {
					new Login().setVisible(true);
					UserDashboard.this.dispose();
				}
				
			}
		});
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/logout.png")));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(1117, 25, 153, 40);
		panel_1.add(lblNewLabel_2);
		
		useremail = new JLabel("user@gmail.com");
		useremail.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/user.png")));
		useremail.setForeground(Color.WHITE);
		useremail.setFont(new Font("Segoe UI", Font.BOLD, 20));
		useremail.setBounds(747, 28, 332, 35);
		panel_1.add(useremail);
		JTxTDate = new JLabel("");
		JTxTDate.setBounds(290, 30, 206, 20);
		panel_1.add(JTxTDate);
		JTxTDate.setHorizontalAlignment(SwingConstants.LEFT);
		JTxTDate.setForeground(new Color(255, 255, 255));
		JTxTDate.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		JTxTTime = new JLabel("");
		JTxTTime.setBounds(290, 60, 206, 20);
		panel_1.add(JTxTTime);
		JTxTTime.setHorizontalAlignment(SwingConstants.LEFT);
		JTxTTime.setForeground(new Color(255, 255, 255));
		JTxTTime.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/Logo home.png")));
		lblNewLabel_13.setBounds(71, 25, 64, 64);
		panel_1.add(lblNewLabel_13);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(102, 102, 102));
		panel_2.setBounds(0, 105, 290, 605);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(new Color(255, 33, 33));
		panel_3.setBounds(0, 32, 290, 60);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("  HOME");
		lblNewLabel_4.setBackground(new Color(255, 33, 33));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/house.png")));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNewLabel_4.setBounds(0, 0, 290, 60);
		panel_3.add(lblNewLabel_4);
		
		panel_4 = new Panel();
		panel_4.setBackground(new Color(102, 102, 102));
		panel_4.setBounds(0, 143, 290, 50);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		menu1 = new JLabel("  Mua áo");
		menu1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu1.setForeground(new Color(0, 0, 0));
		menu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4.setBackground(selectionColor);
				panel_5.setBackground(sideColor);
				menu1.setForeground(textSelectionColor);
				dark1.setVisible(false);
				light1.setVisible(true);
				Purchase purchase = new Purchase();
				purchase.setVisible(true);
				
				panel_4_1.setBackground(backsize);
				panel_5_1.setBackground(backsize);
				menu2.setForeground(black);
				dark2.setVisible(true);
				light2.setVisible(false);
				PurchaseDetail purchasedetail = new PurchaseDetail();
				purchasedetail.setVisible(false);
				
				panel_4_2.setBackground(backsize);
				panel_5_2.setBackground(backsize);
				menu3.setForeground(black);
				dark3.setVisible(true);
				light3.setVisible(false);
				new UserAccount().setVisible(false);
			}
		});
		menu1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu1.setBounds(74, 0, 216, 50);
		panel_4.add(menu1);
		
		panel_5 = new Panel();
		panel_5.setBackground(new Color(102, 102, 102));
		panel_5.setBounds(0, 0, 16, 50);
		panel_4.add(panel_5);
		
		dark1 = new JLabel("");
		dark1.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/purchase_shirt_Dark.png")));
		dark1.setBounds(44, 0, 24, 50);
		panel_4.add(dark1);
		
		light1 = new JLabel("");
		light1.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/purchase_shirt_Light.png")));
		light1.setBounds(44, 0, 24, 50);
		panel_4.add(light1);
		
		panel_4_1 = new Panel();
		panel_4_1.setLayout(null);
		panel_4_1.setBackground(new Color(102, 102, 102));
		panel_4_1.setBounds(0, 193, 290, 50);
		panel_2.add(panel_4_1);
		
		menu2 = new JLabel("  Chi tiết mua áo");
		menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2.setForeground(new Color(0, 0, 0));
		menu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4_1.setBackground(selectionColor);
				panel_5_1.setBackground(sideColor);
				menu2.setForeground(textSelectionColor);
				dark2.setVisible(false);
				light2.setVisible(true);
				PurchaseDetail purchasedetail = new PurchaseDetail();
				purchasedetail.setVisible(true);
				
				panel_4.setBackground(backsize);
				panel_5.setBackground(backsize);
				menu1.setForeground(black);
				dark1.setVisible(true);
				light1.setVisible(false);
				Purchase purchase = new Purchase();
				purchase.setVisible(false);
				
				panel_4_2.setBackground(backsize);
				panel_5_2.setBackground(backsize);
				menu3.setForeground(black);
				dark3.setVisible(true);
				light3.setVisible(false);
				new UserAccount().setVisible(false);
			}
		});
		menu2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu2.setBounds(74, 0, 216, 50);
		panel_4_1.add(menu2);
		
		panel_5_1 = new Panel();
		panel_5_1.setBackground(new Color(102, 102, 102));
		panel_5_1.setBounds(0, 0, 16, 50);
		panel_4_1.add(panel_5_1);
		
		dark2 = new JLabel("");
		dark2.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/purchase_detail_Dark.png")));
		dark2.setBounds(44, 0, 24, 50);
		panel_4_1.add(dark2);
		
		light2 = new JLabel("");
		light2.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/purchase_detail_Light.png")));
		light2.setBounds(44, 0, 24, 50);
		panel_4_1.add(light2);
		
		panel_4_2 = new Panel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(new Color(102, 102, 102));
		panel_4_2.setBounds(0, 243, 290, 50);
		panel_2.add(panel_4_2);
		
		menu3 = new JLabel("  Tài khoản");
		menu3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3.setForeground(new Color(0, 0, 0));
		menu3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_4_2.setBackground(selectionColor);
				panel_5_2.setBackground(sideColor);
				menu3.setForeground(textSelectionColor);
				dark3.setVisible(false);
				light3.setVisible(true);
				new UserAccount().setVisible(true);
				
				panel_4.setBackground(backsize);
				panel_5.setBackground(backsize);
				menu1.setForeground(black);
				dark1.setVisible(true);
				light1.setVisible(false);
				Purchase purchase = new Purchase();
				purchase.setVisible(false);
				
				panel_4_1.setBackground(backsize);
				panel_5_1.setBackground(backsize);
				menu2.setForeground(black);
				dark2.setVisible(true);
				light2.setVisible(false);
				PurchaseDetail purchasedetail = new PurchaseDetail();
				purchasedetail.setVisible(false);
			}
		});
		menu3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menu3.setBounds(74, 0, 216, 50);
		panel_4_2.add(menu3);
		
		panel_5_2 = new Panel();
		panel_5_2.setBackground(new Color(102, 102, 102));
		panel_5_2.setBounds(0, 0, 16, 50);
		panel_4_2.add(panel_5_2);
		
		dark3 = new JLabel("");
		dark3.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/account_Dark.png")));
		dark3.setBounds(44, 0, 24, 50);
		panel_4_2.add(dark3);
		
		light3 = new JLabel("");
		light3.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/account_light.png")));
		light3.setBounds(44, 0, 24, 50);
		panel_4_2.add(light3);
		
		panel_8 = new Panel();
		panel_8.setLayout(null);
		panel_8.setBounds(0, 293, 290, 50);
		panel_2.add(panel_8);
		
		panel_5_2_1 = new Panel();
		panel_5_2_1.setBackground(new Color(102, 102, 102));
		panel_5_2_1.setBounds(0, 0, 16, 50);
		panel_8.add(panel_5_2_1);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/logout (1).png")));
		lblNewLabel_8.setBounds(46, 11, 24, 24);
		panel_8.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("   Thoát");
		lblNewLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_9.setForeground(new Color(0, 0, 0));
		lblNewLabel_9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_9.setBounds(78, 0, 212, 50);
		panel_8.add(lblNewLabel_9);
		
		Panel panel_6 = new Panel();
		panel_6.setBackground(new Color(51, 51, 51));
		panel_6.setBounds(290, 105, 990, 605);
		panel.add(panel_6);
		panel_6.setLayout(null);
//		panel_6.add(Giaodien);
		
		
		Panel panel_7 = new Panel();
		panel_7.setBackground(new Color(51, 51, 51));
		panel_7.setBounds(0, 0, 990, 90);
		panel_6.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SHOP YUSIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(324, 1, 351, 91);
		panel_7.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 33, 33));
		lblNewLabel_1.setFont(new Font("Plantagenet Cherokee", Font.BOLD, 50));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/style.png")));
		lblNewLabel.setBounds(341, 105, 640, 480);
		panel_6.add(lblNewLabel);
		
		lblNewLabel_5 = new JLabel("Phong cách hiện đại");
		lblNewLabel_5.setForeground(new Color(255, 33, 33));
		lblNewLabel_5.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_5.setBounds(20, 118, 311, 38);
		panel_6.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Trẻ trung năng động");
		lblNewLabel_6.setForeground(new Color(255, 33, 33));
		lblNewLabel_6.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_6.setBounds(20, 254, 311, 38);
		panel_6.add(lblNewLabel_6);
		
		lblNewLabel_10 = new JLabel("Lịch lãm sang trọng");
		lblNewLabel_10.setForeground(new Color(255, 33, 33));
		lblNewLabel_10.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_10.setBounds(20, 186, 311, 38);
		panel_6.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Mặc vào là có NY");
		lblNewLabel_11.setForeground(new Color(255, 33, 33));
		lblNewLabel_11.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_11.setBounds(20, 322, 255, 38);
		panel_6.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(UserDashboard.class.getResource("/icons/heart-arrows.png")));
		lblNewLabel_12.setForeground(new Color(255, 33, 33));
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_12.setBounds(288, 316, 32, 38);
		panel_6.add(lblNewLabel_12);
		

		setTime();
		init();
	}
}
