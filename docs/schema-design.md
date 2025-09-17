docs/schema-design.md# Database Schema Design (MySQL)

## Tables

### Doctors
- id (PK, INT, AUTO_INCREMENT)
- name (VARCHAR)
- specialty (VARCHAR)
- available_times (TEXT)

### Patients
- id (PK, INT, AUTO_INCREMENT)
- name (VARCHAR)
- email (VARCHAR, UNIQUE)
- phone_number (VARCHAR, UNIQUE)

### Appointments
- id (PK, INT, AUTO_INCREMENT)
- doctor_id (FK → Doctors.id)
- patient_id (FK → Patients.id)
- appointment_time (DATETIME)

### Prescriptions
- id (PK, INT, AUTO_INCREMENT)
- appointment_id (FK → Appointments.id)
- medicine (VARCHAR)
- dosage (VARCHAR)
