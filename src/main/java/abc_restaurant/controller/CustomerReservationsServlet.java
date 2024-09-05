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

import abc_restaurant.dao.ReservationDAO;
import abc_restaurant.model.Customer;
import abc_restaurant.model.Reservation;


/**
 * Servlet implementation class CustomerReservationsServlet
 */
@WebServlet("/customer/reservations")
public class CustomerReservationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReservationDAO reservationDAO = new ReservationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

        if (loggedInCustomer != null) {
            try {
                List<Reservation> reservations = reservationDAO.getReservationsWithRestaurantNameByCustomerId(loggedInCustomer.getId());
                request.setAttribute("reservations", reservations);
                request.getRequestDispatcher("/myReservations.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=An error occurred while retrieving your reservations.");
            }
        } else {
            response.sendRedirect("login.jsp?message=You must be logged in to view your reservations.");
        }
    }

}
