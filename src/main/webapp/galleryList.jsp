<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gallery List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Gallery List</h2>
        <a href="${pageContext.request.contextPath}/gallery?action=create" class="btn btn-primary mb-3">Add New Image</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="gallery" items="${galleryList}">
                    <tr>
                        <td>${gallery.id}</td>
                        <td>${gallery.name}</td>
                        <td><img src="images/uploads/gallery/${gallery.image}" alt="${gallery.name}" width="100"></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/gallery?action=edit&id=${gallery.id}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="${pageContext.request.contextPath}/gallery?action=delete&id=${gallery.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this image?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${empty galleryList}">
            <p>No images found.</p>
        </c:if>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>