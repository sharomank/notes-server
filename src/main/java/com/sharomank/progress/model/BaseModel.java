package com.sharomank.progress.model;

import java.time.LocalDateTime;

public interface BaseModel {
    String getId();

    String getName();

    void setName(String name);

    LocalDateTime getCreated();

    void setCreated(LocalDateTime created);
}
