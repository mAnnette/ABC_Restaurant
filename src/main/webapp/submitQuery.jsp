<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Submit Query</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Submit Query</h2>

        <form action="${pageContext.request.contextPath}/customer/query" method="post">
            <input type="hidden" name="reservationId" value="${param.reservationId}">
            <div class="form-group">
                <label for="queryText">Your Query:</label>
                <textarea class="form-control" id="queryText" name="queryText" rows="4" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Submit Query</button>
        </form>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>