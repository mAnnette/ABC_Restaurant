package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Offer;



public class OfferDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "Isuranga@123";


    public void createOffer(Offer offer) throws SQLException {
        String sql = "INSERT INTO offers (offer_name, description, discount_percentage, start_date, end_date, image, restaurant_id, apply_to_all_restaurants) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, offer.getOfferName());
            ps.setString(2, offer.getDescription());
            ps.setDouble(3, offer.getDiscountPercentage());
            ps.setTimestamp(4, offer.getStartDate());
            ps.setTimestamp(5, offer.getEndDate());
            ps.setString(6, offer.getImage());
            if (offer.isApplyToAllRestaurants()) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, offer.getRestaurantId());
            }
            ps.setBoolean(8, offer.isApplyToAllRestaurants());

            ps.executeUpdate();
        }
    }


    public List<Offer> getOffersForRestaurant(int restaurantId) throws SQLException {
        String sql = "SELECT * FROM offers WHERE apply_to_all_restaurants = TRUE OR restaurant_id = ?";
        List<Offer> offers = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Offer offer = new Offer();
                    offer.setId(rs.getInt("id"));
                    offer.setOfferName(rs.getString("offer_name"));
                    offer.setDescription(rs.getString("description"));
                    offer.setDiscountPercentage(rs.getDouble("discount_percentage"));
                    offer.setStartDate(rs.getTimestamp("start_date"));
                    offer.setEndDate(rs.getTimestamp("end_date"));
                    offer.setImage(rs.getString("image"));
                    offer.setRestaurantId(rs.getInt("restaurant_id"));
                    offer.setApplyToAllRestaurants(rs.getBoolean("apply_to_all_restaurants"));

                    offers.add(offer);
                }
            }
        }

        return offers;
    }


    public void updateOffer(Offer offer) throws SQLException {
        String sql = "UPDATE offers SET offer_name = ?, description = ?, discount_percentage = ?, start_date = ?, end_date = ?, image = ?, restaurant_id = ?, apply_to_all_restaurants = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, offer.getOfferName());
            ps.setString(2, offer.getDescription());
            ps.setDouble(3, offer.getDiscountPercentage());
            ps.setTimestamp(4, offer.getStartDate());
            ps.setTimestamp(5, offer.getEndDate());
            ps.setString(6, offer.getImage());
            if (offer.isApplyToAllRestaurants()) {
                ps.setNull(7, Types.INTEGER);
            } else {
                ps.setInt(7, offer.getRestaurantId());
            }
            ps.setBoolean(8, offer.isApplyToAllRestaurants());
            ps.setInt(9, offer.getId());

            ps.executeUpdate();
        }
    }

    public void deleteOffer(int offerId) throws SQLException {
        String sql = "DELETE FROM offers WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, offerId);
            ps.executeUpdate();
        }
    }
    
    public List<Offer> getAllOffers() throws SQLException {
        String sql = "SELECT * FROM offers";
        List<Offer> offers = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Offer offer = new Offer();
                offer.setId(rs.getInt("id"));
                offer.setOfferName(rs.getString("offer_name"));
                offer.setDescription(rs.getString("description"));
                offer.setDiscountPercentage(rs.getDouble("discount_percentage"));
                offer.setStartDate(rs.getTimestamp("start_date"));
                offer.setEndDate(rs.getTimestamp("end_date"));
                offer.setImage(rs.getString("image"));
                offer.setRestaurantId(rs.getInt("restaurant_id"));
                offer.setApplyToAllRestaurants(rs.getBoolean("apply_to_all_restaurants"));

                offers.add(offer);
            }
        }

        return offers;
    }
    
    public Offer getOfferById(int id) throws SQLException {
        String sql = "SELECT * FROM offers WHERE id = ?";
        Offer offer = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    offer = new Offer();
                    offer.setId(rs.getInt("id"));
                    offer.setOfferName(rs.getString("offer_name"));
                    offer.setDescription(rs.getString("description"));
                    offer.setDiscountPercentage(rs.getDouble("discount_percentage"));
                    offer.setStartDate(rs.getTimestamp("start_date"));
                    offer.setEndDate(rs.getTimestamp("end_date"));
                    offer.setImage(rs.getString("image"));
                    offer.setRestaurantId(rs.getInt("restaurant_id"));
                    offer.setApplyToAllRestaurants(rs.getBoolean("apply_to_all_restaurants"));
                }
            }
        }

        return offer;
    }

}
