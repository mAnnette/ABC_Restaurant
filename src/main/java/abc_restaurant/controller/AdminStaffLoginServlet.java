package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.RestaurantDAO;
import abc_restaurant.dao.StaffDAO;
import abc_restaurant.model.Restaurant;




@WebServlet("/AdminStaffLoginServlet")
public class AdminStaffLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StaffDAO staffDAO = new StaffDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");

        if ("staff".equals(role)) {
            RestaurantDAO restaurantDAO = new RestaurantDAO();
            List<Restaurant> restaurantList = null;

            try {
                restaurantList = restaurantDAO.getAllRestaurants();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("restaurantList", restaurantList);
        }

        request.getRequestDispatcher("admin&staffLogin.jsp").forward(request, response);
    }

    
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String role = request.getParameter("role");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    if ("admin".equals(role) && "admin".equals(username) && "admin".equals(password)) {
	        response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
	    } else if ("staff".equals(role)) {
	        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
	        boolean isValidStaff = staffDAO.checkStaffCredentials(username, password, restaurantId);
	        if (isValidStaff) {
	            request.getSession().setAttribute("loggedInStaff", username);
	            request.getSession().setAttribute("restaurantId", restaurantId);
	            response.sendRedirect(request.getContextPath() + "/staffDashboard.jsp");
	        } else {
	            response.sendRedirect(request.getContextPath() + "/admin&staffLogin.jsp?error=Invalid credentials");
	        }
	    } else {
	        response.sendRedirect(request.getContextPath() + "/admin&staffLogin.jsp?error=Invalid role or credentials");
	    }
	}
	
	

}
