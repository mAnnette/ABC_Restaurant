<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Service" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Services - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Manage Services</h2>
        <a href="service?action=create" class="btn btn-primary mb-3">Add New Service</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Check if serviceList is empty -->
                <c:choose>
                    <c:when test="${empty serviceList}">
                        <tr>
                            <td colspan="5">No services found</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <!-- Use JSTL to iterate over serviceList and display services -->
                        <c:forEach var="service" items="${serviceList}">
                            <tr>
                                <td>${service.serviceId}</td>
                                <td>${service.serviceName}</td>
                                <td>${service.description}</td>
                                <td><img src="${service.image}" alt="${service.serviceName}" width="100"></td>
                                <td>
                                    <a href="service?action=edit&id=${service.serviceId}" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="service?action=delete&id=${service.serviceId}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this service?');">Delete</a>
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