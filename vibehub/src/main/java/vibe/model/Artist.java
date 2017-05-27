package main.java.vibe.model;

/**
 * Created by artur on 04/04/2017.
 */
public class Artist {
    private final String name;
    private final String bio;
    private final String url;
    private final String imagesUri;
    private final String mbid;

    public Artist(String name, String bio, String url, String imagesUri, String mbid) {
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
        return "Artist:{\n" +
                "Name:\"" + getName() + "\"\n" +
                "Bio:\"" + getBio() + "\"\n" +
                "Url:\"" + getUrl() + "\"\n" +
                "Images Uri:\"" + getImagesUri() + "\"\n" +
                "Mbid:\"" + getMbid() + "\"\n" +
                "}";
    }

}
