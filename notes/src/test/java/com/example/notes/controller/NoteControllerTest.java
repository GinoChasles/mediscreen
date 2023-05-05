package com.example.notes.controller;

import com.example.notes.NotesApplication;
import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(classes = NotesApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    NoteService noteService;
    @InjectMocks
    NoteController noteController;
    @Autowired
    ObjectMapper mapper;

    private Note note1;
    private Note note2;

    private List<Note> noteList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        note1 = new Note("randomKey", 1L, "Note 1 test");
        note2 = new Note("randomKey2", 1L, "Note 2 test");

        noteList.add(note1);
        noteList.add(note2);
    }


    /*@Test
    public void findByIdTest_NotFound() throws Exception {
        mockMvc.perform(get("/patHistory/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByIdTest() throws Exception {

        Mockito.when(noteService.findById(anyString())).thenReturn(Optional.ofNullable(note1));
        mockMvc.perform(get("/patHistory/{id}", "Rq7DFUHhyIrV1sCh"))
                .andExpect(status().isOk());
    }*/

    @Test
    @Order(1)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/patHistory/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllByIdTest_NotFound() throws Exception {
        mockMvc.perform(get("/patHistory/10000/all"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    public void findAllByIdTest() throws Exception {
        mockMvc.perform(get("/patHistory/1/all"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void addNoteTest() throws Exception {

        String json = mapper.writeValueAsString(note1);
        mockMvc.perform(post("/patHistory/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());
    }

  /* @Test
   @Order(4)

    public void updateNoteTest() throws Exception {
        Note updateNote = note1;
        updateNote.setNote("Updating note 1");
        String json = mapper.writeValueAsString(updateNote);
        when(noteService.update(anyString(), any(Note.class))).thenReturn(updateNote);

        mockMvc.perform(put("/patHistory/{id}", note1.getKey())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk());
    }*/

    @Test
    public void updateNoteTest_NotFound() throws Exception {
        Note updateNote = note1;
        updateNote.setNote("Updating note 1");
        String json = mapper.writeValueAsString(updateNote);

        mockMvc.perform(put("/patHistory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTest_NotFound() throws Exception {
        mockMvc.perform(delete("/patHistory/{id}", 1))
                .andExpect(status().isNotFound());
    }

   /* @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/patHistory/{id}", 1))
                .andExpect(status().isOk());
    }*/

    @Test
    public void deleteAllTest() throws Exception {
        mockMvc.perform(delete("/patHistory/"))
                .andExpect(status().isOk());
    }
}
