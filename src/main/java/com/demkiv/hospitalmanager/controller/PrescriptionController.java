package com.demkiv.hospitalmanager.controller;

import com.demkiv.hospitalmanager.dto.PrescriptionDTO;
import com.demkiv.hospitalmanager.dto.mapper.EntityMapper;
import com.demkiv.hospitalmanager.security.SecurityFilter;
import com.demkiv.hospitalmanager.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final SecurityFilter securityFilter;
    private final EntityMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPrescriptions() {
        return ResponseEntity.ok().body(prescriptionService.findAll().stream().map(mapper::fromPrescription).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.fromPrescription(prescriptionService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestBody PrescriptionDTO prescription, @RequestParam Long staffId) {
        securityFilter.isDoctorOrAdmin(staffId);
        return ResponseEntity
                .ok()
                .body(mapper.fromPrescription(prescriptionService.add(mapper.toPrescription(prescription))));
    }

    @PostMapping("/{id}/execute")
    public ResponseEntity<?> execute(@PathVariable Long id, @RequestParam Long staffId) {
        securityFilter.executionFilter(id, staffId);
        prescriptionService.execute(id, staffId);
        return ResponseEntity.ok().body("Prescription was successfully executed");
    }

    @PutMapping
    public ResponseEntity<?> updatePrescription(@RequestBody PrescriptionDTO prescription) {
        return ResponseEntity
                .ok()
                .body(mapper.fromPrescription(prescriptionService.update(mapper.toPrescription(prescription))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable long id, @RequestParam Long staffId) {
        securityFilter.isAdmin(staffId);
        prescriptionService.delete(id);
        return ResponseEntity.ok().body("Prescription was successfully deleted");
    }
}
