<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Service" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Service - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Service</h2>
        <form action="service" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="serviceId" value="${service.serviceId}">
            <div class="form-group">
                <label for="serviceName">Service Name:</label>
                <input type="text" class="form-control" id="serviceName" name="serviceName" value="${service.serviceName}" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required>${service.description}</textarea>
            </div>
            <div class="form-group">
                <label for="image">Image (URL or path to the image):</label>
                <textarea class="form-control" id="image" name="image" required>${service.image}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Update Service</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>