package oose.dea.presentation.controller;

import oose.dea.domain.Playlist;
import oose.dea.domain.User;
import oose.dea.presentation.model.PlaylistModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Servlet voor het bekijken van alle playlists van een gebruiker
 * Requirement M2 wordt aangetoond, Servlets bevatten geen markup, alleen Java-code
 */
@WebServlet("/playlists")
public class PlaylistsViewPageController extends HttpServlet {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private PlaylistModel playlistModel;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Playlist> playlists = playlistModel.getPlaylists(user.getUserId());
            String totalPlaylistDuration = playlistModel.getTotalDurationAllPlaylists(user.getUserId());
            request.setAttribute("allPlaylists", playlists);
            request.setAttribute("totalPlaylistDuration", totalPlaylistDuration);
            request.getRequestDispatcher("playlistsView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (request.getParameter("addPlaylistSubmit") != null) {
                Playlist playlist = new Playlist(user.getUserId(), request.getParameter("newPlaylistName"), new Date(), false);
                playlistModel.addPlaylist(playlist);
            } else if (request.getParameter("deletePlaylistSubmit") != null) {
                playlistModel.deletePlaylist(request.getParameter("playlistName"));
            }
            List<Playlist> playlists = playlistModel.getPlaylists(user.getUserId());
            String totalPlaylistDuration = playlistModel.getTotalDurationAllPlaylists(user.getUserId());
            request.setAttribute("allPlaylists", playlists);
            request.setAttribute("totalPlaylistDuration", totalPlaylistDuration);
            request.getRequestDispatcher("playlistsView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
