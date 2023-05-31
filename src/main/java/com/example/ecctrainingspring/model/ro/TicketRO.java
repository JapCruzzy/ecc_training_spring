package com.example.ecctrainingspring.model.ro;

import com.example.ecctrainingspring.enums.EnumSeverity;
import com.example.ecctrainingspring.model.dto.TicketDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TicketRO extends TicketDTO implements Serializable {

    private String title;
    private String description;
    private EnumSeverity severity;
}
