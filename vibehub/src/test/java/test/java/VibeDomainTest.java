package test.java;

import main.java.util.HttpRequest;
import main.java.util.IRequest;
import main.java.vibe.VibeService;
import main.java.vibe.VibeServiceCache;
import main.java.vibe.data.LastfmApi;
import main.java.vibe.data.SetListApi;
import main.java.vibe.model.Artist;
import main.java.vibe.model.Event;
import main.java.vibe.model.Track;
import main.java.vibe.model.Venue;
import org.junit.Test;

public class VibeDomainTest {

    @Test
    public void testVibeService() {
        IRequest req = new HttpRequest();
        VibeService api = new VibeService(new SetListApi(req), new LastfmApi(req));
        Artist artist = api.getArtist("Cher");
        Track track = api.getTrack("believe", "Cher");
        System.out.println(artist);
        System.out.println(track);
        Iterable<Venue> venues = api.searchVenues("Lisboa");
        for (Venue v :
                venues) {
            System.out.println(v);
        }
        Iterable<Event> events = api.getEvents("13d67585");
        for (Event e :
                events) {
            System.out.println(e);
        }
    }

    @Test
    public void testVibeServiceCache() {
        IRequest req = new HttpRequest();
        VibeService api = new VibeServiceCache(new SetListApi(req), new LastfmApi(req));
        Artist artist = api.getArtist("Cher");
        Track track = api.getTrack("believe", "Cher");
        System.out.println(artist);
        System.out.println(track);
        Iterable<Venue> venues = api.searchVenues("Lisboa");
        for (Venue v :
                venues) {
            System.out.println(v);
        }
        Iterable<Event> events = api.getEvents("13d67585");
        for (Event e :
                events) {
            System.out.println(e);
        }
    }
}
