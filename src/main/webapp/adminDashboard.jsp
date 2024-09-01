<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - ABC Restaurant</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
         
            
            <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
                <div class="position-sticky">
                	<h2><a class="navbar-brand" href="index.jsp">ABC Restaurant</a></h2>
                    <h2 class="sidebar-heading">Admin Menu</h2>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#" data-bs-toggle="collapse" data-bs-target="#manageUsersSubmenu" aria-expanded="false">
                                Manage Users
                            </a>
                            <ul class="collapse" id="manageUsersSubmenu">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Manage Customers</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="staff?action=list">Manage Staff</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Generate Reports</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Manage Promotions</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="service?action=list">Manage Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="facility?action=list">Manage Facilities</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/adminLogout.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>

       
            <main class="col-md-9 ms-sm-auto col-lg-10 px-4">
             
            
                <div class="container mt-4">
                   
                    <h2>Admin Dashboard</h2>
                    
                
                    <div class="row">
                        <div class="col-md-3">
                            <div class="card text-white bg-primary mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Total Reservations</h5>
                                    <p class="card-text"><%= request.getAttribute("totalReservations") %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card text-white bg-success mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Total Customers</h5>
                                    <p class="card-text"><%= request.getAttribute("totalCustomers") %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card text-white bg-warning mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Active Promotions</h5>
                                    <p class="card-text"><%= request.getAttribute("activePromotions") %></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card text-white bg-danger mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Staff on Duty</h5>
                                    <p class="card-text"><%= request.getAttribute("staffOnDuty") %></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                
                    <div class="mb-4">
                        <canvas id="myChart"></canvas>
                    </div>

                    <!-- Admin functionalities -->
                </div>
            </main>
        </div>
    </div>

    <!-- JavaScript -->
    <script src="js/bootstrap.bundle.min.js"></script>
    
</body>
</html>