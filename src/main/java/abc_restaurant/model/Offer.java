package abc_restaurant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Offer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String offerName;
    private String description;
    private double discountPercentage;
    private Timestamp startDate;
    private Timestamp endDate;
    private String image;
    private int restaurantId;
    private boolean applyToAllRestaurants;

    public Offer() {
    }


    public Offer(int id, String offerName, String description, double discountPercentage, Timestamp startDate, Timestamp endDate, String image, int restaurantId, boolean applyToAllRestaurants) {
        this.id = id;
        this.offerName = offerName;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.restaurantId = restaurantId;
        this.applyToAllRestaurants = applyToAllRestaurants;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public boolean isApplyToAllRestaurants() {
        return applyToAllRestaurants;
    }

    public void setApplyToAllRestaurants(boolean applyToAllRestaurants) {
        this.applyToAllRestaurants = applyToAllRestaurants;
    }

}
