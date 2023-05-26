package com.example.ecctrainingspring.service;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> findAllTickets();
    Optional<Ticket> findTicket(Long id);
    Ticket createTicket(Ticket ticket);
    Ticket updateTicketById(Long id, Ticket ticketRO);
    void deleteTicketById(Long id);
}
