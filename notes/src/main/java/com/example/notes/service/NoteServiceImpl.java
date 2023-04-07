package com.example.notes.service;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository repo) {
        noteRepository = repo;
    }

    public Optional<Note> findById(String id) {
        return noteRepository.findByKey(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByPatId(long id) {
        return noteRepository.findAllByPatId(id);
    }

    public Note insert(Note note) {
        Note noteToInsert = new Note();
        noteToInsert.setPatId(note.getPatId());
        noteToInsert.setNote(note.getNote());
        String key = RandomStringUtils.random(16, true, true);
        noteToInsert.setKey(key);
        noteRepository.insert(noteToInsert);

        return noteToInsert;
    }

    public Note update(String id, Note note) {
        Optional<Note> optionalNote = this.findById(id);
        if (optionalNote.isPresent()) {
            Note noteToUpdate = optionalNote.get();
            noteToUpdate.setPatId(note.getPatId());
            noteToUpdate.setNote(note.getNote());
            noteToUpdate.setKey(note.getKey());
            return noteRepository.save(noteToUpdate);
        } else {
            return null;
        }
    }

    public void delete(String id) {
        noteRepository.deleteByKey(id);
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public void addAll(List<Note> noteList) {
        noteList.forEach((this::insert));
    }
}
