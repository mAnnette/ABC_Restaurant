<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Staff" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Staff - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Staff Member</h2>
        <form action="staff" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="staffId" value="${staff.id}">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="${staff.username}" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="${staff.password}" required>
            </div>
            <div class="mb-3">
                <label for="position" class="form-label">Position</label>
                <input type="text" class="form-control" id="position" name="position" value="${staff.position}" required>
            </div>
            <div class="mb-3">
                <label for="restaurantId" class="form-label">Branch</label>
                <select class="form-control" id="restaurantId" name="restaurantId" required>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}" 
                                <c:if test="${restaurant.id == staff.restaurantId}">selected</c:if>>
                            ${restaurant.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update Staff</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>