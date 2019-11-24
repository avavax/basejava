package com.basejava.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Position {
    private String title;
    private String description;
    private YearMonth start;
    private YearMonth finish;

    public Position(YearMonth start, YearMonth finish, String title, String description) {
        Objects.requireNonNull(title, "Title must not be null");
        Objects.requireNonNull(start, "Start must not be null");
        Objects.requireNonNull(finish, "Finish must not be null");
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
    }

    public Position(YearMonth start, YearMonth finish, String title) {
        this(start, finish, title, null);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public YearMonth getStart() {
        return start;
    }

    public void setStart(YearMonth start) {
        this.start = start;
    }

    public YearMonth getFinish() {
        return finish;
    }

    public void setFinish(YearMonth finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return start + " - " + finish + " " + title + "\n" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (!title.equals(position.title)) return false;
        if (description != null ? !description.equals(position.description) : position.description != null)
            return false;
        if (!start.equals(position.start)) return false;
        return finish.equals(position.finish);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + start.hashCode();
        result = 31 * result + finish.hashCode();
        return result;
    }
}
