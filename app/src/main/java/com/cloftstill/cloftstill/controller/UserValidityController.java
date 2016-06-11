package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dcblera on 11/06/16.
 */
public class UserValidityController {
    private UserValidityController(){}

    public static String requestValidityCheck(Authenticable authenticable) throws JSONException {
        String response = null;

        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.VALIDATE_USER.toString());

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("Senha", authenticable.getPassword());
        jsonBody.put("EnderecoMAC", authenticable.getMacAddress());
        jsonBody.put("CodigoSIM", authenticable.getSerialNumber());

        response = handler.executePOST(uri, jsonBody.toString());
        return response;
    }
}
