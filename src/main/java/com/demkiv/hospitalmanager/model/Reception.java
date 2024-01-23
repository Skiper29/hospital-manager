package com.demkiv.hospitalmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reception")
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Staff doctor;

    private boolean isDischarge;

    private String preliminaryDiagnosis;

    private String finalDiagnosis;

    private Date dateOfAdmission;

    private Date dateOfDischarge;

    @OneToMany
    @JoinColumn(name = "reception_id")
    private List<Prescription> prescriptions;
}
