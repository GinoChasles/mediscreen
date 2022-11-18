package com.example.mediscreen.Service;

import com.example.mediscreen.model.Patient;

import java.util.Optional;

public interface PatientService {
    Optional<Patient> findById(long id);

    //TODO: find a patient but with what parameters ??
    Optional<Patient> findByPatient(long id);

    Patient insert(Patient patient);
    Patient update(long id, Patient patient);

}
