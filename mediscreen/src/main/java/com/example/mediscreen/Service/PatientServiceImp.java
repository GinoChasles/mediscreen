package com.example.mediscreen.Service;

import com.example.mediscreen.model.Patient;
import com.example.mediscreen.repository.PatientsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceImp implements PatientService {
    private final PatientsRepo patientsRepo;

    public PatientServiceImp(PatientsRepo repo) {
        patientsRepo = repo;
    }

    @Override
    public Optional<Patient> findById(long id) {
        return patientsRepo.findById(id);
    }

    @Override
    public Optional<Patient> findByPatient(long id) {
        return null;
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
