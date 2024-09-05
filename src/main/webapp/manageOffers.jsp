<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Offers</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Manage Offers</h2>
        <a href="offers?action=create" class="btn btn-primary mb-2">Create New Offer</a>
        <c:if test="${not empty offerList}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Offer Name</th>
                        <th>Description</th>
                        <th>Discount Percentage</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Image</th>
                        <th>Restaurant</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="offer" items="${offerList}">
                        <tr>
                            <td>${offer.id}</td>
                            <td>${offer.offerName}</td>
                            <td>${offer.description}</td>
                            <td>${offer.discountPercentage}%</td>
                            <td>${offer.startDate}</td>
                            <td>${offer.endDate}</td>
                            <td><img src="images/uploads/offers/${offer.image}" alt="${offer.offerName}" width="100"></td>
                            <td>
                                <c:choose>
                                    <c:when test="${offer.applyToAllRestaurants}">
                                        All Restaurants
                                    </c:when>
                                    <c:otherwise>
                                        ${offer.restaurantId}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="offers?action=edit&id=${offer.id}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="offers?action=delete&id=${offer.id}" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty offerList}">
            <p>No offers found.</p>
        </c:if>
    </div>
</body>
</html>