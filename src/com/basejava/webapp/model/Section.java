package com.basejava.webapp.model;

import java.util.ArrayList;

public abstract class Section {
    private String description;

    protected Section(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract ArrayList<ListItem> getList();
}
