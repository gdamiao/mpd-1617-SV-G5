package main.java.vibe.data.dto;

import com.google.gson.annotations.SerializedName;

public class ImageDto {
    @SerializedName("#text")
    private final String text;
    private final String size;

    public ImageDto() {
        this.text = "";
        this.size = "";
    }

    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }
}
