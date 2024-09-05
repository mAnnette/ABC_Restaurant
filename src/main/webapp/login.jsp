<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp" />
  

 
    <div class="container mt-4">
        <h2>Login</h2>
        <form action="Customer/login" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
        <p class="mt-2">Don't have an account? <a href="registration.jsp">Register here</a></p>
        <% if (request.getParameter("error") != null) { %>
            <div class="alert alert-danger mt-3" role="alert">
                <%= request.getParameter("error") %>
            </div>
        <% } %>
        
        <% if (request.getParameter("message") != null) { %>
            <div class="alert alert-info mt-3" role="alert">
                <%= request.getParameter("message") %>
            </div>
        <% } %>
    </div>

    <script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>