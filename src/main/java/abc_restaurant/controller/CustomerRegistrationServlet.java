package abc_restaurant.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.CustomerDAO;
import abc_restaurant.model.Customer;




@WebServlet("/Customer/register")
public class CustomerRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String contactNumber = request.getParameter("contactNumber");
	        String password = request.getParameter("password");  

	       
	        Customer customer = new Customer();
	        customer.setFirstName(firstName);
	        customer.setLastName(lastName);
	        customer.setEmail(email);
	        customer.setAddress(address);
	        customer.setContactNumber(contactNumber);
	        customer.setPassword(password);

	   
	        CustomerDAO customerDAO = new CustomerDAO();
	        
	        try {
	            // Register customer
	            boolean isRegistered = customerDAO.registerCustomer(customer, password);
	            
	            if (isRegistered) {
	                // Redirect to success page
	                response.sendRedirect(request.getContextPath() + "/login.jsp");
	            } else {
	                // Redirect to registration page with error
	                response.sendRedirect(request.getContextPath() + "/registration.jsp?error=Registration failed. Please try again.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect(request.getContextPath() + "/registration.jsp?error=An error occurred. Please try again.");
	        }
	    }

}
