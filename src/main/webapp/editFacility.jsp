<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Facility" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Facility - Admin Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Facility</h2>
        <form action="facility" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="facilityId" value="${facility.id}">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${facility.name}" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required>${facility.description}</textarea>
            </div>
            <div class="form-group">
                <label for="image">Image:(URL or path to the image)</label>
                <textarea class="form-control" id="image" name="image" required>${facility.image}</textarea>
            </div>
            <div class="form-group">
                <label for="location">Location:</label>
                <input type="text" class="form-control" id="location" name="location" value="${facility.location}" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Facility</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>