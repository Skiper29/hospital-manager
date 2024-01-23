package com.demkiv.hospitalmanager.service;

import com.demkiv.hospitalmanager.exception.exceptions.CannotPerformOperationException;
import com.demkiv.hospitalmanager.exception.exceptions.ElementNotFoundException;
import com.demkiv.hospitalmanager.model.Patient;
import com.demkiv.hospitalmanager.model.Reception;
import com.demkiv.hospitalmanager.repository.ReceptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceptionService {
    private final ReceptionRepository receptionRepository;
    private final StaffService staffService;

    public Reception add(Reception reception) {
        reception.setDischarge(false);
        reception.setDateOfAdmission(Date.valueOf(LocalDate.now()));
        return receptionRepository.save(reception);
    }

    public Reception findById(Long id) {
        return receptionRepository
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id.toString()));
    }

    public List<Reception> findByDoctorId(Long id) {
        return receptionRepository.findByDoctor(staffService.findById(id));
    }

    public List<Reception> findAll() {
        return receptionRepository.findAll();
    }

    public List<Reception> findByPatient(Patient patient) {
        return receptionRepository.findByPatient(patient);
    }

    public Reception update(Reception reception) {
        return receptionRepository.save(reception);
    }

    public void delete(Long id) {
        receptionRepository.deleteById(id);
    }

    public void discharge(Long id, String finalDiagnosis) {
        Reception reception = findById(id);
        if (reception.isDischarge()) {
            throw new CannotPerformOperationException("Patient already discharged");
        }
        reception.setFinalDiagnosis(finalDiagnosis);
        reception.setDateOfDischarge(Date.valueOf(LocalDate.now()));
        reception.setDischarge(true);
        update(reception);
    }
}
