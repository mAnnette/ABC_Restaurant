<%@ page import="java.util.List" %>
<%@ page import="abc_restaurant.model.Facility" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Facilities</title>
</head>
<body>
    <h1>List of Facilities</h1>
    <a href="facility?action=create">Add New Facility</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Image</th>
            <th>Location</th>
            <th>Actions</th>
        </tr>
        <% 
            List<Facility> facilities = (List<Facility>) request.getAttribute("facilities");
            if (facilities != null) {
                for (Facility facility : facilities) {
        %>
        <tr>
            <td><%= facility.getId() %></td>
            <td><%= facility.getName() %></td>
            <td><%= facility.getDescription() %></td>
            <td><img src="<%= facility.getImage() %>" alt="<%= facility.getName() %>" width="100"/></td>
            <td><%= facility.getLocation() %></td>
            <td>
                <a href="facility?action=edit&id=<%= facility.getId() %>">Edit</a>
                <a href="facility?action=delete&id=<%= facility.getId() %>" onclick="return confirm('Are you sure you want to delete this facility?');">Delete</a>
                <a href="facility?action=details&id=<%= facility.getId() %>">View Details</a>
            </td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="6">No facilities found.</td>
        </tr>
        <% 
            }
        %>
    </table>
</body>
</html>