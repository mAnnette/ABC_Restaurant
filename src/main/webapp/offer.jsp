<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ABC Restaurant - Offers</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- header -->
    <jsp:include page="header.jsp" />

    <div class="container mt-4">
        <h2>Our Offers</h2>
        
        <!-- Dropdown to filter offers by restaurant -->
        <form action="offers" method="get" class="mb-3">
            <input type="hidden" name="action" value="listOffersForCustomer">
            <div class="form-group">
                <label for="restaurantFilter">Filter by Restaurant:</label>
                <select class="form-control" id="restaurantFilter" name="restaurantId" onchange="this.form.submit()">
                    <option value="">All Restaurants</option>
                    <c:forEach var="restaurant" items="${restaurantList}">
                        <option value="${restaurant.id}" ${restaurant.id == selectedRestaurantId ? 'selected' : ''}>${restaurant.name}</option>
                    </c:forEach>
                </select>
            </div>
        </form>

        <!-- Offers List -->
        <div class="row">
            <c:forEach var="offer" items="${offerList}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="images/uploads/offers/${offer.image}" class="card-img-top" alt="${offer.offerName}">
                        <div class="card-body">
                            <h5 class="card-title">${offer.offerName}</h5>
                            <p class="card-text">${offer.description}</p>
                            <% 
                                    DecimalFormat df = new DecimalFormat("0"); 
                                    String discount = df.format(((abc_restaurant.model.Offer)pageContext.getAttribute("offer")).getDiscountPercentage());
                                    
                                    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                    String startDateTime = dateTimeFormat.format(((abc_restaurant.model.Offer)pageContext.getAttribute("offer")).getStartDate());
                                    String endDateTime = dateTimeFormat.format(((abc_restaurant.model.Offer)pageContext.getAttribute("offer")).getEndDate());
                                %>

                                <p class="card-text"><strong>Discount:</strong> <%= discount %>%</p>
                                <p class="card-text"><strong>Valid From:</strong> <%= startDateTime %></p>
                                <p class="card-text"><strong>Valid Until:</strong> <%= endDateTime %></p>  
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty offerList}">
                <p>No offers available.</p>
            </c:if>
        </div>
    </div>

    <!-- footer -->
    <jsp:include page="footer.jsp" />

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>