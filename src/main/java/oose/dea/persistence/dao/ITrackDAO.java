package oose.dea.persistence.dao;

import oose.dea.domain.Track;

import java.util.List;

/**
 * Interface voor de trackDAO
 * Requirement IM1 wordt aangetoond, deze interface maakt het mogelijk om makkelijk van relationele database te wisselen
 */
public interface ITrackDAO {

    List<Track> findByPlaylistId(int playlistId);

    List<Track> findNotExistingInPlaylist(int playlistId);

    Track findById(int trackId);

    void addToPlaylist(int trackId, int playlistId);
}
