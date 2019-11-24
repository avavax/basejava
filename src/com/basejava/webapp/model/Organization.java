package com.basejava.webapp.model;

import java.time.YearMonth;
import java.util.Objects;

public class Organization {
    private Link link;
    private String title;
    private YearMonth start;
    private YearMonth finish;
    private String description;

    public Organization(String name,
                        String url,
                        YearMonth start,
                        YearMonth finish,
                        String title,
                        String description) {
        Objects.requireNonNull(start, "startDate must not be null");
        Objects.requireNonNull(finish, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.link = new Link(name, url);
        this.start = start;
        this.finish = finish;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
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
        return link.toString()  + "\n" + start + " - " + finish + "\n" + title + "\n" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!link.equals(that.link)) return false;
        if (!title.equals(that.title)) return false;
        if (!start.equals(that.start)) return false;
        if (!finish.equals(that.finish)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = link.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + finish.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
