package com.example.ecctrainingspring.service.impl;

import com.example.ecctrainingspring.enums.EnumStatus;
import com.example.ecctrainingspring.exception.TicketNotFoundException;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;
import com.example.ecctrainingspring.repository.EmployeeRepository;
import com.example.ecctrainingspring.repository.TicketRepository;
import com.example.ecctrainingspring.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    final
    TicketRepository ticketRepository;
    private final EmployeeRepository employeeRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             EmployeeRepository employeeRepository) {
        this.ticketRepository = ticketRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> findTicket(Long id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            throw new TicketNotFoundException("Ticket Not Found!");
        }
        return ticket;
    }

    @Override
    public Ticket createTicket(TicketRO ticketRO) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRO.getTitle());
        ticket.setDescription(ticketRO.getDescription());
        ticket.setSeverity(ticketRO.getSeverity());
        ticket.setStatus(EnumStatus.NEW);
        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public Ticket updateTicketById(Long id, Ticket ticketRO) {
        Optional<Ticket> ticketUpdate = ticketRepository.findById(id);

        if (!ticketRepository.existsById(id)) {
           throw new TicketNotFoundException("Ticket does not exist!");
        }

        Ticket ticket = ticketUpdate.get();
        ticket.setTitle(ticketRO.getTitle());
        ticket.setDescription(ticketRO.getDescription());
        ticket.setSeverity(ticketRO.getSeverity());
        ticket.setStatus(ticketRO.getStatus());
        ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public void deleteTicketById(Long id){
        ticketRepository.deleteById(id);
    }
}
