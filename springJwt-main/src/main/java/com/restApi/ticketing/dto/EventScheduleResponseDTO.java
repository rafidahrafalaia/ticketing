package com.restApi.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restApi.ticketing.model.EventSchedule;

import java.time.LocalTime;

public class EventScheduleResponseDTO {
    private Integer id;
    private String date;
    private String time;
    @JsonProperty("open_to_sell")
    private Boolean openToSell;
    public EventScheduleResponseDTO(EventSchedule dateTimeEvent) {
        LocalTime start = dateTimeEvent.getTimeSellStart();
        LocalTime end = dateTimeEvent.getTimeSellEnd();
        LocalTime current = LocalTime.now();
        this.id = dateTimeEvent.getId();
        this.date = dateTimeEvent.getTimeEventStart().toLocalDate().toString();
        this.time = dateTimeEvent.getTimeEventStart().toLocalTime().toString();
        this.openToSell = (current.isAfter(start) && current.isBefore(end));
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getOpenToSell() {
        return openToSell;
    }

    public void setOpenToSell(Boolean openToSell) {
        this.openToSell = openToSell;
    }
}
