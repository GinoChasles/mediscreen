package com.example.notes;

import com.example.notes.model.Note;
import com.example.notes.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class NotesApplication implements CommandLineRunner {
	@Autowired
	private NoteServiceImpl noteServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		noteServiceImpl.deleteAll();
		Note pat1 = new Note(1, "Patient states that they are 'feeling terrific' Weight at or below recommended level");
		Note pat2 = new Note(2, "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late\"");
		Note pat3 = new Note(2, "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic");
		Note pat4 = new Note(3, "Patient states that they are short term Smoker ");
		Note pat5 = new Note(3, "Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high");
		Note pat6 = new Note(4, "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication");
		Note pat7 = new Note(4, "Patient states that they are experiencing back pain when seated for a long time");
		Note pat8 = new Note(4, "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level");
		Note pat9 = new Note(4, "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
		List<Note> dataNoteList = new ArrayList<>();
		dataNoteList.add(pat1);
		dataNoteList.add(pat2);
		dataNoteList.add(pat3);
		dataNoteList.add(pat4);
		dataNoteList.add(pat5);
		dataNoteList.add(pat6);
		dataNoteList.add(pat7);
		dataNoteList.add(pat8);
		dataNoteList.add(pat9);

		noteServiceImpl.addAll(dataNoteList);
	}

}
