package com.repeta.airport.plane;

import com.repeta.airport.airport.Airport;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "plane_log")
public class PlaneLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "plane_id", foreignKey = @ForeignKey(name = "plane_log_plane_id_fk"))
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "airport_id", foreignKey = @ForeignKey(name = "plane_log_airport_id_fk"))
    private Airport airport;

    private String state;

    private LocalDateTime time;

}
