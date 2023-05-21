package com.example.ecctrainingspring.service;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.po.Ticket;
import com.example.ecctrainingspring.model.ro.EmployeeRO;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findEmployee(Long id);
    void createEmployee(EmployeeRO employeeRo);
    int updateEmployeeById(Long id, EmployeeRO employeeRO);
    void deleteEmployeeById(Long id);
    void assignTicket(Long employeeId, Long ticketId);
    void addWatchers(Long ticketId, List<Long> employeeNumber);
}
