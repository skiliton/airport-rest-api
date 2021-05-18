package com.repeta.airport.airport;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;

    private String city;

    private String name;

}
