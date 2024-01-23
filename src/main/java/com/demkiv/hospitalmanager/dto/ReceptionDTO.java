package com.demkiv.hospitalmanager.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ReceptionDTO {
    private Long id;

    private Long patient;

    private Long doctor;

    private boolean isDischarge;

    private String preliminaryDiagnosis;

    private String finalDiagnosis;

    private Date dateOfAdmission;

    private Date dateOfDischarge;


}
