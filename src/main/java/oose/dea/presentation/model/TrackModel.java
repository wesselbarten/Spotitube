package oose.dea.presentation.model;

import oose.dea.domain.Track;
import oose.dea.service.TrackService;

import javax.inject.Inject;
import java.util.List;

/**
 * Model for the Track domain
 */
public class TrackModel {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private TrackService trackService;

    public List<Track> getTracksNotExistingInPlaylist(int playlistId) {
        return trackService.getTracksNotExistingInPlaylist(playlistId);
    }

    public List<Track> getTracksByPlaylistId(int playlistId) {
        return trackService.getTracksByPlaylistId(playlistId);
    }

    public Track getTrackById(int trackId) {
        return trackService.getTrackById(trackId);
    }

    public void addTrackToPlaylist(int trackId, int playlistId) {
        trackService.addTrackToPlaylist(trackId, playlistId);
    }
}
