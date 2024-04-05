package com.restApi.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class TicketRequestDTO {
        @JsonProperty("event_schedule_id")
        @NotNull
        private Integer eventScheduleId;

        public Integer getEventScheduleId() {
                return eventScheduleId;
        }

        public void setEventScheduleId(Integer eventScheduleId) {
                this.eventScheduleId = eventScheduleId;
        }
}