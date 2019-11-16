package com.basejava.webapp.model;

import java.util.ArrayList;

public class SimpleSection extends AbstractSection<String> {

    public SimpleSection(String description) {
        super(description);
    }

    @Override
    public ArrayList<String> getList() {
        return null;
    }

}
