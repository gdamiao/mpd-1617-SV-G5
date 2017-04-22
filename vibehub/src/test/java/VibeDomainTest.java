package test.java;

import com.google.gson.Gson;
import main.java.util.HttpRequest;
import main.java.util.IRequest;
import main.java.vibe.data.dto.EventDto;
import main.java.vibe.data.dto.LfmArtistDto;
import main.java.vibe.data.dto.LfmTrackDto;
import main.java.vibe.data.dto.VenueDto;
import org.junit.Test;

import static main.java.util.queries.Queries.join;

public class VibeDomainTest {

    @Test
    public void testGetEvent() {
        Gson gson = new Gson();
        String uri = "";
        IRequest req = new HttpRequest();
        Iterable<String> body = req.getContent(uri);
        String json = join(body);
        EventDto dto = gson.fromJson(json, EventDto.class);
        System.out.println(dto);
    }

    @Test
    public void testGetVenue() {
        Gson gson = new Gson();
        String uri = "";
        IRequest req = new HttpRequest();
        Iterable<String> body = req.getContent(uri);
        String json = join(body);
        VenueDto dto = gson.fromJson(json, VenueDto.class);
        System.out.println(dto);
    }

    @Test
    public void testGetArtist() {
        Gson gson = new Gson();
        String uri = "";
        IRequest req = new HttpRequest();
        Iterable<String> body = req.getContent(uri);
        String json = join(body);
        LfmArtistDto dto = gson.fromJson(json, LfmArtistDto.class);
        System.out.println(dto);
    }

    @Test
    public void testGetTrack() {
        Gson gson = new Gson();
        String uri = "";
        IRequest req = new HttpRequest();
        Iterable<String> body = req.getContent(uri);
        String json = join(body);
        LfmTrackDto dto = gson.fromJson(json, LfmTrackDto.class);
        System.out.println(dto);
    }

    @Test
    public void testVibeServiceLazy() {

    }

    @Test
    public void testVibeServiceLazyAndCache() {

    }
}
