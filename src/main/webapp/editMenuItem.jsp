<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Category" %>
<%@ page import="abc_restaurant.model.MenuItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Menu Item</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Menu Item</h2>
        <form action="menuItem" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${menuItem.id}">
            <div class="mb-3">
                <label for="name" class="form-label">Item Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${menuItem.name}" required>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price (Rs.)</label>
                <input type="text" class="form-control" id="price" name="price" value="Rs. ${menuItem.price}" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3">${menuItem.description}</textarea>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label">Category</label>
                <select class="form-control" id="categoryId" name="categoryId" required>
                    <% 
                        List<Category> categories = (List<Category>) request.getAttribute("categories");
                		MenuItem menuItem = (MenuItem) request.getAttribute("menuItem");
                        for (Category category : categories) {
                    %>
                        <option value="<%= category.getId() %>" <%= menuItem.getCategoryId() == category.getId() ? "selected" : "" %>>
                            <%= category.getName() %>
                        </option>
                    <% 
                        }
                    %>
                </select>
            </div>
             <div class="mb-3">
                <label for="image" class="form-label">Image Name</label>
                <input type="text" class="form-control" id="image" name="image" value="${menuItem.image}" placeholder="image.jpg/png/jpeg" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Menu Item</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>