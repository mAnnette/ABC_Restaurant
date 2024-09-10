<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"> 
    <link rel="stylesheet" href="css/style.css"> 
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> 
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="d-flex flex-column flex-shrink-0 p-3 bg-body-tertiary" style="width: 280px;">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
            <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>

            <h2><a class="navbar-brand" href="index.jsp">ABC Restaurant</a></h2>
                    <h2 class="sidebar-heading">Staff Menu</h2>
                    <ul class="nav flex-column">
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/staff/reservations" class="nav-link <% if ("viewReservations".equals(request.getParameter("action"))) { %> active <% } %>" aria-current="page">
    				<svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
    				Manage Reservations
				</a>
            </li>
             <li>
                <a href="${pageContext.request.contextPath}/staff/viewQueries" class="nav-link <% if ("viewQueries".equals(request.getParameter("action"))) { %> active <% } %>">
                    <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
                    Respond to Queries
                </a>
            </li>
             <li>
			    <a href="${pageContext.request.contextPath}/category?action=list" class="nav-link <% if ("viewCategories".equals(request.getParameter("action"))) { %> active <% } %>">
			        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"></use></svg>
			        Manage Categories
			    </a>
			</li>
			  <li>
                <a href="${pageContext.request.contextPath}/menuItem?action=list" class="nav-link <% if ("viewMenuItems".equals(request.getParameter("action"))) { %> active <% } %>">
                    <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#menu"></use></svg>
                    Manage Menu Items
                </a>
            </li>
            <li>
                <a href="staffDashboard.jsp?action=viewPayments" class="nav-link <% if ("viewPayments".equals(request.getParameter("action"))) { %> active <% } %>">
                    <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
                    Process Payments
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/staffLogout.jsp" class="nav-link">
                    <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#logout"></use></svg>
                    Sign Out
                </a>
            </li>
        </ul>
        <hr>
    </div>

</body>
</html>