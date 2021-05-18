package com.repeta.airport.plane;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "technical_inspection")
public class TechnicalInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "plane_id", foreignKey = @ForeignKey(name = "technical_inspection_plane_id_fk"))
    private Plane plane;

    private String verdict;

    private LocalDate inspectionDate;

}
