package com.repeta.airport.user;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String roles;

}
