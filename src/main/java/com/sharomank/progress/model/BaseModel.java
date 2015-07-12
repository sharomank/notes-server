package com.sharomank.progress.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public abstract class BaseModel {
    @Id
    private String id;
    private String name;
    private LocalDateTime created;
    private LocalDateTime deleted;

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
}
