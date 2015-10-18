package com.sharomank.notes.model.base;

public class NamedModel extends AbstractModel {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
