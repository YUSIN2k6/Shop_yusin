package admin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import connection.MyConnection;
import dao.DetailDAO;
 
public class StatisticsDAO {
	private static StatisticsDAO instance;

	public static StatisticsDAO getInstance() {
		if (instance == null) {
			instance = new StatisticsDAO();
		}
		return instance;
	}

	public StatisticsDAO() {

	}
	Connection con = MyConnection.getConnection();
	PreparedStatement ps;
    Statement st;
    ResultSet rs;
   

    public void insertshirt(int qty) {
        try {
            ps = con.prepareStatement("INSERT INTO shirt (qty) VALUES (?);");
          
            ps.setInt(1, qty);   
           
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public int getTotalqtysold() {
		int qty = 0;
		try {
			ps = con.prepareStatement("SELECT SUM(qty) AS totalqty FROM shirt;");
			rs = ps.executeQuery();
			if (rs.next()) {
				qty = rs.getInt("totalqty");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}
    public void insertTotalprice(int price, Date day) {
        try {
            ps = con.prepareStatement("INSERT INTO totalprice (price, mdate) VALUES (?, ?);");
          
            ps.setInt(1, price);   
            ps.setDate(2, day);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getTotalshirt() {
		int qty = 0;
		try {
			ps = con.prepareStatement("SELECT SUM(qty) AS totalqty FROM category;");
			rs = ps.executeQuery();
			if (rs.next()) {
				qty = rs.getInt("totalqty");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}
    public int getTotalmoney() {
		int qty = 0;
		try {
			ps = con.prepareStatement("SELECT SUM(price) AS totalqty FROM totalprice;");
			rs = ps.executeQuery();
			if (rs.next()) {
				qty = rs.getInt("totalqty");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qty;
	}
    public Map<Integer, Integer> getMonthlyRevenue2025() {
        Map<Integer, Integer> monthlyRevenue = new LinkedHashMap<>();
        try {
            ps = con.prepareStatement(
                "SELECT MONTH(mdate) AS month, SUM(price) AS total " +
                "FROM totalprice " +
                "WHERE YEAR(mdate) = 2025 " +
                "GROUP BY MONTH(mdate);"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                int total = rs.getInt("total");
                monthlyRevenue.put(month, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyRevenue;
    }
}
