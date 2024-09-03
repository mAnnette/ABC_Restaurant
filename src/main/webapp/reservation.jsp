<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Make a Reservation - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <div class="container mt-4">
        <h2>Make a Reservation</h2>

        <c:if test="${not empty availabilityMessage}">
            <div class="alert alert-info mt-3">
                ${availabilityMessage}
            </div>
        </c:if>

        <form action="reservation" method="post">
            <input type="hidden" name="action" value="checkAvailability">
            <div class="form-group">
                <label for="restaurant">Select Restaurant:</label>
                <select class="form-control" id="restaurant" name="restaurantId" required>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}">${restaurant.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group mt-3">
                <label for="date">Reservation Date:</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <div class="form-group mt-3">
                <label for="time">Reservation Time:</label>
                <input type="time" class="form-control" id="time" name="time" required>
            </div>
            <div class="form-group mt-3">
                <label for="reservationType">Reservation Type:</label>
                <select class="form-control" id="reservationType" name="reservationType" required>
                    <option value="Dine-In">Dine-In</option>
                    <option value="Takeaway">Takeaway</option>
                    <option value="Delivery">Delivery</option>
                </select>
            </div>
            <div class="form-group mt-3">
                <label for="guests">Number of Guests:</label>
                <input type="number" class="form-control" id="guests" name="guests" min="1" required>
            </div>
            <div class="form-group mt-3">
                <label for="facilities">Additional Facilities/Services:</label>
                <c:forEach var="facility" items="${facilityList}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="facility${facility.id}" name="facilityIds" value="${facility.name}">
                        <label class="form-check-label" for="facility${facility.id}">
                            ${facility.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
            <button type="submit" class="btn btn-secondary mt-3">Check Availability</button>
        </form>
    </div>

    <jsp:include page="footer.jsp" />

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>