package main.java.vibe.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import main.java.util.IRequest;
import main.java.vibe.data.dto.EventDto;
import main.java.vibe.data.dto.SearchEventsDto;
import main.java.vibe.data.dto.SearchVenuesDto;
import main.java.vibe.data.dto.VenueDto;

import java.io.*;

import static main.java.util.queries.Queries.join;

/**
 * Created by artur on 04/04/2017.
 */
public class SetListApi {

    private static final String SETLIST_TOKEN = ApiUtils.getApiKey("setlist-app-key.txt");
    private static final String SETLIST_HOST = "https://api.setlist.fm";
    private static final String SETLIST_VENUE = "/rest/0.1/search/venues";
    private static final String SETLIST_JSON_ARG = ".json";
    private static final String SETLIST_VENUE_ARGS = "?cityName=%s";
    private static final String SETLIST_NUM_PAGS = "p=%s";
    private static final String SETLIST_ADD_MORE_ARG = "&";
    private static final String SETLIST_ADD_FIRST_ARG = "?";
    private static final String SETLIST_EVENT = "/rest/0.1/venue/%s/setlists";

    private final IRequest req;

    public SetListApi(IRequest req) {
        this.req = req;
    }

    public VenueDto[] getVenues(String query) {
        query = query.replace(' ', '+');
        String path = SETLIST_HOST + SETLIST_VENUE + SETLIST_JSON_ARG + SETLIST_VENUE_ARGS;
        String url = String.format(path, query, SETLIST_TOKEN);
        String content = join(req.getContent(url));
        int itemsPerPage = getPageInfo(content, "@itemsPerPage");
        int total = getPageInfo(content, "@total");
        int numOfPages = calcNumOfPages(itemsPerPage, total);
        return getAllVenues(query, content, total, numOfPages);
    }

    public VenueDto[] getVenues(String query, int n) {
        query = query.replace(' ', '+');
        String path = SETLIST_HOST + SETLIST_VENUE + SETLIST_JSON_ARG + SETLIST_VENUE_ARGS + SETLIST_ADD_MORE_ARG + SETLIST_NUM_PAGS;
        String url = String.format(path, query, n);
        String content = join(req.getContent(url));
        SearchVenuesDto dto = ApiUtils.gson.fromJson(content, SearchVenuesDto.class);
        return dto.getVenuesDto().getVenuesDto();
    }

    public EventDto[] getEvents(String query) {
        query = query.replace(' ', '+');
        String path = SETLIST_HOST + SETLIST_EVENT + SETLIST_JSON_ARG;
        String url = String.format(path, query, SETLIST_TOKEN);
        String content;
        try{
            content = join(req.getContent(url));
        } catch (UncheckedIOException e) {
            return new EventDto[0];
        }
        int itemsPerPage = getPageInfo(content, "@itemsPerPage");
        int total = getPageInfo(content, "@total");
        int numOfPages = calcNumOfPages(itemsPerPage, total);
        return getAllEvents(query, content, total, numOfPages);

    }

    public EventDto[] getEvents(String query, int n) {
        query = query.replace(' ', '+');
        String path = SETLIST_HOST + SETLIST_EVENT + SETLIST_JSON_ARG + SETLIST_ADD_FIRST_ARG + SETLIST_NUM_PAGS;
        String url = String.format(path, query, n);
        String content;
        try{
            content = join(req.getContent(url));
        } catch (UncheckedIOException e) {
            return new EventDto[0];
        }
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement setList = parser.parse(content)
                .getAsJsonObject().get("setlists")
                .getAsJsonObject().get("setlist");
        SearchEventsDto dto =
                new SearchEventsDto(
                        setList.isJsonArray() ?
                                gson.fromJson(setList, EventDto[].class) :
                                new EventDto[]{gson.fromJson(
                                        setList, EventDto.class)});
        return dto.getEventsDto();
    }

    private EventDto[] getAllEvents(String query, String content, int total, int numOfPages) {
        EventDto[] dtos = new EventDto[total];
        int idx = 0;
        int idx1 = 0;
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement setList = parser.parse(content)
                .getAsJsonObject().get("setlists")
                .getAsJsonObject().get("setlist");
        EventDto[] aux =
                new SearchEventsDto(
                        setList.isJsonArray() ?
                                gson.fromJson(setList, EventDto[].class) :
                                new EventDto[]{gson.fromJson(
                                        setList, EventDto.class)
                                })
                        .getEventsDto();
        for (int l = 2 ; l <= numOfPages+1 ; l++) {
            for ( ; idx1 < aux.length; idx++, idx1++) {
                dtos[idx] = aux[idx1];
            }
            if(l<=numOfPages) {
                aux = getEvents(query, l);
                idx1 = 0;
            }
        }
        return dtos;
    }

    private VenueDto[] getAllVenues(String query, String content, int total, int numOfPages) {
        VenueDto[] dtos = new VenueDto[total];
        int idx = 0;
        int idx1 = 0;
        VenueDto[] aux = ApiUtils.gson.fromJson(content, SearchVenuesDto.class).getVenuesDto().getVenuesDto();
        for (int l = 2 ; l <= numOfPages+1 ; l++) {
            for ( ; idx1 < aux.length; idx++, idx1++) {
                dtos[idx] = aux[idx1];
            }
            if(l<=numOfPages) {
                aux = getVenues(query, l);
                idx1 = 0;
            }
        }
        return dtos;
    }

    private int calcNumOfPages(int itemsPerPage, int total) {
        return (int)Math.ceil((float)total/itemsPerPage);
    }

    private int getPageInfo(String content, String s) {
        int idx = content.indexOf(s, 0);
        int idx1 = content.indexOf(',', idx);
        return Integer.parseInt(content.substring(idx+s.length()+3, idx1-1));
    }
}