package com.demkiv.hospitalmanager.dto;

import com.demkiv.hospitalmanager.model.enums.PrescriptionType;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class PrescriptionDTO {
    private Long id;

    private PrescriptionType prescriptionType;

    private String name;

    private boolean isExecuted;

    private Date executionDate;

    private Long reception;

    private Long executor;
}
