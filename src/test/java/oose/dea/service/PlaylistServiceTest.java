package oose.dea.service;

import oose.dea.domain.Playlist;
import oose.dea.persistence.dao.IPlaylistDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test voor de PlaylistService
 * Requirement T1 wordt aangetoond d.m.v. deze test
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Mock
    private Playlist playlist;

    @Mock
    private IPlaylistDAO playlistDAO;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    public void testAddPlaylist() {
        playlistService.addPlaylist(playlist);
        verify(playlistDAO, times(1)).addPlaylist(playlist);
    }

    @Test
    public void testGetPlaylistByName() {
        when(playlistDAO.findByName("Party")).thenReturn(playlist);
        Playlist res = playlistService.getPlaylistByName("Party");
        verify(playlistDAO, times(1)).findByName("Party");
        assertEquals(res, playlist);
    }
    @Test
    public void testGetPlaylistsByUser() {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);
        when(playlistDAO.findByUserId(1)).thenReturn(playlists);
        List<Playlist> res = playlistService.getPlaylistsByUser(1);
        verify(playlistDAO, times(1)).findByUserId(1);
        assertEquals(res, playlists);
    }

    @Test
    public void testDeletePlaylist() {
        playlistService.deletePlaylist("Party");
        verify(playlistDAO, times(1)).delete("Party");
    }

    @Test
    public void testEditPlaylistName() {
        playlistService.editPlaylistName("Party", "Party");
        verify(playlistDAO, times(1)).updateName("Party", "Party");
    }
}
