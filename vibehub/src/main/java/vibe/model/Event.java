package main.java.vibe.model;


import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Created by artur on 04/04/2017.
 */
public class Event {
    private final Supplier<Artist> artist;//TO TEST
    private final String artistName;
    private final String eventDate;
    private final String tour;
    private final String[] tracksNames;
    private final Iterable<Track> tracks;
    private final String setListId;

    public Event(Supplier<Artist> artist, String artistName, String eventDate, String tour, String[] tracksNames, Iterable<Track> tracks, String setListId) {
        this.artist = artist;
        this.artistName = artistName;
        this.eventDate = eventDate;
        this.tour = tour;
        this.tracksNames = tracksNames;
        this.tracks = tracks;
        this.setListId = setListId;
    }

    public String getArtist() {

    }

    public String getArtistName() {
        return artistName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getTour() {
        return tour;
    }

    public String[] getTracksNames() {
        return tracksNames;
    }

    public Iterable<Track> getTracks() {
        return tracks;
    }

    public String getSetListId() {
        return setListId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "" +
                "artistName='" + artistName + '\'' +
                "eventDate='" + eventDate + '\'' +
                "tour='" + tour + '\'' +
                "tracksNames" + Arrays.toString(tracksNames) +
                "tracks=" + tracks +
                "setListId='" + setListId + '\'' +
                "}";
    }
}
