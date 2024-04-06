package com.restApi.ticketing.repository;

import com.restApi.ticketing.model.Event;
import com.restApi.ticketing.model.EventSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventScheduleRepository extends JpaRepository<EventSchedule, Integer> {
}
