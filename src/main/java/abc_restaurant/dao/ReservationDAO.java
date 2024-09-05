package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Reservation;



public class ReservationDAO {
	
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

    public void createReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (customer_id, restaurant_id, reservation_date, reservation_type, number_of_guests, additional_facilities, status, customer_name, customer_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, reservation.getCustomerId());
            ps.setInt(2, reservation.getRestaurantId());
            ps.setTimestamp(3, reservation.getReservationDateTime());
            ps.setString(4, reservation.getReservationType());
            ps.setInt(5, reservation.getNumberOfGuests());
            ps.setString(6, reservation.getAdditionalRequests());
            ps.setString(7, reservation.getStatus());
            ps.setString(8, reservation.getCustomerName()); 
            ps.setString(9, reservation.getCustomerEmail()); 
            ps.executeUpdate();
        }
    }


    public boolean isAvailable(int restaurantId, Timestamp reservationDateTime, String reservationType, int numberOfGuests) throws SQLException {
        String capacityQuery = "SELECT capacity FROM restaurants WHERE id = ?";
        String reservationQuery = "SELECT SUM(number_of_guests) AS total_guests FROM reservations WHERE restaurant_id = ? AND reservation_date = ? AND status = 'Confirmed'";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            int capacity;
            try (PreparedStatement psCapacity = con.prepareStatement(capacityQuery)) {
                psCapacity.setInt(1, restaurantId);
                try (ResultSet rsCapacity = psCapacity.executeQuery()) {
                    if (rsCapacity.next()) {
                        capacity = rsCapacity.getInt("capacity");
                    } else {
                        throw new SQLException("Restaurant capacity not found.");
                    }
                }
            }

            int reservedGuests = 0;
            try (PreparedStatement psReservation = con.prepareStatement(reservationQuery)) {
                psReservation.setInt(1, restaurantId);
                psReservation.setTimestamp(2, reservationDateTime);

                try (ResultSet rsReservation = psReservation.executeQuery()) {
                    if (rsReservation.next()) {
                        reservedGuests = rsReservation.getInt("total_guests");
                    }
                }
            }
            return (reservedGuests + numberOfGuests) <= capacity;
        }
    }


    public static Reservation getReservationById(int id) throws SQLException {
        String query = "SELECT * FROM reservations WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("restaurant_id"),
                        rs.getString("reservation_type"),
                        rs.getTimestamp("reservation_date"),
                        rs.getInt("number_of_guests"),
                        rs.getString("additional_facilities"),
                        rs.getString("status"),
                        rs.getString("customer_name"), 
                        rs.getString("customer_email")  
                    );
                }
            }
        }
        return null;
    }
    

    public List<Reservation> getReservationsByCustomerId(int customerId) throws SQLException {
        String query = "SELECT * FROM reservations WHERE customer_id = ?";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getInt("id"));
                    reservation.setCustomerId(rs.getInt("customer_id"));
                    reservation.setRestaurantId(rs.getInt("restaurant_id"));
                    reservation.setReservationDateTime(rs.getTimestamp("reservation_date"));
                    reservation.setReservationType(rs.getString("reservation_type"));
                    reservation.setNumberOfGuests(rs.getInt("number_of_guests"));
                    reservation.setAdditionalRequests(rs.getString("additional_facilities"));
                    reservation.setStatus(rs.getString("status"));
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }
    
    
    public List<Reservation> getReservationsByRestaurantId(int restaurantId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations WHERE restaurant_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getInt("id"));
                    reservation.setCustomerId(rs.getInt("customer_id"));
                    reservation.setRestaurantId(rs.getInt("restaurant_id"));
                    reservation.setReservationDateTime(rs.getTimestamp("reservation_date"));
                    reservation.setReservationType(rs.getString("reservation_type"));
                    reservation.setNumberOfGuests(rs.getInt("number_of_guests"));
                    reservation.setAdditionalRequests(rs.getString("additional_facilities"));
                    reservation.setStatus(rs.getString("status"));
                    reservation.setCustomerName(rs.getString("customer_name"));  
                    reservation.setCustomerEmail(rs.getString("customer_email"));
                    
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }
    
    
    public void updateReservationStatus(int reservationId, String status) throws SQLException {
        String query = "UPDATE reservations SET status = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, reservationId);
            ps.executeUpdate();
        }
    }
    
    public List<Reservation> getReservationsWithRestaurantNameByCustomerId(int customerId) throws SQLException {
        String query = "SELECT r.id, r.customer_id, r.restaurant_id, res.name AS restaurant_name, r.reservation_date, r.reservation_type, r.number_of_guests, r.additional_facilities, r.status " +
                       "FROM reservations r " +
                       "JOIN restaurants res ON r.restaurant_id = res.id " +
                       "WHERE r.customer_id = ?";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getInt("id"));
                    reservation.setCustomerId(rs.getInt("customer_id"));
                    reservation.setRestaurantId(rs.getInt("restaurant_id"));
                    reservation.setRestaurantName(rs.getString("restaurant_name")); 
                    reservation.setReservationDateTime(rs.getTimestamp("reservation_date"));
                    reservation.setReservationType(rs.getString("reservation_type"));
                    reservation.setNumberOfGuests(rs.getInt("number_of_guests"));
                    reservation.setAdditionalRequests(rs.getString("additional_facilities"));
                    reservation.setStatus(rs.getString("status"));
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

}
