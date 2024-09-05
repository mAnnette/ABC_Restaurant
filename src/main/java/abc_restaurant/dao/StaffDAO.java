package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Staff;



public class StaffDAO {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Isuranga@123";

	   
	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("JDBC Driver not found", e);
	        }
	    }

	   
	    public static List<Staff> getAllStaff() {
	        List<Staff> staffList = new ArrayList<>();
	        String query = "SELECT * FROM staff";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Staff staff = new Staff();
	                staff.setId(rs.getInt("staff_id"));
	                staff.setUsername(rs.getString("username"));
	                staff.setPassword(rs.getString("password"));
	                staff.setPosition(rs.getString("position"));
	                staff.setCreatedAt(rs.getTimestamp("created_at"));
	                staff.setRestaurantId(rs.getInt("restaurant_id"));
	                staffList.add(staff);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return staffList;
	    }
	    
	
	    public Staff getStaffById(int id) {
	        Staff staff = null;
	        String query = "SELECT * FROM staff WHERE staff_id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    staff = new Staff();
	                    staff.setId(rs.getInt("staff_id"));
	                    staff.setUsername(rs.getString("username"));
	                    staff.setPassword(rs.getString("password"));
	                    staff.setPosition(rs.getString("position"));
	                    staff.setCreatedAt(rs.getTimestamp("created_at"));
	                    staff.setRestaurantId(rs.getInt("restaurant_id"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return staff;
	    }


	    public static void createStaff(String username, String password, String position, int restaurantId) {
	        String query = "INSERT INTO staff (username, password, position, restaurant_id) VALUES (?, ?, ?, ?)";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ps.setString(3, position);
	            ps.setInt(4, restaurantId);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	  
	    public static void updateStaff(int staffId, String username, String password, String position, int restaurantId) {
	        String query = "UPDATE staff SET username = ?, password = ?, position = ?, restaurant_id = ? WHERE staff_id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ps.setString(3, position);
	            ps.setInt(4, restaurantId);
	            ps.setInt(5, staffId);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
	    public static void deleteStaff(int staffId) {
	        String query = "DELETE FROM staff WHERE staff_id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, staffId);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    

	    public boolean checkStaffCredentials(String username, String password, int restaurantId) {
	        String query = "SELECT * FROM staff WHERE username = ? AND password = ? AND restaurant_id = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            ps.setInt(3, restaurantId);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	 
	    public boolean checkStaffCredentials(String username, String password) {
	        String query = "SELECT * FROM staff WHERE username = ? AND password = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public Staff getStaffByUsername(String username) throws SQLException {
	        String sql = "SELECT * FROM staff WHERE username = ?";
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, username);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    Staff staff = new Staff();
	                    staff.setId(rs.getInt("id"));
	                    staff.setUsername(rs.getString("username"));
	                    staff.setPassword(rs.getString("password"));
	                    return staff;
	                }
	            }
	        }
	        return null;
	    }

}
