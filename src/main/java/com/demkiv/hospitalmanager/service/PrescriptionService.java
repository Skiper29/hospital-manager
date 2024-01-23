package com.demkiv.hospitalmanager.service;

import com.demkiv.hospitalmanager.exception.exceptions.CannotPerformOperationException;
import com.demkiv.hospitalmanager.exception.exceptions.ElementNotFoundException;
import com.demkiv.hospitalmanager.model.Patient;
import com.demkiv.hospitalmanager.model.Prescription;
import com.demkiv.hospitalmanager.model.Reception;
import com.demkiv.hospitalmanager.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final StaffService staffService;
    private final ReceptionService receptionService;

    public Prescription add(Prescription prescription) {
        prescription.setExecuted(false);
        return prescriptionRepository.save(prescription);
    }

    public Prescription findById(Long id) {
        return prescriptionRepository
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id.toString()));
    }

    public List<Prescription> findByExecutorId(Long id) {
        return prescriptionRepository.findByExecutor(staffService.findById(id));
    }

    public List<Prescription> findByPatient(Patient patient) {
        return receptionService
                .findByPatient(patient)
                .stream()
                .map(Reception::getPrescriptions)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public Prescription update(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public void delete(Long id) {
        prescriptionRepository.deleteById(id);
    }

    public void execute(Long id, Long executorId) {
        Prescription prescription = findById(id);
        if (prescription.isExecuted()) {
            throw new CannotPerformOperationException("Prescription already executed");
        }
        prescription.setExecutionDate(Date.valueOf(LocalDate.now()));
        prescription.setExecuted(true);
        prescription.setExecutor(staffService.findById(executorId));
        update(prescription);
    }
}
