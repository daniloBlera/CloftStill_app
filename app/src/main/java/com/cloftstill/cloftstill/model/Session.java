package com.cloftstill.cloftstill.model;

import android.content.Context;
public class Session {
    static User admin;
    static Context context;
    static String macAdress;
    static String serialNumber;

    public static void setMacAdress(String macAdress) {
        Session.macAdress = macAdress;
    }

    public static void setAdmin(User admin) {
        Session.admin = admin;
    }

    public static void setContext(Context context) {
        Session.context = context;
    }

    public static void setSerialNumber(String serialNumber) {
        Session.serialNumber = serialNumber;
    }

    public static String getMacAdress() {
        return macAdress;
    }

    public static Context getContext() {
        return context;
    }

    public static String getSerialNumber() {
        return serialNumber;
    }

    public static User getAdmin() {
        return admin;
    }

}
