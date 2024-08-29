<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - ABC Restaurant</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">ABC Restaurant</a>
    </nav>

 
    <div class="container mt-4">
        <h2>Login</h2>
        <form action="AdminStaffLoginServlet" method="get">
            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-control" id="role" name="role" required onchange="this.form.submit()">
                    <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="staff" ${param.role == 'staff' ? 'selected' : ''}>Staff</option>
                </select>
            </div>
        </form>

        <form action="AdminStaffLoginServlet" method="post">
            <input type="hidden" name="role" value="${param.role}">

            
            <c:if test="${param.role == 'staff'}">
                <div class="form-group">
                    <label for="restaurantId">Select Branch:</label>
                    <select class="form-control" id="restaurantId" name="restaurantId" required>
                        <c:forEach var="restaurant" items="${restaurantList}">
                            <option value="${restaurant.id}">${restaurant.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>