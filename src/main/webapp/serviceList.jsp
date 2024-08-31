<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Services</title>
</head>
<body>
    <h1>List of Services</h1>
    <a href="service?action=create">Add New Service</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        <% 
            List<Service> services = (List<Service>) request.getAttribute("services");
            if (services != null) {
                for (Service service : services) {
        %>
        <tr>
            <td><%= service.getServiceId() %></td>
            <td><%= service.getServiceName() %></td>
            <td><%= service.getDescription() %></td>
            <td><img src="<%= service.getImage() %>" alt="<%= service.getServiceName() %>" width="100"/></td>
            <td>
                <a href="service?action=edit&id=<%= service.getServiceId() %>">Edit</a>
                <a href="service?action=delete&id=<%= service.getServiceId() %>" onclick="return confirm('Are you sure you want to delete this service?');">Delete</a>
                <a href="service?action=details&id=<%= service.getServiceId() %>">View Details</a>
            </td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="5">No services found.</td>
        </tr>
        <% 
            }
        %>
    </table>
</body>
</html>