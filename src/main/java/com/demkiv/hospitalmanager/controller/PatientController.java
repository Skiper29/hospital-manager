package com.demkiv.hospitalmanager.controller;


import com.demkiv.hospitalmanager.dto.mapper.EntityMapper;
import com.demkiv.hospitalmanager.model.Patient;
import com.demkiv.hospitalmanager.security.SecurityFilter;
import com.demkiv.hospitalmanager.service.PatientService;
import com.demkiv.hospitalmanager.service.PrescriptionService;
import com.demkiv.hospitalmanager.service.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final SecurityFilter securityFilter;
    private final ReceptionService receptionService;
    private final PrescriptionService prescriptionService;
    private final EntityMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatients() {
        return ResponseEntity.ok().body(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok().body(patientService.findById(id));
    }

    @GetMapping("/{id}/receptions")
    public ResponseEntity<?> getReceptionsByPatientId(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(receptionService
                        .findByPatient(patientService.findById(id))
                        .stream()
                        .map(mapper::fromReception)
                        .toList());
    }

    @GetMapping("/{id}/prescriptions")
    public ResponseEntity<?> getPrescriptionsByPatientId(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(prescriptionService
                        .findByPatient(patientService.findById(id))
                        .stream()
                        .map(mapper::fromPrescription)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient, @RequestParam Long staffId) {
        securityFilter.isDoctorOrAdmin(staffId);
        return ResponseEntity.ok().body(patientService.add(patient));
    }

    @PutMapping
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
        return ResponseEntity.ok().body(patientService.update(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable long id, @RequestParam Long staffId) {
        securityFilter.isDoctorOrAdmin(staffId);
        patientService.delete(id);
        return ResponseEntity.ok().body("Patient was successfully deleted");
    }

}
