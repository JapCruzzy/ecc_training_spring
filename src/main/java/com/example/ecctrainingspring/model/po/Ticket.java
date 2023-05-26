package com.example.ecctrainingspring.model.po;

import com.example.ecctrainingspring.enums.EnumSeverity;
import com.example.ecctrainingspring.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketNo;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private EnumSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumStatus status;

    @OneToOne
    private Employee assignee;

    @ManyToMany
    private List<Employee> watchers;
}
