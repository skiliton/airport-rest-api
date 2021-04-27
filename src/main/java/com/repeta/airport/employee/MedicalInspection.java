package com.repeta.airport.employee;

import jdk.vm.ci.meta.Local;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Data
@Table(name = "medical_inspection")
public class MedicalInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime inspectionDate;

    private boolean passed;

    @OneToOne
    @JoinColumn(name = "pilot_id", foreignKey = @ForeignKey(name = "medical_inspection"))
    private Employee pilot;

}
