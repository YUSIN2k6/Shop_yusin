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
import ITEM.item;
import ITEM.temp;
import connection.MyConnection;

public class PurchaseDetailDAO {
	private static PurchaseDetailDAO instance;
    public static PurchaseDetailDAO getInstance() {
		if(instance == null) {
			instance = new PurchaseDetailDAO();
		}
		return instance;
	}
	Connection con = MyConnection.getConnection();
	PreparedStatement ps;
    Statement st;
    ResultSet rs;
    public void getitemValue(JTable table, String search) {
	    String sql = "SELECT * FROM purchasedetail WHERE CONCAT(IFNULL(pid, ''), IFNULL(pname, '')) LIKE ? ORDER BY pid DESC";
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setString(1, "%" + search + "%");
	        rs = ps.executeQuery();
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        table.getColumnModel().getColumn(0).setPreferredWidth(85);
			table.getColumnModel().getColumn(1).setPreferredWidth(335);
			table.getColumnModel().getColumn(2).setPreferredWidth(105);
			table.getColumnModel().getColumn(3).setPreferredWidth(85);
			table.getColumnModel().getColumn(4).setPreferredWidth(205);
			table.getColumnModel().getColumn(5).setPreferredWidth(275);
			Object[] row;
	        while (rs.next()) {
	            row = new Object[6];
	            row[0] = rs.getInt("pid");
	            row[1] = rs.getString("pname");
	            row[2] = rs.getString("pcolor");
	            row[3] = rs.getInt("pqty");
	            row[4] = rs.getLong("price");
	            row[5] = rs.getString("pdate");
	            model.addRow(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
    public void additem(int id, String name, String color, int qty, long price, String date) {
        try {
            ps = con.prepareStatement("INSERT INTO purchasedetail (pid, pname, pcolor, pqty, price, pdate) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2,name);
            ps.setString(3, color);
            ps.setInt(4, qty);
            ps.setLong(5, price);
            ps.setString(6, date);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteitem(int id) {
	    String sql = "DELETE FROM purchasedetail WHERE pid = ?";
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
    public long Totalprice() {
        long total = 0;
        try {
            ps = con.prepareStatement("SELECT SUM(price) AS total_price FROM purchasedetail;");
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getLong("total_price"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total+10000; 
    }
    public int quanty() {
    	int qty = 0;
        try {
            ps = con.prepareStatement("SELECT SUM(pqty) AS total_qty FROM purchasedetail;");
            rs = ps.executeQuery();
            while (rs.next()) {
                qty = rs.getInt("total_qty"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty; 
    }
    public void addhd(String idhd) {
    	try {
			ps = con.prepareStatement("INSERT INTO idhd (id) VALUES(?)");
			ps.setString(1,idhd);
            ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void deletehd(String idhd) {
	    String sql = "DELETE FROM idhd WHERE id = ?";
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setString(1, idhd);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
    public String idhd() {
        String id = null;
        try {
            ps = con.prepareStatement("SELECT id FROM IDHD LIMIT 1;");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getString("id"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; 
    }
    public int getTotalprice() {
        int id = 0;
        try {
            ps = con.prepareStatement("SELECT price FROM totalprice LIMIT 1;");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("price"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id; 
    }
    public ArrayList<temp> loadbuytemp() {
		ArrayList<temp> list = new ArrayList<>();
		try {
			
			String sql = "SELECT * FROM purchasetemp ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); 
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(4);
				int qty = rs.getInt(3);
				int totalprice = rs.getInt(5);
				temp c = new temp(id, name, qty, price, totalprice);
				
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return list;
	}
    public int getMaxrow() {
		int row = 0; 
		try {
			st =con.createStatement();
			rs = st.executeQuery("SELECT MAX(pid) FROM purchasetemp");
			while (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
    public int getMaxrowProduct() {
		int row = 0; 
		try {
			st =con.createStatement();
			rs = st.executeQuery("SELECT MAX(id) FROM category");
			while (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
    public void inserttemp(int id , String name, int price, int qty,int totalprice) {
    	try {
			ps = con.prepareStatement("INSERT INTO purchasetemp (pid, pname, price, pqty, totalprice) VALUES (?,?,?,?,?);");
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setInt(3,price);
			ps.setInt(4,qty);
			ps.setInt(5,totalprice);
			
            ps.executeUpdate(); 
		} catch (SQLException e) {
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
    public int getqtyatTemp(String name) {
        int qty = 0;
        try {
            ps = con.prepareStatement("SELECT pqty FROM purchasetemp WHERE pname  = ?");
            ps.setString(1, name.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
            	qty = rs.getInt("pqty");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }
    public String getNameatCategory(int stt) {
        String name = null;
        try {
            ps = con.prepareStatement("SELECT name FROM category WHERE id = ?");
            ps.setInt(1, stt);
            rs = ps.executeQuery();
            if (rs.next()) {
            	name = rs.getString("name");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getNameatTemp(int stt) {
        String name = null;
        try {
            ps = con.prepareStatement("SELECT pname FROM purchasetemp WHERE pid = ?");
            ps.setInt(1, stt);
            rs = ps.executeQuery();
            if (rs.next()) {
            	name = rs.getString("pname");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public int getidatCategory(String name) {
    	int id = 0;
        try {
            ps = con.prepareStatement("SELECT id FROM category WHERE name  = ?");
            ps.setString(1, name.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
            	id = rs.getInt("id");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    	
    }
    public int getidatTemp(String name) {
    	int id = 0;
        try {
            ps = con.prepareStatement("SELECT pid FROM purchasetemp WHERE pname  = ?");
            ps.setString(1, name.trim());
            rs = ps.executeQuery();
            if (rs.next()) {
            	id = rs.getInt("pid");
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    	
    }
    public void updatetemp(String name,int qty, int totalprice) {
    	String sql = "UPDATE purchasetemp SET pqty = ?, totalprice =?  WHERE pname = ?";
    	try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,qty);
			ps.setInt(2,totalprice);
			ps.setString(3,name);
			
			ps.executeUpdate();
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void deletetemp(String name) {
    	String sql = "DELETE FROM purchasetemp WHERE pname = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name.trim());
          
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public void Deletealltemp() {
		try {
			ps = con.prepareStatement("TRUNCATE TABLE purchasetemp;");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
}
