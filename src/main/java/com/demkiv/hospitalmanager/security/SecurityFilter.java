package com.demkiv.hospitalmanager.security;

import com.demkiv.hospitalmanager.exception.exceptions.NoAccessException;
import com.demkiv.hospitalmanager.model.Prescription;
import com.demkiv.hospitalmanager.model.Staff;
import com.demkiv.hospitalmanager.model.enums.PrescriptionType;
import com.demkiv.hospitalmanager.model.enums.Role;
import com.demkiv.hospitalmanager.service.PrescriptionService;
import com.demkiv.hospitalmanager.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityFilter {
    private final StaffService staffService;
    private final PrescriptionService prescriptionService;

    public void isDoctorOrAdmin(Long id) {
        Staff staff = staffService.findById(id);
        if (staff.getRole() != Role.ADMIN && staff.getRole() != Role.DOCTOR) {
            throw new NoAccessException("This user cannot perform this operation");
        }
    }

    public void isAdmin(Long id) {
        Staff staff = staffService.findById(id);
        if (staff.getRole() != Role.ADMIN) {
            throw new NoAccessException("This user cannot perform this operation");
        }
    }

    public void executionFilter(Long prescriptionId, Long staffId) {
        Staff staff = staffService.findById(staffId);
        Prescription prescription = prescriptionService.findById(prescriptionId);
        if (staff.getRole() == Role.NURSE && prescription.getPrescriptionType() == PrescriptionType.OPERATION) {
            throw new NoAccessException("This staff cannot execute prescription");
        }
    }
}
