package oose.dea.presentation.controller;

import oose.dea.domain.Playlist;
import oose.dea.domain.Track;
import oose.dea.presentation.model.PlaylistModel;
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
 * Servlet voor het bekijken van een playlist, met de daar bijhorende tracks
 * Requirement M2 wordt aangetoond, Servlets bevatten geen markup, alleen Java-code
 */
@WebServlet("/playlist")
public class PlaylistDetailViewPageController extends HttpServlet {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private PlaylistModel playlistModel;

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private TrackModel trackModel;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        if (name != null) {
            Playlist playlist = playlistModel.getPlaylistByName(name);
            List<Track> tracks = trackModel.getTracksByPlaylistId(playlist.getPlaylistId());
            request.setAttribute("playlist", playlist);
            request.setAttribute("tracks", tracks);
            request.getRequestDispatcher("playlistDetailView.jsp").forward(request, response);
        } else {
            response.sendRedirect("/playlists");
        }
    }
}
