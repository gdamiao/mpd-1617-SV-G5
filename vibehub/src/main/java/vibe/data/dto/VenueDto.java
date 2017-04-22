package main.java.vibe.data.dto;


import java.util.Arrays;

/**
 * Created by artur on 04/04/2017.
 */
public class VenueDto {

    private final String name;

    private final EventDto[] events;

    public VenueDto(String name, EventDto[] events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public EventDto[] getEvents() { return events; }

    @Override
    public String toString() {
        return "VenueDto{" +
                "name='" + name + '\'' +
                ", events" + Arrays.toString(events) +
                "}";
    }

    public static <R, T> VenueDto valueof(String line) {
        return null;
    }
}
