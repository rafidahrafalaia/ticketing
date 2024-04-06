package com.restApi.ticketing.repository;

import com.restApi.ticketing.enums.Status;
import com.restApi.ticketing.model.Ticket;
import com.restApi.ticketing.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("""
SELECT t FROM Ticket t WHERE t.eventSchedule.id = :scheduleId AND t.status = :status
""")
    List<Ticket> findAllAvailableTicketByDateTimeId(Integer scheduleId, Status status);
}
