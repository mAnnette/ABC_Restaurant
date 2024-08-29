package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDashboardDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "Isuranga@123";

    // Constructor
    public AdminDashboardDAO() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get the total number of reservations
    public int getTotalReservations() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM reservations";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throwing for further handling if needed
        }
        return count;
    }

    // Method to get the total number of customers
    public int getTotalCustomers() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM customers";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throwing for further handling if needed
        }
        return count;
    }

    // Method to get the total number of active promotions
    public int getActivePromotions() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM promotions WHERE active = true";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throwing for further handling if needed
        }
        return count;
    }

    // Method to get the total number of staff on duty
    public int getStaffOnDuty() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM staff WHERE on_duty = true";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throwing for further handling if needed
        }
        return count;
    }

}
