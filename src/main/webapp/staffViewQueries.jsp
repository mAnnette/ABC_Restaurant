<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Queries - Staff</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Queries for Your Restaurant</h2>

        <c:forEach var="query" items="${queries}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Query from ${query.customerName} (Reservation ID: ${query.reservationId})</h5>
                    <p class="card-text"><strong>Query:</strong> ${query.queryText}</p>
                    <c:if test="${query.responseText == null}">
                        <form action="${pageContext.request.contextPath}/staff/viewQueries" method="post">
                            <input type="hidden" name="queryId" value="${query.id}">
                            <div class="form-group">
                                <label for="responseText">Your Response:</label>
                                <textarea class="form-control" id="responseText" name="responseText" rows="4" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary mt-3">Submit Response</button>
                        </form>
                    </c:if>
                    <c:if test="${query.responseText != null}">
                        <p class="card-text"><strong>Response:</strong> ${query.responseText}</p>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>

 
</body>
</html>