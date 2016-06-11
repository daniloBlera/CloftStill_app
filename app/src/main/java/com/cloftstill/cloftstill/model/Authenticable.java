package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 11/06/16.
 */
public class Authenticable {

    private String password;
    private String macAddress;
    private String serialNumber;

    public Authenticable(String password, String macAddress, String simCardSerialNumber) {
        this.password = password;
        this.macAddress = macAddress;
        this.serialNumber = simCardSerialNumber;
    }

    public String getPassword(){
        return this.password;
    }

    public String getMacAddress(){
        return this.macAddress;
    }

    public String getSerialNumber(){
        return this.serialNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }

    public void setSerialNumber(String simCardSerialNumber){
        this.serialNumber = simCardSerialNumber;
    }
}
