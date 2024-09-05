<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Offer" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Offers</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1>List of Offers</h1>
        <a href="offer?action=create" class="btn btn-primary mb-3">Add New Offer</a>
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
                <% 
                    List<Offer> offers = (List<Offer>) request.getAttribute("offerList");
                    if (offers != null) {
                        for (Offer offer : offers) {
                %>
                <tr>
                    <td><%= offer.getId() %></td>
                    <td><%= offer.getOfferName() %></td>
                    <td><%= offer.getDescription() %></td>
                    <td><%= offer.getDiscountPercentage() %>%</td>
                    <td><%= offer.getStartDate() %></td>
                    <td><%= offer.getEndDate() %></td>
                    <td><img src="images/uploads/offers/<%= offer.getImage() %>" alt="<%= offer.getOfferName() %>" width="100"/></td>
                    <td>
                        <%= offer.isApplyToAllRestaurants() ? "All Restaurants" : offer.getRestaurantId() %>
                    </td>
                    <td>
                        <a href="offer?action=edit&id=<%= offer.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="offer?action=delete&id=<%= offer.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this offer?');">Delete</a>
                        <a href="offer?action=details&id=<%= offer.getId() %>" class="btn btn-info btn-sm">View Details</a>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="9">No offers found.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>