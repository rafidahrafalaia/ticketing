package com.restApi.ticketing.controller;

import com.restApi.ticketing.dto.EventResponseDTO;
import com.restApi.ticketing.model.Event;
import com.restApi.ticketing.repository.EventRepository;
import com.restApi.ticketing.response.success.SuccessPaginationResponse;
import com.restApi.ticketing.response.success.SuccessResponse;
import com.restApi.ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    private final  EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("events")
    public ResponseEntity<Object> getEvents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Event> events = eventService.getAll(page-1, size);
        List<Object> eventsDTO = events.getContent().stream().map(event -> new EventResponseDTO(event)).collect(Collectors.toList());
        SuccessPaginationResponse response = new SuccessPaginationResponse("successfully get events", eventsDTO, events.getNumber(), events.getSize());
        return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
    }
    @GetMapping("event/{id}")
    public ResponseEntity<Object> getEvents(@PathVariable int id) {
        Event event = eventService.getById(id);
        EventResponseDTO eventsDTO = new EventResponseDTO(event);

        SuccessResponse response = new SuccessResponse("successfully get event", eventsDTO);

        return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
    }
}
