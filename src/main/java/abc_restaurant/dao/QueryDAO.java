package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Query;



public class QueryDAO {
	
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


	    public void submitQuery(Query query) throws SQLException {
	        String sql = "INSERT INTO queries (reservation_id, customer_id, customer_name, query_text, status) VALUES (?, ?, ?, ?, ?)";
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, query.getReservationId());
	            ps.setInt(2, query.getCustomerId());
	            ps.setString(3, query.getCustomerName());
	            ps.setString(4, query.getQueryText());
	            ps.setString(5, query.getStatus());
	            ps.executeUpdate();
	        }
	    }
	    
	
	    public void respondToQuery(int queryId, String responseText, int responderId, boolean isStaff) throws SQLException {
	        String sql = "UPDATE queries SET response_text = ?, response_date = CURRENT_TIMESTAMP, " +
	                     (isStaff ? "staff_id" : "admin_id") + " = ?, status = 'Answered' WHERE id = ?";
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, responseText);
	            ps.setInt(2, responderId);
	            ps.setInt(3, queryId);
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new SQLException("No rows affected; query ID may not exist.");
	            }
	        }
	    }

	    public List<Query> getAllQueries() throws SQLException {
	        String sql = "SELECT * FROM queries";
	        List<Query> queries = new ArrayList<>();
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Query query = new Query(
	                        rs.getInt("id"),
	                        rs.getInt("reservation_id"),
	                        rs.getInt("customer_id"),
	                        rs.getString("customer_name"),
	                        rs.getString("query_text"),
	                        rs.getTimestamp("query_date"),
	                        rs.getString("response_text"),
	                        rs.getTimestamp("response_date"),
	                        rs.getInt("staff_id"),
	                        rs.getInt("admin_id"),
	                        rs.getString("status")
	                );
	                queries.add(query);
	            }
	        }
	        return queries;
	    }

	
	    public List<Query> getQueriesByRestaurantId(int restaurantId) throws SQLException {
	        String sql = "SELECT q.* FROM queries q " +
	                     "JOIN reservations r ON q.reservation_id = r.id " +
	                     "WHERE r.restaurant_id = ?";
	        List<Query> queries = new ArrayList<>();
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, restaurantId);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Query query = new Query(
	                            rs.getInt("id"),
	                            rs.getInt("reservation_id"),
	                            rs.getInt("customer_id"),
	                            rs.getString("customer_name"),
	                            rs.getString("query_text"),
	                            rs.getTimestamp("query_date"),
	                            rs.getString("response_text"),
	                            rs.getTimestamp("response_date"),
	                            rs.getInt("staff_id"),
	                            rs.getInt("admin_id"),
	                            rs.getString("status")
	                    );
	                    queries.add(query);
	                }
	            }
	        }
	        return queries;
	    }

	    public List<Query> getQueriesByReservationId(int reservationId) throws SQLException {
	        String sql = "SELECT * FROM queries WHERE reservation_id = ?";
	        List<Query> queries = new ArrayList<>();
	        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, reservationId);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Query query = new Query(
	                            rs.getInt("id"),
	                            rs.getInt("reservation_id"),
	                            rs.getInt("customer_id"),
	                            rs.getString("customer_name"),
	                            rs.getString("query_text"),
	                            rs.getTimestamp("query_date"),
	                            rs.getString("response_text"),
	                            rs.getTimestamp("response_date"),
	                            rs.getInt("staff_id"),
	                            rs.getInt("admin_id"),
	                            rs.getString("status")
	                    );
	                    queries.add(query);
	                }
	            }
	        }
	        return queries;
	    }

}
