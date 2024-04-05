package com.restApi.ticketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "event_schedule")
public class EventSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "time_event_start")
    private LocalDateTime timeEventStart;
    @NotNull
    @Column(name = "time_sell_start")
    LocalTime timeSellStart;
    @NotNull
    @Column(name = "time_sell_end")
    LocalTime timeSellEnd;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @NotNull
    @OneToMany(mappedBy = "eventSchedule")
    private List<Ticket> tickets;


    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getTimeEventStart() {
        return timeEventStart;
    }

    public void setTimeEventStart(LocalDateTime timeEventStart) {
        this.timeEventStart = timeEventStart;
    }

    public LocalTime getTimeSellStart() {
        return timeSellStart;
    }

    public void setTimeSellStart(LocalTime timeSellStart) {
        this.timeSellStart = timeSellStart;
    }

    public LocalTime getTimeSellEnd() {
        return timeSellEnd;
    }

    public void setTimeSellEnd(LocalTime timeSellEnd) {
        this.timeSellEnd = timeSellEnd;
    }

    public Event getEvent() {
        return event;
    }

    public void setId(int i) {
    }
}
