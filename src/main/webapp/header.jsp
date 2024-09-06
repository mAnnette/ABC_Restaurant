<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Customer" %>
<%

    Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");
%>

<style>
    .nav-link {
        color: #d77e35 !important; 
        font-weight: bold; 
        transition: color 0.3s ease; 
        padding-left: 10px;
        padding-right: 10px;
    }

    .nav-link:hover {
        color: black !important; 
    }

    .reservation-btn {
        background-color: #f1a01f;
        color: white !important;
        font-weight: bold;
        border-radius: 20px;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        transition: background-color 0.3s ease;
        margin-left: 10px;
    }

    .reservation-btn:hover {
        background-color: black;
        color: white !important;
    }

    .navbar {
        display: flex;
        align-items: center;
        justify-content: space-between;
        flex-wrap: nowrap;
    }

    .nav-menu {
        flex-grow: 1;
        margin-left: 10px; 
        white-space: nowrap; 
    }

    .right-section {
        display: flex;
        align-items: center;
        justify-content: flex-end; 
    }

    .search-form {
        margin-right: 15px; 
        flex-grow: 1;
    }


    .profile-name {
        color: black !important; 
        font-weight: bold;
        text-align: center;
    }

    .dropdown-item {
        color: #d77e35 !important; 
    }

    .dropdown-item:hover {
        color: black !important; 
    }


    .profile-icon {
        width: 32px;
        height: 32px;
        fill: #d77e35; 
    }
</style>

<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="navbar">
          
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">
                <img src="${pageContext.request.contextPath}/images/uploads/logo.png" alt="ABC Restaurant Logo" width="100" height="100" class="me-2">
            </a>

           
            <ul class="nav col-12 col-lg-auto nav-menu mb-2 justify-content-center mb-md-0">
                <li><a href="index.jsp" class="nav-link px-2 link-secondary">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/aboutUs.jsp" class="nav-link px-2 link-body-emphasis">About Us</a></li>
                <li><a href="${pageContext.request.contextPath}/facility?action=show" class="nav-link px-2 link-body-emphasis">Facilities</a></li>
                <li><a href="${pageContext.request.contextPath}/service?action=show" class="nav-link px-2 link-body-emphasis">Services</a></li>
                <li><a href="${pageContext.request.contextPath}/menuItem?action=listMenuItemsForCustomer" class="nav-link px-2 link-body-emphasis">Menu</a></li>
                <li><a href="#" class="nav-link px-2 link-body-emphasis">Gallery</a></li>
                <li><a href="${pageContext.request.contextPath}/offers?action=listOffersForCustomer" class="nav-link px-2 link-body-emphasis">Offers</a></li>
              
            </ul>

     
            <div class="right-section">
          
                <form class="search-form col-12 col-lg-auto mb-3 mb-lg-0" role="search">
                    <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
                </form>

                <c:if test="${loggedInCustomer != null}">
                    <div class="dropdown ms-3">
                        <a href="#" class="nav-link px-2 link-body-emphasis dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
           
                            <img src="${pageContext.request.contextPath}/images/uploads/profile.svg" alt="Profile" class="profile-icon">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item profile-name disabled" href="#">Hello, ${loggedInCustomer.firstName} ${loggedInCustomer.lastName}!</a></li>
                            <li><a href="${pageContext.request.contextPath}/customer/profile" class="dropdown-item">Profile</a></li>
                            <li><a href="${pageContext.request.contextPath}/customer/reservations" class="dropdown-item">My Reservations</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout.jsp">Sign out</a></li>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${loggedInCustomer == null}">
                    <a href="login.jsp" class="nav-link px-2 link-body-emphasis">Login</a>
                    <a href="registration.jsp" class="nav-link px-2 link-body-emphasis">Register</a>
                </c:if>
                
                <a href="${pageContext.request.contextPath}/reservation" class="reservation-btn">Reservation</a>
            </div>
        </div>
    </div>
</header>