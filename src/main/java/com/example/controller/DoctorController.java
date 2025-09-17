package com.example.controller;

import com.example.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable Long id,
                                             @RequestHeader("Authorization") String token) {
        // Q5: validate token, return structured response
        return ResponseEntity.ok(doctorService.getAvailableSlots(id));
    }
}
