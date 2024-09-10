package abc_restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import abc_restaurant.model.Gallery;


public class GalleryDAO {
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

    public void createGallery(Gallery gallery) throws SQLException {
        String sql = "INSERT INTO gallery (name, image) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getImage());
            ps.executeUpdate();
        }
    }
    
    public void save(Gallery gallery) throws SQLException {
        String sql = "INSERT INTO gallery (name, image) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getImage());
            ps.executeUpdate();
        }
    }

    public List<Gallery> getAllGalleryImages() throws SQLException {
        String sql = "SELECT * FROM gallery";
        List<Gallery> galleryList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Gallery gallery = new Gallery();
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                gallery.setImage(rs.getString("image"));

                galleryList.add(gallery);
            }
        }

        return galleryList;
    }

    public void deleteGalleryImage(int id) throws SQLException {
        String sql = "DELETE FROM gallery WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Gallery getGalleryImageById(int id) throws SQLException {
        String sql = "SELECT * FROM gallery WHERE id = ?";
        Gallery gallery = null;

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gallery = new Gallery();
                    gallery.setId(rs.getInt("id"));
                    gallery.setName(rs.getString("name"));
                    gallery.setImage(rs.getString("image"));
                }
            }
        }

        return gallery;
    }

    public void updateGalleryImage(Gallery gallery) throws SQLException {
        String sql = "UPDATE gallery SET name = ?, image = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getImage());
            ps.setInt(3, gallery.getId());
            ps.executeUpdate();
        }
    }

}
