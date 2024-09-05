package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc_restaurant.dao.QueryDAO;
import abc_restaurant.model.Customer;
import abc_restaurant.model.Query;


/**
 * Servlet implementation class CustomerQueryServlet
 */
@WebServlet("/customer/query")
public class CustomerQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QueryDAO queryDAO = new QueryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reservationId = request.getParameter("reservationId");
        request.setAttribute("reservationId", reservationId);
        request.getRequestDispatcher("/submitQuery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

        if (loggedInCustomer != null) {
            int reservationId = Integer.parseInt(request.getParameter("reservationId"));
            String queryText = request.getParameter("queryText");
            String customerName = loggedInCustomer.getFirstName() + " " + loggedInCustomer.getLastName();

            Query query = new Query(0, reservationId, loggedInCustomer.getId(), customerName, queryText, null,
                                    null, null, 0, 0, "Pending");

            try {
                queryDAO.submitQuery(query);
                response.sendRedirect(request.getContextPath() + "/customer/reservations?success=Query submitted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/customer/reservations?error=Error submitting query.");
            }
        } else {
            response.sendRedirect("login.jsp?message=You must be logged in to submit a query.");
        }
    }

}
