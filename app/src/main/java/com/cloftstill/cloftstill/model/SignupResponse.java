package com.cloftstill.cloftstill.model;

/**
 * Possíveis mensagens de resposta do servidor para o cadastro de solicitação.
 */
public enum SignupResponse {
    JSON_MISSING_KEYS,
    JSON_MISSING_ARGS,
    ALREADY_ACCEPTED,
    SOLICITATION_ACCEPTED,
    INTERNAL_ERROR;

    @Override
    public String toString() {
        switch(this) {
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
            default:
                return "UNDEFINED";
        }
    }
}
