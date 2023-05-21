package com.example.ecctrainingspring.model.po;

import com.example.ecctrainingspring.enums.EnumDepartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_number", columnDefinition = "BIGINT(20) NOT NULL UNIQUE KEY auto_increment")
    private Long employeeNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private EnumDepartment department;


}
