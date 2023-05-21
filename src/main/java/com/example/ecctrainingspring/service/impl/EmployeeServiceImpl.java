package com.example.ecctrainingspring.service.impl;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.EmployeeRO;
import com.example.ecctrainingspring.repository.EmployeeRepository;
import com.example.ecctrainingspring.repository.TicketRepository;
import com.example.ecctrainingspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Employee findEmployee(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void createEmployee(EmployeeRO employeeRO) {
        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeRO.getEmployeeNumber());
        employee.setFirstName(employeeRO.getFirstName());
        employee.setMiddleName(employeeRO.getMiddleName());
        employee.setLastName(employeeRO.getLastName());
        employee.setDepartment(employeeRO.getDepartment());
        employeeRepository.save(employee);
    }

    @Override
    public int updateEmployeeById(Long id, EmployeeRO employeeRo) {
        int rowAffected = 0;
        Optional<Employee> employeeUpdate = employeeRepository.findById(id);

        if (employeeRepository.existsById(id)) {
            Employee employee = employeeUpdate.get();
            employee.setEmployeeNumber(employeeRo.getEmployeeNumber());
            employee.setFirstName(employeeRo.getFirstName());
            employee.setMiddleName(employeeRo.getMiddleName());
            employee.setLastName(employeeRo.getLastName());
            employee.setDepartment(employeeRo.getDepartment());
            employeeRepository.save(employee);
            rowAffected++;
        }
        return rowAffected;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void assignTicket(Long employeeNumber, Long ticketId) {
        Ticket ticket = ticketRepository.findTicketByTicketNo(ticketId);
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
