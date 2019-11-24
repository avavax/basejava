package com.basejava.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Objects;

public class Organization {
    private Link link;
    private ArrayList<Position> positions;

    public Organization (String name,
                         String url,
                         ArrayList<Position> positions) {
        Objects.requireNonNull(positions, "Positions must not be null");
        this.link = new Link(name, url);
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
