package com.repeta.airport.flight;

import com.repeta.airport.airport.Airport;
import com.repeta.airport.plane.Plane;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "plane_id", foreignKey = @ForeignKey(name = "flight_plane_id_fk"))
    private Plane plane;

    private LocalDateTime takeoff;

    private LocalDateTime landing;

    private String type;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", foreignKey = @ForeignKey(name = "flight_airport_id_fk_dep"))
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "transfer_airport_id", foreignKey = @ForeignKey(name = "flight_airport_id_fk_tra"))
    private Airport transferAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", foreignKey = @ForeignKey(name = "flight_airport_id_fk_des"))
    private Airport destinationAirport;

    private int ticketPrice;

    private int maxPassengers;

    private int minPassengers;

}
