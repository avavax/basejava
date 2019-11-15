package com.basejava.webapp.model;

import java.util.ArrayList;

public class SimpleSection extends Section {

    public SimpleSection(String description) {
        super(description);
    }

    @Override
    public ArrayList<ListItem> getList() {
        return null;
    }

}
