package abc_restaurant.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Query implements Serializable  {


	private static final long serialVersionUID = 1L;
	
	 private int id;
	    private int reservationId;
	    private int customerId;
	    private String customerName;
	    private String queryText;
	    private Timestamp queryDate;
	    private String responseText;
	    private Timestamp responseDate;
	    private int staffId;
	    private int adminId;
	    private String status;

	
	    public Query(int id, int reservationId, int customerId, String customerName, String queryText, Timestamp queryDate,
	                 String responseText, Timestamp responseDate, int staffId, int adminId, String status) {
	        this.id = id;
	        this.reservationId = reservationId;
	        this.customerId = customerId;
	        this.customerName = customerName;
	        this.queryText = queryText;
	        this.queryDate = queryDate;
	        this.responseText = responseText;
	        this.responseDate = responseDate;
	        this.staffId = staffId;
	        this.adminId = adminId;
	        this.status = status;
	    }

	    // Getters and Setters

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getReservationId() {
	        return reservationId;
	    }

	    public void setReservationId(int reservationId) {
	        this.reservationId = reservationId;
	    }

	    public int getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(int customerId) {
	        this.customerId = customerId;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public String getQueryText() {
	        return queryText;
	    }

	    public void setQueryText(String queryText) {
	        this.queryText = queryText;
	    }

	    public Timestamp getQueryDate() {
	        return queryDate;
	    }

	    public void setQueryDate(Timestamp queryDate) {
	        this.queryDate = queryDate;
	    }

	    public String getResponseText() {
	        return responseText;
	    }

	    public void setResponseText(String responseText) {
	        this.responseText = responseText;
	    }

	    public Timestamp getResponseDate() {
	        return responseDate;
	    }

	    public void setResponseDate(Timestamp responseDate) {
	        this.responseDate = responseDate;
	    }

	    public int getStaffId() {
	        return staffId;
	    }

	    public void setStaffId(int staffId) {
	        this.staffId = staffId;
	    }

	    public int getAdminId() {
	        return adminId;
	    }

	    public void setAdminId(int adminId) {
	        this.adminId = adminId;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

}
