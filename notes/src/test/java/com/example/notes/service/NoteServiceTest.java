package com.example.notes.service;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepository;

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


    @Test
    public void findByIdTest() {
        when(noteRepository.findByKey(anyString())).thenReturn(Optional.of(note1));
        Optional<Note> optionalNote = noteService.findById(note1.getKey());
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            assertThat(note.getNote()).isEqualTo(note1.getNote());
        }
    }

    @Test
    public void findAllTest() {
        when(noteRepository.findAll()).thenReturn(noteList);
        List<Note> result = noteService.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getKey()).isEqualTo(note1.getKey());
        assertThat(result.get(1).getKey()).isEqualTo(note2.getKey());
    }

    @Test
    public void findByPatIdTest() {
        when(noteRepository.findAllByPatId(anyLong())).thenReturn(noteList);
        List<Note> result = noteService.findByPatId(1L);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getKey()).isEqualTo(note1.getKey());
        assertThat(result.get(1).getKey()).isEqualTo(note2.getKey());
    }

    @Test
    public void insertTest() {
        when(noteRepository.insert(any(Note.class))).thenReturn(note1);
        Note result = noteService.insert(note1);
        assertThat(result.getNote()).isEqualTo(note1.getNote());
    }

    @Test
    public void updateTest() {
        when(noteRepository.findByKey(anyString())).thenReturn(Optional.of(note1));
        Note noteToUpdate = note1;
        noteToUpdate.setNote("Updating note 1");
        when(noteRepository.save(noteToUpdate)).thenReturn(noteToUpdate);

        Note result = noteService.update(note1.getKey(), noteToUpdate);
        if (result != null) {
            assertThat(result.getNote()).isEqualTo(noteToUpdate.getNote());
        }
    }

    @Test
    public void updateTest_NoteNotPresent() {
        when(noteRepository.findByKey(anyString())).thenReturn(Optional.empty());
        Note noteToUpdate = note1;
        noteToUpdate.setNote("Updating note 1");
        Note result = noteService.update(note1.getKey(), noteToUpdate);
        assertThat(result).isNull();

    }


}
