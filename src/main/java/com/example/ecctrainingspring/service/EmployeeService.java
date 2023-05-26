package com.example.ecctrainingspring.service;

import com.example.ecctrainingspring.model.po.Employee;
import com.example.ecctrainingspring.model.ro.EmployeeRO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Optional<Employee> findEmployee(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployeeById(Long id, EmployeeRO employeeRO);
    void deleteEmployeeById(Long id) throws Exception;
    void assignTicket(Long employeeId, Long ticketId);
    void addWatchers(Long ticketId, List<Long> employeeNumber);
}
