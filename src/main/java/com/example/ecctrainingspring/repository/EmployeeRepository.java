package com.example.ecctrainingspring.repository;

import com.example.ecctrainingspring.model.po.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    Employee findAllByEmployeeNumber(Long employeeNumber);
    Employee findEmployeeByEmployeeNumber(Long employeeNumber);
    List<Employee> findByEmployeeNumberIn(List<Long> employeeNumber);
}
