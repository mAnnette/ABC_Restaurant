<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Staff - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Create Staff Member</h2>
        <form action="staff" method="post">
            <input type="hidden" name="action" value="create">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="position" class="form-label">Position</label>
                <input type="text" class="form-control" id="position" name="position" required>
            </div>
            <div class="mb-3">
                <label for="restaurantId" class="form-label">Branch</label>
                <select class="form-control" id="restaurantId" name="restaurantId" required>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}">${restaurant.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Create Staff</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>