package main.java.vibe;

import main.java.vibe.data.LastfmApi;
import main.java.vibe.data.SetListApi;
import main.java.vibe.model.Artist;
import main.java.vibe.model.Event;
import main.java.vibe.model.Track;
import main.java.vibe.model.Venue;

import java.util.HashMap;

public class VibeServiceCache extends VibeService{

    public VibeServiceCache(SetListApi setListApi, LastfmApi lastfmApi) {
        super(setListApi, lastfmApi);
    }

    private final Map<> cache = new HashMap<>();

    @Override
    public Iterable<Venue> searchVenues(String query) {

    }

    @Override
    public Iterable<Event> getEvents(String query) {

    }

    @Override
    public Artist getArtist(String artist) {

    }

    @Override
    public Track getTrack(String track, String artist) {

    }

    @Override
    public Track[] getTracks(String[] tracks, String artist) {

    }
}
