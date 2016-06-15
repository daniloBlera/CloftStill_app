package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 15/06/16.
 */
public enum IsAdminResponse {
    INCORRECT_PASSWORD,
    TRUE,
    FALSE;

    @Override
    public String toString() {
        switch (this) {
            case INCORRECT_PASSWORD:
                return "INCORRECT_PASSWORD";
            case TRUE:
                return "ADMIN_VALIDITY_CONFIRMED";
            case FALSE:
                return "USER_IS_NOT_ADMIN";
            default:
                return "UNDEFINED";
        }
    }
}
