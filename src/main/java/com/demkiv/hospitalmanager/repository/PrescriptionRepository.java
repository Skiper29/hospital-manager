package com.demkiv.hospitalmanager.repository;

import com.demkiv.hospitalmanager.model.Prescription;
import com.demkiv.hospitalmanager.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByExecutor(Staff staff);
}
