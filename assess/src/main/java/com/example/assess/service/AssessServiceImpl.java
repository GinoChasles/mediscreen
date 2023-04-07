package com.example.assess.service;

import com.example.assess.model.AssessPatient;
import com.example.assess.model.Note;
import com.example.assess.model.Patient;
import com.example.assess.proxy.MicroServiceNote;
import com.example.assess.proxy.MicroServicePatient;
import com.example.assess.utils.Assess;
import com.example.assess.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Assess service.
 */
@Service
public class AssessServiceImpl implements AssessService {
    private final MicroServiceNote microServiceNote;
    private final MicroServicePatient microServicePatient;


    /**
     * Instantiates a new Assess service.
     *
     * @param msp the msp
     * @param msn the msn
     */
    public AssessServiceImpl(MicroServicePatient msp, MicroServiceNote msn) {
        this.microServiceNote = msn;
        this.microServicePatient = msp;
    }


    @Override
    public AssessPatient getAssess(long id) throws JsonProcessingException {
        String patientJson = microServicePatient.getPatient(id);

        ObjectMapper patientMapper = new ObjectMapper();
        Patient patient = patientMapper.readValue(patientJson, Patient.class);

        String noteJson = microServiceNote.getPatientNotes(id);
        ObjectMapper noteMapper = new ObjectMapper();
        AssessPatient result = new AssessPatient();
        result.setFirstName(patient.getFirstname());
        result.setLastName(patient.getLastname());
        result.setAge(patient.getAge());
        if (noteJson != null) {
            List<Note> noteList = noteMapper.readValue(noteJson, new TypeReference<List<Note>>() {
            });
            int assessCompt = Utils.getAssesCompt(noteList);
            result.setAssessement(Assess.NONE);

            //demander que se passe t il s'il y a 6 déclencheurs
            if (assessCompt == 0) {
                result.setAssessement(Assess.NONE);
            }
            if (result.getAge() > Utils.ageLimitor && assessCompt == 2) {
                result.setAssessement(Assess.BORDERLINE);
            }
            if (result.getAge() < Utils.ageLimitor && patient.getGender() == "M" && assessCompt == 3) {
                result.setAssessement(Assess.INDANGER);
            }
            if (result.getAge() < Utils.ageLimitor && patient.getGender() == "F" && assessCompt == 4) {
                result.setAssessement(Assess.INDANGER);
            }
            if (result.getAge() > Utils.ageLimitor && assessCompt == 6) {
                result.setAssessement(Assess.INDANGER);
            }
            if (result.getAge() < Utils.ageLimitor && patient.getGender() == "M" && assessCompt == 5) {
                result.setAssessement(Assess.EARLYONSET);
            }
            if (result.getAge() < Utils.ageLimitor && patient.getGender() == "F" && assessCompt == 7) {
                result.setAssessement(Assess.EARLYONSET);
            }
            if (result.getAge() > Utils.ageLimitor && assessCompt >= 8) {
                result.setAssessement(Assess.EARLYONSET);
            }
        } else {
            result.setAssessement(Assess.NONE);
        }


        return result;
    }


}
