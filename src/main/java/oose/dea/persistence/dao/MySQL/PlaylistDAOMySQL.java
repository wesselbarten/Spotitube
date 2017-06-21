package oose.dea.persistence.dao.MySQL;

import oose.dea.domain.Playlist;
import oose.dea.persistence.connection.ConnectionFactory;
import oose.dea.persistence.dao.IPlaylistDAO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementatie voor de MySQL playlistDAO
 * Requirement IM1 en D2 worden aangetoond, door het implementeren van de playlistDAO interface kan er makkelijk
 * gewisseld worden van relationele database
 */
public class PlaylistDAOMySQL implements IPlaylistDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public void addPlaylist(Playlist playlist) {
        String sqlQuery = "INSERT INTO Playlists (owner, name, creation_date, offline_available) VALUES (?, ?, ?, ?)";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
                ) {
            preparedStatement.setInt(1, playlist.getOwner());
            preparedStatement.setString(2, playlist.getName());
            preparedStatement.setDate(3, new Date(playlist.getCreationDate().getTime()));
            preparedStatement.setBoolean(4, playlist.isOfflineAvailable());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be inserted due to database problem.", e);
        }
    }

    public Playlist findByName(String name) {
        String sqlQuery = "SELECT * FROM Playlists WHERE name=?";
        Playlist playlist = null;
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
                ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playlist = new Playlist(resultSet.getInt("owner"), resultSet.getString("name"), resultSet.getDate("creation_date"), resultSet.getBoolean("offline_available"));
                playlist.setPlaylistId(resultSet.getInt("playlist_id"));
                // return playlist;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be retrieved due to persistence problem.", e);
        }
        return playlist;
    }

    public List<Playlist> findByUserId(int userId) {
        List<Playlist> resultList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Playlists WHERE owner=?";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
                ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Playlist playlist = new Playlist(resultSet.getInt("owner"), resultSet.getString("name"), resultSet.getDate("creation_date"), resultSet.getBoolean("offline_available"));
                playlist.setPlaylistId(resultSet.getInt("playlist_id"));
                resultList.add(playlist);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be retrieved due to persistence problem.", e);
        }
        return resultList;
    }

    public void delete(String name) {
        String sqlQuery = "DELETE FROM Playlists WHERE name = ?";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
                ){
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be retrieved due to persistence problem.", e);
        }
    }

    public void updateName(String oldName, String name) {
        String sqlQuery = "UPDATE Playlists SET name = ? WHERE name = ?";
        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
                ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, oldName);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be retrieved due to persistence problem.", e);
        }
    }

    public String findTotalDurationAllPlaylists(int userId) {
        String sqlQuery = "SELECT user_id, SEC_TO_TIME(total_duration) AS total_duration FROM UsersWithTotalPlaylistDurationView WHERE user_id = ?";

        try (
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("total_duration");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Playlist could not be retrieved due to persistence problem.", e);
        }

        return null;
    }
}
