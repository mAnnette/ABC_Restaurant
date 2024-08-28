<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="abc_restaurant.model.Customer" %>
<%

    Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");
%>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
           
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">
               
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use href="#bootstrap"></use>
                </svg>
            </a>

            <a class="navbar-brand" href="#">ABC Restaurant</a>

       
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="index.jsp" class="nav-link px-2 link-secondary">Home</a></li>
                <li><a href="#" class="nav-link px-2 link-body-emphasis">About Us</a></li>
                <li><a href="${pageContext.request.contextPath}/facility?action=show" class="nav-link px-2 link-body-emphasis">Facilities</a></li>
                <li><a href="${pageContext.request.contextPath}/service?action=show" class="nav-link px-2 link-body-emphasis">Services</a></li>
                <li><a href="#" class="nav-link px-2 link-body-emphasis">Menu</a></li>
                <li><a href="#" class="nav-link px-2 link-body-emphasis">Gallery</a></li>
                <li><a href="#" class="nav-link px-2 link-body-emphasis">Offers</a></li>
				<li><a href="${pageContext.request.contextPath}/reservation" class="nav-link px-2 link-body-emphasis">Reservation</a></li>
       			<li><a href="#" class="nav-link px-2 link-body-emphasis">Submit Query</a></li>
                
       
                <c:if test="${loggedInCustomer != null}">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link px-2 link-body-emphasis dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="Profile" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="logout.jsp">Sign out</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${loggedInCustomer == null}">
                    <li><a href="login.jsp" class="nav-link px-2 link-body-emphasis">Login</a></li>
                    <li><a href="registration.jsp" class="nav-link px-2 link-body-emphasis">Register</a></li>
                </c:if>
            </ul>

          
            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
            </form>
            
          
        </div>
    </div>
</header>