package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc_restaurant.dao.ReservationDAO;
import abc_restaurant.model.Reservation;
import abc_restaurant.util.EmailUtil;



@WebServlet("/staff/reservations")
public class StaffReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReservationDAO reservationDAO = new ReservationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("restaurantId") != null) {
            int restaurantId = (Integer) session.getAttribute("restaurantId");

            try {
                List<Reservation> reservations = reservationDAO.getReservationsByRestaurantId(restaurantId);
                request.setAttribute("reservations", reservations);
                request.getRequestDispatcher("/viewReservations.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("staffDashboard.jsp?error=Error retrieving reservations");
            }
        } else {
            response.sendRedirect("admin&staffLogin.jsp?error=Please login as staff to view this page");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));

        try {
            if ("confirmReservation".equals(action)) {
                reservationDAO.updateReservationStatus(reservationId, "Confirmed");

                Reservation reservation = ReservationDAO.getReservationById(reservationId);

                if (reservation != null) {
                    String subject = "Your Reservation is Confirmed!";
                    String body = buildConfirmationEmailBody(reservation);

                    System.out.println("Attempting to send confirmation email to: " + reservation.getCustomerEmail());

                    EmailUtil.sendEmail(reservation.getCustomerEmail(), subject, body);

                   
                    System.out.println("Confirmation email sent to: " + reservation.getCustomerEmail());
                }

              
                response.sendRedirect(request.getContextPath() + "/staff/reservations?success=Reservation confirmed and email sent.");
            } else if ("cancelReservation".equals(action)) {
                reservationDAO.updateReservationStatus(reservationId, "Cancelled");

                Reservation reservation = ReservationDAO.getReservationById(reservationId);

                if (reservation != null) {
                    String subject = "Your Reservation has been Cancelled";
                    String body = buildCancellationEmailBody(reservation);
                    System.out.println("Attempting to send cancellation email to: " + reservation.getCustomerEmail());
                    EmailUtil.sendEmail(reservation.getCustomerEmail(), subject, body);
                    System.out.println("Cancellation email sent to: " + reservation.getCustomerEmail());
                }
                response.sendRedirect(request.getContextPath() + "/staff/reservations?success=Reservation cancelled and email sent.");
            } else {
                response.sendRedirect(request.getContextPath() + "/staff/reservations?error=Invalid action.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/staff/reservations?error=Database error occurred.");
        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/staff/reservations?error=Failed to send email.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/staff/reservations?error=An unexpected error occurred.");
        }
    }
    

    private String buildConfirmationEmailBody(Reservation reservation) {
        return "Dear " + reservation.getCustomerName() + ",\n\n" +
               "Your reservation at ABC Restaurant has been confirmed.\n\n" +
               "Reservation Details:\n" +
               "Reservation ID: " + reservation.getId() + "\n" +
               "Date & Time: " + reservation.getReservationDateTime() + "\n" +
               "Type: " + reservation.getReservationType() + "\n" +
               "Number of Guests: " + reservation.getNumberOfGuests() + "\n" +
               "Additional Requests: " + reservation.getAdditionalRequests() + "\n\n" +
               "We look forward to serving you.\n\n" +
               "Best Regards,\n" +
               "ABC Restaurant";
    }


    private String buildCancellationEmailBody(Reservation reservation) {
        return "Dear " + reservation.getCustomerName() + ",\n\n" +
               "We regret to inform you that your reservation at ABC Restaurant has been cancelled.\n\n" +
               "Reservation Details:\n" +
               "Reservation ID: " + reservation.getId() + "\n" +
               "Date & Time: " + reservation.getReservationDateTime() + "\n" +
               "Type: " + reservation.getReservationType() + "\n" +
               "Number of Guests: " + reservation.getNumberOfGuests() + "\n" +
               "Additional Requests: " + reservation.getAdditionalRequests() + "\n\n" +
               "If you have any questions, please contact us.\n\n" +
               "Best Regards,\n" +
               "ABC Restaurant";
    }

}
