package com.shoping.mavenproject7.servlets;

import com.shoping.mavenproject7.Dao.UserDao;
import com.shoping.mavenproject7.entities.User;
import com.shoping.mavenproject7.helper.FactoryProvider;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession httpSession = request.getSession();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Basic validation
        if (email == null || email.isEmpty()) {
            request.setAttribute("message", "Email cannot be empty.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (password == null || password.isEmpty()) {
            request.setAttribute("message", "Password cannot be empty.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        UserDao userDao = new UserDao(FactoryProvider.getfactory());
        User user = userDao.getUserByEmailAndPassword(email, password);

        // Debugging output for user type
        if (user != null) {
            System.out.println("User Type: " + user.getUserType()); // Debug output

            // Successful login
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("welcomeMessage", "Welcome " + user.getUserName() + "!");

            // Check user type and redirect accordingly
            if (user.getUserType().equalsIgnoreCase("Admin")) {
                response.sendRedirect("Admin.jsp");
            } else if (user.getUserType().equalsIgnoreCase("Normal")) {
                response.sendRedirect("Normal.jsp");
            } else {
                // Handle unexpected user types
                request.setAttribute("message", "Unrecognized user type.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Handle invalid login
            request.setAttribute("message", "Invalid details. Try another email.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
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
        return "Short description";
    }
}
