package oose.dea.domain;

public class Song extends Track {

    private String album, genre;

    public Song(int trackId, String name, String performer, long duration, String url, String contentType, String album, String genre) {
        super(trackId, name, performer, duration, url, contentType);
        this.album = album;
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

}
