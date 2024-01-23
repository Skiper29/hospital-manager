package com.demkiv.hospitalmanager.repository;

import com.demkiv.hospitalmanager.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
