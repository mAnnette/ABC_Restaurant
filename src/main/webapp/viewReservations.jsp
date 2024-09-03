<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Reservations - Staff Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Reservations</h2>
        <c:if test="${not empty reservations}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Customer Email</th>
                        <th>Reservation Date</th>
                        <th>Type</th>
                        <th>Guests</th>
                        <th>Additional Facilities</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reservation" items="${reservations}">
                        <tr>
                            <td>${reservation.customerId}</td>
                            <td>${reservation.customerName}</td>
                            <td>${reservation.customerEmail}</td> 
                            <td>${reservation.reservationDateTime}</td>
                            <td>${reservation.reservationType}</td>
                            <td>${reservation.numberOfGuests}</td>
                            <td>${reservation.additionalRequests}</td>
                            <td>${reservation.status}</td>
                            <td>
                                <c:if test="${reservation.status == 'Pending'}">
				                <form action="${pageContext.request.contextPath}/staff/reservations" method="post" style="display:inline;">
				                    <input type="hidden" name="action" value="confirmReservation">
				                    <input type="hidden" name="reservationId" value="${reservation.id}">
				                    <button type="submit" class="btn btn-success btn-sm">Confirm</button>
				                </form>
				                <form action="${pageContext.request.contextPath}/staff/reservations" method="post" style="display:inline;">
				                    <input type="hidden" name="action" value="cancelReservation">
				                    <input type="hidden" name="reservationId" value="${reservation.id}">
				                    <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
				                </form>
				            </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty reservations}">
            <p>No reservations found for your restaurant.</p>
        </c:if>
    </div>
</body>
</html>