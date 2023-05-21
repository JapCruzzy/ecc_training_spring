package com.example.ecctrainingspring.service;

import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllTickets();
    Ticket findTicket(Long id);
    void createTicket(TicketRO ticketRO);
    int updateTicketById(Long id, TicketRO ticketRO);
    void deleteTicketById(Long id);
}
