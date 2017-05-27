package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class SearchVenuesDto {
    @SerializedName("venues")
    private final VenuesDto venuesDto;

    public SearchVenuesDto(VenuesDto venuesDto) {
        this.venuesDto = venuesDto;
    }

    public VenuesDto getVenuesDto() {
        return venuesDto;
    }
}
