package abc_restaurant.model;

import java.io.Serializable;

public class Category implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private int restaurantId;

    public Category() {}

    public Category(int id, String name, int restaurantId) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

}
