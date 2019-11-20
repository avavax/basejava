package com.basejava.webapp.model;

import java.time.YearMonth;

public class Organization {
    private String title;
    private String subtitle;
    private String link;
    private YearMonth start;
    private YearMonth finish;
    private String description;

    public Organization(String title,
                        String subtitle,
                        String link,
                        YearMonth start,
                        YearMonth finish,
                        String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
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
        return title + " (" + link + ") "  + "\n" + start + " - " + finish + "\n" + subtitle + "\n" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (subtitle != null ? !subtitle.equals(that.subtitle) : that.subtitle != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (finish != null ? !finish.equals(that.finish) : that.finish != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (finish != null ? finish.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
