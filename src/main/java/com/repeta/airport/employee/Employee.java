package com.repeta.airport.employee;

import com.repeta.airport.user.User;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @ManyToOne
    @JoinColumn(name = "brigade", foreignKey = @ForeignKey(name = "employee_brigade_id_fk"))
    private Brigade brigade;

    @ManyToOne
    @JoinColumn(name = "supervisor_id",foreignKey = @ForeignKey(name = "staff_staff_id_fk"))
    private Employee supervisor;

    private String department;

    private String name;

    private String surname;

    private LocalDateTime dateOfBirth;

    private char gender;

    private int kids;

    private double salHour;

    private Calendar employedSince;
    
    @OneToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "employee_user_id_fk"))
    private User user;

}
