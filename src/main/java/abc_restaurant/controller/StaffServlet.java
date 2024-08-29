package abc_restaurant.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.RestaurantDAO;
import abc_restaurant.dao.StaffDAO;
import abc_restaurant.model.Restaurant;
import abc_restaurant.model.Staff;





@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private RestaurantDAO restaurantDAO;
    private StaffDAO staffDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        restaurantDAO = new RestaurantDAO();
        staffDAO = new StaffDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; 
        }

 
        String idParam = request.getParameter("id");

        switch (action) {
            case "delete":
                if (idParam != null && !idParam.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(idParam);
                        StaffDAO.deleteStaff(id);
                        response.sendRedirect("staff?action=list"); 
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid staff ID format");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Staff ID is missing or empty");
                }
                break;
            case "list":
                listStaff(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listStaff(request, response);
                break;
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> staffList = StaffDAO.getAllStaff();
        RestaurantDAO restaurantDAO = new RestaurantDAO(); 
        List<Restaurant> restaurantList;
        try {
            restaurantList = restaurantDAO.getAllRestaurants(); 
        } catch (SQLException e) {
            throw new ServletException("Error fetching restaurants", e);
        }
        request.setAttribute("staffList", staffList);
        request.setAttribute("restaurantList", restaurantList);
        request.getRequestDispatcher("/manageStaff.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants(); 
            request.setAttribute("restaurantList", restaurantList);
            request.getRequestDispatcher("/createStaff.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve restaurants");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Staff staff = staffDAO.getStaffById(id);
                if (staff != null) {
                    try {
                        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants(); 
                        request.setAttribute("restaurantList", restaurantList);
                        request.setAttribute("staff", staff);
                        request.getRequestDispatcher("/editStaff.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve restaurants");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Staff member not found");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid staff ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Staff ID is missing or empty");
        }
        
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createStaff(request, response);
                break;
            case "update":
                updateStaff(request, response);
                break;
            default:
                response.sendRedirect("staff?action=list");
                break;
        }
    }

    private void createStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        StaffDAO.createStaff(username, password, position, restaurantId);
        response.sendRedirect("staff?action=list");
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("staffId");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String position = request.getParameter("position");
                int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                StaffDAO.updateStaff(id, username, password, position, restaurantId);
                response.sendRedirect("staff");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid staff ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Staff ID is missing or empty");
        }
    }
}
