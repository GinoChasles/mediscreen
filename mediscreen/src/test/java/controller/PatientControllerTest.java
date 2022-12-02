package controller;

import com.example.mediscreen.MediscreenApplication;
import com.example.mediscreen.model.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MediscreenApplication.class)
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    Date datePatient1 = new Date(1966-03-30);
    Patient patient1 = new Patient( "SecondTest", "Test", datePatient1, "M", "1 Brookside St", "100-222-3333");


    @Test
    public void addPatientTest() throws Exception {
        Date newDate = new Date(1912 / 12 / 12);
        Patient patientZero = new Patient("Béber", "LaCouille", newDate, "F", "3 rue bidon", "07491888255");
        String json = mapper.writeValueAsString(patientZero);
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders
                .post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(mock).andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void updateTest() throws Exception {
        Patient patientZero = patient1;
        patientZero.setFirstname("ThirdTest");
        String json = mapper.writeValueAsString(patientZero);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void addPatientWithoutAddressAndPhoneTest() throws Exception {
        Date newDate = new Date(1912 / 12 / 12);
        Patient patientZero = new Patient("TestWithoutAddress", "WithoutPhone", newDate, "F");
        String json = mapper.writeValueAsString(patientZero);
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders
                .post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(mock).andExpect(status().isOk());
    }
}
