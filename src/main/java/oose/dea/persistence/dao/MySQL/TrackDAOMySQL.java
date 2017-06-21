package oose.dea.persistence.dao.MySQL;

import oose.dea.domain.Song;
import oose.dea.domain.Track;
import oose.dea.domain.Video;
import oose.dea.persistence.connection.ConnectionFactory;
import oose.dea.persistence.dao.ITrackDAO;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Implementatie voor de MySQL trackDAO
 * Requirement IM1 en D2 worden aangetoond, door het implementeren van de trackDAO interface kan er makkelijk
 * gewisseld worden van relationele database
 */
public class TrackDAOMySQL implements ITrackDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    private List<Track> findByPlaylist(String sqlQuery, int playlistId) {
        List<Track> resultList = new ArrayList<>();
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (Track.VIDEO.equals(resultSet.getString("content_type"))) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(resultSet.getDate("publication_date"));
                    resultList.add(new Video(resultSet.getInt("track_id"), resultSet.getString("name"), resultSet.getString("performer"), resultSet.getLong("duration"), resultSet.getString("url"), resultSet.getString("content_type"), resultSet.getInt("play_count"), cal , resultSet.getString("description")));
                } else if (Track.SONG.equals(resultSet.getString("content_type"))) {
                    resultList.add(new Song(resultSet.getInt("track_id"), resultSet.getString("name"), resultSet.getString("performer"), resultSet.getLong("duration"), resultSet.getString("url"), resultSet.getString("content_type"), resultSet.getString("album"), resultSet.getString("genre")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Tracks could not be retrieved due to persistence problem.", e);
        }
        return resultList;

    }

    public List<Track> findByPlaylistId(int playlistId) {
        String sqlQuery = "SELECT * FROM Tracks t INNER JOIN PlaylistTracks pt ON t.track_id = pt.track_id WHERE pt.playlist_id = ?";
        return findByPlaylist(sqlQuery, playlistId);
    }

    public List<Track> findNotExistingInPlaylist(int playlistId) {
        String sqlQuery = "SELECT * FROM Tracks WHERE track_id NOT IN (SELECT track_id FROM PlaylistTracks WHERE playlist_id = ?)";
        return findByPlaylist(sqlQuery, playlistId);
    }

    public Track findById(int trackId) {
        String sqlQuery = "SELECT * FROM Tracks WHERE track_id = ?";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ) {
            preparedStatement.setInt(1, trackId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (Track.VIDEO.equals(resultSet.getString("content_type"))) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(resultSet.getDate("publication_date"));
                    return new Video(resultSet.getInt("track_id"), resultSet.getString("name"), resultSet.getString("performer"), resultSet.getLong("duration"), resultSet.getString("url"), resultSet.getString("content_type"), resultSet.getInt("play_count"), cal , resultSet.getString("description"));
                } else if (Track.SONG.equals(resultSet.getString("content_type"))) {
                    return new Song(resultSet.getInt("track_id"), resultSet.getString("name"), resultSet.getString("performer"), resultSet.getLong("duration"), resultSet.getString("url"), resultSet.getString("content_type"), resultSet.getString("album"), resultSet.getString("genre"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Tracks could not be retrieved due to persistence problem.", e);
        }
        return null;
    }

    public void addToPlaylist(int trackId, int playlistId) {
        String sqlQuery = "INSERT INTO PlaylistTracks (playlist_id, track_id) VALUES (?, ?)";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
                ){
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Tracks could not be retrieved due to persistence problem.", e);
        }
    }
}
