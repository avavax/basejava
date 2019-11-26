package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private ArrayList<Organization> list;

    public OrganizationSection(ArrayList<Organization> list) {
        Objects.requireNonNull(list, "Organizations must not be null");
        this.list = list;
    }

    public void setList(ArrayList<Organization> list) {
        this.list = list;
    }

    public ArrayList<Organization> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Organization item : list) {
            result.append(item.toString() + "\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
