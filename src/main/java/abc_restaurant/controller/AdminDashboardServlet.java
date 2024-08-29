package abc_restaurant.controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.AdminDashboardDAO;




@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminDashboardDAO adminDashboardDAO;

    @Override
    public void init() throws ServletException {
        // Initialize AdminDashboardDAO
        adminDashboardDAO = new AdminDashboardDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch the data from the DAO
            int totalReservations = adminDashboardDAO.getTotalReservations();
            int totalCustomers = adminDashboardDAO.getTotalCustomers();
            int activePromotions = adminDashboardDAO.getActivePromotions();
            int staffOnDuty = adminDashboardDAO.getStaffOnDuty();

            // Set attributes to forward to JSP
            request.setAttribute("totalReservations", totalReservations);
            request.setAttribute("totalCustomers", totalCustomers);
            request.setAttribute("activePromotions", activePromotions);
            request.setAttribute("staffOnDuty", staffOnDuty);

            // Forward to the JSP
            request.getRequestDispatcher("/admin_dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving dashboard data", e);
        }
    }

}