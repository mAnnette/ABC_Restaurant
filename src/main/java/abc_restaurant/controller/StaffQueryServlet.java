package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc_restaurant.dao.QueryDAO;
import abc_restaurant.dao.StaffDAO;
import abc_restaurant.model.Query;
import abc_restaurant.model.Staff;



/**
 * Servlet implementation class StaffQueryServlet
 */
@WebServlet("/staff/viewQueries")
public class StaffQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QueryDAO queryDAO = new QueryDAO();
	private StaffDAO staffDAO = new StaffDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (restaurantId != null) {
            try {
                List<Query> queries = queryDAO.getQueriesByRestaurantId(restaurantId);
                request.setAttribute("queries", queries);
                request.getRequestDispatcher("/staffViewQueries.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=Error retrieving queries.");
            }
        } else {
            response.sendRedirect("admin&staffLogin.jsp?error=You must be logged in to view queries.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("loggedInStaff");

        if (username != null) {
            try {
                Staff loggedInStaff = staffDAO.getStaffByUsername(username); // Retrieve Staff object using username
                int queryId = Integer.parseInt(request.getParameter("queryId"));
                String responseText = request.getParameter("responseText");

                queryDAO.respondToQuery(queryId, responseText, loggedInStaff.getId(), true);
                response.sendRedirect(request.getContextPath() + "/staff/viewQueries?success=Response submitted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/staff/viewQueries?error=Error submitting response.");
            }
        } else {
            response.sendRedirect("admin&staffLogin.jsp?error=You must be logged in to submit a response.");
        }
    }

}
