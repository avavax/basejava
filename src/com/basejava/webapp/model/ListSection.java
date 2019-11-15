package com.basejava.webapp.model;

import java.util.ArrayList;

public class ListSection extends Section {
    private ArrayList<ListItem> list = new ArrayList<>();

    public ListSection() {
        super(null);
    }

    public void setList(ArrayList<ListItem> list) {
        this.list = list;
    }

    @Override
    public ArrayList<ListItem> getList() {
        return list;
    }
}
