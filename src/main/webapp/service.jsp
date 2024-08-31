<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Services - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        .service-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin: 15px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .service-card img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }
    </style>
</head>
<body>
 <!-- header -->
    <jsp:include page="header.jsp" />
    
    <div class="container mt-4">
        <h2>Our Services</h2>
        <div class="row">
            <c:forEach var="service" items="${serviceList}">
                <div class="col-md-4">
                    <div class="service-card">
                        <img src="images/uploads/${service.image}" alt="${service.serviceName}">
                        <h3>${service.serviceName}</h3>
                        <p>${service.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    
     <!--footer -->
    <jsp:include page="footer.jsp" />
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>