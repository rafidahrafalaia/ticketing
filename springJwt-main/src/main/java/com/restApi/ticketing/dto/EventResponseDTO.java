package com.restApi.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restApi.ticketing.model.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private String organizer;
    @JsonProperty("event_schedule")
    private List<EventScheduleResponseDTO> eventSchedule;

    // Constructor
    public EventResponseDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getName();
        this.organizer = event.getOrganizer();
        this.eventSchedule = event.getEventSchedules().stream().map(eventSchedule -> new EventScheduleResponseDTO(eventSchedule)).collect(Collectors.toList());
    }

    // Getters and Setters (You can generate these using your IDE or manually)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public List<EventScheduleResponseDTO> getEventSchedule() {
        return eventSchedule;
    }
}