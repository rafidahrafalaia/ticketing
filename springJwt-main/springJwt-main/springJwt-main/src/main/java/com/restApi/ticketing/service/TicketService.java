package com.restApi.ticketing.service;


import com.restApi.ticketing.dto.TicketRequestDTO;
import com.restApi.ticketing.enums.Status;
import com.restApi.ticketing.model.*;
import com.restApi.ticketing.repository.EventScheduleRepository;
import com.restApi.ticketing.repository.TicketRepository;
import com.restApi.ticketing.repository.TicketUserRepository;
import com.restApi.ticketing.repository.UserRepository;
import com.restApi.ticketing.response.exception.LimitExceededException;
import com.restApi.ticketing.response.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TicketService extends RuntimeException {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketUserRepository ticketUserRepository;
    private final EventScheduleRepository eventScheduleRepository;
    private final Lock lock = new ReentrantLock();

    public TicketService(UserRepository repository, TicketRepository ticketRepository, TicketUserRepository ticketUserRepository, EventScheduleRepository eventScheduleRepository) {
        this.userRepository = repository;
        this.ticketRepository = ticketRepository;
        this.ticketUserRepository = ticketUserRepository;
        this.eventScheduleRepository = eventScheduleRepository;
    }

    private void validateLimitBook(Integer userId, Integer eventScheduleId, Integer limitBook) throws LimitExceededException {
        List<TicketUser> ticketUsers = this.ticketUserRepository.findAllByUserAndEventScheduleId(userId, eventScheduleId);
        if((long) ticketUsers.size() > limitBook) {
            throw new LimitExceededException("Transaction exceeds limit");
        }
    }
    private void validateTimeSchedule(Integer eventScheduleId) throws AccessDeniedException {
        EventSchedule eventSchedule = this.eventScheduleRepository.findById(eventScheduleId)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Event Schedule not fount"));
        LocalTime start = eventSchedule.getTimeSellStart();
        LocalTime end = eventSchedule.getTimeSellEnd();
        LocalTime current = LocalTime.now();
        if(!current.isAfter(start) || !current.isBefore(end)) {
            throw new AccessDeniedException("User has no access for this action yet");
        }
    }
    public TicketUser bookTicket(String username, TicketRequestDTO body, Integer limitBook)  {
        lock.lock();
        try {
            User user = this.userRepository.findByUsername(username)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("User not fount"));

            this.validateLimitBook(user.getId(), body.getEventScheduleId(), limitBook);
            this.validateTimeSchedule(body.getEventScheduleId());

            Ticket ticket = this.ticketRepository.findAllAvailableTicketByDateTimeId(body.getEventScheduleId(), Status.AVAILABLE)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Ticket not fount"));

            TicketUser ticketUser = new TicketUser();
            ticketUser.setTicket(ticket);
            ticketUser.setUser(user);
            this.ticketUserRepository.save(ticketUser);

            ticket.setStatus(Status.SOLD);
            this.ticketRepository.save(ticket);

            return ticketUser;
        } catch (AccessDeniedException | LimitExceededException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public List<TicketUser> getAllByUser(String username) {
        User user = this.userRepository.findByUsername(username).stream().findFirst().orElseThrow();
        return this.ticketUserRepository.findAllByUserId(user.getId());
    }

}
