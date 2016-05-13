package com.cloftstill.cloftstill.model;


public enum UserType {
    ADMIN,
    COMMON,
    VISIT;

    public String toString() {
        switch(this) {
            case ADMIN:
                return "admin";
            case COMMON:
                return "common";
            case VISIT:
                return "visit";
            default:
                return "visit";
        }
    }
}
