package main.java.vibe.data.dto;

public class SearchEventsDto {
    private final EventDto[] eventsDto;

        public SearchEventsDto(EventDto[] eventsDto) {
        this.eventsDto = eventsDto;
    }

    public EventDto[] getEventsDto() {
        return eventsDto;
    }
}
