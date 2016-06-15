package com.cloftstill.cloftstill.model;

/**
 * Agrupa todas as possíveis respostas do servidor.
 */
public enum ServerResponse {
    JSON_MISSING_KEYS,
    JSON_MISSING_ARGS,
    ALREADY_ACCEPTED,
    SOLICITATION_ACCEPTED,
    INTERNAL_ERROR,
    INCORRECT_PASSWORD,
    ADMIN_VERIFICATION_TRUE,
    ADMIN_VERIFICATION_FALSE,
    REQUEST_SENT,
    UNREGISTERED_USER;

    @Override
    public String toString() {
        switch (this) {
            case JSON_MISSING_ARGS:
                return "JSON_INVALID_ARGS";
            case JSON_MISSING_KEYS:
                return "JSON_MISSING_KEYS";
            case ALREADY_ACCEPTED:
                return "SOLICITATION_ALREADY_ACCEPTED";
            case SOLICITATION_ACCEPTED:
                return "SOLICITATION_ACCEPTED";
            case INTERNAL_ERROR:
                return "INTERNAL_SERVER_ERROR";
            case REQUEST_SENT:
                return "REQUEST_SENT";
            case ADMIN_VERIFICATION_TRUE:
                return "ADMIN_VALIDITY_CONFIRMED";
            case ADMIN_VERIFICATION_FALSE:
                return "USER_IS_NOT_ADMIN";
            case INCORRECT_PASSWORD:
                return "INCORRECT_PASSWORD";
            case UNREGISTERED_USER:
                return "UNREGISTERED_USER";
            default:
                return "UNDEFINED";
        }
    }
}
