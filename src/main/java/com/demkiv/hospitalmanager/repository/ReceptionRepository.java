package com.demkiv.hospitalmanager.repository;

import com.demkiv.hospitalmanager.model.Patient;
import com.demkiv.hospitalmanager.model.Reception;
import com.demkiv.hospitalmanager.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    List<Reception> findByPatient(Patient patient);
    List<Reception> findByDoctor(Staff staff);
}
