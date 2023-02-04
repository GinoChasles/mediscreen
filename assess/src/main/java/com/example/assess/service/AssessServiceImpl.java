package com.example.assess.service;

import com.example.assess.model.AssessPatient;
import com.example.assess.model.Note;
import com.example.assess.model.Patient;
import com.example.assess.proxy.MicroServiceNote;
import com.example.assess.proxy.MicroServicePatient;
import com.example.assess.utils.Assess;
import com.example.assess.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessServiceImpl implements AssessService {
    private final MicroServiceNote microServiceNote;
    private final MicroServicePatient microServicePatient;


    public AssessServiceImpl(MicroServicePatient msp, MicroServiceNote msn) {
        microServiceNote = msn;
        microServicePatient = msp;
    }


    @Override
    public AssessPatient getAssess(long id) {
        Patient patient = microServicePatient.getPatient(id);
        List<Note> noteList = microServiceNote.getPatientNotes(id);
        AssessPatient result = new AssessPatient();

        result.setFirstName(patient.getFirstName());
        result.setLastName(patient.getLastName());
        result.setAge(patient.getAge());

        int assessCompt = Utils.getAssesCompt(noteList);


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

        return result;
    }


}
