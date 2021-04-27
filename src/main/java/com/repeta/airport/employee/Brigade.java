package com.repeta.airport.employee;

import com.repeta.airport.plane.Plane;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "brigade")
public class Brigade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "plane_id", foreignKey = @ForeignKey(name = "brigade_plane_id_fk"))
    private Plane plane;

    private String type;
}
