package com.example.mediscreen.controller;

import com.example.mediscreen.Service.PatientServiceImp;
import com.example.mediscreen.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PatientServiceImp patientServiceImp;

    public PatientController(PatientServiceImp service) {
        patientServiceImp = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> findById(@PathVariable(value = "id") int id) {
        logger.info("Searching patient with his id: " + id);
        Optional<Patient> result = patientServiceImp.findById(id);
        if (result.isEmpty()) {
            logger.error("Patient not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Patient found : " + result);
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Patient> addFireStation(@RequestBody Patient patient) {
        logger.info("Creating patient folder in system.");

        return ResponseEntity.ok(patientServiceImp.insert(patient));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Patient> update(@PathVariable(value = "id") int id, @RequestBody Patient patient) {
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
