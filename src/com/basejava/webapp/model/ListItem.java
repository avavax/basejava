package com.basejava.webapp.model;

public class ListItem {
    private String title;
    private String subtitle;
    private String link;
    private String yearMonthStart;
    private String yearMonthFinish;
    private String description;

    public ListItem(String title,
                    String subtitle,
                    String link,
                    String yearMonthStart,
                    String yearMonthFinish,
                    String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
        this.yearMonthStart = yearMonthStart;
        this.yearMonthFinish = yearMonthFinish;
        this.description = description;
    }

    public ListItem(String description) {
        this.title = null;
        this.subtitle = null;
        this.link = null;
        this.yearMonthStart = null;
        this.yearMonthFinish = null;
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

    public String getYearMonthStart() {
        return yearMonthStart;
    }

    public void setYearMonthStart(String yearMonthStart) {
        this.yearMonthStart = yearMonthStart;
    }

    public String getYearMonthFinish() {
        return yearMonthFinish;
    }

    public void setYearMonthFinish(String yearMonthFinish) {
        this.yearMonthFinish = yearMonthFinish;
    }
}
