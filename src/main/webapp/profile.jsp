<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Customer, abc_restaurant.model.Reservation" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="header.jsp" />	
    <div class="container mt-4">
        <h2>Customer Profile</h2>
        
        <!-- Customer Details -->
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">Profile Information</h5>
                <p><strong>Name:</strong> ${loggedInCustomer.firstName} ${loggedInCustomer.lastName}</p>
                <p><strong>Email:</strong> ${loggedInCustomer.email}</p>
                <p><strong>Contact Number:</strong> ${loggedInCustomer.contactNumber}</p> <!-- Updated field -->
                <p><strong>Address:</strong> ${loggedInCustomer.address}</p>
            </div>
        </div>
        
       

    <!-- Include footer -->
    <jsp:include page="footer.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>