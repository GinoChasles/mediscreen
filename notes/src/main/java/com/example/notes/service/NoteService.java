package com.example.notes.service;

import com.example.notes.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * The interface Note service.
 */
public interface NoteService {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Note> findById(String id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Note> findAll();

    /**
     * Find by pat id list.
     *
     * @param id the id
     * @return the list
     */
    List<Note> findByPatId(long id);

    /**
     * Insert note.
     *
     * @param note the note
     * @return the note
     */
    Note insert(Note note);

    /**
     * Update note.
     *
     * @param id   the id
     * @param note the note
     * @return the note
     */
    Note update(String id, Note note);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Add all.
     *
     * @param noteList the note list
     */
    void addAll(List<Note> noteList);

}
