package oose.dea.presentation.model;

import oose.dea.domain.Playlist;
import oose.dea.service.PlaylistService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistModel {

    /**
     * Requirement IM2 wordt aangetoond, creatie van classes vindt zoveel mogelijk plaats op basis van het DIP-principe
     */
    @Inject
    private PlaylistService playlistService;

    public void addPlaylist(Playlist playlist) {
        playlistService.addPlaylist(playlist);
    }

    public Playlist getPlaylistByName(String playlistName) {
        return playlistService.getPlaylistByName(playlistName);
    }

    public List<Playlist> getPlaylists(int userId) {
        return playlistService.getPlaylistsByUser(userId);
    }

    public void deletePlaylist(String name) {
        playlistService.deletePlaylist(name);
    }

    public void editPlaylistName(String oldName, String name) {
        playlistService.editPlaylistName(oldName, name);
    }

    public String getTotalDurationAllPlaylists(int userId) {
        return playlistService.getTotalDurationAllPlaylists(userId);
    }
}
