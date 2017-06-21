package oose.dea.domain;

import java.util.Date;

public class Playlist {

    private int owner;
    private int playlistId;
    private String name;
    private Date creationDate;
    private boolean offlineAvailable;

    public Playlist(int owner, String name, Date creationDate, boolean offlineAvailable) {
        this.owner = owner;
        this.name = name;
        this.creationDate = creationDate;
        this.offlineAvailable = offlineAvailable;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getOwner() {
        return owner;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }
}
