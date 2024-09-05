<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
        /* Set a fixed height for the carousel */
        .carousel-item img {
            height: 500px; /* Set desired height */
            object-fit: cover; /* Ensures the image covers the container without distortion */
        }

        /* Optional: Adjust for smaller screens */
        @media (max-width: 768px) {
            .carousel-item img {
                height: 300px; /* Set a smaller height for smaller screens */
            }
        }
    </style>
</head>
<body>
<!-- header -->
    <jsp:include page="header.jsp" />

    <!-- Carousel/Slider -->
    <div id="carouselExampleIndicators" class="carousel slide mt-4" data-bs-ride="carousel" data-bs-interval="3000">
        <ol class="carousel-indicators">
            <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"></li>
            <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></li>
            <li data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/uploads/image slider1.jpeg" class="d-block w-100" alt="Image 1">
            </div>
            <div class="carousel-item">
                <img src="images/uploads/image slider2.jpeg" class="d-block w-100" alt="Image 2">
            </div>
            <div class="carousel-item">
                <img src="images/uploads/image slider3.jpeg" class="d-block w-100" alt="Image 3">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </a>
    </div>

    <!--footer -->
    <jsp:include page="footer.jsp" />

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>