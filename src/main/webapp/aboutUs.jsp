<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us - ABC Restaurant</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <style>
        .about-section {
            margin-bottom: 50px;
        }

        .about-section img {
            max-width: 100%;
            height: auto;
            border-radius: 10px;
        }

        .about-section h2, h3 {
            margin-top: 20px;
        }

        /* Flexbox for alternating text-image layout */
        .row {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }

        .row .col-md-6 {
            padding: 10px;
        }

        .text-content {
            font-size: 1.1em;
            line-height: 1.6;
        }
    </style>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	
    <div class="container mt-5">
        <h2 class="text-center mb-5">ABC Restaurant</h2>

        <!-- First row: Text on the left, image on the right -->
        <div class="row about-section">
            <div class="col-md-6">
            <h3>About Us</h3>
                <p class="text-content">
                    Welcome to ABC Restaurant, where culinary expertise meets impeccable service.
                    Founded in 2000, ABC Restaurant has been serving customers with a diverse menu of exquisite dishes,
                    ranging from traditional to modern cuisine, all crafted with the finest ingredients.
                </p>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/images/uploads/aboutUs/indoor dine in.jpg" alt="ABC Restaurant Interior">
            </div>
        </div>

        <!-- Second row: Image on the left, text on the right -->
        <div class="row about-section flex-row-reverse">
            <div class="col-md-6">
             <h3>Our Vision</h3>
                <p class="text-content">
                    Our vision is to become the go-to destination for food lovers, offering a unique dining experience 
                    that blends tradition and innovation. We strive to provide an inviting atmosphere where every meal 
                    is an unforgettable journey of flavors.
                </p>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/images/uploads/aboutUs/outdoor dine in.jpg" alt="Our Vision">
            </div>
        </div>

        <!-- Third row: Text on the left, image on the right -->
        <div class="row about-section">
            <div class="col-md-6">
            <h3>Our Mission</h3>
                <p class="text-content">
                    At ABC Restaurant, we are committed to delivering the highest standards of quality, 
                    taste, and service. We source the freshest ingredients and use authentic cooking methods 
                    to create dishes that not only satisfy the palate but also nourish the body and soul.
                </p>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/images/uploads/aboutUs/dine in.jpg" alt="Our Mission">
            </div>
        </div>

        <!-- Fourth row: Image on the left, text on the right -->
        <div class="row about-section flex-row-reverse">
            <div class="col-md-6">
            <h3>Our Team</h3>
                <p class="text-content">
                    Our passionate team of chefs, waitstaff, and managers work tirelessly to ensure that every 
                    customer has a memorable dining experience. From the kitchen to the dining room, 
                    we aim to exceed your expectations at every step.
                </p>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/images/uploads/aboutUs/take away.jpg" alt="Our Team">
            </div>
        </div>

        <!-- Fifth row: Text on the left, image on the right -->
        <div class="row about-section">
            <div class="col-md-6">
             <h3>Contact Us</h3>
                <p class="text-content">
                    If you have any questions or feedback, feel free to get in touch with us at:
                    <br>Email: abcrestaurant6@gmail.com
                    <br>Phone: +123-456-7890
                </p>
            </div>
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/images/uploads/aboutUs/delivery.jpg" alt="Contact Us">
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>