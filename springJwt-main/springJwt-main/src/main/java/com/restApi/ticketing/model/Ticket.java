package com.restApi.ticketing.model;

import com.restApi.ticketing.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;


    @OneToOne(mappedBy = "ticket")
    private TicketUser ticketUser;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "event_schedule_id")
    private EventSchedule eventSchedule;

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TicketUser getTicketUser() {
        return ticketUser;
    }

    public void setTicketUser(TicketUser ticketUser) {
        this.ticketUser = ticketUser;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventSchedule getEventSchedule() {
        return eventSchedule;
    }

    public void setEventSchedule(EventSchedule eventSchedule) {
        this.eventSchedule = eventSchedule;
    }
}
