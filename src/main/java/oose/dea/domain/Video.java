package oose.dea.domain;

import java.util.Calendar;

public class Video extends Track {

    private int playCount;
    private Calendar publicationDate;
    private String description;


    public Video(int trackId, String name, String performer, long duration, String url, String contentType, int playCount, Calendar publicationDate, String description) {
        super(trackId, name, performer, duration, url, contentType);
        this.playCount = playCount;
        this.publicationDate = publicationDate;
        this.description = description;
    }
}
