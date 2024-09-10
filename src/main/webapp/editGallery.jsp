<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Gallery Image</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Gallery Image</h2>
        <form action="${pageContext.request.contextPath}/gallery" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${gallery.id}">
            
            <!-- Image Name Field -->
            <div class="form-group">
                <label for="name">Image Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${gallery.name}" required>
            </div>
            
            <!-- Image File Name Field -->
            <div class="form-group">
                <label for="image">Image File Name:</label>
                <input type="text" class="form-control" id="image" name="image" value="${gallery.image}" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary mt-3">Update Image</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>