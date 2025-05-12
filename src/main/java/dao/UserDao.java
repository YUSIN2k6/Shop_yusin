package dao;

import java.sql.Connection;
import connection.MyConnection;
import user.Login;
import user.UserDashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class UserDao {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

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

    public int getMaxrow() {
        int row = 0; 
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT MAX(uid) FROM userr");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row + 1;
    }

    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("SELECT * FROM userr WHERE uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(int id, String username, String email, String password, String phone, String address) {
        String sql = "INSERT INTO userr (uid, uname, uemail, upassword, uphone, uaddress, usalt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, salt);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Người dùng đã đăng ký thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertuser(int id, String username, String email, String password, String phone, String address) {
        String sql = "INSERT INTO userr (uid, uname, uemail, upassword, uphone, uaddress, usalt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, hashedPassword);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, salt);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Người dùng đã được thêm thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getUserValue(int id) {
        String[] value = new String[7];
        try {
            ps = con.prepareStatement("SELECT * FROM userr WHERE uid = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                value[0] = rs.getString(1);
                value[1] = rs.getString(2);
                value[2] = rs.getString(3);
                value[3] = rs.getString(4);
                value[4] = rs.getString(5);
                value[5] = rs.getString(6);
                value[6] = rs.getString(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int getUserId(String email) {
        int id = 0;
        try {
            ps = con.prepareStatement("SELECT uid FROM userr WHERE uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void update(int id, String username, String email, String password, String phone, String address) {
        String sql = "UPDATE userr SET uname = ?, uemail = ?, upassword = ?, uphone = ?, uaddress = ?, usalt = ? WHERE uid = ?";
        try {
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, salt);
            ps.setInt(7, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Tài khoản đã cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản cập nhật thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        ImageIcon icon = new ImageIcon(UserDao.class.getResource("/icons/LOGO4.png"));
        int a = JOptionPane.showOptionDialog(null, "Bạn có chắc muốn xóa tài khoản hay không?", 
                "Xóa tài khoản", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                icon, null, null);
        if (a == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement("DELETE FROM userr WHERE uid = ?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Tài khoản đã bị xóa!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getUserValue(JTable table, String search) {
        String sql = "SELECT * FROM userr WHERE CONCAT(IFNULL(uid, ''), IFNULL(uname, ''), IFNULL(uemail, '')) LIKE ? ORDER BY uid DESC";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
            table.getColumnModel().getColumn(1).setPreferredWidth(177);
            table.getColumnModel().getColumn(2).setPreferredWidth(215);
            table.getColumnModel().getColumn(3).setPreferredWidth(115);
            table.getColumnModel().getColumn(4).setPreferredWidth(110);
            table.getColumnModel().getColumn(5).setPreferredWidth(315);

            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt("uid");
                row[1] = rs.getString("uname");
                row[2] = rs.getString("uemail");
                row[3] = rs.getString("upassword");
                row[4] = rs.getString("uphone");
                row[5] = rs.getString("uaddress");
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thêm phương thức login cho người dùng
    public boolean login(String email, String password) {
        try {
            ps = con.prepareStatement("SELECT upassword, usalt FROM userr WHERE uemail = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("upassword");
                String salt = rs.getString("usalt");
                String inputHash = hashPassword(password, salt);
                return storedHash.equals(inputHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}