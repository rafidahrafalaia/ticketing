package com.restApi.ticketing.repository;

import com.restApi.ticketing.model.Ticket;
import com.restApi.ticketing.model.TicketUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketUserRepository extends JpaRepository<TicketUser, Integer> {

    @Query("""
select t from TicketUser t inner join User u on t.user.id = u.id
where t.user.id = :userId
""")
    List<TicketUser> findAllByUserId(Integer userId);
    @Query("""
select tu from TicketUser tu
inner join User u on tu.user.id = u.id
inner join Ticket t on tu.ticket.id = t.id
where tu.user.id = :userId and t.eventSchedule.id = :eventScheduleId
""")
    List<TicketUser> findAllByUserAndEventScheduleId(Integer userId, Integer eventScheduleId);
}
