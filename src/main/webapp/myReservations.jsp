<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Reservation" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Reservations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="header.jsp" />
    <div class="container mt-4">
        <h2>My Reservations</h2>
        
        <!-- Reservation Details -->
        <c:if test="${not empty reservations}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Reservation ID</th>
                        <th>Restaurant</th>
                        <th>Date & Time</th>
                        <th>Type</th>
                        <th>Guests</th>
                        <th>Additional Requests</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reservation" items="${reservations}">
                        <tr>
                            <td>${reservation.id}</td>
                            <td>${reservation.restaurantName}</td> <!-- Displaying restaurant name -->
                            <td>${reservation.reservationDateTime}</td>
                            <td>${reservation.reservationType}</td>
                            <td>${reservation.numberOfGuests}</td>
                            <td>${reservation.additionalRequests}</td>
                            <td>${reservation.status}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/customer/query?reservationId=${reservation.id}" class="btn btn-primary btn-sm">Submit Query</a>
                            </td> <!-- Submit Query button -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty reservations}">
            <p>You have no reservations at this time.</p>
        </c:if>
    </div>

    <!-- Include footer -->
    <jsp:include page="footer.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>