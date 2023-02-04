package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Note controller.
 */
@RestController
@RequestMapping("/patHistory")
public class NoteController {
    private final NoteService noteService;
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Instantiates a new Note controller.
     *
     * @param service the service
     */
    public NoteController(NoteService service) {
        noteService = service;
    }


    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable(value = "id") long id) {
        logger.info("Searching note with his id: " + id);
        Optional<Note> result = noteService.findById(id);
        if (result.isEmpty()) {
            logger.error("note not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("note found : " + result);
            return ResponseEntity.ok().body(result.get());
        }
    }

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/")
    public ResponseEntity<List<Note>> findAll() {
        logger.info("Searching all notes");
        List<Note> noteList = noteService.findAll();
        if (noteList.isEmpty()) {
            logger.error("notes not found");
            return ResponseEntity.noContent().build();
        } else {
            logger.error("notes found");
            return ResponseEntity.ok(noteList);
        }
    }


    /**
     * Find all by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}/all")
    public ResponseEntity<List<Note>> findAllById(@PathVariable(value = "id") long id) {
        logger.info("Searching all notes for patId" + id);
        List<Note> noteList = noteService.findByPatId(id);
        if (noteList.isEmpty()) {
            logger.error("notes not found");
            return ResponseEntity.noContent().build();
        } else {
            logger.error("notes found");
            return ResponseEntity.ok(noteList);
        }
    }

    /**
     * Add patient response entity.
     *
     * @param note the note
     * @return the response entity
     */
    @PostMapping(value = "/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        logger.info("Creating note folder in system.");

        return ResponseEntity.ok(noteService.insert(note));
    }


    /**
     * Update response entity.
     *
     * @param id   the id
     * @param note the note
     * @return the response entity
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Note> update(@PathVariable(value = "id") long id, @RequestBody Note note) {
        logger.info("Starting updating note...");
        Note note1 = noteService.update(id, note);
        if (note1 == null) {
            logger.error("note with id :" + id + " is not found.");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("note updating ok !");
            return ResponseEntity.ok().body(note1);
        }
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value = "/id")
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        logger.info("Starting deleting note...");
        Optional<Note> result = noteService.findById(id);
        if (result.isEmpty()) {
            logger.error("Note not found");
            return ResponseEntity.notFound().build();
        } else {
            noteService.delete(id);
            logger.info("Note is delete");
//            return ResponseEntity.noContent().build();
            return ResponseEntity.ok("Deleted");
        }

    }
}
