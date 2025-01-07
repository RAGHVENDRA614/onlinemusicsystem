package com.shoping.mavenproject7.servlets;

import com.shoping.mavenproject7.entities.User;
import com.shoping.mavenproject7.helper.FactoryProvider;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServlet extends HttpServlet {

    // Regular expression for validating email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the registration form (Register.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Processes registration requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve user input
        String userName = request.getParameter("user_name");
        String userEmail = request.getParameter("user_email");
        String userPassword = request.getParameter("user_password");
        String userPhone = request.getParameter("user_phone");
        String userAddress = request.getParameter("user_address");

        // Perform validation
        StringBuilder validationErrors = new StringBuilder();

        if (userName == null || userName.trim().isEmpty()) {
            validationErrors.append("Username is required.<br>");
        }
        if (userEmail == null || !Pattern.matches(EMAIL_REGEX, userEmail)) {
            validationErrors.append("Invalid email format.<br>");
        }
        if (userPassword == null || userPassword.length() < 6) {
            validationErrors.append("Password must be at least 6 characters long.<br>");
        }
        if (userPhone == null || userPhone.trim().isEmpty()) {
            validationErrors.append("Phone number is required.<br>");
        }
        if (userAddress == null || userAddress.trim().isEmpty()) {
            validationErrors.append("Address is required.<br>");
        }

        // If there are validation errors, return them to the user
        if (validationErrors.length() > 0) {
            request.setAttribute("errorMessage", validationErrors.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Attempt to register the user
        try {
            User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, "Normal");
            Session hibernateSession = FactoryProvider.getfactory().openSession();
            Transaction tx = hibernateSession.beginTransaction();

            // Save the user to the database
            hibernateSession.save(user);
            tx.commit();
            hibernateSession.close();

            // Set a success message to the request scope
            request.setAttribute("message", "Registration Successful!");

            // Forward to the index.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration Failed! Please try again later.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
