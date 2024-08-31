package abc_restaurant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.ServiceDAO;
import abc_restaurant.model.Service;



@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
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
                        int serviceId = Integer.parseInt(idParam);
                        ServiceDAO.deleteService(serviceId);
                        response.sendRedirect("service?action=list");
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service ID format");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is missing or empty");
                }
                break;
            case "list":
                listServices(request, response);
                break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "show":
                showCustomerServices(request, response);
                break;
            default:
                listServices(request, response);
                break;
        }
    }

    private void listServices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> serviceList = ServiceDAO.getAllServices();
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("/manageServices.jsp").forward(request, response);
    }
    
    private void showCustomerServices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Service> serviceList = ServiceDAO.getAllServices();
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("/service.jsp").forward(request, response);  // Forward to customer-facing JSP
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createService.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int serviceId = Integer.parseInt(idParam);
                Service service = ServiceDAO.getServiceById(serviceId);
                if (service != null) {
                    request.setAttribute("service", service);
                    request.getRequestDispatcher("/editService.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Service not found");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is missing or empty");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createService(request, response);
                break;
            case "update":
                updateService(request, response);
                break;
            default:
                response.sendRedirect("service?action=list");
                break;
        }
    }

    private void createService(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        ServiceDAO.createService(serviceName, description, image);
        response.sendRedirect("service?action=list");
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("serviceId");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int serviceId = Integer.parseInt(idParam);
                String serviceName = request.getParameter("serviceName");
                String description = request.getParameter("description");
                String image = request.getParameter("image");
                ServiceDAO.updateService(serviceId, serviceName, description, image);
                response.sendRedirect("service?action=list");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID is missing or empty");
        }
    }
       
  

}
