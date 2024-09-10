package abc_restaurant.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc_restaurant.dao.GalleryDAO;
import abc_restaurant.model.Gallery;

/**
 * Servlet implementation class GalleryServlet
 */
@WebServlet("/gallery")
public class GalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GalleryDAO galleryDAO;

    @Override
    public void init() throws ServletException {
        galleryDAO = new GalleryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list"; 
        }

        switch (action) {
	        case "showGalleryForCustomer": 
	            showGalleryForCustomer(request, response);
	            break;
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteGalleryImage(request, response);
                break;
            case "list":
            default:
                listGalleryImages(request, response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/createGallery.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Gallery gallery = galleryDAO.getGalleryImageById(id);
                if (gallery != null) {
                    request.setAttribute("gallery", gallery);
                    request.getRequestDispatcher("/editGallery.jsp").forward(request, response);
                } else {
                    response.sendRedirect("gallery?action=list&error=Image not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("gallery?action=list&error=Error retrieving image.");
            }
        } else {
            response.sendRedirect("gallery?action=list&error=Invalid image ID.");
        }
    }
    
    
    private void showGalleryForCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Gallery> galleryList = galleryDAO.getAllGalleryImages();
            request.setAttribute("galleryList", galleryList);
            request.getRequestDispatcher("/galleryCustomer.jsp").forward(request, response); 
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving gallery.");
        }
    }

    private void listGalleryImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Gallery> galleryList = galleryDAO.getAllGalleryImages();
            request.setAttribute("galleryList", galleryList);
            request.getRequestDispatcher("/galleryList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error retrieving gallery images.");
        }
    }

    private void deleteGalleryImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                galleryDAO.deleteGalleryImage(id);
                response.sendRedirect("gallery?action=list&success=Image deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("gallery?action=list&error=Error deleting image.");
            }
        } else {
            response.sendRedirect("gallery?action=list&error=Invalid image ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createGalleryImage(request, response);
                break;
            case "update":
                updateGalleryImage(request, response);
                break;
            default:
                response.sendRedirect("gallery?action=list");
                break;
        }
    }

    protected void createGalleryImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");

        if (name == null || name.trim().isEmpty() || image == null || image.trim().isEmpty()) {
            response.sendRedirect("gallery?action=create&error=Please provide both name and image.");
            return;
        }

        Gallery newGallery = new Gallery(name, image);
        
        try {
            galleryDAO.createGallery(newGallery);
            response.sendRedirect("gallery?action=list&success=Gallery item created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("gallery?action=create&error=Error creating gallery item.");
        }
    }

    private void updateGalleryImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String image = request.getParameter("image");

        if (idStr != null && !idStr.isEmpty() && name != null && !name.isEmpty() && image != null && !image.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Gallery gallery = new Gallery(id, name, image);
                galleryDAO.updateGalleryImage(gallery);
                response.sendRedirect("gallery?action=list&success=Image updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("gallery?action=list&error=Error updating image.");
            }
        } else {
            response.sendRedirect("gallery?action=list&error=Name and image are required.");
        }
    }

}
