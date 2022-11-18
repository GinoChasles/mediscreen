package com.example.mediscreen.repository;

import com.example.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(long id);
}
