package main.java.vibe.model;


/**
 * Created by artur on 04/04/2017.
 */
public class Venue {
    private final String name;
    private final Iterable<Event> events;

    public Venue(String name, Iterable<Event> events) {
        this.name = name;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public Iterable<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Venue{\n" +
                "name=\"" + getName() + "\",\n" +
                "events=" + getEvents() + "\n" +
                "}";
    }
}
