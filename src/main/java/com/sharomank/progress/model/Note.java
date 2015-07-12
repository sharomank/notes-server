package com.sharomank.progress.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document
public class Note extends BaseModel {
    private String description;
    private LocalDateTime completed;
    private String colorHex;
    private Set<String> tags;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}