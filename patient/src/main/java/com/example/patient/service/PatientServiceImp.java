package com.example.patient.service;

import com.example.patient.model.Patient;
import com.example.patient.repository.PatientsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Patient service imp.
 */
@Service
public class PatientServiceImp implements PatientService {
    private final PatientsRepo patientsRepo;

    /**
     * Instantiates a new Patient service imp.
     *
     * @param repo the repo
     */
    public PatientServiceImp(PatientsRepo repo) {
        patientsRepo = repo;
    }

    @Override
    public Optional<Patient> findById(long id) {
        return patientsRepo.findById(id);
    }


    @Override
    public Patient insert(Patient patient) {
        return patientsRepo.save(patient);
    }

    @Override
    public Patient update(long id, Patient patient) {
        Optional<Patient> optionalPatient = this.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patientToUpdate = optionalPatient.get();
            patientToUpdate.setAddress(patient.getAddress());
            patientToUpdate.setBirthdate(patient.getBirthdate());
            patientToUpdate.setGender(patient.getGender());
            patientToUpdate.setFirstname(patient.getFirstname());
            patientToUpdate.setLastname(patient.getLastname());
            patientToUpdate.setPhone(patient.getPhone());

            return patientsRepo.save(patientToUpdate);
        } else {
            return null;
        }
    }
}
