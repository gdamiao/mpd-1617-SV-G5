package main.java.vibe.data.dto;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import main.java.vibe.model.Artist;
import main.java.vibe.model.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by artur on 04/04/2017.
 */
public class EventDto {

    private final String artistName;
    private final String eventDate;
    private final String tour;
    private final String[] tracksNames;
    private final String setListId;

    public EventDto(String artistName, String eventDate, String tour, JsonElement sets, String setListId) {
        this.artistName = artistName;
        this.eventDate = eventDate;
        this.tour = tour;
        this.setListId = setListId;
        if(sets.isJsonPrimitive())
            tracksNames = new String[0];
        else {
            List<String> res = new ArrayList<>();
            JsonElement set = sets.getAsJsonObject().get("set");
            if(set.isJsonObject()) insertSongsInto(set, res);
            else {
                set
                        .getAsJsonArray()
                        .forEach(elem -> insertSongsInto(elem, res));
            }
            tracksNames = res.toArray(new String[res.size()]);
        }
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

    public String[] getTracksNames(){
        return tracksNames;
    }

    private void insertSongsInto(JsonElement elem, List<String> res) {
        JsonElement song = elem.getAsJsonObject().get("song");
        if(song.isJsonObject()) {
            res.add(songToString(song));
        } else {
            JsonArray songs = song.getAsJsonArray();
            songs.forEach(this::songToString);
        }
    }

    private String songToString(JsonElement song) {
        return song.getAsJsonObject().get("@name").getAsString();
    }

    public String getSetListId() {
        return setListId;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "artistName='" + artistName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", tour='" + tour + '\'' +
                ", tracksNames" + Arrays.toString(tracksNames) +
                ", setListId='" + setListId + '\'' +
                "}";
    }
}
