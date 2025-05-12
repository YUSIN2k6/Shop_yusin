package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ITEM.detailitem;
import ITEM.infobuy;
import connection.MyConnection;

public class DetailDAO {
	private static DetailDAO instance;

	public static DetailDAO getInstance() {
		if (instance == null) {
			instance = new DetailDAO();
		}
		return instance;
	}

	public DetailDAO() {

	}
	Connection con = MyConnection.getConnection();
	PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public int getMaxrowDetail() {
		int row = 0; 
		try {
			st =con.createStatement();
			rs = st.executeQuery("SELECT MAX(pid) FROM purchasedetail");
			while (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row+1;
	}
    public void insertdetail(int id, String name, int qty, int price, int totalprice) {
        try {
            ps = con.prepareStatement("INSERT INTO purchasedetail (pid, pname, pqty, price, totalprice) VALUES (?,?,?,?,?);");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, qty);   
            ps.setInt(4, price); 
            ps.setInt(5, totalprice);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateinfo(infobuy p) {
    	String sql = "UPDATE infobuy SET idhd = ?, totalprice =? , hour = ? , day = ? WHERE stt = 1";
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1,p.getIdhd());
			ps.setInt(2,p.getTotalprice());
			ps.setString(3,p.getHour());
			ps.setDate(4,p.getDay());
			ps.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void updateresetinfo() {
        String sql = "UPDATE infobuy SET idhd = ?, totalprice = ?, hour = ?, day = ? WHERE stt = 1";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "########"); // Hoặc "" nếu muốn để rỗng
            ps.setInt(2, 0);
            ps.setString(3, "00:00");
            ps.setNull(4, java.sql.Types.DATE); // Hoặc ps.setDate(4, java.sql.Date.valueOf("1970-01-01")) nếu cần ngày cụ thể
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String[] getInfoBuyValue() {
		String[] value = new String[5];
		try {
			ps = con.prepareStatement("SELECT * FROM Infobuy WHERE stt = 1");
			rs = ps.executeQuery();
			if (rs.next()) {
				value[0] = rs.getString("stt");
				value[1] = rs.getString("idhd");
				value[2] = rs.getString("totalprice");
				value[3] = rs.getString("hour");
				value[4] = rs.getString("day");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
    public void Deleteall() {
		try {
			ps = con.prepareStatement("TRUNCATE TABLE purchasedetail;");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public ArrayList<detailitem> loaddetailitem() {
		ArrayList<detailitem> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM purchasedetail ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int qty = rs.getInt(3);
				int price = rs.getInt(4);
				int totalprice = rs.getInt(5);
				detailitem buy = new detailitem(id, name, qty, price,totalprice);
				list.add(buy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
    public void updatetotalprice(infobuy p) {
    	String sql = "UPDATE infobuy SET totalprice =? WHERE stt = 1";
    	try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,p.getTotalprice());			
			ps.executeUpdate();		
		}  catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int gettotalprice() {
        int price = 0;
        try {
            ps = con.prepareStatement("SELECT totalprice FROM infobuy WHERE stt = 1");
            rs = ps.executeQuery();
            if (rs.next()) {
            	price = rs.getInt("totalprice");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }
    public int gettotalqty() {
		int qty = 0;
		try {
			ps = con.prepareStatement("SELECT SUM(pqty) AS total_totalqty FROM purchasedetail;");
			rs = ps.executeQuery();
			if (rs.next()) {
				qty = rs.getInt("total_totalqty");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}
    public int gettabledetail() {
		int qty = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) AS total_totaltable FROM purchasedetail;");
			rs = ps.executeQuery();
			if (rs.next()) {
				qty = rs.getInt("total_totaltable");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}
}
