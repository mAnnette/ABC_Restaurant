package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.OfferDAO;
import abc_restaurant.dao.RestaurantDAO;
import abc_restaurant.model.Offer;
import abc_restaurant.model.Restaurant;



/**
 * Servlet implementation class OfferServlet
 */
@WebServlet("/offers")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OfferDAO offerDAO;
    private RestaurantDAO restaurantDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        offerDAO = new OfferDAO();
        restaurantDAO = new RestaurantDAO();
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
                        offerDAO.deleteOffer(id);
                        response.sendRedirect("offers?action=list");
                    } catch (NumberFormatException | SQLException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid offer ID format");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Offer ID is missing or empty");
                }
                break;
            case "list":
                listOffers(request, response);
                break;
            case "listOffersForCustomer":
                listOffersForCustomer(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listOffers(request, response);
                break;
        }
    }

    private void listOffers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            List<Offer> offerList = offerDAO.getAllOffers();
            request.setAttribute("offerList", offerList);
            request.getRequestDispatcher("/manageOffers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve offers");
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
            request.setAttribute("restaurantList", restaurantList);
            request.getRequestDispatcher("/createOffer.jsp").forward(request, response);
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
                Offer offer = offerDAO.getOfferById(id);
                if (offer != null) {
                    List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
                    request.setAttribute("restaurantList", restaurantList);
                    request.setAttribute("offer", offer);
                    request.getRequestDispatcher("/editOffer.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Offer not found");
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid offer ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Offer ID is missing or empty");
        }
    }
    
    private void listOffersForCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String restaurantIdStr = request.getParameter("restaurantId");
            List<Offer> offerList;

            if (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty()) {
                int restaurantId = Integer.parseInt(restaurantIdStr);
                offerList = offerDAO.getOffersForRestaurant(restaurantId);
            } else {
                offerList = offerDAO.getAllOffers();
            }

            List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

            request.setAttribute("offerList", offerList);
            request.setAttribute("restaurantList", restaurantList);
            request.setAttribute("selectedRestaurantId", restaurantIdStr);

            request.getRequestDispatcher("/offer.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve offers");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOffer(request, response);
                break;
            case "update":
                updateOffer(request, response);
                break;
            default:
                response.sendRedirect("offers?action=list");
                break;
        }
    }

    private void createOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String offerName = request.getParameter("offerName");
        String description = request.getParameter("description");
        double discountPercentage = Double.parseDouble(request.getParameter("discountPercentage"));
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String image = request.getParameter("image");
        boolean applyToAllRestaurants = Boolean.parseBoolean(request.getParameter("applyToAllRestaurants"));

        int restaurantId = 0;
        if (!applyToAllRestaurants) {
            String restaurantIdStr = request.getParameter("restaurantId");
            if (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty()) {
                try {
                    restaurantId = Integer.parseInt(restaurantIdStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID format");
                    return; 
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required unless applying to all restaurants");
                return; 
            }
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date parsedStartDate = sdf.parse(startDateStr);
            Date parsedEndDate = sdf.parse(endDateStr);

            Timestamp startDate = new Timestamp(parsedStartDate.getTime());
            Timestamp endDate = new Timestamp(parsedEndDate.getTime());

            Offer offer = new Offer(0, offerName, description, discountPercentage, startDate, endDate, image, restaurantId, applyToAllRestaurants);
            offerDAO.createOffer(offer);
            response.sendRedirect("offers?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to create offer");
        }
    }

    private void updateOffer(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String idParam = request.getParameter("offerId");
        if (idParam == null || idParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Offer ID is missing or empty");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            String offerName = request.getParameter("offerName");
            String description = request.getParameter("description");
            String discountPercentageStr = request.getParameter("discountPercentage");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            String image = request.getParameter("image");
            boolean applyToAllRestaurants = Boolean.parseBoolean(request.getParameter("applyToAllRestaurants"));

            if (offerName == null || offerName.trim().isEmpty() ||
                description == null || description.trim().isEmpty() ||
                discountPercentageStr == null || discountPercentageStr.trim().isEmpty() ||
                startDateStr == null || startDateStr.trim().isEmpty() ||
                endDateStr == null || endDateStr.trim().isEmpty() ||
                image == null || image.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "One or more fields are missing or empty");
                return;
            }

            double discountPercentage = Double.parseDouble(discountPercentageStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Timestamp startDate;
            Timestamp endDate;

            try {
                Date parsedStartDate = sdf.parse(startDateStr);
                Date parsedEndDate = sdf.parse(endDateStr);
                startDate = new Timestamp(parsedStartDate.getTime());
                endDate = new Timestamp(parsedEndDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format: " + e.getMessage());
                return;
            }

            int restaurantId = 0;
            if (!applyToAllRestaurants) {
                String restaurantIdStr = request.getParameter("restaurantId");
                if (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty()) {
                    restaurantId = Integer.parseInt(restaurantIdStr);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required unless applying to all restaurants");
                    return;
                }
            }

            Offer offer = new Offer(id, offerName, description, discountPercentage, startDate, endDate, image, restaurantId, applyToAllRestaurants);
            offerDAO.updateOffer(offer);
            response.sendRedirect("offers?action=list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric format in offer data: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error while updating offer: " + e.getMessage());
        }
    }

}
