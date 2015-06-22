package com.sharomank.progress.repository;

import com.sharomank.progress.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoteRepository extends MongoRepository<Note, String> {
}
