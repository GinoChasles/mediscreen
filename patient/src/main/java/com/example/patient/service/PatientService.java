package com.example.patient.service;

import com.example.patient.model.Patient;

import java.util.List;
import java.util.Optional;

/**
 * The interface Patient service.
 */
public interface PatientService {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Patient> findById(long id);

    /**
     * Insert patient.
     *
     * @param patient the patient
     * @return the patient
     */
    Patient insert(Patient patient);

    /**
     * Update patient.
     *
     * @param id      the id
     * @param patient the patient
     * @return the patient
     */
    Patient update(long id, Patient patient);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Patient> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Insert all.
     *
     * @param patientList the patient list
     */
    void insertAll(List<Patient> patientList);
}
