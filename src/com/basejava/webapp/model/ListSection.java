package com.basejava.webapp.model;

import java.util.ArrayList;

public class ListSection extends AbstractSection<String> {

    private ArrayList<String> list = new ArrayList<>();

    public ListSection() {
        super(null);
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public ArrayList<String> getList() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }
}
