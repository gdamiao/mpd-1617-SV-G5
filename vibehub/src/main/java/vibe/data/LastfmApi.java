package main.java.vibe.data;


import main.java.util.HttpRequest;
import main.java.util.IRequest;
import main.java.vibe.data.dto.LfmArtistDto;
import main.java.vibe.data.dto.LfmTrackDto;

import static main.java.util.queries.Queries.join;

/**
 * Created by artur on 04/04/2017.
 */
public class LastfmApi {

    private static final String LASTFM_TOKEN = ApiUtils.getApiKey("lastfm-app-key.txt");
    private static final String LASTFM_HOST = "http://ws.audioscrobbler.com/2.0/";
    private static final String LASTFM_ARTIST = "?method=artist.getinfo&artist=%s&api_key=%s";
    private static final String LASTFM_JSON_ARG = "&format=json";
    private static final String LASTFM_TRACK = "?method=track.getInfo&api_key=%s&artist=%s&track=%s";

    private final IRequest req;

    public LastfmApi(IRequest req) {
        this.req = req;
    }

    public LastfmApi() {
        this.req = new HttpRequest();
    }

    public LfmArtistDto getArtistInfo(String artist) {
        artist = artist.replace(' ', '+');
        String path = LASTFM_HOST + LASTFM_ARTIST + LASTFM_JSON_ARG;
        String url = String.format(path, artist, LASTFM_TOKEN);
        String content = join(req.getContent(url));
        return ApiUtils.gson.fromJson(content, LfmArtistDto.class);

    }

    public LfmTrackDto getTrackInfo(String track, String artist) {
        track = track
                .replace(' ', '+')
                .replace('\"', ' ');
        if(track.charAt(0) == ' '){
            track = track.substring(1, track.length()-1);
        }
        artist = artist.replace(' ', '+');
        String path = LASTFM_HOST + LASTFM_TRACK + LASTFM_JSON_ARG;
        String url = String.format(path, LASTFM_TOKEN, artist, track);
        String content = join(req.getContent(url));
        return ApiUtils.gson.fromJson(content, LfmTrackDto.class);
    }
}
