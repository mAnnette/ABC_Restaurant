<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Facility" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Facilities - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Manage Facilities</h2>
        <a href="facility?action=create" class="btn btn-primary mb-3">Add New Facility</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                     <th>Image</th>
                    <th>Location</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty facilityList}">
                        <tr>
                            <td colspan="5">No facilities found</td>
                        </tr>
                    </c:when>
                    <c:otherwise>                      
                        <c:forEach var="facility" items="${facilityList}">
                            <tr>
                                <td>${facility.id}</td>
                                <td>${facility.name}</td>
                                <td>${facility.description}</td>
                                <td>${facility.image}</td>
                                <td>${facility.location}</td>
                                <td>
                                    <a href="facility?action=edit&id=${facility.id}" class="btn btn-warning btn-sm">Edit</a>
                                    <a href="facility?action=delete&id=${facility.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this facility?');">Delete</a>
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