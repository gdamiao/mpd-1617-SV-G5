package main.java.vibe;

import main.java.vibe.data.LastfmApi;
import main.java.vibe.data.SetListApi;
import main.java.vibe.model.Artist;
import main.java.vibe.model.Event;
import main.java.vibe.model.Track;
import main.java.vibe.model.Venue;

import java.util.HashMap;
import java.util.Map;

import static main.java.util.queries.Queries.*;

public class VibeServiceCache extends VibeService{

    public VibeServiceCache(SetListApi setListApi, LastfmApi lastfmApi) {
        super(setListApi, lastfmApi);
    }

    private final Map<String, Map<String, Venue>> venuesCache = new HashMap<>();
    private final Map<String, Map<String, Event>> eventsCache = new HashMap<>();
    private final Map<String, Artist> artistsCache = new HashMap<>();
    private final Map<TrackInfo, Map<String, Track>> tracksCache = new HashMap<>();

    @Override
    public Iterable<Venue> searchVenues(String query) {
        Map<String, Venue> venues = getOrCreateVenue(query, venuesCache);
        return () -> {
            if(!venues.isEmpty()) return venues.values().iterator();
            Iterable<Venue> values = super.searchVenues(query);
            return peek(values, item -> venues.put(item.getName(), item)).iterator();
        };
    }

    @Override
    public Iterable<Event> getEvents(String query) {
        Map<String, Event> events = getOrCreateEvent(query, eventsCache);
        return () -> {
            if(!events.isEmpty()) return events.values().iterator();
            Iterable<Event> values = super.getEvents(query);
            return peek(values, item -> events.put(item.getSetListId(), item)).iterator();
        };
    }

    @Override
    public Artist getArtist(String artist) {
        return getOrCreateArtist(artist, artistsCache);

    }

    @Override
    public Track getTrack(String track, String artist) {
        TrackInfo trackInfo = new TrackInfo(artist, track);
        Map<String, Track> tr = getOrCreateTrack(trackInfo, tracksCache);
        if(tr.keySet().contains(track)) return tr.get(track);
        Track t = super.getTrack(track, artist);
        tr.put(t.getName(), t);
        return t;
    }

    private Map<String, Venue> getOrCreateVenue(String query, Map<String, Map<String, Venue>> cache) {
        Map<String, Venue> venues = cache.get(query);
        if(venues == null) {
            venues = new HashMap<>();
            cache.put(query, venues);
        }
        return venues;
    }

    private Map<String, Event> getOrCreateEvent(String query, Map<String, Map<String, Event>> cache) {
        Map<String, Event> events = cache.get(query);
        if(events == null) {
            events = new HashMap<>();
            cache.put(query, events);
        }
        return events;
    }

    private Artist getOrCreateArtist(String artistName, Map<String, Artist> cache) {
        Artist artist = cache.get(artistName);
        if(artist == null) {
            artist = super.getArtist(artistName);
            cache.put(artist.getName(), artist);
        }
        return artist;
    }

    private Map<String, Track> getOrCreateTrack(TrackInfo trackInfo, Map<TrackInfo, Map<String, Track>> cache) {
        Map<String, Track> tracks = cache.get(trackInfo);
        if(tracks == null) {
            tracks = new HashMap<>();
            cache.put(trackInfo, tracks);
        }
        return tracks;
    }
}

class TrackInfo {
    final String artistName;
    final String trackName;

    public TrackInfo(String artistName, String trackName) {
        this.artistName = artistName;
        this.trackName = trackName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackInfo trackInfo = (TrackInfo) o;

        if (!(trackInfo.artistName.equals(this.artistName) && trackInfo.trackName.equals(this.trackName))) return false;
        return trackInfo.artistName.equals(this.artistName) && trackInfo.trackName.equals(this.trackName);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int res = 1;
        res = (res * prime + artistName.hashCode())* prime + trackName.hashCode();
        return res;
    }
}
