package com.restApi.ticketing;
import com.restApi.ticketing.dto.TicketRequestDTO;
import com.restApi.ticketing.enums.Status;
import com.restApi.ticketing.model.EventSchedule;
import com.restApi.ticketing.model.Ticket;
import com.restApi.ticketing.model.TicketUser;
import com.restApi.ticketing.model.User;
import com.restApi.ticketing.repository.EventScheduleRepository;
import com.restApi.ticketing.repository.TicketRepository;
import com.restApi.ticketing.repository.UserRepository;
import com.restApi.ticketing.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookTicketTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private EventScheduleRepository eventScheduleRepository;
    @InjectMocks
    private TicketService ticketService;

    @Test
    void testBookTicketSuccess() {
        User user = new User();
        user.setId(1);
        user.setUsername("user");

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setStatus(Status.AVAILABLE);

        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setId(1);
        eventSchedule.setTimeSellStart(LocalTime.now().minus(Duration.ofMinutes(30)));
        eventSchedule.setTimeSellEnd(LocalTime.now().plus(Duration.ofMinutes(30)));

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        TicketRequestDTO reqTicketDTO = new TicketRequestDTO();
        reqTicketDTO.setEventScheduleId(1);

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(ticketRepository.findAllAvailableTicketByDateTimeId(1, Status.AVAILABLE)).thenReturn(ticketList);
        when(eventScheduleRepository.findById(1)).thenReturn(Optional.of(eventSchedule));

        TicketUser ticketUser = ticketService.bookTicket("user", reqTicketDTO);

        assertNotNull(ticketUser); //verify that ticketUser is inserted
        assertEquals(user, ticketUser.getUser()); //verify that user in db is the same w/ current user
        assertEquals(ticket, ticketUser.getTicket()); //verify that ticket that is booked is correct w/ the one inserted in ticketUser
        assertEquals(Status.SOLD, ticket.getStatus()); //verify that status ticket updated to SOLD
    }
}
