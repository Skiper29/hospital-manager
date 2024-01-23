package com.demkiv.hospitalmanager.model;

import com.demkiv.hospitalmanager.model.enums.PrescriptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PrescriptionType prescriptionType;

    private String name;

    private boolean isExecuted;

    private Date executionDate;

    @ManyToOne
    private Reception reception;

    @ManyToOne
    private Staff executor;
}
