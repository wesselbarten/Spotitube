package oose.dea.service;

import oose.dea.domain.Playlist;
import oose.dea.persistence.dao.IPlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Rest service voor het ophalen, wijzigen en verwijderen van playlists
 * Requirement IO2 en IN1 worden aangetoond, elke methode is Restful aan te roepen
 */
@Path("/showPlaylists")
public class PlaylistService {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private IPlaylistDAO playlistDAO;

    @PUT
    @Consumes("application/json")
    public void addPlaylist(Playlist playlist) {
        playlistDAO.addPlaylist(playlist);
    }

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    public Playlist getPlaylistByName(@PathParam("name") String playlistname) {
        return playlistDAO.findByName(playlistname);
    }

    @GET
    @Path("/user/{userId}")
    @Produces("application/json")
    public List<Playlist> getPlaylistsByUser(@PathParam("userId") int userId) {
        return playlistDAO.findByUserId(userId);
    }

    @DELETE
    @Consumes("application/json")
    public void deletePlaylist(String name) {
        playlistDAO.delete(name);
    }

    @PUT
    @Consumes("application/json")
    public void editPlaylistName(String oldName, String name) {
        playlistDAO.updateName(oldName, name);
    }

    @GET
    @Path("/playlistDuration/{userId}")
    public String getTotalDurationAllPlaylists(@PathParam("userId") int userId) {
        return playlistDAO.findTotalDurationAllPlaylists(userId);
    }
}
