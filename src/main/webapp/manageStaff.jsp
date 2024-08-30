<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Staff" %>
<%@ page import="abc_restaurant.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Staff - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Manage Staff</h2>
        <a href="staff?action=create" class="btn btn-primary mb-3">Add New Staff Member</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Position</th>
                    <th>Branch</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Check if staffList is empty -->
                <c:choose>
                    <c:when test="${empty staffList}">
                        <tr>
                            <td colspan="5">No staff members found</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <!-- Use JSTL to iterate over staffList and display staff members -->
                        <c:forEach var="staff" items="${staffList}">
                            <tr>
                                <td>${staff.id}</td>
                                <td>${staff.username}</td>
                                <td>${staff.position}</td>
                                <td>
                                    <c:forEach var="restaurant" items="${restaurantList}">
                                        <c:if test="${restaurant.id == staff.restaurantId}">
                                            ${restaurant.name}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="staff?action=edit&id=${staff.id}" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="staff?action=delete&id=${staff.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this staff member?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>