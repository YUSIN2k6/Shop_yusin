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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_8_1;
	UserDao user = new UserDao();
	Color notedit = new Color(204,204,204);
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void init() {
		textField.setBackground(notedit);
		textField.setText(String.valueOf(user.getMaxrow()));
	}
	private boolean isEmpty() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
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
	
	public SignUp() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/icons/aaa (2).png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				new Thread(() -> {
		            for (double i = 0.1; i <= 1.0; i += 0.1) {
		                float f = (float) i;
		                SignUp.this.setOpacity(f);
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
		setBounds(400, 100, 450, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setBounds(0, 0, 450, 531);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setBounds(0, 0, 450, 528);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Đăng ký");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel_2.setBounds(57, 25, 321, 52);
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(0, 0, 1, 1);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				ImageIcon icon = new ImageIcon("/icons/LOGO2.png");
				if (!(input<'0' || input>'9')&&input != '\b' ) {
					e.consume();
					JOptionPane.showMessageDialog(SignUp.this, "Tên bạn đặt không chứa bất kỳ ký tự số nào!","Cảnh báo",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		textField_1.setBounds(62, 112, 321, 31);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(62, 178, 321, 31);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(62, 244, 321, 31);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("Tên:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(61, 86, 77, 25);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(59, 156, 79, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(63, 218, 75, 25);
		panel_1.add(lblNewLabel_6);
		
		Button btnSave = new Button("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon("/icons/aa.png");
				if (isEmpty()) {
					int id = Integer.parseInt(textField.getText());
					String username = textField_1.getText(); 
					String email = textField_2.getText();
					String password = String.valueOf(passwordField.getPassword());
					String phone = textField_3.getText();
					String address = textField_4.getText();
					if(!user.isEmailExist(email)) {
						if(!user.isPhoneExist(phone)) {
							user.insert(id, username, email, password, phone, address);
							new Login().setVisible(true);
							dispose();
						}  else {
							JOptionPane.showMessageDialog(SignUp.this, "Số điện thoại đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
						}
					} else {
						JOptionPane.showMessageDialog(SignUp.this, "Địa chỉ email đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
					}
				}
			}
		});
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setForeground(new Color(51, 51, 51));
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSave.setBounds(62, 442, 154, 52);
		panel_1.add(btnSave);
		
		Button btnBack = new Button("Quay về");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setForeground(new Color(51, 51, 51));
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnBack.setBounds(229, 442, 154, 52);
		panel_1.add(btnBack);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar((char) 0);
				lblNewLabel_8.setVisible(false);
				lblNewLabel_8_1.setVisible(true);
			}
		});
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.setIcon(new ImageIcon(SignUp.class.getResource("/icons/hide.png")));
		lblNewLabel_8.setBounds(391, 254, 36, 14);
		panel_1.add(lblNewLabel_8);
		
		lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setEchoChar('*');
				lblNewLabel_8.setVisible(true);
				lblNewLabel_8_1.setVisible(false);
			}
		});
		lblNewLabel_8_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8_1.setIcon(new ImageIcon(SignUp.class.getResource("/icons/visible.png")));
		lblNewLabel_8_1.setBounds(391, 254, 36, 14);
		panel_1.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại:");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(64, 286, 107, 25);
		panel_1.add(lblNewLabel_4_1);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textField_3.setColumns(10);
		textField_3.setBounds(65, 312, 321, 31);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_5_1 = new JLabel("Địa chỉ:");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(62, 356, 79, 14);
		panel_1.add(lblNewLabel_5_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(65, 378, 321, 31);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_7 = new JLabel("x");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_7.setBounds(408, 0, 42, 47);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 35));
		init();
	}
}
