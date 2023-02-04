package com.example.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Note.
 */
@Document(collection = "Notes")
public class Note {
    @Id
    private long id;

    private long patId;

    private String notes;


    /**
     * Instantiates a new Note.
     */
    public Note() {
    }

    /**
     * Instantiates a new Note.
     *
     * @param id    the id
     * @param patId the pat id
     * @param notes the notes
     */
    public Note(long id, long patId, String notes) {
        this.id = id;
        this.patId = patId;
        this.notes = notes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets pat id.
     *
     * @return the pat id
     */
    public long getPatId() {
        return patId;
    }

    /**
     * Sets pat id.
     *
     * @param patId the pat id
     */
    public void setPatId(long patId) {
        this.patId = patId;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
