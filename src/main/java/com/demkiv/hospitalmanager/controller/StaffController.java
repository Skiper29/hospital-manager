package com.demkiv.hospitalmanager.controller;

import com.demkiv.hospitalmanager.dto.mapper.EntityMapper;
import com.demkiv.hospitalmanager.model.Staff;
import com.demkiv.hospitalmanager.security.SecurityFilter;
import com.demkiv.hospitalmanager.service.PatientService;
import com.demkiv.hospitalmanager.service.PrescriptionService;
import com.demkiv.hospitalmanager.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    private final SecurityFilter securityFilter;
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;
    private final EntityMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllStaff() {
        return ResponseEntity.ok().body(staffService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStaffById(@PathVariable Long id) {
        return ResponseEntity.ok().body(staffService.findById(id));
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<?> getPatientByDoctor(@PathVariable Long id) {
        return ResponseEntity.ok().body(patientService.findByDoctorId(id));
    }

    @GetMapping("/{id}/executed-prescriptions")
    public ResponseEntity<?> getExecutedPrescriptions(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(prescriptionService
                        .findByExecutorId(id)
                        .stream()
                        .map(mapper::fromPrescription)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody Staff staff, @RequestParam Long staffId) {
        securityFilter.isAdmin(staffId);
        return ResponseEntity.ok().body(staffService.add(staff));
    }

    @PutMapping
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok().body(staffService.update(staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable long id, @RequestParam Long staffId) {
        securityFilter.isAdmin(staffId);
        staffService.delete(id);
        return ResponseEntity.ok().body("Staff was successfully deleted");
    }
}
