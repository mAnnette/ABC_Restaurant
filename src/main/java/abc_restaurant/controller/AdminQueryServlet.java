package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.QueryDAO;
import abc_restaurant.model.Query;



/**
 * Servlet implementation class AdminQueryServlet
 */
@WebServlet("/admin/viewQueries")
public class AdminQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QueryDAO queryDAO = new QueryDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Query> queries = queryDAO.getAllQueries();
            request.setAttribute("queries", queries);
            request.getRequestDispatcher("/adminViewQueries.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving queries.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int queryId = Integer.parseInt(request.getParameter("queryId"));
        String responseText = request.getParameter("responseText");

        try {
            queryDAO.respondToQuery(queryId, responseText, 0, false); // Admin responderId = 0 (no session)
            response.sendRedirect(request.getContextPath() + "/admin/viewQueries?success=Response submitted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/viewQueries?error=Error submitting response.");
        }
    }

}
