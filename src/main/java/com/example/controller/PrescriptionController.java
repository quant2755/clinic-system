package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    // POST endpoint to save prescription
    @PostMapping
    public ResponseEntity<?> savePrescription(@Valid @RequestBody PrescriptionRequest request,
                                              @RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or missing token");
        }

        if (request.getPatientId() == null || request.getMedication() == null) {
            return ResponseEntity.badRequest()
                    .body("Missing patientId or medication");
        }

        return ResponseEntity.ok("Prescription saved successfully");
    }

    // inner request DTO
    public static class PrescriptionRequest {
        private Long patientId;
        private String medication;

        public Long getPatientId() { return patientId; }
        public void setPatientId(Long patientId) { this.patientId = patientId; }

        public String getMedication() { return medication; }
        public void setMedication(String medication) { this.medication = medication; }
    }
}
