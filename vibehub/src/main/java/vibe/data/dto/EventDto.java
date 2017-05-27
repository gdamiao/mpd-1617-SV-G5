package main.java.vibe.data.dto;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by artur on 04/04/2017.
 */
public class EventDto {
    private final JsonElement artist;
    private final JsonElement sets;
    @SerializedName("@eventDate")
    private final String eventDate;
    @SerializedName("@tour")
    private final String tour;
    @SerializedName("@id")
    private final String setListId;

    public EventDto(JsonElement artist, String eventDate, String tour, JsonElement sets, String setListId) {
        this.artist = artist;
        this.eventDate = eventDate;
        this.tour = tour;
        this.setListId = setListId;
        this.sets = sets;
    }

    public String getArtistName() {
        return artist.getAsJsonObject().get("@name").getAsString();
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getTour() {
        return tour;
    }

    public String getSetListId() {
        return setListId;
    }

    public String[] getTracksNames(){
        if(sets.isJsonPrimitive())
            return new String[0];
        else {
            List<String> res = new ArrayList<>();
            JsonElement set = sets.getAsJsonObject().get("set");
            if(set.isJsonObject()) insertSongsInto(set, res);
            else {
                set
                        .getAsJsonArray()
                        .forEach(elem -> insertSongsInto(elem, res));
            }
            String[] ret = res.toArray(new String[res.size()]);
            return ret;
        }
    }

    private void insertSongsInto(JsonElement elem, List<String> res) {
        JsonElement song = elem.getAsJsonObject().get("song");
        if(song.isJsonObject()) {
            res.add(songToString(song));
        } else {
            JsonArray songs = song.getAsJsonArray();
            songs.forEach(s -> res.add(songToString(s)));
        }
    }

    private String songToString(JsonElement song) {
        return "\"" + song.getAsJsonObject().get("@name").getAsString() + "\"";
    }
}
