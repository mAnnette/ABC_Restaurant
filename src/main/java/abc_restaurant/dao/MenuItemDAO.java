package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Category;
import abc_restaurant.model.MenuItem;



public class MenuItemDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/abc_restaurant";
    private static final String USER = "root";
    private static final String PASSWORD = "Isuranga@123";

    public void createMenuItem(MenuItem menuItem) throws SQLException {
    	
    	 try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             throw new SQLException("MySQL Driver not found", e);
         }
        String sql = "INSERT INTO menu_items (name, price, description, category_id, image) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, menuItem.getName());
            ps.setBigDecimal(2, menuItem.getPrice());
            ps.setString(3, menuItem.getDescription());
            ps.setInt(4, menuItem.getCategoryId());
            ps.setString(5, menuItem.getImage());
            ps.executeUpdate();
        }
    }

    
    public List<MenuItem> getMenuItemsByRestaurant(int restaurantId) throws SQLException {
        String sql = "SELECT mi.*, c.name AS category_name FROM menu_items mi JOIN categories c ON mi.category_id = c.id WHERE c.restaurant_id = ?";
        List<MenuItem> menuItems = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setId(rs.getInt("id"));
                    menuItem.setName(rs.getString("name"));
                    menuItem.setPrice(rs.getBigDecimal("price"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItem.setCategoryId(rs.getInt("category_id"));
                    menuItem.setCategoryName(rs.getString("category_name"));
                    menuItem.setImage(rs.getString("image"));
                    menuItems.add(menuItem);
                }
            }
        }

        return menuItems;
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

    
    public MenuItem getMenuItemById(int id) throws SQLException {
        String sql = "SELECT * FROM menu_items WHERE id = ?";
        MenuItem menuItem = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    menuItem = new MenuItem();
                    menuItem.setId(rs.getInt("id"));
                    menuItem.setName(rs.getString("name"));
                    menuItem.setPrice(rs.getBigDecimal("price"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItem.setCategoryId(rs.getInt("category_id"));
                    menuItem.setImage(rs.getString("image")); 
                }
            }
        }

        return menuItem;
    }

    
    public void updateMenuItem(MenuItem menuItem) throws SQLException {
        String sql = "UPDATE menu_items SET name = ?, price = ?, description = ?, category_id = ?, image = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, menuItem.getName());
            ps.setBigDecimal(2, menuItem.getPrice());
            ps.setString(3, menuItem.getDescription());
            ps.setInt(4, menuItem.getCategoryId());
            ps.setString(5, menuItem.getImage());
            ps.setInt(6, menuItem.getId());
            ps.executeUpdate();
        }
    }

    
    public void deleteMenuItem(int id) throws SQLException {
        String sql = "DELETE FROM menu_items WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    
    public List<MenuItem> getAllMenuItems() throws SQLException {
        String sql = "SELECT mi.*, c.name AS category_name FROM menu_items mi JOIN categories c ON mi.category_id = c.id";
        List<MenuItem> menuItems = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt("id"));
                menuItem.setName(rs.getString("name"));
                menuItem.setPrice(rs.getBigDecimal("price"));
                menuItem.setDescription(rs.getString("description"));
                menuItem.setCategoryId(rs.getInt("category_id"));
                menuItem.setCategoryName(rs.getString("category_name"));
                menuItem.setImage(rs.getString("image"));
                menuItems.add(menuItem);
            }
        }

        return menuItems;
    }
    
    
    public List<MenuItem> getAllMenu() throws SQLException {
        String sql = "SELECT * FROM menu_items";
        List<MenuItem> menuItems = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MenuItem menuItem = new MenuItem(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getString("description"),
                    rs.getInt("category_id"),
                    rs.getString("image")
                );
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

}
