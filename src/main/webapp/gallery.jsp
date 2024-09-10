<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gallery</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Gallery</h2>
        <div class="row">
            <c:forEach var="gallery" items="${galleryList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="images/uploads/gallery/${gallery.image}" class="card-img-top" alt="Gallery Image">
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty galleryList}">
            <p>No images available in the gallery.</p>
        </c:if>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>