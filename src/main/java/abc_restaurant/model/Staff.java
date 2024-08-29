package abc_restaurant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 private int id;
	    private String username;
	    private String password;
	    private String position;
	    private Timestamp createdAt;
	    private int restaurantId;

	    // Default constructor
	    public Staff() {}

	    // Parameterized constructor
	    public Staff(int id, String username, String password, String position, Timestamp createdAt, int restaurantId) {
	        this.id = id;
	        this.username = username;
	        this.password = password;
	        this.position = position;
	        this.createdAt = createdAt;
	        this.restaurantId = restaurantId;
	    }

	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public Timestamp getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Timestamp createdAt) {
	        this.createdAt = createdAt;
	    }
	    
	    public int getRestaurantId() {
	        return restaurantId;
	    }
	    
	    public void setRestaurantId(int restaurantId) {
	        this.restaurantId = restaurantId;
	    }

	    @Override
	    public String toString() {
	        return "Staff{" +
	                "id=" + id +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", position='" + position + '\'' +
	                ", createdAt=" + createdAt +
	                ", restaurantId=" + restaurantId +
	                '}';
	    }

}
