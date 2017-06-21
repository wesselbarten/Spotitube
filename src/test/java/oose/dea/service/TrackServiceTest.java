package oose.dea.service;

import oose.dea.domain.Track;
import oose.dea.persistence.dao.ITrackDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test voor de TrackService
 * Requirement T1 wordt aangetoond d.m.v. deze test
 */
@RunWith(MockitoJUnitRunner.class)
public class TrackServiceTest {

    @Mock
    private Track track;

    @Mock
    private ITrackDAO trackDAO;

    @InjectMocks
    private TrackService trackService;

    @Test
    public void testGetTracksByPlaylistId() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        when(trackDAO.findByPlaylistId(1)).thenReturn(tracks);
        List<Track> res = trackService.getTracksByPlaylistId(1);
        verify(trackDAO, times(1)).findByPlaylistId(1);
        assertEquals(res, tracks);
    }

    @Test
    public void testGetTracks() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        when(trackDAO.findNotExistingInPlaylist(1)).thenReturn(tracks);
        List<Track> res = trackService.getTracksNotExistingInPlaylist(1);
        verify(trackDAO, times(1)).findNotExistingInPlaylist(1);
        assertEquals(res, tracks);
    }

    @Test
    public void testGetTrackById() {
        when(trackDAO.findById(1)).thenReturn(track);
        Track res = trackService.getTrackById(1);
        verify(trackDAO, times(1)).findById(1);
        assertEquals(res, track);
    }

    @Test
    public void testAddTrackToPlaylist() {
        trackService.addTrackToPlaylist(1,1);
        verify(trackDAO, times(1)).addToPlaylist(1,1);
    }
}
