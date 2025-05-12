package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.UserDao;
import user.Login;
import user.SignUp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Toolkit;

public class ManageUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_8_1;
	private JLabel lblNewLabel_8;
	DefaultTableModel model;
	int rowIndex;
	Color textPrimaryColor = new Color (0, 0, 0);
	Color primaryColor = new Color (102,102,102);
	UserDao user = new UserDao();
	String[] value = new String[6];
	private void init() {
		UserTable();
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageUser frame = new ManageUser();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	
	
	private void UserTable() {
		user.getUserValue(table,"");
		model = (DefaultTableModel) table.getModel();
		table.setRowHeight(30);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.CYAN);
	}
	
	private boolean isEmpty() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
		
		if (textField.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Vui lòng chọn một người dùng","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_1.getText().isEmpty()) {
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
		} if (textField_4.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Số điện thoại cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_4.getText().length() > 11) { 
			JOptionPane.showConfirmDialog(this, "Số điện thoại quá dài","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_4.getText().length() < 10) { 
			JOptionPane.showConfirmDialog(this, "Số điện thoại quá ngắn","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		} if (textField_5.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(this, "Địa chỉ cần phải điền vào","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
			return false;
		}
		return true;
	}
	
	private boolean check() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
		String newEmail = textField_2.getText();
		String newPhone = textField_4.getText();
		String oldEmail = model.getValueAt(rowIndex, 2).toString();
		String oldPhone = model.getValueAt(rowIndex, 4).toString();
		if(newEmail.equals(oldEmail) && newPhone.equals(oldPhone)) {
			return false;
		} else {
			if(!newEmail.equals(oldEmail)) {
				boolean x = user.isEmailExist(newEmail);
				if (x) {
					JOptionPane.showConfirmDialog(this, "Địa chỉ email đã tồn tại!","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
					return x;
				}
				
			}
			if(!newPhone.equals(oldPhone)) {
				boolean x = user.isPhoneExist(newPhone);
				if (x) {
					JOptionPane.showConfirmDialog(this, "Số điện thoại đã tồn tại!","Cảnh báo",JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
					return x;
				}
				
			}
		}
		return false;
	}
	
	private void clear() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		passwordField.setText("");
		textField_4.setText("");
		textField_5.setText("");
		table.clearSelection();
	}
	
	public ManageUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManageUser.class.getResource("/icons/aaa (2).png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				new Thread(() -> {
		            for (double i = 0.1; i <= 1.0; i += 0.1) {
		                float f = (float) i;
		                ManageUser.this.setOpacity(f); 
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
		
		textField = new JTextField();
		textField.setBackground(new Color(204, 204, 204));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(33, 98, 321, 31);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				ImageIcon icon = new ImageIcon("/icons/aa.png");
				if (!(input<'0' || input>'9')&&input != '\b' ) {
					e.consume();
					JOptionPane.showMessageDialog(ManageUser.this, "Tên bạn đặt không chứa bất kỳ ký tự số nào!","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(33, 164, 321, 31);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(33, 230, 321, 31);
		panel_1.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(33, 296, 321, 31);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("ID người dùng:");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(34, 72, 108, 25);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tên người dùng:");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(32, 138, 110, 25);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(30, 208, 79, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu:");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(34, 270, 75, 25);
		panel_1.add(lblNewLabel_6);
		textField.setText(String.valueOf(user.getMaxrow())); // hiện id
		Button btnSave = new Button("Thêm");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(String.valueOf(user.getMaxrow()));
				ImageIcon icon = new ImageIcon("/icons/aa.png");
				if (isEmpty()) {
					int id = Integer.parseInt(textField.getText());
					String username = textField_1.getText(); 
					String email = textField_2.getText();
					String password = String.valueOf(passwordField.getPassword());
					String phone = textField_4.getText();
					String address = textField_5.getText();
					if(!user.isEmailExist(email)) {
						if(!user.isPhoneExist(phone)) {
							user.insertuser(id, username, email, password, phone, address);
							table.setModel(new DefaultTableModel(null,new Object[]{"ID" ,"Tên người dùng", "Email", "Mật khẩu", "Số điện thoại",  "Địa chỉ"}));
							user.getUserValue(table,"");
							clear();
						}  else {
							JOptionPane.showMessageDialog(ManageUser.this, "Số điện thoại đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
						}
					} else {
						JOptionPane.showMessageDialog(ManageUser.this, "Địa chỉ email đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE,icon);
					}
				}
				
				
			}
		});
		btnSave.setBackground(new Color(0, 255, 0));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnSave.setBounds(30, 482, 154, 40);
		panel_1.add(btnSave);
		
		Button btnDelete = new Button("Xoá");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEmpty()) {
					int id = Integer.parseInt(textField.getText());
					user.delete(id);
					table.setModel(new DefaultTableModel(null,new Object[]{"ID" ,"Tên người dùng", "Email", "Mật khẩu", "Số điện thoại",  "Địa chỉ"}));
					user.getUserValue(table,"");
					clear();
				}
			}
		});
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnDelete.setBounds(197, 482, 154, 40);
		panel_1.add(btnDelete);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            if (passwordField != null) {
		                passwordField.setEchoChar((char) 0);
		                lblNewLabel_8.setVisible(false);
		                lblNewLabel_8_1.setVisible(true);
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace(); // Ghi lại lỗi
		            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
		        }
		    }
		});
		lblNewLabel_8.setIcon(new ImageIcon(ManageUser.class.getResource("/icons/hide.png")));
		lblNewLabel_8.setBounds(366, 307, 36, 14);
		panel_1.add(lblNewLabel_8);
		
		lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (passwordField != null) {
					try {	
						passwordField.setEchoChar('*');
						lblNewLabel_8.setVisible(true);
						lblNewLabel_8_1.setVisible(false);
					}  catch (Exception ex) {
			            ex.printStackTrace(); // Ghi lại lỗi
			            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
			        }
				}
			}
		});
		lblNewLabel_8_1.setIcon(new ImageIcon(ManageUser.class.getResource("/icons/visible.png")));
		lblNewLabel_8_1.setBounds(366, 307, 36, 14);
		panel_1.add(lblNewLabel_8_1);
		
		Button btnClear = new Button("Cập nhật");
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty()) {
					if(!check()) {
						int id = Integer.parseInt(textField.getText());
						String username = textField_1.getText(); 
						String email = textField_2.getText();
						String password = String.valueOf(passwordField.getPassword());
						String phone = textField_4.getText();
						String address = textField_5.getText();
						user.update(id, username, email, password, phone, address);
						table.setModel(new DefaultTableModel(null,new Object[]{"ID" ,"Tên người dùng", "Email", "Mật khẩu", "Số điện thoại",  "Địa chỉ"}));
						user.getUserValue(table,"");
						clear();
						
					}
				}
			}
		});
		btnClear.setBackground(new Color(202, 188, 11));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnClear.setBounds(30, 537, 154, 40);
		panel_1.add(btnClear);
		
		Button btnAdd = new Button("Dọn sạch");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnAdd.setBackground(new Color(0, 128, 192));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnAdd.setBounds(197, 537, 154, 40);
		panel_1.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm: ");
		lblNewLabel_1.setIcon(new ImageIcon(ManageUser.class.getResource("/icons/search_Light.png")));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1.setBounds(560, 11, 126, 40);
		panel_1.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				table.setModel(new DefaultTableModel(null,new Object[]{"ID" ,"Tên người dùng", "Email", "Mật khẩu", "Số điện thoại",  "Địa chỉ"}));
				user.getUserValue(table,textField_3.getText());
				
			}
		});

		
		textField_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(681, 16, 266, 30);
		panel_1.add(textField_3);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(1012, 400));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // Chọn để hiện lên thông tin các textfield
				model = (DefaultTableModel) table.getModel();
				rowIndex = table.getSelectedRow();
				
				textField.setText(model.getValueAt(rowIndex, 0).toString());
			    textField_1.setText(model.getValueAt(rowIndex, 1).toString());
			    textField_2.setText(model.getValueAt(rowIndex, 2).toString());
			    passwordField.setText(model.getValueAt(rowIndex, 3).toString());
			    textField_4.setText(model.getValueAt(rowIndex, 4).toString());
			    textField_5.setText(model.getValueAt(rowIndex, 5).toString());
		
				
			}
		});
		table.setShowHorizontalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn ng\u01B0\u1EDDi d\u00F9ng", "Email", "M\u1EADt kh\u1EA9u", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(177);
		table.getColumnModel().getColumn(2).setPreferredWidth(215);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(315);
		table.setBounds(400, 55, 580, 2000);
//		panel_1.add(table);
//		user.getUserValue(table,"");
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportBorder(new LineBorder(new Color(64, 128, 128), 2));
		scrollPane.setBounds(400, 55, 580, 530);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("Quản lý người dùng");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel_2.setBounds(10, 11, 364, 52);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Số điện thoại: ");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(32, 338, 110, 25);
		panel_1.add(lblNewLabel_4_1);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textField_4.setColumns(10);
		textField_4.setBounds(33, 364, 321, 31);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_5_1 = new JLabel("Địa chỉ: ");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(30, 408, 79, 14);
		panel_1.add(lblNewLabel_5_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(33, 430, 321, 31);
		panel_1.add(textField_5);
		
		JLabel lblNewLabel = new JLabel("x");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				AdminDashboards.panel_4.setBackground(primaryColor);
				AdminDashboards.panel_5.setBackground(primaryColor);
				AdminDashboards.menu1.setForeground(textPrimaryColor);
				AdminDashboards.dark1.setVisible(true);
				AdminDashboards.Light1.setVisible(false);
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel.setBounds(948, 0, 42, 47);
		panel_1.add(lblNewLabel);
		init();
	}
}
