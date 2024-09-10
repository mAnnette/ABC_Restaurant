<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="abc_restaurant.model.MenuItem" %>
<%@ page import="abc_restaurant.model.Category" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"> <!-- Include your CSS file -->
</head>
<body>

 <jsp:include page="header.jsp" />
    <div class="container mt-5">
    
    <!-- Restaurant Dropdown to filter categories and menu items -->
    <div class="container mt-5">
        <form action="${pageContext.request.contextPath}/menuItem" method="get">
            <input type="hidden" name="action" value="listMenuItemsForCustomer">
            <div class="form-group">
                <label for="restaurantFilter">Select Restaurant:</label>
                <select class="form-control" id="restaurantFilter" name="restaurantId" onchange="this.form.submit()">
                    <option value="">All Restaurants</option>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}" ${restaurant.id == selectedRestaurantId ? 'selected' : ''}>${restaurant.name}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
    
    
        <c:forEach var="category" items="${categories}">
            <!-- Category Headline -->
            <h2 class="my-4">${category.name}</h2>
            <div class="row">
                <c:forEach var="item" items="${menuItems}">
                    <c:if test="${item.categoryId == category.id}">
                        <!-- Menu Item Card -->
                        <div class="col-md-4 mb-4">
                             <div class="card" style="width: 200px; margin: 0 auto;"> <!-- Limit card width to 200px -->
                                <img src="images/uploads/menuItems/${item.image}" class="card-img-top" alt="${item.name}" style="width: 200px; height: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">${item.name}</h5>
                                    <p class="card-text">Rs. ${item.price}</p>
                                    <a href="#" class="btn btn-primary">Order</a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    
    <script src="js/bootstrap.bundle.min.js"></script> <!-- Include your JS file -->
</body>
</html>
