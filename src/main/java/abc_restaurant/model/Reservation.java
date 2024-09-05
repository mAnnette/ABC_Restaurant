package abc_restaurant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 private int id;
	    private int customerId;
	    private int restaurantId;
	    private String reservationType;
	    private Timestamp reservationDateTime;
	    private int numberOfGuests;
	    private String additionalRequests;
	    private String status; 
	    private String customerName;
	    private String customerEmail;
	    private String restaurantName;

	
	    public Reservation() {}

	
	    public Reservation(int id, int customerId, int restaurantId, String reservationType, 
	            Timestamp reservationDateTime, int numberOfGuests, 
	            String additionalRequests, String status, String customerName, String customerEmail) {
	        this.id = id;
	        this.customerId = customerId;
	        this.restaurantId = restaurantId;
	        this.reservationType = reservationType;
	        this.reservationDateTime = reservationDateTime;
	        this.numberOfGuests = numberOfGuests;
	        this.additionalRequests = additionalRequests;
	        this.status = status;
	        this.customerName = customerName;  
	        this.customerEmail = customerEmail; 
	    }

	
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(int customerId) {
	        this.customerId = customerId;
	    }

	    public int getRestaurantId() {
	        return restaurantId;
	    }

	    public void setRestaurantId(int restaurantId) {
	        this.restaurantId = restaurantId;
	    }

	    public String getReservationType() {
	        return reservationType;
	    }

	    public void setReservationType(String reservationType) {
	        this.reservationType = reservationType;
	    }

	    public Timestamp getReservationDateTime() {
	        return reservationDateTime;
	    }

	    public void setReservationDateTime(Timestamp reservationDateTime) {
	        this.reservationDateTime = reservationDateTime;
	    }

	    public int getNumberOfGuests() {
	        return numberOfGuests;
	    }

	    public void setNumberOfGuests(int numberOfGuests) {
	        this.numberOfGuests = numberOfGuests;
	    }

	    public String getAdditionalRequests() {
	        return additionalRequests;
	    }

	    public void setAdditionalRequests(String additionalRequests) {
	        this.additionalRequests = additionalRequests;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	    
	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) { 
	        this.customerName = customerName;
	    }

	    public String getCustomerEmail() { 
	        return customerEmail;
	    }

	    public void setCustomerEmail(String customerEmail) { 
	        this.customerEmail = customerEmail;
	    }
	    
	    public String getRestaurantName() {
	        return restaurantName;
	    }

	    public void setRestaurantName(String restaurantName) {
	        this.restaurantName = restaurantName;
	    }

}
