package com.repeta.airport.flight;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flight_log")
public class FlightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "flight_log_flight_id_fk"))
    private Flight flight;

    private String state;

    private LocalDateTime time;

}
