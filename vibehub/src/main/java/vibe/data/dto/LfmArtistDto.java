package main.java.vibe.data.dto;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.plaf.PanelUI;

/**
 * Created by artur on 04/04/2017.
 */
public class LfmArtistDto {
    private final String name;
    private final String bio;
    private final String url;
    private final String imagesUri;
    private final String mbid;

    public LfmArtistDto(String name, String bio, String url, String imagesUri, String mbid) {
        this.name = name;
        this.bio = bio;
        this.url = url;
        this.imagesUri = imagesUri;
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getUrl() {
        return url;
    }

    public String getImagesUri() {
        return imagesUri;
    }

    public String getMbid() {
        return mbid;
    }

    @Override
    public String toString() {
        return "LfmArtistDto{" +
                "name='" + getName() + '\'' +
                ", bio='" + getBio() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", imagesUri='" + getImagesUri() + '\'' +
                ", mbid='" + getMbid() + '\'' +
                '}';
    }
}
