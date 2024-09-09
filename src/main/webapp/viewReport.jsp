<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reservation Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Reservation Report</h2>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Customer Email</th>
                    <th>Restaurant ID</th>
                    <th>Reservation Type</th>
                    <th>Number of Guests</th>
                    <th>Status</th>
                  
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reservation" items="${reservations}">
                    <tr>
                        <td>${reservation.id}</td>
                        <td>${reservation.customerName}</td>
                        <td>${reservation.customerEmail}</td>
                        <td>${reservation.restaurantId}</td>
                        <td>${reservation.reservationType}</td>
                        <td>${reservation.numberOfGuests}</td>
                        <td>${reservation.status}</td>
                      
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>