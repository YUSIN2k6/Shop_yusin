package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.MyConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class ForgotPasswordDao {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    private String verificationCode;
    private long codeGenerationTime;

    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        verificationCode = String.valueOf(code);
        codeGenerationTime = System.currentTimeMillis();
        return verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public long getCodeGenerationTime() {
        return codeGenerationTime;
    }

    public boolean sendVerificationEmail(String toEmail, String code) {
        final String fromEmail = "tuannh.24itb@vku.udn.vn";
        final String password = "sfzjqzljbshquwfe"; // App Password (không có khoảng trắng)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã Xác Nhận Đổi Mật Khẩu");
            message.setText("Mã xác nhận của bạn là: " + code);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể gửi email: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("SELECT * FROM userr WHERE uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Địa chỉ email không tồn tại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("SELECT * FROM userr WHERE uphone = ?");
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Số điện thoại không tồn tại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setPassword(String email, String password) {
        String sql = "UPDATE userr SET upassword = ?, usalt = ? WHERE uemail = ?";
        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            ps = con.prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setString(2, salt);
            ps.setString(3, email);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Mật khẩu đã thay đổi thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không thể thay đổi mật khẩu!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}