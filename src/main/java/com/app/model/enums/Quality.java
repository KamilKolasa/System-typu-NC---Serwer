package com.app.model.enums;

public enum Quality {

    FULL_HD("FULL HD"),
    ULTRA_HD("ULTRA HD"),
    _4K("4K");

    private String fullName;

    Quality(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
