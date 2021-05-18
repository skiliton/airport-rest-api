package com.repeta.airport.plane;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    @ManyToOne
    @JoinColumn(name = "status", foreignKey = @ForeignKey(name = "plane_plane_log_id_fk"))
    private PlaneLog status;

    private LocalDate firstFlight;

}
