<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.MenuItem" %>
<%@ page import="abc_restaurant.model.Category" %>
<%@ page import="abc_restaurant.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>


	<jsp:include page="header.jsp" />
    <div class="container mt-4">
        <h2>Our Menu</h2>

        <!-- Dropdown to filter menu items by restaurant -->
		<form action="menuItem" method="get" class="mb-3">
		    <input type="hidden" name="action" value="listMenuItemsForCustomer">
		    <div class="form-group">
		        <label for="restaurantFilter">Filter by Restaurant:</label>
		        <select class="form-control" id="restaurantFilter" name="restaurantId">
				    <option value="">All Restaurants</option>
				    <option value="1">ABC Restaurant Colombo</option>
				    <option value="2">Restaurant 2</option>
				</select>
		    </div>
		</form>

        <!-- Display menu items grouped by category -->
        <c:forEach var="category" items="${categories}">
            <h3>${category.name}</h3>
            <div class="row">
                <c:forEach var="menuItem" items="${menuItems}">
                    <c:if test="${menuItem.categoryId == category.id}">
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <img src="images/uploads/menuItems/${menuItem.image}" class="card-img-top" alt="${menuItem.name}">
                                <div class="card-body">
                                    <h5 class="card-title">${menuItem.name}</h5>
                                    <p class="card-text">Rs. ${menuItem.price}</p>
                                    <form action="order" method="post">
                                        <input type="hidden" name="menuItemId" value="${menuItem.id}">
                                        <button type="submit" class="btn btn-primary" disabled>Order</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
        
        <!-- If there are no menu items to display -->
        <c:if test="${empty categories || empty menuItems}">
            <p>No menu items available for the selected restaurant.</p>
        </c:if>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>