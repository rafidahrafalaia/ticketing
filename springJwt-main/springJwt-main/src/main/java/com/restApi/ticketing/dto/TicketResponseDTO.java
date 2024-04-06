package com.restApi.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restApi.ticketing.model.Ticket;

public class TicketResponseDTO {

    private String code;
    @JsonProperty("event_schedule")
    private EventScheduleResponseDTO eventSchedule;

    public TicketResponseDTO(Ticket ticket) {
        this.code = ticket.getCode();
        this.eventSchedule = new EventScheduleResponseDTO(ticket.getEventSchedule());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EventScheduleResponseDTO getEventSchedule() {
        return eventSchedule;
    }

    public void setEventSchedule(EventScheduleResponseDTO eventSchedule) {
        this.eventSchedule = eventSchedule;
    }
}