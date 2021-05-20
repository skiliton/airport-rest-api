package com.repeta.airport.passenger;

import com.repeta.airport.flight.Flight;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "passenger_check")
public class PassengerCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", foreignKey = @ForeignKey(name = "passenger_check_passenger_id_fk"))
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", foreignKey = @ForeignKey(name = "passenger_check_flight_id_fk"))
    private Flight flight;

    private String checkName;

    private boolean checkResult;

}
