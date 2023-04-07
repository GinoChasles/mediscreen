package com.example.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Note.
 */
@Document(collection = "Notes")
public class Note {

    @Id
    private String key;


    private long patId;


    private String note;


    /**
     * Instantiates a new Note.
     */
    public Note() {
    }

    /**
     * Instantiates a new Note.
     *
     *
     * @param patId the pat id
     * @param note the notes
     */
    public Note( long patId, String note) {
        this.patId = patId;
        this.note = note;
    }


    public Note(String key, long patId, String note) {
        this.key = key;
        this.patId = patId;
        this.note = note;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
    public String getNote() {
        return note;
    }

    /**
     * Sets notes.
     *
     * @param note the notes
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "key='" + key + '\'' +
                ", patId=" + patId +
                ", notes='" + note + '\'' +
                '}';
    }
}
