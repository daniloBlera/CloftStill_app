package com.cloftstill.cloftstill.controller;


import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dcblera on 11/06/16.
 */
public class AdminValidityController {
    private AdminValidityController(){}

    public static String requestAdminCheck(Authenticable authenticable) {
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.CHECK_IF_ADMIN.toString());

        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put(Authenticable.Fields.PASSWORD.toString(),
                    authenticable.getPassword());

            jsonBody.put(Authenticable.Fields.MAC_ADDRESS.toString(),
                    authenticable.getMacAddress());

            jsonBody.put(Authenticable.Fields.SIM_SERIAL_NUMBER.toString(),
                    authenticable.getSerialNumber());

            String response = handler.executePOST(uri, jsonBody.toString());
            String serverResponse = new JSONObject(response).getString("message");

            return serverResponse;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
