<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Categories</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1 class="mb-4">List of Categories</h1>
        <a href="category?action=create" class="btn btn-primary mb-3">Add New Category</a>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null && !categories.isEmpty()) {
                        for (Category category : categories) {
                %>
                <tr>
                    <td><%= category.getId() %></td>
                    <td><%= category.getName() %></td>
                    <td>
                        <a href="category?action=edit&id=<%= category.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="category?action=delete&id=<%= category.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this category?');">Delete</a>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="3" class="text-center">No categories found.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
    <!-- Bootstrap JS -->
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>