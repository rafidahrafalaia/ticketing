package com.restApi.ticketing.dto;

import com.restApi.ticketing.model.EventSchedule;

public class EventScheduleResponseDTO {
    private Integer id;
    private String date;
    private String time;
    public EventScheduleResponseDTO(EventSchedule dateTimeEvent) {
        this.id = dateTimeEvent.getId();
        this.date = dateTimeEvent.getTimeEventStart().toLocalDate().toString();
        this.time = dateTimeEvent.getTimeEventStart().toLocalTime().toString();
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
