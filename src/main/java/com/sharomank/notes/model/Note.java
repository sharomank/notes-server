package com.sharomank.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Note implements BaseModel {
    @Id
    private String id;

    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime deleted;
    private LocalDateTime completed;

    public Note() {
    }

    public Note(String name, String description, LocalDateTime created, LocalDateTime deleted, LocalDateTime completed) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.deleted = deleted;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }
}