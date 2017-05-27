package main.java.vibe.model;


import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Created by artur on 04/04/2017.
 */
public class Event {
    private final Supplier<Artist> artist;
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

    public Artist getArtist() {
        return artist.get();
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
        return "Event{\n" +
                "Artist=\"" + getArtist() + "\"\n" +
                "Artist Name=\"" + getArtistName() + "\"\n" +
                "Event Date=\"" + getEventDate() + "\"\n" +
                "Tour='" + getTour() + "\"\n" +
                "Tracks Names" + Arrays.toString(getTracksNames()) + "\n" +
                "Tracks=" + getTracks() + "\n" +
                "SetListId=\"" + getSetListId() + "\"\n" +
                "}";
    }
}
