package com.sharomank.notes.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public abstract class AbstractModel implements BaseModel {
    @Id
    private String id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean deleted;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public LocalDateTime getUpdated() {
        return updated;
    }

    @Override
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
