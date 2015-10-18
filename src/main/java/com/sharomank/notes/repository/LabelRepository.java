package com.sharomank.notes.repository;

import com.sharomank.notes.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface LabelRepository extends MongoRepository<Label, String> {
}
