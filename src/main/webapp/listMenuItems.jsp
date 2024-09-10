<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.MenuItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Menu Items</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Menu Items</h2>
        <a href="menuItem?action=create" class="btn btn-primary mb-3">Add New Menu Item</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
                    if (menuItems != null && !menuItems.isEmpty()) {
                        for (MenuItem menuItem : menuItems) {
                %>
                <tr>
                    <td><%= menuItem.getId() %></td>
                    <td><%= menuItem.getName() %></td>
                    <td>Rs. <%= menuItem.getPrice() %></td>
                    <td><%= menuItem.getDescription() %></td>
                    <td><%= menuItem.getCategoryName() %></td>
                    <td><img src="images/uploads/menuItems/<%= menuItem.getImage() %>" alt="<%= menuItem.getName() %>" width="100"/></td>
                    
                    <td>
                        <a href="menuItem?action=edit&id=<%= menuItem.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="menuItem?action=delete&id=<%= menuItem.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this menu item?');">Delete</a>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No menu items found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>