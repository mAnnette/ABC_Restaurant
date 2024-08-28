package abc_restaurant.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.CustomerDAO;
import abc_restaurant.model.Customer;



@WebServlet("/Customer/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

   
        CustomerDAO customerDAO = new CustomerDAO();

        try {
            Customer customer = customerDAO.authenticate(email, password);

            if (customer != null) {
                request.getSession().setAttribute("loggedInCustomer", customer);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp?error=Email or password is incorrect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=An error occurred. Please try again.");
        }
    }


}
