package com.demkiv.hospitalmanager.dto.mapper;

import com.demkiv.hospitalmanager.dto.PrescriptionDTO;
import com.demkiv.hospitalmanager.dto.ReceptionDTO;
import com.demkiv.hospitalmanager.model.Prescription;
import com.demkiv.hospitalmanager.model.Reception;
import com.demkiv.hospitalmanager.repository.ReceptionRepository;
import com.demkiv.hospitalmanager.service.PatientService;
import com.demkiv.hospitalmanager.service.ReceptionService;
import com.demkiv.hospitalmanager.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private final PatientService patientService;
    private final StaffService staffService;
    private final ReceptionService receptionService;
    private final ReceptionRepository receptionRepository;

    public ReceptionDTO fromReception(Reception reception) {
        return ReceptionDTO
                .builder()
                .id(reception.getId())
                .patient(reception.getPatient().getId())
                .doctor(reception.getDoctor().getId())
                .isDischarge(reception.isDischarge())
                .preliminaryDiagnosis(reception.getPreliminaryDiagnosis())
                .finalDiagnosis(reception.getFinalDiagnosis())
                .dateOfAdmission(reception.getDateOfAdmission())
                .dateOfDischarge(reception.getDateOfDischarge())
                .build();
    }

    public Reception toReception(ReceptionDTO reception) {
        Reception reception1 = receptionRepository.findById(reception.getId()).orElse(null);
        return Reception
                .builder()
                .id(reception.getId())
                .patient(patientService.findById(reception.getPatient()))
                .doctor(staffService.findById(reception.getDoctor()))
                .isDischarge(reception.isDischarge())
                .preliminaryDiagnosis(reception.getPreliminaryDiagnosis())
                .finalDiagnosis(reception.getFinalDiagnosis())
                .dateOfAdmission(reception.getDateOfAdmission())
                .dateOfDischarge(reception.getDateOfDischarge())
                .prescriptions(reception1 == null ? null : reception1.getPrescriptions())
                .build();
    }

    public PrescriptionDTO fromPrescription(Prescription prescription) {
        return PrescriptionDTO
                .builder()
                .id(prescription.getId())
                .name(prescription.getName())
                .prescriptionType(prescription.getPrescriptionType())
                .isExecuted(prescription.isExecuted())
                .executionDate(prescription.getExecutionDate())
                .executor(prescription.isExecuted() ? prescription.getExecutor().getId() : -1)
                .reception(prescription.getReception().getId())
                .build();
    }

    public Prescription toPrescription(PrescriptionDTO prescription) {
        return Prescription
                .builder()
                .id(prescription.getId())
                .name(prescription.getName())
                .prescriptionType(prescription.getPrescriptionType())
                .isExecuted(prescription.isExecuted())
                .executionDate(prescription.getExecutionDate())
                .executor(prescription.isExecuted() ? staffService.findById(prescription.getExecutor()) : null)
                .reception(receptionService.findById(prescription.getReception()))
                .build();
    }
}
