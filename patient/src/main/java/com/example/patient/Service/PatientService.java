package com.example.patient.Service;

import com.example.patient.model.Patient;

import java.util.Optional;

public interface PatientService {
    Optional<Patient> findById(long id);
    Patient insert(Patient patient);
    Patient update(long id, Patient patient);
}
