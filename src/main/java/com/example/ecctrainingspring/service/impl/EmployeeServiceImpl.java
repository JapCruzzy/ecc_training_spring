package com.example.ecctrainingspring.service.impl;

import com.example.ecctrainingspring.exception.EmployeeNotFoundException;
import com.example.ecctrainingspring.exception.EmployeeNumberAlreadyExistException;
import com.example.ecctrainingspring.exception.TicketCannotBeDeletedException;
import com.example.ecctrainingspring.exception.TicketNotFoundException;
import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.EmployeeRO;
import com.example.ecctrainingspring.repository.EmployeeRepository;
import com.example.ecctrainingspring.repository.TicketRepository;
import com.example.ecctrainingspring.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final
    TicketRepository ticketRepository;
    final
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found!");
        }
        return employee;
    }

    @Override
    public Employee createEmployee(Employee employeeRO) throws EmployeeNumberAlreadyExistException {

        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeRO.getEmployeeNumber());
        employee.setFirstName(employeeRO.getFirstName());
        employee.setMiddleName(employeeRO.getMiddleName());
        employee.setLastName(employeeRO.getLastName());
        employee.setDepartment(employeeRO.getDepartment());
        employeeRepository.save(employee);

        return employee;

    }

    @Override
    public Employee updateEmployeeById(Long id, EmployeeRO employeeRo) {
        Optional<Employee> employeeUpdate = employeeRepository.findById(id);

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee does not exist!");
        }

        Employee employee = employeeUpdate.get();
        employee.setFirstName(employeeRo.getFirstName());
        employee.setMiddleName(employeeRo.getMiddleName());
        employee.setLastName(employeeRo.getLastName());
        employee.setDepartment(employeeRo.getDepartment());
        employeeRepository.save(employee);

        return employee;

    }

    @Override
    public void deleteEmployeeById(Long id) throws Exception {
        Employee emp = employeeRepository.findEmployeeById(id);
        Ticket ticket = ticketRepository.findTicketByAssignee_EmployeeNumber(emp.getEmployeeNumber());

        if (ticket != null) {
            throw new TicketCannotBeDeletedException("Cannot delete employee because " +
                    "it has still tickets assigned");
        }

        employeeRepository.deleteById(id);
    }

    @Override
    public void assignTicket(Long employeeNumber, Long ticketId) {
        Ticket ticket = ticketRepository.findTicketByTicketNo(ticketId);

        if (ticket == null)
            throw new TicketNotFoundException("Can't find ticket");

        ticket.setAssignee(employeeRepository.findEmployeeByEmployeeNumber(employeeNumber));
        ticketRepository.save(ticket);

    }

    @Override
    public void addWatchers(Long ticketId, List<Long> employeeNumbers) {
        Ticket ticket = ticketRepository.findTicketByTicketNo(ticketId);
        ticket.setWatchers(employeeRepository.findByEmployeeNumberIn(employeeNumbers));
        ticketRepository.save(ticket);
    }
}
