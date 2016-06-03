package com.cloftstill.cloftstill.model;

/**
 * Created by dcblera on 03/06/16.
 */
public class UL {
    public String EnderecoMAC;
    public String CodigoSIM;
    public String Senha;
    public String PosicaoGPS;

    public UL(String mac, String serial, String senha, String local){
        this.EnderecoMAC = mac;
        this.CodigoSIM = serial;
        this.Senha = senha;
        this.PosicaoGPS = local;
    }

    public String getEnderecoMAC() {
        return EnderecoMAC;
    }

    public void setEnderecoMAC(String enderecoMAC) {
        this.EnderecoMAC = enderecoMAC;
    }

    public String getCodigoSIM() {
        return CodigoSIM;
    }

    public void setCodigoSIM(String codigoSIM) {
        this.CodigoSIM = codigoSIM;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }

    public String getPosicaoGPS() {
        return PosicaoGPS;
    }

    public void setPosicaoGPS(String posicaoGPS) {
        this.PosicaoGPS = posicaoGPS;
    }
}
