package com.cloftstill.cloftstill.controller;

import android.net.ConnectivityManager;
import android.util.Log;

import com.cloftstill.cloftstill.Model.ServerInfo;

import org.apache.http.protocol.HTTP;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe responsavel por comunicar ao webserver a abertura do portao.
 */
public class DoorController {
    public DoorController() {
        super();
    }

    /**
     * Requisita a abertura do portao pela internet.
     */
    public void requestOpen() {
        try {
            URL url = new URL("http://" + ServerInfo.ADDRESS + ":" + ServerInfo.PORT_NUMBER);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            Log.d("ERRO", "MalformedURLException");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtem a mensagem para requisição HTTP
     *
     * @return Mensagem de requisição http
     */
    private String getRequestMessage() {
        return "._.";
    }
}
