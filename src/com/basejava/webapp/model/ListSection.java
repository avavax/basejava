package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<String> list;

    public ListSection() {
    }

    public ListSection(List<String> list) {
        Objects.requireNonNull(list, "List must not be null");
        this.list = list;
    }

    public ListSection(String... items) {
        this(Arrays.asList(items));
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String item : list) {
            result.append(item + "\n");
        }
        return result.toString();
    }

    public String toHTML() {
        StringBuilder result = new StringBuilder();
        result.append("<ul>");
        for (String item : list) {
            result.append("<li>" + item + "</li>");
        }
        result.append("</ul>");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
