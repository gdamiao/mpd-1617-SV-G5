package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class TrackDto {
    private final String name;
    @SerializedName("artist")
    private final ArtistDto artistDto;
    @SerializedName("album")
    private final AlbumDto albumDto;
    @SerializedName("url")
    private final String trackUrl;
    private final int duration;

    public TrackDto() {
        this.name = "";
        this.artistDto = new ArtistDto();
        this.albumDto = new AlbumDto();
        this.trackUrl = "";
        this.duration = 0;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistDto.getName();
    }

    public String getAlbumName() {
        return albumDto.getTitle();
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public String getAlbumUrl() {
        return albumDto.getUrl();
    }

    public int getDuration() {
        return duration;
    }

    public String[] getImagesUrl() {
        ImageDto[] images = albumDto.getImages();
        String[] imagesUrl = new String[images.length];
        for (int i = 0; i < images.length; i++) {
            imagesUrl[i] = images[i].getText();
        }
        return imagesUrl;
    }
}
