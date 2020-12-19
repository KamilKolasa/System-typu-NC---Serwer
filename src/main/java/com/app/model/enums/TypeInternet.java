package com.app.model.enums;

public enum TypeInternet {

    cableFiber("kabel światłowodowy"),
    cableStandard("kabel zwykły"),
    linkRadio("łącze radiowe");

    private String fullName;

    TypeInternet(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
