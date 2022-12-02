package com.example.patient.controller;

import com.example.patient.service.PatientServiceImp;
import com.example.patient.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Patient controller.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PatientServiceImp patientServiceImp;

    /**
     * Instantiates a new Patient controller.
     *
     * @param service the service
     */
    public PatientController(PatientServiceImp service) {
        patientServiceImp = service;
    }


    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable(value = "id") long id) {
        logger.info("Searching patient with his id: " + id);
        Optional<Patient> result = patientServiceImp.findById(id);
        if (result.isEmpty()) {
            logger.error("Patient not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Patient found : " + result);
            return ResponseEntity.ok().body(result.get());
        }
    }

    /**
     * Add patient response entity.
     *
     * @param patient the patient
     * @return the response entity
     */
    @PostMapping(value = "/add")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        logger.info("Creating patient folder in system.");

        return ResponseEntity.ok(patientServiceImp.insert(patient));
    }

    /**
     * Update response entity.
     *
     * @param id      the id
     * @param patient the patient
     * @return the response entity
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Patient> update(@PathVariable(value = "id") int id, @Valid @RequestBody Patient patient) {
        logger.info("Starting updating patient...");
        Patient patient1 = patientServiceImp.update(id, patient);
        if (patient1 == null) {
            logger.error("Patient with id :" + id + " is not found.");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Patient updating ok !");
            return ResponseEntity.ok().body(patient1);
        }
    }
}
