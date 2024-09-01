package abc_restaurant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.FacilityDAO;
import abc_restaurant.model.Facility;



@WebServlet("/facility")
public class FacilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
                        FacilityDAO.deleteFacility(id);
                        response.sendRedirect("facility?action=list"); 
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid facility ID format");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Facility ID is missing or empty");
                }
                break;
            case "list":
                listFacilities(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "show":
                showFacilities(request, response);
                break;
            default:
                listFacilities(request, response);
                break;
        }
    }

    private void listFacilities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Facility> facilityList = FacilityDAO.getAllFacilities();
        request.setAttribute("facilityList", facilityList);
        request.getRequestDispatcher("/manageFacilities.jsp").forward(request, response);
    }
    
    private void showFacilities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Facility> facilityList = FacilityDAO.getAllFacilities();
        request.setAttribute("facilityList", facilityList);
        request.getRequestDispatcher("/facility.jsp").forward(request, response);
    }
    

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createFacility.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id"); 
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Facility facility = FacilityDAO.getFacilityById(id);
                if (facility != null) {
                    request.setAttribute("facility", facility);
                    request.getRequestDispatcher("/editFacility.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Facility not found");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid facility ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Facility ID is missing or empty");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createFacility(request, response);
                break;
            case "update":
                updateFacility(request, response);
                break;
            default:
                response.sendRedirect("facility?action=list");
                break;
        }
    }

    private void createFacility(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String location = request.getParameter("location");
        FacilityDAO.createFacility(name, description, image, location);
        response.sendRedirect("facility?action=list");
    }

    private void updateFacility(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("facilityId");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String image = request.getParameter("image");
                String location = request.getParameter("location");
                FacilityDAO.updateFacility(id, name, description, image, location);
                response.sendRedirect("facility");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid facility ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Facility ID is missing or empty");
        }
    }

}
