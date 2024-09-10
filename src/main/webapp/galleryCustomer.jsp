<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Gallery</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        .gallery-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); /* Responsive columns */
            gap: 10px; /* Space between images */
            justify-items: center;
            align-items: center;
            margin: 20px;
        }

        .gallery-item {
            position: relative;
            overflow: hidden;
            transition: transform 0.35s ease;
        }

        .gallery-item img {
            display: block;
            max-width: 100%;
            height: auto;
            transition: transform 800ms, opacity 800ms;
        }

        .gallery-item:hover img {
            transform: scale(1.05); /* Slight zoom on hover */
            opacity: 0.9;
        }

        .gallery-item:after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.3); /* Overlay effect */
            opacity: 0;
            transition: opacity 350ms;
        }

        .gallery-item:hover:after {
            opacity: 1; /* Overlay on hover */
        }
    </style>
</head>
<body>

	<jsp:include page="header.jsp" />
	
    <div class="container mt-4">
        <h2 class="text-center">Gallery</h2>
        <div class="gallery-container">
            <c:forEach var="gallery" items="${galleryList}">
                <div class="gallery-item">
                    <img src="images/uploads/gallery/${gallery.image}" alt="${gallery.name}">
                </div>
            </c:forEach>
            <c:if test="${empty galleryList}">
                <p>No images available in the gallery.</p>
            </c:if>
        </div>
    </div>

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>