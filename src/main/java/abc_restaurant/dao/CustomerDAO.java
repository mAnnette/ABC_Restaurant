package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import abc_restaurant.model.Customer;



public class CustomerDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "Isuranga@123";

    
    public boolean registerCustomer(Customer customer, String password) throws SQLException {
        String sql = "INSERT INTO customers (firstName, lastName, email, address, contactNumber, password) VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getContactNumber());
            ps.setString(6, customer.getPassword());  // Store the password (ensure it's hashed in a real application)

            result = ps.executeUpdate();
        }
        return result > 0;
    }


    public Customer authenticate(String email, String password) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";
        Customer customer = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("contactNumber"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }
        return customer;
    }

}
