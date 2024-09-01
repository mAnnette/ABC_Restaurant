package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Facility;



public class FacilityDAO {
	
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

    public static List<Facility> getAllFacilities() {
        List<Facility> facilityList = new ArrayList<>();
        String query = "SELECT * FROM facilities";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Facility facility = new Facility();
                facility.setId(rs.getInt("id"));
                facility.setName(rs.getString("name"));
                facility.setDescription(rs.getString("description"));
                facility.setImage(rs.getString("image"));
                facility.setLocation(rs.getString("location"));
                facilityList.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityList;
    }

    public static void createFacility(String name, String description, String image, String location) {
        String query = "INSERT INTO facilities (name, description, image, location) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, image);
            ps.setString(4, location);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFacility(int facilityId, String name, String description, String image, String location) {
        String query = "UPDATE facilities SET name = ?, description = ?, image = ?, location = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, image);
            ps.setString(4, location);
            ps.setInt(5, facilityId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFacility(int facilityId) {
        String query = "DELETE FROM facilities WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, facilityId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Facility getFacilityById(int facilityId) {
        String query = "SELECT * FROM facilities WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, facilityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Facility facility = new Facility();
                    facility.setId(rs.getInt("id"));
                    facility.setName(rs.getString("name"));
                    facility.setDescription(rs.getString("description"));
                    facility.setImage(rs.getString("image"));
                    facility.setLocation(rs.getString("location"));
                    return facility;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
