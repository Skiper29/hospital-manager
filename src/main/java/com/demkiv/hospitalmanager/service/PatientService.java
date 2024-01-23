package com.demkiv.hospitalmanager.service;

import com.demkiv.hospitalmanager.exception.exceptions.ElementNotFoundException;
import com.demkiv.hospitalmanager.model.Patient;
import com.demkiv.hospitalmanager.model.Reception;
import com.demkiv.hospitalmanager.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PatientService {

    private PatientRepository patientRepository;
    private ReceptionService receptionService;

    @Autowired
    public void setReceptionService(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    @Lazy
    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient add(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        return patientRepository
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id.toString()));
    }

    public List<Patient> findByDoctorId(Long id) {
        List<Reception> receptions = receptionService.findByDoctorId(id);
        return receptions.stream().map(Reception::getPatient).collect(Collectors.toList());
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
