package com.repeta.airport.passenger;

import com.repeta.airport.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private char gender;

    @OneToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "passenger_user_id_fk"))
    private User user;

}
