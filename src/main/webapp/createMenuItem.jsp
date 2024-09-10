<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Menu Item</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Create Menu Item</h2>
        <form action="menuItem" method="post">
            <input type="hidden" name="action" value="create">
            <div class="mb-3">
                <label for="name" class="form-label">Item Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price (Rs.)</label>
                <input type="text" class="form-control" id="price" name="price" placeholder="Rs. 0.00" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3"></textarea>
            </div>
            <div class="mb-3">
                <label for="categoryId" class="form-label">Category</label>
                <select class="form-control" id="categoryId" name="categoryId" required>
                    <% 
                        List<Category> categories = (List<Category>) request.getAttribute("categories");
                        for (Category category : categories) {
                    %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                    <% 
                        }
                    %>
                </select>
            </div>
             <div class="mb-3">
                <label for="image" class="form-label">Image Name</label>
                <input type="text" class="form-control" id="image" name="image" placeholder="image.jpg/png/jpeg" required>
            </div>
            <button type="submit" class="btn btn-primary">Create Menu Item</button>
        </form>
    </div>
      <jsp:include page="footer.jsp" />
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>