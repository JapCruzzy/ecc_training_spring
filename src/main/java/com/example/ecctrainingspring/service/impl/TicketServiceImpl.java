package com.example.ecctrainingspring.service.impl;

import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;
import com.example.ecctrainingspring.repository.TicketRepository;
import com.example.ecctrainingspring.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    final
    TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findTicket(Long id) {
        return ticketRepository.findTicketByTicketNo(id);
    }

    @Override
    public void createTicket(TicketRO ticketRO) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRO.getTitle());
        ticket.setDescription(ticketRO.getDescription());
        ticket.setSeverity(ticketRO.getSeverity());
        ticket.setStatus(ticketRO.getStatus());
        ticketRepository.save(ticket);
    }

    @Override
    public int updateTicketById(Long id, TicketRO ticketRO) {
        int rowAffected = 0;
        Optional<Ticket> ticketUpdate = ticketRepository.findById(id);

        if (ticketRepository.existsById(id)) {
            Ticket ticket = ticketUpdate.get();
            ticket.setTitle(ticketRO.getTitle());
            ticket.setDescription(ticketRO.getDescription());
            ticket.setSeverity(ticketRO.getSeverity());
            ticket.setStatus(ticketRO.getStatus());
            ticketRepository.save(ticket);
            rowAffected++;
        }

        return rowAffected;
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
    }
}
