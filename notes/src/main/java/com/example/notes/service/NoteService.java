package com.example.notes.service;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository repo) {
        noteRepository = repo;
    }

    public Optional<Note> findById(long id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByPatId(long id) {
        return noteRepository.findAllByPatId(id);
    }

    public Note insert(Note note) {
        return noteRepository.save(note);
    }

    public Note update(long id, Note note) {
        Optional<Note> optionalNote = this.findById(id);
        if (optionalNote.isPresent()) {
            Note noteToUpdate = optionalNote.get();
            noteToUpdate.setPatId(note.getPatId());
            noteToUpdate.setNotes(note.getNotes());
            return noteRepository.save(noteToUpdate);
        } else {
            return null;
        }
    }

    public void delete(long id) {
        noteRepository.deleteById(id);
    }
}
