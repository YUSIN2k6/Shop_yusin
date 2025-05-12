package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.AdminDashboards;
import connection.MyConnection;
import dao.UserDao;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Toolkit;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAdmingmailcom;
    private JPasswordField passwordField;
    private JRadioButton rdbtnNewRadioButton;
    private JRadioButton rdbtnNewRadioButton_2;
    private ButtonGroup bg = new ButtonGroup();
    private JLabel lblNewLabel_8_1;
    private JLabel lblNewLabel_8;
    private UserDao userDao = new UserDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        bg.add(rdbtnNewRadioButton);
        bg.add(rdbtnNewRadioButton_2);
        rdbtnNewRadioButton.setSelected(true);
    }

    private boolean isEmpty() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
        
        // Kiểm tra email có rỗng không
        if (txtAdmingmailcom.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Địa chỉ email cần phải điền vào", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        
        // Kiểm tra định dạng email
        if (!txtAdmingmailcom.getText().matches("^.+@.+\\..+$")) {
            JOptionPane.showConfirmDialog(this, "Địa chỉ email không hợp lệ", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        
        // Kiểm tra mật khẩu có rỗng không
        String password = String.valueOf(passwordField.getPassword());
        if (password.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Mật khẩu cần phải điền vào", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        
        // Kiểm tra mật khẩu có chứa ký tự in hoa và ký tự số
//        if (!password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
//            JOptionPane.showConfirmDialog(this, "Mật khẩu phải chứa ít nhất một ký tự in hoa và một ký tự số", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
//            return false;
//        }
        
        return true;
    }

    public Login() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icons/aaa (2).png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    for (double i = 0.1; i <= 1.0; i += 0.1) {
                        float f = (float) i;
                        Login.this.setOpacity(f);
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
        setBounds(300, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        Panel panel = new Panel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 400, 500);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/icons/YUSIN logo.png")));
        lblNewLabel_1.setForeground(new Color(0, 0, 128));
        lblNewLabel_1.setBackground(new Color(0, 0, 0));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 30));
        lblNewLabel_1.setBounds(0, 0, 400, 500);
        panel.add(lblNewLabel_1);

        Panel panel_1 = new Panel();
        panel_1.setForeground(Color.WHITE);
        panel_1.setBackground(new Color(51, 51, 51));
        panel_1.setBounds(400, 0, 400, 500);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_5 = new JLabel("Đăng Nhập");
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_5.setBounds(29, 21, 327, 57);
        panel_1.add(lblNewLabel_5);

        txtAdmingmailcom = new JTextField();
        txtAdmingmailcom.setBounds(44, 135, 300, 30);
        panel_1.add(txtAdmingmailcom);
        txtAdmingmailcom.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(44, 215, 300, 30);
        panel_1.add(passwordField);

        JLabel lblNewLabel_6 = new JLabel("Email:");
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel_6.setBounds(44, 110, 62, 14);
        panel_1.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Mật khẩu:");
        lblNewLabel_7.setForeground(Color.WHITE);
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel_7.setBounds(44, 190, 90, 20);
        panel_1.add(lblNewLabel_7);

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
        lblNewLabel_8.setIcon(new ImageIcon(Login.class.getResource("/icons/hide.png")));
        lblNewLabel_8.setBounds(348, 222, 36, 14);
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
        lblNewLabel_8_1.setIcon(new ImageIcon(Login.class.getResource("/icons/visible.png")));
        lblNewLabel_8_1.setBounds(348, 222, 36, 14);
        panel_1.add(lblNewLabel_8_1);

        rdbtnNewRadioButton = new JRadioButton("Người dùng");
        rdbtnNewRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rdbtnNewRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtnNewRadioButton.setForeground(Color.WHITE);
        rdbtnNewRadioButton.setBackground(new Color(51, 51, 51));
        rdbtnNewRadioButton.setBounds(54, 276, 109, 23);
        panel_1.add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_2 = new JRadioButton("Người quản lý");
        rdbtnNewRadioButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rdbtnNewRadioButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtnNewRadioButton_2.setBackground(new Color(51, 51, 51));
        rdbtnNewRadioButton_2.setForeground(Color.WHITE);
        rdbtnNewRadioButton_2.setBounds(197, 276, 118, 23);
        panel_1.add(rdbtnNewRadioButton_2);

        JButton btnNewButton = new JButton("Đăng Nhập");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    String email = txtAdmingmailcom.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    ImageIcon icon = new ImageIcon("/icons/LOGO2.png");
                    if (rdbtnNewRadioButton.isSelected()) {
                        // Đăng nhập cho người dùng
                        if (userDao.login(email, password)) {
                            UserDashboard usdb = new UserDashboard();
                            usdb.setVisible(true);
                            usdb.useremail.setText(email);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(Login.this, "Địa chỉ email hoặc mật khẩu không đúng", "Đăng nhập thất bại", JOptionPane.WARNING_MESSAGE, icon);
                        }
                    } else {
                        // Đăng nhập cho admin (giữ nguyên logic cũ)
                        try {
                            Connection con = MyConnection.getConnection();
                            PreparedStatement ps;
                            ps = con.prepareStatement("SELECT * FROM admin WHERE email =? and password = ?");
                            ps.setString(1, email);
                            ps.setString(2, password);
                            ResultSet rs = ps.executeQuery();
                            if (rs.next()) {
                                AdminDashboards addb = new AdminDashboards();
                                addb.setVisible(true);
                                addb.adminemail.setText(email);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(Login.this, "Địa chỉ email hoặc mật khẩu không đúng", "Đăng nhập thất bại", JOptionPane.WARNING_MESSAGE, icon);
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setForeground(new Color(51, 51, 51));
        btnNewButton.setBounds(44, 324, 300, 38);
        panel_1.add(btnNewButton);

        JLabel lblNewLabel_9 = new JLabel("Không có tài khoảng?");
        lblNewLabel_9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_9.setForeground(Color.WHITE);
        lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel_9.setBounds(65, 380, 154, 25);
        panel_1.add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel("Đăng ký");
        lblNewLabel_10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp().setVisible(true);
                dispose();
            }
        });
        lblNewLabel_10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_10.setForeground(Color.WHITE);
        lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_10.setBounds(235, 382, 62, 23);
        panel_1.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel("Quên mật khẩu?");
        lblNewLabel_11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ForgotPassword().setVisible(true);
                dispose();
            }
        });
        lblNewLabel_11.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_11.setForeground(Color.WHITE);
        lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_11.setBounds(141, 416, 118, 23);
        panel_1.add(lblNewLabel_11);

        JLabel lblNewLabel_12 = new JLabel("x");
        lblNewLabel_12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_12.setForeground(Color.WHITE);
        lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblNewLabel_12.setBounds(358, 0, 42, 47);
        panel_1.add(lblNewLabel_12);
        init();
    }
}