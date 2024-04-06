package com.restApi.ticketing.repository;

import com.restApi.ticketing.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Page<Event> findAll(Pageable pageable);
}
