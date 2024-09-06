<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Make a Reservation - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <!-- Include header -->
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
                        <option value="${restaurant.id}" 
                                <c:if test="${restaurant.id == param.restaurantId}">selected</c:if>>
                            ${restaurant.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

         
            <div class="form-group mt-3">
                <label for="date">Reservation Date:</label>
                <input type="date" class="form-control" id="date" name="date" value="${param.date}" required>
            </div>

    
            <div class="form-group mt-3">
                <label for="time">Reservation Time:</label>
                <input type="time" class="form-control" id="time" name="time" value="${param.time}" required>
            </div>

          
            <div class="form-group mt-3">
                <label for="reservationType">Reservation Type:</label>
                <select class="form-control" id="reservationType" name="reservationType" required>
                    <option value="Dine-In" <c:if test="${param.reservationType == 'Dine-In'}">selected</c:if>>Dine-In</option>
                    <option value="Takeaway" <c:if test="${param.reservationType == 'Takeaway'}">selected</c:if>>Takeaway</option>
                    <option value="Delivery" <c:if test="${param.reservationType == 'Delivery'}">selected</c:if>>Delivery</option>
                </select>
            </div>

       
            <div class="form-group mt-3">
                <label for="guests">Number of Guests:</label>
                <input type="number" class="form-control" id="guests" name="guests" value="${param.guests}" min="1" required>
            </div>

            
            <div class="form-group mt-3">
                <label for="facilities">Additional Facilities/Services:</label>
                <c:forEach var="facility" items="${facilityList}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="facility${facility.id}" name="facilityIds" value="${facility.name}"
                            <c:if test="${fn:contains(param.facilityIds, facility.name)}">checked</c:if>>
                        <label class="form-check-label" for="facility${facility.id}">
                            ${facility.name}
                        </label>
                    </div>
                </c:forEach>
            </div>

       
            <button type="submit" class="btn btn-secondary mt-3">Check Availability</button>
        </form>


        <c:if test="${isAvailable}">
            <div class="alert alert-success mt-3">
                ${availabilityMessage}
            </div>
         
            <form action="reservation" method="post">
                <input type="hidden" name="action" value="makeReservation">
                <input type="hidden" name="restaurantId" value="${param.restaurantId}">
                <input type="hidden" name="reservationType" value="${param.reservationType}">
                <input type="hidden" name="date" value="${param.date}">
                <input type="hidden" name="time" value="${param.time}">
                <input type="hidden" name="guests" value="${param.guests}">
             
                <button type="submit" class="btn btn-primary mt-3">Confirm Reservation</button>
            </form>
        </c:if>
    </div>


    <jsp:include page="footer.jsp" />

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>