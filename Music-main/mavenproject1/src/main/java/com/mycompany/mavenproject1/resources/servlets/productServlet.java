package com.shoping.mavenproject7.servlets;

import com.shoping.mavenproject7.Dao.CategoryDao;
import com.shoping.mavenproject7.Dao.ProductDao;
import com.shoping.mavenproject7.entities.Category;
import com.shoping.mavenproject7.entities.Product;
import com.shoping.mavenproject7.helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MultipartConfig
public class productServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory factory = FactoryProvider.getfactory();
        Session session = factory.openSession();

        String message = null;

        try {
            String open = request.getParameter("Open");

            if ("Add Product".equalsIgnoreCase(open)) {
                String pName = request.getParameter("productName");
                String pDesc = request.getParameter("productDescription");
                String priceParam = request.getParameter("productPrice");
                String discountParam = request.getParameter("productDiscount");
                String quantityParam = request.getParameter("productQuantity");
                String catIdParam = request.getParameter("categoryId");

                if (pName != null && pDesc != null && priceParam != null && discountParam != null
                        && quantityParam != null && catIdParam != null) {
                    try {
                        int pPrice = Integer.parseInt(priceParam);
                        int pDisc = Integer.parseInt(discountParam);
                        int pQuantity = Integer.parseInt(quantityParam);
                        int catId = Integer.parseInt(catIdParam);

                        Part part = request.getPart("productImage"); // Get image part
                        String imagePath = saveImage(part); // Save the image and get its path

                        Product p = new Product();
                        p.setpName(pName);
                        p.setpDisc(pDesc);
                        p.setpPrice(pPrice);
                        p.setpDiscount(pDisc);
                        p.setpQuantity(pQuantity);
                        p.setpPhoto(imagePath); // Save the image path in the product

                        // Get category by ID
                        CategoryDao cDao = new CategoryDao(factory);
                        Category category = cDao.getCategoryById(catId);
                        p.setCategory(category);

                        // ProductDao
                        ProductDao pDao = new ProductDao(factory);
                        session.beginTransaction();
                        pDao.saveProduct(p);

                        session.getTransaction().commit();
                        message = "Product saved successfully with name: " + pName; // Set success message
                    } catch (Exception e) {
                        if (session.getTransaction() != null) {
                            session.getTransaction().rollback();
                        }
                        e.printStackTrace();
                        message = "Error saving product: " + e.getMessage(); // Set error message
                    }
                } else {
                    message = "Product details are missing.";
                }
            } else {
                message = "Operation is not defined for products.";
            }
        } finally {
            session.close();
        }

        // Fetch total products to display on admin page
        List<Product> products = new ProductDao(factory).getAllProducts();
        int totalProducts = products.size();

        // Set message and total products as request attributes
        request.setAttribute("message", message);
        request.setAttribute("totalProducts", totalProducts);
        // Forward the request to admin.jsp
        request.getRequestDispatcher("Admin.jsp").forward(request, response);
    }

    // Helper method to save image
    private String saveImage(Part part) throws IOException {
        String fileName = part.getSubmittedFileName(); // Get the original file name
        String imagePath = getServletContext().getRealPath("Image" + File.separator + "product") 
                + File.separator + fileName; // Build the absolute path to save the image

        // Ensure the directory exists, create it if not
        File directory = new File(getServletContext().getRealPath("Image" + File.separator + "product"));
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        // Save the uploaded file
        File file = new File(imagePath);
        part.write(file.getAbsolutePath()); // Write the file to the disk

        // Return the relative path to store in the database
        return "Image/product/" + fileName; // Return only the relative path for the image
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
        return "Product Operation Servlet";
    }
}