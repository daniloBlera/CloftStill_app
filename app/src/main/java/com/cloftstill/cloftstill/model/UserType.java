package com.cloftstill.cloftstill.model;


public enum UserType {
    ADMIN,
    WORKER,
    VISIT;

    public String toString() {
        switch(this) {
            case ADMIN:
                return "ADMIN";
            case WORKER:
                return "FUNCIONARIO";
            case VISIT:
                return "VISITANTE";
            default:
                return "VISITANTE";
        }
    }
}
