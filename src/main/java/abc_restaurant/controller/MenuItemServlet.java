package abc_restaurant.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc_restaurant.dao.CategoryDAO;
import abc_restaurant.dao.MenuItemDAO;
import abc_restaurant.dao.RestaurantDAO;
import abc_restaurant.model.Category;
import abc_restaurant.model.MenuItem;
import abc_restaurant.model.Restaurant;


/**
 * Servlet implementation class MenuItemServlet
 */
@WebServlet("/menuItem")
public class MenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private MenuItemDAO menuItemDAO;
    private CategoryDAO categoryDAO;
    private RestaurantDAO restaurantDAO;

    @Override
    public void init() throws ServletException {
        menuItemDAO = new MenuItemDAO();
        categoryDAO = new CategoryDAO();
        restaurantDAO = new RestaurantDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "listMenuItemsForCustomer":
                    listMenuItemsForCustomer(request, response);
                    break;
              
                default:
                    if (session != null && session.getAttribute("restaurantId") != null) {
                        int restaurantId = (Integer) session.getAttribute("restaurantId");
                        switch (action) {
                            case "create":
                                showCreateForm(request, response, restaurantId);
                                break;
                            case "edit":
                                showEditForm(request, response, restaurantId);
                                break;
                            case "delete":
                                deleteMenuItem(request, response, restaurantId);
                                break;
                            default:
                                listMenuItems(request, response, restaurantId);
                                break;
                        }
                    } else {
                        response.sendRedirect("admin&staffLogin.jsp?error=Please login as staff to view this page");
                    }
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("restaurantId") != null) {
            int restaurantId = (Integer) session.getAttribute("restaurantId");

            String action = request.getParameter("action");
            try {
                if ("create".equals(action)) {
                    createMenuItem(request, response, restaurantId);
                } else if ("update".equals(action)) {
                    updateMenuItem(request, response, restaurantId);
                } else {
                    response.sendRedirect("menuItem?action=list");
                }
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }
        } else {
            response.sendRedirect("admin&staffLogin.jsp?error=Please login as staff to view this page");
        }
    }

    private void listMenuItems(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws ServletException, IOException, SQLException {
        List<MenuItem> menuItems = menuItemDAO.getMenuItemsByRestaurant(restaurantId);
        request.setAttribute("menuItems", menuItems);
        request.getRequestDispatcher("/listMenuItems.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws ServletException, IOException, SQLException {
        List<Category> categories = categoryDAO.getCategoriesByRestaurant(restaurantId);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/createMenuItem.jsp").forward(request, response);
    }

    private void createMenuItem(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws SQLException, IOException {
        String name = request.getParameter("name");
        BigDecimal price = new BigDecimal(request.getParameter("price").replace("Rs. ", ""));
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String image = request.getParameter("image");

        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDescription(description);
        menuItem.setCategoryId(categoryId);
        menuItem.setImage(image);

        menuItemDAO.createMenuItem(menuItem);
        response.sendRedirect("menuItem?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        MenuItem menuItem = menuItemDAO.getMenuItemById(id);

        if (menuItem != null) {
            List<Category> categories = categoryDAO.getCategoriesByRestaurant(restaurantId);
            request.setAttribute("menuItem", menuItem);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/editMenuItem.jsp").forward(request, response);
        } else {
            response.sendRedirect("menuItem?action=list&error=Menu item not found.");
        }
    }

    private void updateMenuItem(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        BigDecimal price = new BigDecimal(request.getParameter("price").replace("Rs. ", ""));
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String image = request.getParameter("image");

        MenuItem menuItem = new MenuItem();
        menuItem.setId(id);
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDescription(description);
        menuItem.setCategoryId(categoryId);
        menuItem.setImage(image);

        menuItemDAO.updateMenuItem(menuItem);
        response.sendRedirect("menuItem?action=list");
    }

    private void deleteMenuItem(HttpServletRequest request, HttpServletResponse response, int restaurantId) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        menuItemDAO.deleteMenuItem(id);
        response.sendRedirect("menuItem?action=list");
    }
    
    
    private void listMenuItemsForCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String restaurantIdStr = request.getParameter("restaurantId");
        List<MenuItem> menuItems;
        List<Category> categories;

        int restaurantId = (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty()) ? Integer.parseInt(restaurantIdStr) : 0;

        if (restaurantId > 0) {
            menuItems = menuItemDAO.getMenuItemsByRestaurant(restaurantId);
            categories = categoryDAO.getCategoriesByRestaurant(restaurantId);
        } else {
            menuItems = menuItemDAO.getAllMenuItems();
            categories = categoryDAO.getAllCategories();
        }

        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

        request.setAttribute("menuItems", menuItems);
        request.setAttribute("categories", categories);
        request.setAttribute("restaurantList", restaurantList);
        request.setAttribute("selectedRestaurantId", restaurantIdStr);

        request.getRequestDispatcher("/menu.jsp").forward(request, response);
    }
    

}
