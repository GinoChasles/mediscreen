package com.example.assess.controller;

import com.example.assess.AssessApplication;
import com.example.assess.model.AssessPatient;
import com.example.assess.utils.Assess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = AssessApplication.class)
@AutoConfigureMockMvc
public class AssessControllerTest {
    @Autowired
    MockMvc mockMvc;

    //private AssessPatient assessPatient = new AssessPatient("Prenom", "NomDeFamille", 32, Assess.NONE);

    @Test
    public void getAssessTest() throws Exception{
        mockMvc.perform(get("/assess/1")).andExpect(status().isOk());
    }
}
