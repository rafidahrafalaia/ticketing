package com.restApi.ticketing.dto;

import com.restApi.ticketing.model.Ticket;

public class TicketResponseDTO {

    private String code;
    private EventScheduleResponseDTO dateTimeEvent;

    public TicketResponseDTO(Ticket ticket) {
        this.code = ticket.getCode();
        this.dateTimeEvent = new EventScheduleResponseDTO(ticket.getEventSchedule());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EventScheduleResponseDTO getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(EventScheduleResponseDTO dateTimeEvent) {
        this.dateTimeEvent = dateTimeEvent;
    }
}