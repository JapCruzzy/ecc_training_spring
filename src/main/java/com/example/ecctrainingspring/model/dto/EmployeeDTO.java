package com.example.ecctrainingspring.model.dto;

import com.example.ecctrainingspring.model.po.Employee;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeDTO extends Employee {
}
