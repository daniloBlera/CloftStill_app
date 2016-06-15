package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 15/06/16.
 */
public enum OpenDoorResponse {
    JSON_MISSING_KEYS,
    JSON_MISSING_ARGS,
    REQUEST_SENT,
    INCORRECT_PASSWORD,
    UNREGISTERED_USER,
    INTERNAL_ERROR;

    @Override
    public String toString() {
        switch(this) {
        case JSON_MISSING_ARGS:
            return "JSON_INVALID_ARGS";
        case JSON_MISSING_KEYS:
            return "JSON_MISSING_KEYS";
        case REQUEST_SENT:
            return "REQUEST_SENT";
        case INCORRECT_PASSWORD:
            return "INCORRECT_PASSWORD";
        case UNREGISTERED_USER:
            return "UNREGISTERED_USER";
        case INTERNAL_ERROR:
            return "INTERNAL_SERVER_ERROR";
        default:
            return "UNDEFINED";
        }
    }
}
