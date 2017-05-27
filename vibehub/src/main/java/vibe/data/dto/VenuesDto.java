package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class VenuesDto {
    @SerializedName("venue")
    private final VenueDto[] venuesDto;

    public VenuesDto(VenueDto[] venuesDto) {
        this.venuesDto = venuesDto;
    }

    public VenueDto[] getVenuesDto() {
        return venuesDto;
    }
}
