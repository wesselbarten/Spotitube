package oose.dea.service;

import oose.dea.domain.Track;
import oose.dea.persistence.dao.ITrackDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Rest service voor het ophalen van tracks
 * Requirement IO2 en IN1 worden aangetoond, elke methode is Restful aan te roepen
 */
@Path("/showTracks")
public class TrackService {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Produces("application/json")
    @Path("/notInPlaylist/{playlistId}")
    public List<Track> getTracksNotExistingInPlaylist(@PathParam("playlistId") int playlistId) {
        return trackDAO.findNotExistingInPlaylist(playlistId);
    }

    @GET
    @Produces("application/json")
    @Path("/playlist/{playlistId}")
    public List<Track> getTracksByPlaylistId(@PathParam("playlistId") int playlistId) {
        return trackDAO.findByPlaylistId(playlistId);
    }

    @GET
    @Produces("application/json")
    @Path("/{trackId}")
    public Track getTrackById(@PathParam("trackId") int trackId) {
        return trackDAO.findById(trackId);
    }

    @PUT
    @Consumes("application/json")
    public void addTrackToPlaylist(int trackId, int playlistId) {
        trackDAO.addToPlaylist(trackId, playlistId);
    }
}
