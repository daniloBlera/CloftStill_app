package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 11/05/16.
 */
public enum ServerInfo {
    ADDRESS,
    PORT_NUMBER;

    public String toString() {
        switch(this) {
            case ADDRESS:       return "127.0.0.1";
            case PORT_NUMBER:   return "5000";
            default:            return "UNSPECIFIED";
        }
    }
}
