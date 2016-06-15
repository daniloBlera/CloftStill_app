package com.cloftstill.cloftstill.model;

public class User {
    private String name;
    private String serialNumber;
    private String cellNumber;
    private String pinPassword;
    private UserType type;
    private String adminPassword;
    private String cpf;
    private String macAdress;

    public enum Fields {
        NAME,
        PASSWORD,
        MAC_ADDRESS,
        SIM_SERIAL_NUMBER,
        TYPE,
        PHONE_NUMBER,
        CPF;

        @Override
        public String toString() {
            switch (this) {
            case NAME:
                return "Nome";
            case PASSWORD:
                return "Senha";
            case MAC_ADDRESS:
                return "EnderecoMAC";
            case SIM_SERIAL_NUMBER:
                return "CodigoSIM";
            case TYPE:
                return "Tipo";
            case PHONE_NUMBER:
                return "Celular";
            case CPF:
                return "CPF";
            default:
                return "UNDEFINED";
            }
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinPassword() {
        return pinPassword;
    }

    public void setPinPassword(String pinPassword) {
        this.pinPassword = pinPassword;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String simNumber) {
        this.serialNumber = simNumber;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

}
