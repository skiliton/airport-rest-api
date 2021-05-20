package com.repeta.airport.ticket;

import com.repeta.airport.flight.Flight;
import com.repeta.airport.passenger.Passenger;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", foreignKey = @ForeignKey(name = "ticket_passenger_id_fk"))
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "ticket_flight_id_fk"))
    private Flight flight;

    private int seat;

    @ManyToOne
    @JoinColumn(name = "status", foreignKey = @ForeignKey(name = "ticket_ticket_log_id_fk"))
    private TicketLog status;

}
