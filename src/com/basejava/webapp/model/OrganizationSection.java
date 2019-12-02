package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<Organization> list;

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> list) {
        Objects.requireNonNull(list, "Organizations must not be null");
        this.list = list;
    }

    public OrganizationSection(Organization... items) {
        this(Arrays.asList(items));
    }

    public void setList(ArrayList<Organization> list) {
        this.list = list;
    }

    public List<Organization> getList() {
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
