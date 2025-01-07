package com.shoping.mavenproject7.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the current session
        HttpSession httpsession = request.getSession(false); // false to avoid creating a new session

        if (httpsession != null) {
            // Invalidate the session
            httpsession.invalidate();
        }

        // Redirect to index.jsp with a logout message
        request.setAttribute("logoutMessage", "You have successfully logged out.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        return "LogoutServlet that handles user logout and redirects to the homepage";
    }
}
