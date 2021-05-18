package com.repeta.airport.passenger;

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

    private String checkName;

    private boolean checkResult;

}
