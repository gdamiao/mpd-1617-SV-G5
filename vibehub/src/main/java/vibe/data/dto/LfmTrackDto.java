package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artur on 04/04/2017.
 */
public class LfmTrackDto {
    @SerializedName("track")
    private final TrackDto trackDto;

    public  LfmTrackDto() {
        this.trackDto = new TrackDto();
    }

    public TrackDto getTrackDto() {
        return trackDto;
    }
}
