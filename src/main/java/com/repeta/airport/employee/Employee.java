package com.repeta.airport.employee;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int brigade;

    @ManyToOne
    @JoinColumn(name = "supervisor_id",foreignKey = @ForeignKey(name = "staff_staff_id_fk"))
    private Employee supervisor;

    private String department;

    private String name;

    private String surname;

    @Column(name = "date_of_birth")
    private Calendar dateOfBirth;

    private char gender;

    private int kids;

    @Column(name = "sal_hour")
    private double salHour;

    @Column(name = "employed_since")
    private Calendar employedSince;

}
