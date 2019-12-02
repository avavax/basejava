package com.basejava.webapp.model;

import com.basejava.webapp.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final YearMonth NOW = YearMonth.of(3000, 1);

    private String title;
    private String description;
    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth start;
    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth finish;

    public Position() {
    }

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
        return "{\'" +
                title + '\'' +
                ", " + ((description == null) ? ' ' : '\'' + description + "\', ") +
                start +
                " - " + ((finish == NOW) ? "сейчас" : finish) +
                '}';
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
        return finish != null ? finish.equals(position.finish) : position.finish == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + start.hashCode();
        result = 31 * result + (finish != null ? finish.hashCode() : 0);
        return result;
    }
}
