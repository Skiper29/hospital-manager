package com.demkiv.hospitalmanager.controller;

import com.demkiv.hospitalmanager.dto.ReceptionDTO;
import com.demkiv.hospitalmanager.dto.mapper.EntityMapper;
import com.demkiv.hospitalmanager.security.SecurityFilter;
import com.demkiv.hospitalmanager.service.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/receptions")
public class ReceptionController {
    private final ReceptionService receptionService;
    private final SecurityFilter securityFilter;
    private final EntityMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllReceptions() {
        return ResponseEntity.ok().body(receptionService
                .findAll()
                .stream()
                .map(mapper::fromReception)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReceptionById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.fromReception(receptionService.findById(id)));
    }

    @GetMapping("/{id}/prescriptions")
    public ResponseEntity<?> getReceptionPrescriptions(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(receptionService
                        .findById(id)
                        .getPrescriptions()
                        .stream()
                        .map(mapper::fromPrescription)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<?> addReception(@RequestBody ReceptionDTO reception, @RequestParam Long staffId) {
        securityFilter.isDoctorOrAdmin(staffId);
        return ResponseEntity.ok().body(mapper.fromReception(receptionService.add(mapper.toReception(reception))));
    }

    @PostMapping("/{id}/discharge")
    public ResponseEntity<?> discharge(@PathVariable Long id, @RequestParam Long staffId,
                                       @RequestParam String finalDiagnosis) {
        securityFilter.isDoctorOrAdmin(staffId);
        receptionService.discharge(id, finalDiagnosis);
        return ResponseEntity.ok().body("Patient was successfully discharged");
    }

    @PutMapping
    public ResponseEntity<?> updateReception(@RequestBody ReceptionDTO reception) {
        return ResponseEntity.ok().body(mapper.fromReception(receptionService.update(mapper.toReception(reception))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReception(@PathVariable long id, @RequestParam Long staffId) {
        securityFilter.isAdmin(staffId);
        receptionService.delete(id);
        return ResponseEntity.ok().body("Reception was successfully deleted");
    }
}
