package oose.dea.domain;

public class Track {

    public static final String VIDEO = "VIDEO";
    public static final String SONG = "SONG";

    private int trackId;
    private String performer, name, url, contentType;
    private long duration;

    public Track(int trackId, String name, String performer, long duration, String url, String contentType) {
        this.trackId = trackId;
        this.name = name;
        this.performer = performer;
        this.duration = duration;
        this.url = url;
        this.contentType = contentType;
    }

    public int getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public String getPerformer() {
        return performer;
    }

    public String getContentType() {
        return contentType;
    }
}
