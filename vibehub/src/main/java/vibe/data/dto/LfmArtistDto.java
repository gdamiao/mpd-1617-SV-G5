package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by artur on 04/04/2017.
 */
public class LfmArtistDto {
    @SerializedName("artist")
    private final ArtistDto artistDto;

    public LfmArtistDto() {
        this.artistDto = new ArtistDto();
    }

    public ArtistDto getArtistDto() {
        return artistDto;
    }
}
