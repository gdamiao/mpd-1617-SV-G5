package main.java.vibe.data.dto;


import com.google.gson.annotations.SerializedName;

/**
 * Created by artur on 04/04/2017.
 */
public class VenueDto {
    @SerializedName("@name")
    private final String venueName;
    @SerializedName("@id")
    private final String venueId;

    public VenueDto(String venueName, String venueId) {
        this.venueName = venueName;
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueId() {
        return venueId;
    }
}
