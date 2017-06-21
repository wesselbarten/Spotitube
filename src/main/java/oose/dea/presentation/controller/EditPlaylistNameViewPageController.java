package oose.dea.presentation.controller;

import oose.dea.domain.Playlist;
import oose.dea.presentation.model.PlaylistModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet voor het wijzigen van de naam van een playlist
 * Requirement M2 wordt aangetoond, Servlets bevatten geen markup, alleen Java-code
 */
@WebServlet("/editplaylistname")
public class EditPlaylistNameViewPageController extends HttpServlet {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private PlaylistModel playlistModel;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") != null) {
            Playlist playlist = playlistModel.getPlaylistByName(request.getParameter("name"));
            request.setAttribute("playlist", playlist);
            request.getRequestDispatcher("editPlaylistNameView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            String oldName = request.getParameter("oldPlaylistName");
            String name = request.getParameter("playlistName");
            if (oldName != null && name != null) {
                playlistModel.editPlaylistName(oldName, name);
            }
            response.sendRedirect("/playlists");
        } else {
            response.sendRedirect("/login");
        }

    }
}
