<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Edit Category</h2>
        <form action="category" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${category.id}">
            <div class="mb-3">
                <label for="name" class="form-label">Category Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${category.name}" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Category</button>
        </form>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>