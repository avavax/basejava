package com.basejava.webapp.model;

import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

public class SimpleSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private String description;

    public SimpleSection() {
    }

    public SimpleSection(String description) {
        Objects.requireNonNull(description, "Description must not be null");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleSection that = (SimpleSection) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
