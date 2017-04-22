package main.java.vibe.data;


import main.java.util.HttpRequest;
import main.java.util.IRequest;
import main.java.vibe.data.dto.LfmArtistDto;
import main.java.vibe.data.dto.LfmTrackDto;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;

import static main.java.util.queries.Queries.join;

/**
 * Created by artur on 04/04/2017.
 */
public class LastfmApi {

    private static final String LASTFM_TOKEN;
    private static final String LASTFM_HOST = "http://ws.audioscrobbler.com/2.0/";
    private static final String LASTFM_ARTIST = "?method=artist.getinfo&artist=%s&api_key=%s";
    private static final String LASTFM_JSON_ARG = "&format=json";
    private static final String LASTFM_TRACK = "?method=track.getInfo&api_key=%s&artist=%s&track=%s";

    static {
        try {
            URL keyFile = ClassLoader.getSystemResource("lastfm-app-key.txt");
            if(keyFile == null) {
                throw new IllegalStateException(
                        "YOU MUST GOT a KEY in www.last.fm/api and place it in src/main/resources/lastfm-app-key.txt");
            } else {
                InputStream keyStream = keyFile.openStream();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(keyStream))) {
                    LASTFM_TOKEN = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private final IRequest req;
    private final Gson gson = new Gson();

    public LastfmApi(IRequest req) {
        this.req = req;
    }

    public LastfmApi() {
        this.req = new HttpRequest();
    }

    public LfmArtistDto getArtistInfo(String artist) {
        String path = LASTFM_HOST + LASTFM_ARTIST + LASTFM_JSON_ARG;
        String url = String.format(path, artist, LASTFM_TOKEN);
        String content = join(req.getContent(url));
        return gson.fromJson(content, LfmArtistDto.class);

    }

    public LfmTrackDto getTrackInfo(String track, String artist) {
        String path = LASTFM_HOST + LASTFM_TRACK + LASTFM_JSON_ARG;
        String url = String.format(path, LASTFM_TOKEN, artist, track);
        String content = join(req.getContent(url));
        return gson.fromJson(content, LfmTrackDto.class);
    }
}
