package user;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ForgotPasswordDao;

import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JButton;

public class ForgotPassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private Button btnSave;
    private JLabel lblNewLabel_8_1;
    private JLabel lblNewLabel_8;
    private JTextField textField_1;

    Color notedit = new Color(204, 204, 204);
    Color edit = new Color(255, 255, 255);
    ForgotPasswordDao fg = new ForgotPasswordDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ForgotPassword frame = new ForgotPassword();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init() {
        passwordField.setBackground(notedit);
        passwordField.setEditable(false);
        btnSave.setEnabled(false);
    }

    private boolean isEmpty() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
        String password = String.valueOf(passwordField.getPassword());
        if (password.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Mật khẩu mới cần phải được điền vào", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        if (!isStrongPassword(password)) {
            JOptionPane.showConfirmDialog(this, "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường và số!", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        return true;
    }

    private boolean isStrongPassword(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false; // Phải có chữ hoa
        if (!password.matches(".*[a-z].*")) return false; // Phải có chữ thường
        if (!password.matches(".*\\d.*")) return false;   // Phải có số
        return true;
    }

    private boolean emailValidation() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/aa.png"));
        if (textField.getText().isEmpty()) {
            JOptionPane.showConfirmDialog(this, "Địa chỉ email cần phải điền vào", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        if (!textField.getText().matches("^.+@.+\\..+$")) {
            JOptionPane.showConfirmDialog(this, "Địa chỉ email không hợp lệ", "Cảnh báo", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, icon);
            return false;
        }
        return true;
    }

    public ForgotPassword() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ForgotPassword.class.getResource("/icons/aaa (2).png")));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(() -> {
                    for (double i = 0.1; i <= 1.0; i += 0.1) {
                        float f = (float) i;
                        ForgotPassword.this.setOpacity(f);
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 488, 406);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Panel panel = new Panel();
        panel.setLayout(null);
        panel.setBackground(new Color(51, 51, 51));
        panel.setBounds(0, 0, 488, 406);
        contentPane.add(panel);

        Panel panel_1 = new Panel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(51, 51, 51));
        panel_1.setBounds(0, 0, 487, 406);
        panel.add(panel_1);

        JLabel lblNewLabel_2 = new JLabel("Quên mật khẩu?");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewLabel_2.setBounds(57, 25, 321, 52);
        panel_1.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(57, 112, 321, 31);
        panel_1.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(57, 244, 321, 31);
        panel_1.add(passwordField);

        JLabel lblNewLabel_5 = new JLabel("Email:");
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(54, 90, 79, 14);
        panel_1.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Mật khẩu mới:");
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_6.setBounds(57, 218, 104, 25);
        panel_1.add(lblNewLabel_6);

        btnSave = new Button("Lưu");
        btnSave.setForeground(new Color(51, 51, 51));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isEmpty()) {
                    String email = textField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    fg.setPassword(email, password);
                    new Login().setVisible(true);
                    dispose();
                }
            }
        });
        btnSave.setBackground(new Color(255, 255, 255));
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnSave.setBounds(57, 312, 154, 52);
        panel_1.add(btnSave);

        Button btnBack = new Button("Quay về");
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setForeground(new Color(51, 51, 51));
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 25));
        btnBack.setBounds(224, 312, 154, 52);
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
        lblNewLabel_8.setIcon(new ImageIcon(ForgotPassword.class.getResource("/icons/hide.png")));
        lblNewLabel_8.setBounds(388, 254, 36, 14);
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
        lblNewLabel_8_1.setIcon(new ImageIcon(ForgotPassword.class.getResource("/icons/visible.png")));
        lblNewLabel_8_1.setBounds(388, 254, 36, 14);
        panel_1.add(lblNewLabel_8_1);

        JLabel lblNewLabel_7 = new JLabel("x");
        lblNewLabel_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblNewLabel_7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setForeground(Color.WHITE);
        lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblNewLabel_7.setBounds(445, 0, 42, 47);
        panel_1.add(lblNewLabel_7);

        JLabel lblNewLabel_5_1 = new JLabel("Nhập mã xác nhận:");
        lblNewLabel_5_1.setForeground(Color.WHITE);
        lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_5_1.setBounds(54, 154, 141, 14);
        panel_1.add(lblNewLabel_5_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(57, 176, 321, 31);
        panel_1.add(textField_1);

        JButton btnSend = new JButton("Gửi");
        btnSend.setBackground(new Color(255, 255, 255));
        btnSend.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emailValidation()) {
                    String email = textField.getText();
                    if (fg.isEmailExist(email)) {
                        String code = fg.generateVerificationCode();
                        if (fg.sendVerificationEmail(email, code)) {
                            JOptionPane.showMessageDialog(null, "Mã xác nhận đã được gửi đến email của bạn!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            textField_1.requestFocus();
                        }
                    }
                }
            }
        });
        btnSend.setBounds(382, 176, 95, 31);
        panel_1.add(btnSend);

        JButton btnResend = new JButton("Gửi lại");
        btnResend.setBackground(new Color(255, 255, 255));
        btnResend.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnResend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emailValidation()) {
                    String email = textField.getText();
                    if (fg.isEmailExist(email)) {
                        String code = fg.generateVerificationCode();
                        if (fg.sendVerificationEmail(email, code)) {
                            JOptionPane.showMessageDialog(null, "Mã xác nhận mới đã được gửi!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            textField_1.requestFocus();
                        }
                    }
                }
            }
        });
        btnResend.setBounds(382, 210, 95, 31);
        panel_1.add(btnResend);

        textField_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputCode = textField_1.getText();
                long currentTime = System.currentTimeMillis();
                long timeElapsed = (currentTime - fg.getCodeGenerationTime()) / 1000;
                if (timeElapsed > 300) {
                    JOptionPane.showMessageDialog(null, "Mã xác nhận đã hết hạn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (inputCode.equals(fg.getVerificationCode())) {
                    passwordField.setBackground(edit);
                    passwordField.setEditable(true);
                    btnSave.setEnabled(true);
                    passwordField.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        init();
    }
}