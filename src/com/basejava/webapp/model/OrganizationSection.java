package com.basejava.webapp.model;

import java.util.ArrayList;

public class OrganizationSection extends AbstractSection {
    private ArrayList<Organization> list = new ArrayList<>();

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
        if (!super.equals(o)) return false;

        OrganizationSection that = (OrganizationSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + list.hashCode();
        return result;
    }
}
