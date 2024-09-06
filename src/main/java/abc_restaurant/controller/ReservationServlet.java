package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc_restaurant.dao.FacilityDAO;
import abc_restaurant.dao.ReservationDAO;
import abc_restaurant.dao.RestaurantDAO;
import abc_restaurant.model.Customer;
import abc_restaurant.model.Facility;
import abc_restaurant.model.Reservation;
import abc_restaurant.model.Restaurant;




@WebServlet("/reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReservationDAO reservationDAO = new ReservationDAO();
    private RestaurantDAO restaurantDAO = new RestaurantDAO();
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedInCustomer") != null) {
            if ("viewReservations".equals(action)) {
                viewReservationsForCustomer(request, response);
            } else {
                showReservationForm(request, response);
            }
        } else {
            response.sendRedirect("login.jsp?message=You must be logged in to access this page.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("checkAvailability".equals(action)) {
            checkAvailability(request, response);
        } else if ("makeReservation".equals(action)) {
            makeReservation(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void showReservationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
            List<Facility> facilityList = FacilityDAO.getAllFacilities();
            request.setAttribute("restaurantList", restaurantList);
            request.setAttribute("facilityList", facilityList);
            request.getRequestDispatcher("/reservation.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An error occurred while fetching the restaurant list.");
        }
    }

    private void checkAvailability(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            String reservationType = request.getParameter("reservationType");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            Timestamp reservationDateTime = Timestamp.valueOf(date + " " + time + ":00");
            int numberOfGuests = Integer.parseInt(request.getParameter("guests"));

            boolean isAvailable = reservationDAO.isAvailable(restaurantId, reservationDateTime, reservationType, numberOfGuests);

            if (isAvailable) {
                request.setAttribute("availabilityMessage", "The selected time slot is available. Please confirm your reservation.");
                request.setAttribute("restaurantId", restaurantId);
                request.setAttribute("reservationType", reservationType);
                request.setAttribute("date", date);
                request.setAttribute("time", time);
                request.setAttribute("guests", numberOfGuests);
                
                request.setAttribute("isAvailable", true);
                
                showReservationForm(request, response);
            } else {
                request.setAttribute("availabilityMessage", "Sorry, this time slot is fully booked. Please choose another time or reduce the number of guests.");
                showReservationForm(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("availabilityMessage", "An error occurred while checking availability. Please try again.");
            showReservationForm(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("availabilityMessage", "Invalid date or time format. Please try again.");
            showReservationForm(request, response);
        }
    }

    private void makeReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer customer = (Customer) session.getAttribute("loggedInCustomer");

        if (customer != null) {
            try {
                int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                String reservationType = request.getParameter("reservationType");
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                Timestamp reservationDateTime = Timestamp.valueOf(date + " " + time + ":00");
                int numberOfGuests = Integer.parseInt(request.getParameter("guests"));
                String[] facilityIds = request.getParameterValues("facilityIds");

                String additionalFacilities = (facilityIds != null) ? String.join(", ", facilityIds) : "";

                Reservation reservation = new Reservation(0, customer.getId(), restaurantId, reservationType, reservationDateTime, numberOfGuests, additionalFacilities, "Pending", customer.getFirstName() + " " + customer.getLastName(), customer.getEmail());

                reservationDAO.createReservation(reservation);
                
                request.setAttribute("reservation", reservation);
                request.getRequestDispatcher("/reservationConfirmation.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("availabilityMessage", "An error occurred while making the reservation. Please try again.");
                showReservationForm(request, response);
            }
        } else {
            response.sendRedirect("login.jsp?message=You must be logged in to make a reservation.");
        }
    }

    private void viewReservationsForCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

        if (loggedInCustomer != null) {
            try {
                List<Reservation> reservations = reservationDAO.getReservationsByCustomerId(loggedInCustomer.getId());
                request.setAttribute("reservations", reservations);
                request.getRequestDispatcher("/viewReservationsCustomer.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp?message=An error occurred while retrieving your reservations.");
            }
        } else {
            response.sendRedirect("login.jsp?message=You must be logged in to view your reservations.");
        }
    }

}
