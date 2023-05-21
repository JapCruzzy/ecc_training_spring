package com.example.ecctrainingspring.repository;

import com.example.ecctrainingspring.model.po.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findTicketByTicketNo (Long id);
}
