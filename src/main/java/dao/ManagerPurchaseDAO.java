package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ITEM.managedetail;
import ITEM.managepurchase;
import ITEM.temp;
import connection.MyConnection;

public class ManagerPurchaseDAO {
	private static ManagerPurchaseDAO instance;

	public static ManagerPurchaseDAO getInstance() {
		if (instance == null) {
			instance = new ManagerPurchaseDAO();
		}
		return instance;
	}

	public ManagerPurchaseDAO() {

	}
	Connection con = MyConnection.getConnection();
	PreparedStatement ps;
    Statement st;
    ResultSet rs;
    
    public void insertdetail(String idhd, String name, int qty, int price, int totalprice) {
        try {
            ps = con.prepareStatement("INSERT INTO detail (idhd, name, qty, price, totalprice) VALUES (?,?,?,?,?);");
            ps.setString(1, idhd);
            ps.setString(2, name);
            ps.setInt(3, qty);    
            ps.setInt(4, price);  
            ps.setInt(5, totalprice);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertPurchase(String idhd, int totalprice, int totalqty, String hour, Date day) {
        try {
           
            ps = con.prepareStatement("INSERT INTO managepurchase (idhd, totalprice, totalqty, hour, day) VALUES (?,?,?,?,?);");
            ps.setString(1, idhd);
            ps.setInt(2, totalprice);
            ps.setInt(3, totalqty);
            ps.setString(4, hour);
            ps.setDate(5, day);
            ps.executeUpdate();
        } catch (SQLException HD) {
            HD.printStackTrace();
        }
    }
    public ArrayList<managedetail> loadDetail() {
		ArrayList<managedetail> list = new ArrayList<>();
		try {
			
			String sql = "SELECT * FROM detail ORDER BY id DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()) {
				int id = rs.getInt(1);
				String idhd = rs.getString(2);
				String name = rs.getString(3);
				int price = rs.getInt(5);
				int qty = rs.getInt(4);
				int totalprice = rs.getInt(6);
				managedetail c = new managedetail(id,idhd, name, qty, price, totalprice);
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
	}
    public ArrayList<managepurchase> loadPurchased() {
        ArrayList<managepurchase> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM managepurchase ORDER BY id DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String idhd = rs.getString(2);
                int totalprice = rs.getInt(3);
                int totalqty = rs.getInt(4); 
                String hour = rs.getString(5); 
                Date day = rs.getDate(6);
                managepurchase c = new managepurchase(id, idhd, totalprice, totalqty, hour, day);
                list.add(c);
            }
        } catch (SQLException HD) {
            HD.printStackTrace();
        }
        return list;
    }

    public void deletepurchase(String idhd) {
    	int a = JOptionPane.showOptionDialog(null, "Bạn có chắc muốn xoá hoá đơn này hay không?", 
                "Xoá sản phẩm", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, null, null);
		 if (a == JOptionPane.YES_OPTION) {
	    	String sql = "DELETE FROM managepurchase WHERE idhd = ?";
	        try {
	            ps = con.prepareStatement(sql);
	            ps.setString(1, idhd);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
    }
    public void deletedetail(String idhd) {
    	
	    	String sql = "DELETE FROM detail WHERE idhd = ?";
	        try {
	            ps = con.prepareStatement(sql);
	            ps.setString(1, idhd);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
    }
    public void getManagepurchaseValue(JTable table, String search) {
    	String sql = "SELECT * FROM managepurchase WHERE CONCAT(IFNULL(idhd, ''), IFNULL(totalprice, ''), IFNULL(day, '')) LIKE ? ORDER BY id DESC";
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setString(1, "%" + search + "%");
	        rs = ps.executeQuery();
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        model.setRowCount(0); // Xóa tất cả các hàng cũ
	        table.getColumnModel().getColumn(1).setPreferredWidth(170);
	        table.getColumnModel().getColumn(2).setPreferredWidth(200);
	        table.getColumnModel().getColumn(3).setPreferredWidth(100);
	        table.getColumnModel().getColumn(4).setPreferredWidth(200);
	        table.getColumnModel().getColumn(5).setPreferredWidth(200);
			Object[] row;
	        while (rs.next()) {
	            row = new Object[6];
	            row[0] = rs.getInt("id"); 
	            row[1] = rs.getString("idhd"); 
	            row[2] = rs.getInt("totalprice");
	            row[3] = rs.getInt("totalqty");
	            row[4] = rs.getString("hour"); 
	            row[5] = rs.getDate("day"); 

	            model.addRow(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
