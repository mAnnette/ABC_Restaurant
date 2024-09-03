<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Facilities - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        .facility-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin: 15px 0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .facility-card img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }
    </style>
</head>
<body>

    <jsp:include page="header.jsp" />
    <div class="container mt-4">
        <h2>Our Facilities</h2>
        <div class="row">
            <c:forEach var="facility" items="${facilityList}">
                <div class="col-md-4">
                    <div class="facility-card">
                         <img src="images/uploads/${facility.image}" alt="${facility.name}">
                        <h3>${facility.name}</h3>
                        <p>${facility.description}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>