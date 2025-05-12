package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserAccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_8_1;
	private JLabel lblNewLabel_8;
	private int uId;
	String[] value = new String[6];

	Color textPrimaryColor = new Color (0, 0, 0);
	Color primaryColor = new Color (102,102,102);
	UserDao user = new UserDao();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAccount frame = new UserAccount();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setDefault() {
		UserDashboard.panel_4_2.setBackground(primaryColor);
		UserDashboard.panel_5_2.setBackground(primaryColor);
		UserDashboard.menu3.setForeground(textPrimaryColor);
		UserDashboard.dark3.setVisible(true);
		UserDashboard.light3.setVisible(false);
	}
	
	private boolean isEmpty() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/LOGO4.png"));
		if (textField_1.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Tên người dùng cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if(textField_2.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Địa chỉ email cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (!textField_2.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showConfirmDialog(this, "Địa chỉ email không hợp lệ","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (String.valueOf(passwordField.getPassword()).isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Mật khẩu cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_3.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Số điện thoại cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_3.getText().length() > 11) { 
			JOptionPane.showConfirmDialog(this, "Số điện thoại quá dài","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_3.getText().length() < 10) { 
			JOptionPane.showConfirmDialog(this, "Số điện thoại quá ngắn","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_4.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Địa chỉ cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		}
		return true;
	}
	
	private boolean check() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/LOGO4.png"));
		String newEmail = textField_2.getText();
		String newPhone = textField_3.getText();
		if(newEmail.equals(value[2])&&newPhone.equals(value[4])) {
			return false;
		} else {
			if(!newEmail.equals(value[2])) {
				boolean x = user.isEmailExist(newEmail);
				if (x) {
					JOptionPane.showConfirmDialog(this, "Địa chỉ email đã tồn tại!","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
					return x;
				}
				
			}
			if(!newPhone.equals(value[4])) {
				boolean x = user.isPhoneExist(newPhone);
				if (x) {
					JOptionPane.showConfirmDialog(this, "Số điện thoại đã tồn tại!","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
					return x;
				}
				
			}
		}
		return false;
	}
	
	private void init() {
		uId = user.getUserId(UserDashboard.useremail.getText());
		value = user.getUserValue(uId);
		setValue();
	}
	private void setValue() {
		textField.setText(value[0]);
		textField_1.setText(value[1]);
		textField_2.setText(value[2]);
		passwordField.setText(value[3]);
		textField_3.setText(value[4]);
		textField_4.setText(value[5]);

	}
	public UserAccount() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				new Thread(() -> {
		            for (double i = 0.1; i <= 1.0; i += 0.1) {
		                float f = (float) i;
		                UserAccount.this.setOpacity(f); 
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
		setLocation(360,105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(0, 0, 990, 605);
		contentPane.add(panel_1);
		
		JLabel Jtitle = new JLabel("Tài khoảng");
		Jtitle.setHorizontalAlignment(SwingConstants.CENTER);
		Jtitle.setForeground(Color.WHITE);
		Jtitle.setFont(new Font("Times New Roman", Font.BOLD, 40));
		Jtitle.setBounds(57, 25, 881, 52);
		panel_1.add(Jtitle);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(new Color(204, 204, 204));
		textField.setColumns(10);
		textField.setBounds(133, 115, 321, 31);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				ImageIcon icon = new ImageIcon("/icons/LOGO2.png");
				if (!(input<'0' || input>'9')&&input != '\b' ) {
					e.consume();
					JOptionPane.showMessageDialog(UserAccount.this, "Tên bạn đặt không chứa bất kỳ ký tự số nào!","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(133, 181, 321, 31);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 247, 321, 31);
		panel_1.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 313, 321, 31);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("ID:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(134, 89, 108, 25);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tên:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(132, 155, 77, 25);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(130, 225, 79, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(134, 287, 75, 25);
		panel_1.add(lblNewLabel_6);
		
		Button btnUpdate = new Button("Cập nhật");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty()) {
					if(!check()) {
						int id = Integer.parseInt(textField.getText());
						String username = textField_1.getText(); 
						String email = textField_2.getText();
						String password = String.valueOf(passwordField.getPassword());
						String phone = textField_3.getText();
						String address = textField_4.getText();
						user.update(id, username, email, password, phone, address);
						dispose();
						setDefault();
					}
				}
			}
		});
		btnUpdate.setBackground(new Color(0, 255, 0));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnUpdate.setBounds(133, 376, 154, 52);
		panel_1.add(btnUpdate);
		
		Button btnDelete = new Button("Xoá");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.delete(uId);
				System.exit(0);
			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnDelete.setBounds(300, 376, 154, 52);
		panel_1.add(btnDelete);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) 0);
				lblNewLabel_8.setVisible(false);
				lblNewLabel_8_1.setVisible(true);
			}
		});
		lblNewLabel_8.setIcon(new ImageIcon(UserAccount.class.getResource("/icons/hide.png")));
		lblNewLabel_8.setBounds(464, 319, 36, 14);
		panel_1.add(lblNewLabel_8);
		
		lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar('*');
				lblNewLabel_8.setVisible(true);
				lblNewLabel_8_1.setVisible(false);
			}
		});
		lblNewLabel_8_1.setIcon(new ImageIcon(UserAccount.class.getResource("/icons/visible.png")));
		lblNewLabel_8_1.setBounds(464, 319, 36, 14);
		panel_1.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel = new JLabel("x");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				setDefault();
			}
		});
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(948, 0, 42, 47);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại:");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(556, 87, 154, 25);
		panel_1.add(lblNewLabel_4_1);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) { // không cho nhập chữ
					e.consume();
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(557, 115, 321, 31);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_4_2 = new JLabel("Địa chỉ:");
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_2.setBounds(556, 155, 101, 25);
		panel_1.add(lblNewLabel_4_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(557, 181, 321, 31);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UserAccount.class.getResource("/icons/TTLL.png")));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(510, 247, 450, 320);
		panel_1.add(lblNewLabel_1);
		init();
	}
}
