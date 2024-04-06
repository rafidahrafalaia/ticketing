package com.restApi.ticketing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_user")
public class TicketUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public void setId(Integer id) {
        this.id = id;
    }

    //
    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
