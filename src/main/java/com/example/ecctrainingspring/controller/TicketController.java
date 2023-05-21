package com.example.ecctrainingspring.controller;

import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.TicketRO;
import com.example.ecctrainingspring.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
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
    public ResponseEntity<Ticket> viewTicket(@PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.findTicket(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createTicket (@RequestBody TicketRO ticketRo){
        ticketService.createTicket(ticketRo);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateTicket (@PathVariable("id") Long id, @RequestBody TicketRO ticketRo){
        int row = ticketService.updateTicketById(id, ticketRo);
        return new ResponseEntity<>("Successfully updated! " + row + " row affected", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteTicket (@PathVariable("id") Long id){
        ticketService.deleteTicketById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
