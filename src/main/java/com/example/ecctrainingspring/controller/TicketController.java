package com.example.ecctrainingspring.controller;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;
import com.example.ecctrainingspring.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
//@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {

    final
    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/list")
    public List<Ticket> listTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Optional<Ticket>> viewTicket(@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.findTicket(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> createTicket (@RequestBody Ticket ticket){
        Ticket createdticket = ticketService.createTicket(ticket);
        return new ResponseEntity<>(createdticket, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> updateTicket (@PathVariable("id") Long id, @RequestBody Ticket ticketRo){
        Ticket ticket = ticketService.updateTicketById(id, ticketRo);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTicket (@PathVariable("id") Long id){
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
