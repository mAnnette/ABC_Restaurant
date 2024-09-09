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

import abc_restaurant.dao.ReservationDAO;
import abc_restaurant.model.Reservation;



/**
 * Servlet implementation class GenerateReportServlet
 */
@WebServlet("/admin/generateReport")
public class GenerateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReservationDAO reservationDAO = new ReservationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String status = request.getParameter("status");

            Timestamp startDate = Timestamp.valueOf(startDateStr + " 00:00:00");
            Timestamp endDate = Timestamp.valueOf(endDateStr + " 23:59:59");

            List<Reservation> reservations = reservationDAO.generateReservationReport(startDate, endDate, status);

            request.setAttribute("reservations", reservations);
            request.getRequestDispatcher("/viewReport.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error generating the report.");
        }
    }

}
