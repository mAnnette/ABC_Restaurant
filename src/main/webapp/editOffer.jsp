<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Offer</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Offer</h2>
        <form action="offers" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="offerId" value="${offer.id}">
            <div class="form-group">
                <label for="offerName">Offer Name:</label>
                <input type="text" class="form-control" id="offerName" name="offerName" value="${offer.offerName}" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required>${offer.description}</textarea>
            </div>
            <div class="form-group">
                <label for="discountPercentage">Discount Percentage:</label>
                <input type="number" class="form-control" id="discountPercentage" name="discountPercentage" value="${offer.discountPercentage}" required>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="datetime-local" class="form-control" id="startDate" name="startDate" value="${offer.startDate}" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="datetime-local" class="form-control" id="endDate" name="endDate" value="${offer.endDate}" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="text" class="form-control" id="image" name="image" value="${offer.image}" required>
            </div>
            <div class="form-group">
                <label for="restaurantId">Restaurant:</label>
                <select class="form-control" id="restaurantId" name="restaurantId">
                    <option value="">--Select Restaurant--</option>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}" <c:if test="${offer.restaurantId == restaurant.id}">selected</c:if>>${restaurant.name}</option>
                    </c:forEach>
                </select>
            </div>
             <div class="form-check">
                <input type="checkbox" class="form-check-input" id="applyToAllRestaurants" name="applyToAllRestaurants" value="true">
                <label class="form-check-label" for="applyToAllRestaurants">Apply to all restaurants</label>
            </div>
            <c:if test="${param.applyToAllRestaurants eq 'true'}">
                <script>
                    document.getElementById('restaurantId').disabled = true;
                </script>
            </c:if>
            <button type="submit" class="btn btn-primary mt-3">Update Offer</button>
        </form>
    </div>
</body>
</html>