package com.sharomank.notes.repository;

import com.sharomank.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoteRepository extends MongoRepository<Note, String> {
}
