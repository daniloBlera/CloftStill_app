package com.cloftstill.cloftstill.model;

/**
 * Armazena o endereço do host e a porta para conexão.
 */
public enum ServerInfo {
//    ADDRESS,
    ENDPOINT,
    PORT_NUMBER;

    public String toString() {
        switch(this) {
//            case ADDRESS:       return "127.0.0.1";
            case ENDPOINT:      return "10.0.3.2";
            case PORT_NUMBER:   return "5000";
            default:            return "UNSPECIFIED";
        }
    }
}
