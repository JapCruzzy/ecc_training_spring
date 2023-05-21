package com.example.ecctrainingspring.controller;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.ro.EmployeeRO;
import com.example.ecctrainingspring.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<Employee> viewEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeRO employeeRO) {
        employeeService.createEmployee(employeeRO);
        return new ResponseEntity<>("Employee Added", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRO employeeRO) {
        int row = employeeService.updateEmployeeById(id, employeeRO);
        return new ResponseEntity<>("Employee updated successfully! " + row + " row affected.", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/assign-ticket/{ticketId}")
    public ResponseEntity<String> assignTicket (@PathVariable("ticketId") Long ticketId, @RequestParam Long employeeNumber) {
        employeeService.assignTicket(employeeNumber, ticketId);
        return new ResponseEntity<>("Ticket assigned", HttpStatus.ACCEPTED);
    }

    @PutMapping("/add-watchers/{ticketId}")
    public ResponseEntity<String> assignWatchers(@PathVariable("ticketId") Long ticketId, @RequestParam List<Long> employeeNumbers) {
        employeeService.addWatchers(ticketId, employeeNumbers);
        return new ResponseEntity<>("Watchers assigned", HttpStatus.ACCEPTED);
    }
}
