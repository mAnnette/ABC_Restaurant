<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="abc_restaurant.model.Reservation" %>
<%@ page import="abc_restaurant.model.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Confirmation - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container mt-4">
        <h2>Reservation Confirmation</h2>
        <p>Thank you for your reservation, <%= ((Customer) session.getAttribute("loggedInCustomer")).getFirstName() %>!</p>
        
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Reservation Details</h5>
                
                <% 
                    Reservation reservation = (Reservation) request.getAttribute("reservation");
                    if (reservation != null) {
                %>
                <p><strong>Reservation ID:</strong> <%= reservation.getId() %></p>
                <p><strong>Restaurant ID:</strong> <%= reservation.getRestaurantId() %></p>
                <p><strong>Reservation Type:</strong> <%= reservation.getReservationType() %></p>
                <p><strong>Date & Time:</strong> <%= reservation.getReservationDateTime() %></p>
                <p><strong>Number of Guests:</strong> <%= reservation.getNumberOfGuests() %></p>
                <p><strong>Additional Requests:</strong> <%= reservation.getAdditionalFacilities() %></p>
                <p><strong>Status:</strong> <%= reservation.getStatus() %></p>
                <% } else { %>
                    <p>Sorry, there was an error retrieving your reservation details.</p>
                <% } %>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>