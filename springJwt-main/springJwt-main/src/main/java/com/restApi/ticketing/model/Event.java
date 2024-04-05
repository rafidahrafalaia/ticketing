package com.restApi.ticketing.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "organizer")
    private String organizer;
    @OneToMany(mappedBy = "event")
    private List<EventSchedule> eventSchedules;

    public List<EventSchedule> getEventSchedules() {
        return eventSchedules;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizer() {
        return organizer;
    }
}
