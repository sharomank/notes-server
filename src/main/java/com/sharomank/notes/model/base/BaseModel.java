package com.sharomank.notes.model.base;

import java.time.LocalDateTime;

public interface BaseModel {
    String getId();

    String getName();

    void setName(String name);

    LocalDateTime getCreated();

    void setCreated(LocalDateTime created);

    LocalDateTime getUpdated();

    void setUpdated(LocalDateTime updated);

    boolean isDeleted();

    void setDeleted(boolean deleted);
}
