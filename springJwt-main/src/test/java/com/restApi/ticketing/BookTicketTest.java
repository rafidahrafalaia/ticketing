package com.restApi.ticketing;
import com.restApi.ticketing.dto.TicketRequestDTO;
import com.restApi.ticketing.enums.Status;
import com.restApi.ticketing.model.EventSchedule;
import com.restApi.ticketing.model.Ticket;
import com.restApi.ticketing.model.TicketUser;
import com.restApi.ticketing.model.User;
import com.restApi.ticketing.repository.EventScheduleRepository;
import com.restApi.ticketing.repository.TicketRepository;
import com.restApi.ticketing.repository.TicketUserRepository;
import com.restApi.ticketing.repository.UserRepository;
import com.restApi.ticketing.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.AccessDeniedException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static java.util.Collections.emptyList;
@ExtendWith(MockitoExtension.class)
public class BookTicketTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketUserRepository ticketUserRepository;
    @Mock
    private EventScheduleRepository eventScheduleRepository;
    @InjectMocks
    private TicketService ticketService;
    //Positive Case
    @Test
    void bookTicketSuccess() {
        Integer limitBook = 10;
        User user = new User();
        user.setId(1);
        user.setUsername("user");

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setStatus(Status.AVAILABLE);

        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setId(1);
        //set time so it's always open to sell
        eventSchedule.setTimeSellStart(LocalTime.now().minus(Duration.ofMinutes(30)));
        eventSchedule.setTimeSellEnd(LocalTime.now().plus(Duration.ofMinutes(30)));

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        TicketRequestDTO reqTicketDTO = new TicketRequestDTO();
        reqTicketDTO.setEventScheduleId(1);

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(ticketRepository.findAllAvailableTicketByDateTimeId(1, Status.AVAILABLE)).thenReturn(ticketList);
        when(eventScheduleRepository.findById(1)).thenReturn(Optional.of(eventSchedule));
        when(ticketUserRepository.findAllByUserAndEventScheduleId(1,1)).thenReturn(emptyList());

        TicketUser ticketUser = ticketService.bookTicket("user", reqTicketDTO, limitBook);

        assertNotNull(ticketUser); //verify that ticketUser is inserted
        assertEquals(user, ticketUser.getUser()); //verify that user in db is the same w/ current user
        assertEquals(ticket, ticketUser.getTicket()); //verify that ticket that is booked is correct w/ the one inserted in ticketUser
        assertEquals(Status.SOLD, ticket.getStatus()); //verify that status ticket updated to SOLD
    }

    //Negative Cases
    @Test
    void bookTicketInvalidateTimeSchedule() {
        Integer limitBook = 10;
        User user = new User();
        user.setId(1);
        user.setUsername("user");

        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setId(1);
        //set time so it's always not open to sell
        eventSchedule.setTimeSellStart(LocalTime.now().plusHours(1));
        eventSchedule.setTimeSellEnd(LocalTime.now().minusHours(2));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(eventScheduleRepository.findById(1)).thenReturn(Optional.of(eventSchedule));
        when(ticketUserRepository.findAllByUserAndEventScheduleId(1,1)).thenReturn(emptyList());

        assertThrows(RuntimeException.class, () -> ticketService.bookTicket("user", new TicketRequestDTO(), limitBook));
    }

    @Test
    void bookTicketInvalidateLimit() {
        Integer limitBook = 1;
        User user = new User();
        user.setId(1);
        user.setUsername("user");

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setStatus(Status.AVAILABLE);
        Ticket ticket2 = new Ticket();
        ticket2.setId(2);
        ticket2.setStatus(Status.AVAILABLE);

        //set ticketUserList to be more than limit
        List<TicketUser> ticketUserList = new ArrayList<>();
        TicketUser ticketUser = new TicketUser();
        ticketUser.setId(1);
        ticketUser.setUser(user);
        ticketUser.setTicket(ticket);
        ticketUserList.add(ticketUser);
        TicketUser ticketUser2 = new TicketUser();
        ticketUser2.setId(1);
        ticketUser2.setUser(user);
        ticketUser2.setTicket(ticket2);
        ticketUserList.add(ticketUser2);

        EventSchedule eventSchedule = new EventSchedule();
        eventSchedule.setId(1);
        //set time so it's always open to sell
        eventSchedule.setTimeSellStart(LocalTime.now().minus(Duration.ofMinutes(30)));
        eventSchedule.setTimeSellEnd(LocalTime.now().plus(Duration.ofMinutes(30)));

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(eventScheduleRepository.findById(1)).thenReturn(Optional.of(eventSchedule));
        when(ticketUserRepository.findAllByUserAndEventScheduleId(1,1)).thenReturn(ticketUserList);

        assertThrows(RuntimeException.class, () -> ticketService.bookTicket("user", new TicketRequestDTO(), limitBook));
    }
}
