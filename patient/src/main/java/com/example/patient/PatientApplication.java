package com.example.patient;

import com.example.patient.model.Patient;
import com.example.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class PatientApplication implements CommandLineRunner {

	@Autowired
	private PatientService patientService;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
	//string formater
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		Date date1 = formatter.parse("1968-06-22");
	Patient patient1 = new Patient("Ferguson", "Lucas", date1, "M", "2 Warren Street", "387-866-1399");
	Patient patient2 = new Patient("Rees", "Pippa", formatter.parse("1952-9-27"), "F", "745 West Valley Farms Drive", "	628-423-0993");
	Patient patient3 = new Patient("Arnold", "Edward", formatter.parse("1952-11-11"), "M", "599 East Garden Ave", "123-727-2779");
	Patient patient4 = new Patient("Sharp", "Anthony", formatter.parse("1946-11-26"), "M", "894 Hall Street", "451-761-8383");
	Patient patient5 = new Patient("Ince", "Wendy", formatter.parse("1958-06-29"), "F", "4 Southampton Road", "802-911-9975");
	Patient patient6 = new Patient("Ross", "Tracey", formatter.parse("1949-12-07"), "F", "40 Sulphur Springs Dr", "131-396-5049");
	Patient patient7 = new Patient("Wilson", "Claire", formatter.parse("1966-12-31"), "F", "12 Cobblestone St", "300-452-1091");
	Patient patient8 = new Patient("Buckland", "Max", formatter.parse("1945-06-24"), "M", "193 Vale St", "833-534-0864");
	Patient patient9 = new Patient("Clark", "Natalie", formatter.parse("1964-06-18"), "F", "12 Beechwood Road", "241-467-9197");
	Patient patient10 = new Patient("Bailey", "Piers", formatter.parse("1959-06-28"), "M", "1202 Bumble Dr", "747-815-0557");

		List<Patient> patientList = new ArrayList<>();
		patientList.add(patient1);
		patientList.add(patient2);
		patientList.add(patient3);
		patientList.add(patient4);
		patientList.add(patient5);
		patientList.add(patient6);
		patientList.add(patient7);
		patientList.add(patient8);
		patientList.add(patient9);
		patientList.add(patient10);

		patientService.deleteAll();
		patientService.insertAll(patientList);

	}


}
