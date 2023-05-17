package com.example.ecctrainingspring.model;

import com.example.ecctrainingspring.enums.Severity;
import com.example.ecctrainingspring.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private Long ticketNo;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Employee assignee;

    private List<Employee> watchers;
}
