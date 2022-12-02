package service;

import com.example.patient.Service.PatientServiceImp;
import com.example.patient.model.Patient;
import com.example.patient.repository.PatientsRepo;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImp serviceImp;

    @Mock
    private PatientsRepo repo;

    private Patient patient;
    private List<Patient> patientList = new ArrayList<>();
    private long id = 1;

    @BeforeEach
    void setUp() {
        Date datePatient1 = new Date(1966 - 03 - 30);
        patient = new Patient(id,"SecondTest", "Test", datePatient1, "M", "1 Brookside St", "100-222-3333");
        patientList.add(patient);
    }

         @Test
            public void getPatientByIdTest() {
                long id = 1;
                when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(patient));
                Optional<Patient> optionalPatient = serviceImp.findById(id);
                if (optionalPatient.isPresent()) {
                    Patient result = optionalPatient.get();
                    assertThat(result.getFirstname()).isEqualTo(patient.getFirstname());
                }
    }

    @Test
    public void updateTest() {
        when(repo.findById(anyLong())).thenReturn(Optional.of(patient));
        Patient patientToUpdate = patient;
        patientToUpdate.setFirstname("Updating");
        when(repo.save(any(Patient.class))).thenReturn(patientToUpdate);

        Patient patient1 = serviceImp.update(patient.getId(), patientToUpdate);
        if (patient1 != null) {
            assertThat(patient1.getFirstname()).isEqualTo("Updating");
        }
    }

}
