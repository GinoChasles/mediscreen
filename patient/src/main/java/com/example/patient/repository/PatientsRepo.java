package com.example.patient.repository;

import com.example.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Patients repo.
 */
@Repository
public interface PatientsRepo extends JpaRepository<Patient, Long> {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Patient> findById(long id);
}
