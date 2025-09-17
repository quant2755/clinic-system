package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @Future
    private LocalDateTime appointmentTime;  // Q4
}
