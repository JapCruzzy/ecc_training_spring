package com.example.ecctrainingspring.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/view")
    public String viewEmployee(){
        return null;
    }

    @GetMapping("/list")
    public List<String> listEmployee(){
        return null;
    }

    @PostMapping("/create")
    public String createEmployee(){
        return null;
    }

    @PutMapping("/update")
    public String updateEmployee(){
        return null;
    }

    @DeleteMapping("/delete")
    public String deleteEmployee(){
        return null;
    }

    @PostMapping("/assignTicket")
    public String assignTicketToEmployee(){
        return null;
    }
}
