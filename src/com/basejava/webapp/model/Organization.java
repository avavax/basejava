package com.basejava.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link link;
    private List<Position> positions;

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link link, List<Position> positions) {
        Objects.requireNonNull(positions, "Positions must not be null");
        this.link = link;
        this.positions = positions;
    }

    public Organization (String name,
                         String url,
                         YearMonth start,
                         YearMonth finish,
                         String title,
                         String description) {
        this.link = new Link(name, url);
        this.positions = new ArrayList<>();
        Position position = new Position(start, finish, title, description);
        positions.add(position);
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String toHTML() {
        StringBuilder result = new StringBuilder();
        result.append(link.toHTML());
        for (Position item : positions) {
            result.append(item.toHTML());
        }
        result.append("<br>");
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(link.toString() + "\n");
        for (Position item : positions) {
            result.append(item.toString() + "\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!link.equals(that.link)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }
}
