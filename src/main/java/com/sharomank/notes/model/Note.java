package com.sharomank.notes.model;

import com.sharomank.notes.model.base.NamedModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Note extends NamedModel {
    private String description;
    private boolean completed;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}