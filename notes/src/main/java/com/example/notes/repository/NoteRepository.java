package com.example.notes.repository;

import com.example.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {
    //@Query("{ 'patId' : ?0 }")
    List<Note> findAllByPatId(long patId);

    Optional<Note> findByKey(String key);
    void deleteByKey(String key);
}
