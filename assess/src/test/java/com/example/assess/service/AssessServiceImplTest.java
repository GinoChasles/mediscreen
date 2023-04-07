package com.example.assess.service;

import com.example.assess.model.AssessPatient;
import com.example.assess.model.Note;
import com.example.assess.model.Patient;
import com.example.assess.proxy.MicroServiceNote;
import com.example.assess.proxy.MicroServicePatient;
import com.example.assess.utils.Assess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AssessServiceImplTest {

    @InjectMocks
    private AssessServiceImpl service;
    @Mock
    private MicroServiceNote microServiceNote;
    @Mock
    private MicroServicePatient microServicePatient;

    private Patient patient1;
    private Patient patient2;
    private Patient patient3;
    private Patient patient4;
    private List<Note> patient1NoteList = new ArrayList<>();
    private List<Note> patient2NoteList = new ArrayList<>();
    private List<Note> patient3NoteList = new ArrayList<>();
    private List<Note> patient4NoteList = new ArrayList<>();



    @BeforeEach
    void setUp() {
        Date datePatient1 = new Date("1970/01/01");
        Date datePatient2 = new Date("1952/09/27");
        Date datePatient3 = new Date("1952/11/11");
        Date datePatient4 = new Date("1946/11/26");
        patient1 = new Patient(1L, "SecondTest", "Test", datePatient1, "M", "1 Brookside St", "100-222-3333");
        patient2 = new Patient(2L, "SecondTest", "Test", datePatient2, "M", "1 Brookside St", "100-222-3333");
        patient3 = new Patient(3L, "SecondTest", "Test", datePatient3, "M", "1 Brookside St", "100-222-3333");
        patient4 = new Patient(4L, "SecondTest", "Test", datePatient4, "M", "1 Brookside St", "100-222-3333");
        Note note1 = new Note("abcd", 1L, "Patient states that they are 'feeling terrific' Weight at or below recommended level");
        Note note2 = new Note("abbb", 2L, "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late\"");
        Note note3 = new Note("abbbc", 2L, "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic");
        Note note4 = new Note("abbbc1", 3L, "Réaction, Anticorps, Hemoglobin A1C, Microalbumine, Size, Weight, Smoker, Abnormal, Cholesterol, Vertigo, Relapse, Reaction, Antibodies");
        Note note6 = new Note("abbce", 4L, "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication");
        Note note7 = new Note("abbcr", 4L, "Patient states that they are experiencing back pain when seated for a long time");
        Note note8 = new Note("abbct", 4L, "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level");
        Note note9 = new Note("abbcy", 4L, "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
        patient1NoteList.add(note1);
        patient2NoteList.add(note2);
        patient2NoteList.add(note3);
        patient3NoteList.add(note4);
        patient4NoteList.add(note6);
        patient4NoteList.add(note7);
        patient4NoteList.add(note8);
        patient4NoteList.add(note9);
    }

    @Test
    public void getAssess_returnNoneAssess() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        when(microServicePatient.getPatient(anyLong())).thenReturn(ow.writeValueAsString(patient1));
        when(microServiceNote.getPatientNotes(anyLong())).thenReturn(ow.writeValueAsString(patient1NoteList));
        AssessPatient assessPatient = service.getAssess(1L);
        assertThat(assessPatient.getAssessement()).isEqualTo(Assess.NONE);
    }

    @Test
    public void getAssess_returnBorderlineAssess() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        when(microServicePatient.getPatient(anyLong())).thenReturn(ow.writeValueAsString(patient2));
        when(microServiceNote.getPatientNotes(anyLong())).thenReturn(ow.writeValueAsString(patient2NoteList));
        AssessPatient assessPatient = service.getAssess(2L);
        assertThat(assessPatient.getAssessement()).isEqualTo(Assess.BORDERLINE);
    }

    @Test
    public void getAssess_returnInDangerAssess() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        when(microServicePatient.getPatient(anyLong())).thenReturn(ow.writeValueAsString(patient4));
        when(microServiceNote.getPatientNotes(anyLong())).thenReturn(ow.writeValueAsString(patient4NoteList));
        AssessPatient assessPatient = service.getAssess(4L);
        assertThat(assessPatient.getAssessement()).isEqualTo(Assess.INDANGER);
    }

    @Test
    public void getAssess_returnEarlyOnSetAssess() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        when(microServicePatient.getPatient(anyLong())).thenReturn(ow.writeValueAsString(patient3));
        when(microServiceNote.getPatientNotes(anyLong())).thenReturn(ow.writeValueAsString(patient3NoteList));
        AssessPatient assessPatient = service.getAssess(3L);
        assertThat(assessPatient.getAssessement()).isEqualTo(Assess.EARLYONSET);
    }
}
