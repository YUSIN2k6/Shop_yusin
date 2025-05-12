package connection;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import javax.swing.JOptionPane;
//
//public class MyConnection {
//	public static final String username = "";
//	public static final String password = "";
//	public static final String url = "jdbc:mysql://localhost:3306/shop_yusin";
//	public static Connection con = null;
//	public static Connection getConnection() throws SQLException {
//	try {
//        return DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/shop_yusin", "root", ""); // Cập nhật thông tin kết nối nếu cần
//    } catch (SQLException e) {
//        e.printStackTrace();
//        return null;
//    }
//	}
//	public static Connection getConnection() throws SQLException {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection(url, username, password);
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, ""+e, "", JOptionPane.WARNING_MESSAGE);
//		}
//		return con;
//	}
//}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    // Thông tin kết nối
    private static final String URL = "jdbc:mysql://localhost:3306/shop_yusin";
    private static final String USERNAME = "root"; // Thay "root" bằng username thật nếu khác
    private static final String PASSWORD = "";    // Thay "" bằng mật khẩu nếu có

    // Phương thức kết nối
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại!");
            e.printStackTrace();
        }
        return con;
    }
}