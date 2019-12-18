package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Телефон") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-phone\" aria-hidden=\"true\"></i>" + getTitle() + ": " + value;
        }
    },
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-skype\" aria-hidden=\"true\"></i>" + getTitle() + ": " + toLink("skype:" + value, value);
        }
    },
    EMAIL("Почта") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-envelope-o\" aria-hidden=\"true\"></i>" + getTitle() + ": " + toLink("mailto:" + value, value);
        }
    },
    LINKEDIN("Профиль LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-linkedin\" aria-hidden=\"true\"></i>" + toLink(value);
        }
    },
    GITHUB("Профиль GiHUB") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-github\" aria-hidden=\"true\"></i>" + toLink(value);
        }
    },
    STACKOVERFLOW("Профиль Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-stack-overflow\" aria-hidden=\"true\"></i>" + toLink(value);
        }
    },
    SITE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return "<i class=\"fa fa-home\" aria-hidden=\"true\"></i>" + toLink(value);
        }
    };

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
