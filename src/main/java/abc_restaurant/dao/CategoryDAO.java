package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Category;



public class CategoryDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "Isuranga@123";

    public void createCategory(Category category) throws SQLException {
        String sql = "INSERT INTO categories (name, restaurant_id) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getRestaurantId());
            ps.executeUpdate();
        }
    }

    public List<Category> getCategoriesByRestaurant(int restaurantId) throws SQLException {
        String sql = "SELECT * FROM categories WHERE restaurant_id = ?";
        List<Category> categories = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setRestaurantId(rs.getInt("restaurant_id"));
                    categories.add(category);
                }
            }
        }

        return categories;
    }

    public Category getCategoryById(int categoryId, int restaurantId) throws SQLException {
        String sql = "SELECT * FROM categories WHERE id = ? AND restaurant_id = ?";
        Category category = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setRestaurantId(rs.getInt("restaurant_id"));
                }
            }
        }

        return category;
    }

    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE categories SET name = ? WHERE id = ? AND restaurant_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.setInt(3, category.getRestaurantId());
            ps.executeUpdate();
        }
    }

    public void deleteCategory(int categoryId, int restaurantId) throws SQLException {
        String sql = "DELETE FROM categories WHERE id = ? AND restaurant_id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, restaurantId);
            ps.executeUpdate();
        }
    }
    
    public List<Category> getAllCategories() throws SQLException {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setRestaurantId(rs.getInt("restaurant_id"));
                categories.add(category);
            }
        }

        return categories;
    }
    
    
    public List<Category> getAllCategory() throws SQLException {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("restaurant_id")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  
        }
        return categories;
    }

}
