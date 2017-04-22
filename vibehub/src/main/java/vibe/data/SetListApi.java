package main.java.vibe.data;

import com.google.gson.Gson;
import main.java.util.IRequest;
import main.java.vibe.data.dto.EventDto;
import main.java.vibe.data.dto.VenueDto;

import java.io.*;
import java.net.URL;

import static main.java.util.queries.Queries.join;
import static main.java.util.queries.Queries.map;

/**
 * Created by artur on 04/04/2017.
 */
public class SetListApi {

    private static final String SETLIST_TOKEN;
    private static final String SETLIST_HOST = "https://api.setlist.fm";
    private static final String SETLIST_VENUE = "/rest/0.1/search/venues";
    private static final String SETLIST_VENUE_ARGS = "&name=%s&cityName=%s&cityId=%s&stateCode=%s&state=%s&country=%s";
    private static final String SETLIST_NUM_PAGS = "&p=%s";
    private static final String SETLIST_EVENT = "/rest/0.1/venue/%s/setlists.xml";
    private static final String SETLIST_EVENT_ARGS = "&venueId=%s";

    static {
        try {
            URL keyFile = ClassLoader.getSystemResource("setlist-app-key.txt");
            if(keyFile == null) {
                throw new IllegalStateException(
                        "YOU MUST GOT a KEY in api.setlist.fm and place it in src/main/resources/setlist-app-key.txt");
            } else {
                InputStream keyStream = keyFile.openStream();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(keyStream))) {
                    SETLIST_TOKEN = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private final IRequest req;
    private final Gson gson = new Gson();

    public SetListApi(IRequest req) {
        this.req = req;
    }

    public VenueDto[] getVenues(String query) {//TO TEST
        String path = SETLIST_HOST + SETLIST_VENUE + SETLIST_VENUE_ARGS;
        String url = String.format(path, query, SETLIST_TOKEN);
        String content = join(req.getContent(url));
        return gson.fromJson(content, VenueDto[].class);
    }

    public VenueDto[] getVenues(String query, int n) {//TO TEST
        String path = SETLIST_HOST + SETLIST_VENUE + SETLIST_VENUE_ARGS + SETLIST_NUM_PAGS;
        String url = String.format(path, query, n);
        String content = join(req.getContent(url));
        return gson.fromJson(content, VenueDto[].class);
    }

    public EventDto[] getEvents(String query) {//TO TEST
        String path = SETLIST_HOST + SETLIST_EVENT + SETLIST_EVENT_ARGS;
        String url = String.format(path, query, SETLIST_TOKEN);
        String content = join(req.getContent(url));
        return gson.fromJson(content, EventDto[].class);
    }

    public EventDto[] getEvents(String query, int n) {//TO TEST
        String path = SETLIST_HOST + SETLIST_EVENT + SETLIST_EVENT_ARGS + SETLIST_NUM_PAGS;
        String url = String.format(path, query, n);
        String content = join(req.getContent(url));
        return gson.fromJson(content, EventDto[].class);
    }
}
