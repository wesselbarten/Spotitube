package oose.dea.presentation.controller;


import oose.dea.domain.User;
import oose.dea.presentation.model.UserModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet voor het inloggen van de gebruiker
 * Requirement M2 wordt aangetoond, Servlets bevatten geen markup, alleen Java-code
 */
@WebServlet("/login")
public class LoginViewPageController extends HttpServlet {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private UserModel userModel;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("loginView.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().containsKey("email") && request.getParameterMap().containsKey("password")) {

            User user = userModel.getUserByEmailAndPassword(request.getParameter("email"), request.getParameter("password"));
            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("playlists");
            }

        }
    }
}
