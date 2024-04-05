package com.restApi.ticketing.service;


import com.restApi.ticketing.model.Event;
import com.restApi.ticketing.repository.EventRepository;
import com.restApi.ticketing.repository.TicketRepository;
import com.restApi.ticketing.response.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
    }


    public Page<Event> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.eventRepository.findAll(pageable);
    }
    public Event getById(Integer id) {
        Optional<Event> optionalEvent = this.eventRepository.findById(id);

        if(optionalEvent.isEmpty())
            throw new NotFoundException("event not found");

        return optionalEvent.orElseThrow();
    }

}
