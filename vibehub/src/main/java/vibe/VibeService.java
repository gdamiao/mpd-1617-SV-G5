package main.java.vibe;

import main.java.util.IRequest;
import main.java.vibe.data.LastfmApi;
import main.java.vibe.data.SetListApi;
import main.java.vibe.data.dto.EventDto;
import main.java.vibe.data.dto.LfmArtistDto;
import main.java.vibe.data.dto.LfmTrackDto;
import main.java.vibe.data.dto.VenueDto;
import main.java.vibe.model.Artist;
import main.java.vibe.model.Event;
import main.java.vibe.model.Track;
import main.java.vibe.model.Venue;

import static java.util.Arrays.asList;
import static main.java.util.queries.Queries.map;

/**
 * Created by artur on 04/04/2017.
 */
public class VibeService {

    private final SetListApi setListApi;
    private final LastfmApi lastfmApi;

    public VibeService(SetListApi setListApi, LastfmApi lastfmApi) {
        this.setListApi = setListApi;
        this.lastfmApi = lastfmApi;
    }

    public VibeService(IRequest req) {
        this.setListApi = new SetListApi(req);
        this.lastfmApi = new LastfmApi(req);
    }

    public Iterable<Venue> searchVenues(String query) {
        return map(
                asList(setListApi.getVenues(query)),
                this::dtoToVenue
        );
    }

    private Venue dtoToVenue(VenueDto dto) {
        return new Venue(
                dto.getVenueName(),
                getEvents(dto.getVenueId())
        );
    }

    public Iterable<Event> getEvents(String event) {
        return map(
                asList(setListApi.getEvents(event)),
                this::dtoToEvent
        );
    }

    private Event dtoToEvent(EventDto dto) {
        String artistName = dto.getArtistName();
        String[] tracksNames = dto.getTracksNames();
        return new Event(
                () -> getArtist(artistName),
                artistName,
                dto.getEventDate(),
                dto.getTour(),
                tracksNames,
                asList(getTracks(tracksNames, artistName)),
                dto.getSetListId()
        );
    }

    public Artist getArtist(String artist) {
        return dtoToArtist(lastfmApi.getArtistInfo(artist));
    }

    private Artist dtoToArtist(LfmArtistDto dto) {
        return new Artist(
                dto.getArtistDto().getName(),
                dto.getArtistDto().getBio().getSummary(),
                dto.getArtistDto().getUrl(),
                dto.getArtistDto().getImagesUri(),
                dto.getArtistDto().getMbid()
        );
    }

    public Track[] getTracks(String[] tracks, String artist) {
        Track[] res = new Track[tracks.length];
        int l = 0;
        for (String t :tracks ) {
            res[l] = getTrack(t, artist);
            ++l;
        }
        return res;
    }

    public Track getTrack(String track, String artist) {
        return dtoToTrack(lastfmApi.getTrackInfo(track, artist));
    }

    private Track dtoToTrack(LfmTrackDto dto) {
        return new Track(
                dto.getTrackDto().getName(),
                dto.getTrackDto().getArtistName(),
                dto.getTrackDto().getAlbumName(),
                dto.getTrackDto().getTrackUrl(),
                dto.getTrackDto().getImagesUrl(),
                dto.getTrackDto().getAlbumUrl(),
                dto.getTrackDto().getDuration()
        );
    }
}
