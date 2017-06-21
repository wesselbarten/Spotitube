package oose.dea.persistence.dao;

import oose.dea.domain.Playlist;

import java.util.List;

/**
 * Interface voor de playlistDAO
 * Requirement IM1 wordt aangetoond, deze interface maakt het mogelijk om makkelijk van relationele database te wisselen
 */
public interface IPlaylistDAO {

    void addPlaylist(Playlist playlist);

    Playlist findByName(String name);

    List<Playlist> findByUserId(int userId);

    void delete(String name);

    void updateName(String oldName, String name);

    String findTotalDurationAllPlaylists(int userId);
}
