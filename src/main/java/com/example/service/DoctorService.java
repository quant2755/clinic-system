package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DoctorService {
    public List<String> getAvailableSlots(Long doctorId) {
        return Arrays.asList("2025-09-20 10:00", "2025-09-20 11:00");
    }

    public boolean validateLogin(String username, String password) {
        return "doctor".equals(username) && "pass".equals(password);
    }
}
