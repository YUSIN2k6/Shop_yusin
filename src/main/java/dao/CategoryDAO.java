	package dao;
	import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;

import ITEM.category;

import connection.MyConnection;
	public class CategoryDAO {
		private static CategoryDAO instance;

		public static CategoryDAO getInstance() {
			if(instance == null) {
				instance = new CategoryDAO();
			}
			return instance;
		}
		Connection con = MyConnection.getConnection();
		PreparedStatement ps;
	    Statement st;
	    ResultSet rs;
	    public void getcategoryValue(JTable table, String search) {
	    	String sql = "SELECT * FROM category WHERE CONCAT(IFNULL(name, ''), IFNULL(price, '')) LIKE ? ORDER BY id DESC";
		    try {
		        ps = con.prepareStatement(sql);
		        ps.setString(1, "%" + search + "%");
		        rs = ps.executeQuery();
		        DefaultTableModel model = (DefaultTableModel) table.getModel();
		        model.setRowCount(0); // Xóa tất cả các hàng cũ
		        table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(260);
				table.getColumnModel().getColumn(2).setPreferredWidth(200);
				table.getColumnModel().getColumn(3).setPreferredWidth(100);
				Object[] row;
		        while (rs.next()) {
		            row = new Object[4];
		            row[0] = rs.getInt("id"); 
		            row[1] = rs.getString("name"); 
		            row[2] = rs.getInt("price");
		            row[3] = rs.getInt("qty");
		            model.addRow(row);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	    public ArrayList<category> loadcategory() {
			ArrayList<category> list = new ArrayList<>();
			try {
				
				String sql = "SELECT * FROM category ";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery(); 
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int price = rs.getInt(3);
					int qty = rs.getInt(4);
					category c = new category(id, name, price, qty);
					
					list.add(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return list;
		}
	    public void insertProduct(category p) {
	    	 String sql = "INSERT INTO category (name, price, qty) VALUES (?, ?, ?)";
	         try {
	             ps = con.prepareStatement(sql);
	             ps.setString(1, p.getName());
	             ps.setInt(2, p.getPrice());
	             ps.setInt(3, p.getQty());
	             ps.executeUpdate();
	             
				JOptionPane.showMessageDialog(null , "Thêm áo thành công!");

	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
	    }
	    public void updateProduct(int id, String name, int price,int qty) {
	    	String sql = "UPDATE category SET name = ?, price = ?, qty = ? WHERE id = ?";
	    	try {
				ps = con.prepareStatement(sql);
				ps.setString(1,name);
				ps.setInt(2,price);
				ps.setInt(3,qty);
				ps.setInt(4,id);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null , "Sửa áo thành công!");
			}  catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    public void updateProduct2(String name,int qty) {
	    	String sql = "UPDATE category SET qty = ? WHERE name = ?";
	    	try {
				ps = con.prepareStatement(sql);
				ps.setInt(1,qty);
				
				ps.setString(2,name);
				
				ps.executeUpdate();
				
			}  catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    public int getqtyatCategory(String name) {
	        int qty = 0;
	        try {
	            ps = con.prepareStatement("SELECT qty FROM category WHERE name  = ?");
	            ps.setString(1, name.trim());
	            rs = ps.executeQuery();
	            if (rs.next()) {
	            	qty = rs.getInt("qty");
	            } 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return qty;
	    }
	    public void delete(int id) {
			 int a = JOptionPane.showOptionDialog(null, "Bạn có chắc muốn xoá áo này hay không?", 
	                "Xoá áo", 
	                JOptionPane.YES_NO_OPTION, 
	                JOptionPane.QUESTION_MESSAGE, 
	                null, null, null);
			 if (a == JOptionPane.YES_OPTION) {
				try {
					ps = con.prepareStatement("DELETE FROM category WHERE id = ?");
					ps.setInt(1,id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null , "Xoá nhân viên thành công!");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
