package oose.dea.presentation.controller;

import oose.dea.domain.Track;
import oose.dea.domain.User;
import oose.dea.presentation.model.TrackModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet voor toevoegen van tracks aan een playlist
 * Requirement M2 wordt aangetoond, Servlets bevatten geen markup, alleen Java-code
 */
@WebServlet("/addtracks")
public class AddTrackViewPageController extends HttpServlet {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private TrackModel trackModel;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            List<Track> tracks = trackModel.getTracksNotExistingInPlaylist(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("tracks", tracks);
            request.getRequestDispatcher("addTrackView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (request.getParameter("addTrackSubmit") != null) {
                trackModel.addTrackToPlaylist(trackModel.getTrackById(Integer.parseInt(request.getParameter("trackId"))).getTrackId(), Integer.parseInt(request.getParameter("id")));
            }
            List<Track> tracks = trackModel.getTracksNotExistingInPlaylist(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("tracks", tracks);
            request.getRequestDispatcher("addTrackView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
