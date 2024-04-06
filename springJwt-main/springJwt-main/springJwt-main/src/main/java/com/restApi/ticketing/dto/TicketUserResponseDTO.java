package com.restApi.ticketing.dto;

import com.restApi.ticketing.model.TicketUser;

public class TicketUserResponseDTO {
        private Integer id;
        private UserResponseDTO user; // Assuming userId is an Integer
        private TicketResponseDTO ticket; // Assuming ticketId is an Integer

        public TicketUserResponseDTO(TicketUser ticketUser) {
                this.id = ticketUser.getId();
                this.user = new UserResponseDTO(ticketUser.getUser());
                this.ticket = new TicketResponseDTO(ticketUser.getTicket());
        }

        // Getters and setters
        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public UserResponseDTO getUser() {
                return user;
        }

        public void setUser(UserResponseDTO user) {
                this.user = user;
        }

        public TicketResponseDTO getTicket() {
                return ticket;
        }

        public void setTicket(TicketResponseDTO ticket) {
                this.ticket = ticket;
        }
}
