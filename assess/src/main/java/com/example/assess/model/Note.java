package com.example.assess.model;

/**
 * The type Note.
 */
public class Note {
    private String key;
    private Long patId;
    private String note;

    /**
     * Instantiates a new Note.
     */
    public Note() {}

    public Note(String key, Long patId, String note) {
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

    public Long getPatId() {
        return patId;
    }

    public void setPatId(Long patId) {
        this.patId = patId;
    }

    /**
     * Instantiates a new Note.
     *
     * @param note the note
     */
    public Note(String note) {
        this.note = note;
    }

    /**
     * Gets note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(String note) {
        this.note = note;
    }
}
