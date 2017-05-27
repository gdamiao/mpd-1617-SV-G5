package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class ArtistDto {
    private final String name;
    private final BioDto bio;
    private final String url;
    @SerializedName("image")
    private final ImageDto[] images;
    private final String mbid;

    public ArtistDto() {
        name = "";
        bio = new BioDto();
        url = "";
        images = new ImageDto[0];
        mbid = "";
    }

    public String getName() {
        return name;
    }

    public BioDto getBio() {
        return bio;
    }

    public String getUrl() {
        return url;
    }

    public String getMbid() {
        return mbid;
    }

    public String getImagesUri() {
        String imagesUri = "";
        for (ImageDto dto: images) {
            imagesUri += dto.getText() +
                    ", ";
        }
        return imagesUri.substring(0, imagesUri.lastIndexOf(", "));
    }
}
