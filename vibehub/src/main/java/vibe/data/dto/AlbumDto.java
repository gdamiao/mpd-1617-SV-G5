package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class AlbumDto {
    private final String title;
    private final String url;
    @SerializedName("image")
    private final ImageDto[] images;

    public AlbumDto() {
        this.title = "";
        this.url = "";
        this.images = new ImageDto[0];
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public ImageDto[] getImages() {
        return images;
    }
}
