package com.restApi.ticketing.controller;

import com.restApi.ticketing.dto.TicketRequestDTO;
import com.restApi.ticketing.dto.TicketUserResponseDTO;
import com.restApi.ticketing.model.TicketUser;
import com.restApi.ticketing.response.exception.LimitExceededException;
import com.restApi.ticketing.response.success.SuccessResponse;
import com.restApi.ticketing.service.TicketService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
@RestController()
public class TicketController extends HttpServlet {
    private final TicketService ticketService;
    private static final String BOOK_TICKET = "bookTicket" ;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("ticket")
    @RateLimiter(name=BOOK_TICKET, fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<Object> postTicket(HttpServletRequest request, @Valid @RequestBody TicketRequestDTO ticketDTO) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        TicketUser ticketUser = ticketService.bookTicket(username, ticketDTO);
        TicketUserResponseDTO ticketUserDTO = new TicketUserResponseDTO((ticketUser));
        SuccessResponse response = new SuccessResponse("successfully buy ticket", ticketUserDTO);
        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }
    @GetMapping("tickets")
    public ResponseEntity<Object> getTickets(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<TicketUser> ticketUsers = ticketService.getAllByUser(username);
        List<Object> ticketsDTO = ticketUsers.stream().map(ticket -> new TicketUserResponseDTO(ticket)).collect(Collectors.toList());
        SuccessResponse response = new SuccessResponse("successfully get user tickets", ticketsDTO);
        return new ResponseEntity<Object>(response, HttpStatus.ACCEPTED);
    }
    public void rateLimiterFallback(Exception e) throws LimitExceededException {
        throw new LimitExceededException("book ticket request exceeds limit");
    }
}