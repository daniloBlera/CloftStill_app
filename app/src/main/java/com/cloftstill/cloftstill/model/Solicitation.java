package com.cloftstill.cloftstill.model;

import java.sql.Timestamp;

/**
 * JAVABean representante da entidade 'Solicitacao' no banco.
 */
public class Solicitation {
    private String name;
    private String serialNumber;
    private String cellNumber;
    private String pinPassword;
    private UserType type;
    private String status;
    private Timestamp creationTime;
    private String cpf;
    private String macAdress;

    public enum Fields {
        NAME,
        PASSWORD,
        MAC_ADDRESS,
        SIM_SERIAL_NUMBER,
        TYPE,
        PHONE_NUMBER,
        STATUS,
        CREATION_TIME,
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
                case STATUS:
                    return "Status";
                case CREATION_TIME:
                    return "DataCriacao";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp timestamp) {
        this.creationTime = timestamp;
    }
}
