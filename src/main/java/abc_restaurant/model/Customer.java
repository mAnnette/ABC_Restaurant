package abc_restaurant.model;

import java.io.Serializable;

public class Customer implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private int id; 
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String contactNumber;
    private String password;
    
    
    public Customer(int id, String firstName, String lastName, String email, String address, String contactNumber, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.password = password;
    }

  

	public Customer() {
		
	}




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    
    public String getPassword() {
		return password;
	}
    
    public void setPassword(String password) {
		this.password = password;
	}

}
