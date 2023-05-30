package com.example.ecctrainingspring.controller;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.EmployeeRO;
import com.example.ecctrainingspring.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employees")
public class EmployeeController {

    final
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public List<Employee> listEmployee() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Optional<Employee>> viewEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRO employeeRO) {
        Employee employee = employeeService.updateEmployeeById(id, employeeRO);
        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) throws Exception {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/assign-ticket/{ticketId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> assignTicket (@PathVariable("ticketId") Long ticketId, @RequestParam Long employeeNumber) {
        Ticket ticket = employeeService.assignTicket(employeeNumber, ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.ACCEPTED);
    }

    @PutMapping("/add-watchers/{ticketId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignWatchers(@PathVariable("ticketId") Long ticketId, @RequestParam List<Long> employeeNumbers) {
        employeeService.addWatchers(ticketId, employeeNumbers);
        return new ResponseEntity<>("Watchers assigned", HttpStatus.ACCEPTED);
    }
}
