package com.shoping.mavenproject7.servlets;

import com.shoping.mavenproject7.Dao.CategoryDao;
import com.shoping.mavenproject7.entities.Category;
import com.shoping.mavenproject7.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SessionFactory factory = FactoryProvider.getfactory();
        Session session = factory.openSession();

        try (PrintWriter out = response.getWriter()) {
            String operation = request.getParameter("operation");

            if ("Add Category".equalsIgnoreCase(operation)) {
                String title = request.getParameter("categoryTitle");
                String description = request.getParameter("categoryDescription");

                if (title != null && description != null) {
                    // Create a new Category object
                    Category category = new Category();
                    category.setCategoryTitle(title);
                    category.setCategoryDescription(description);

                    CategoryDao categoryDao = new CategoryDao(factory);

                    try {
                        session.beginTransaction(); // Start transaction
                        int catId = categoryDao.saveCategory(category); // Save category
                        session.getTransaction().commit(); // Commit the transaction
                        out.println("Category saved with ID: " + catId);
                    } catch (Exception e) {
                        if (session.getTransaction() != null) {
                            session.getTransaction().rollback(); // Rollback in case of an error
                        }
                        e.printStackTrace();
                        out.println("Error saving category: " + e.getMessage());
                    }
                } else {
                    out.println("Category title or description is missing.");
                }
            } else {
                out.println("Operation is not defined for categories.");
            }
        } finally {
            session.close(); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Category Operation Servlet";
    }
}
