<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Offer</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Create Offer</h2>
        <form action="offers" method="post">
            <input type="hidden" name="action" value="create">
            <div class="form-group">
                <label for="offerName">Offer Name:</label>
                <input type="text" class="form-control" id="offerName" name="offerName" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="discountPercentage">Discount Percentage:</label>
                <input type="number" class="form-control" id="discountPercentage" name="discountPercentage" required>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="text" class="form-control" id="image" name="image" required>
            </div>
           <div class="mb-3">
                <label for="restaurantId" class="form-label">Restaurant</label>
                <select class="form-control" id="restaurantId" name="restaurantId" required>
                <option value="">--Select Restaurant--</option>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}">${restaurant.name}</option>
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
            <button type="submit" class="btn btn-primary mt-3">Create Offer</button>
        </form>
        
	        
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>