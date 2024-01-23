package com.demkiv.hospitalmanager.repository;

import com.demkiv.hospitalmanager.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
