package com.repeta.airport.ticket;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ticket_log")
public class TicketLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_log_ticket_id_fk"))
    private Ticket ticket;

    private String state;

    private LocalDateTime time;

}
