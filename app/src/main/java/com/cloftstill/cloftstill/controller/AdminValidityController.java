package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.IsAdminResponse;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dcblera on 11/06/16.
 */
public class AdminValidityController {
    private AdminValidityController(){}

    public static String requestValidityCheck(Authenticable authenticable) throws JSONException {
        String response = null;

        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.CHECK_IF_ADMIN.toString());

        JSONObject jsonBody = new JSONObject();

        jsonBody.put(User.Fields.PASSWORD.toString(), authenticable.getPassword());
        jsonBody.put(User.Fields.MAC_ADDRESS.toString(), authenticable.getMacAddress());
        jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), authenticable.getSerialNumber());

        response = handler.executePOST(uri, jsonBody.toString());
        JSONObject jsonResponse = new JSONObject(response);

        return jsonResponse.toString();
    }

    public static IsAdminResponse requestAdminCheck(Authenticable authenticable) {
        IsAdminResponse isAdminResponse = null;
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.CHECK_IF_ADMIN.toString());

        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put(User.Fields.PASSWORD.toString(), authenticable.getPassword());
            jsonBody.put(User.Fields.MAC_ADDRESS.toString(), authenticable.getMacAddress());
            jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), authenticable.getSerialNumber());

            String response = handler.executePOST(uri, jsonBody.toString());
            JSONObject jsonResponse = new JSONObject(response);

            String body = jsonResponse.getString("message");

            if (body.equals(IsAdminResponse.INCORRECT_PASSWORD.toString()))
                isAdminResponse = IsAdminResponse.INCORRECT_PASSWORD;
            else if (body.equals(IsAdminResponse.FALSE.toString()))
                isAdminResponse = IsAdminResponse.FALSE;
            else
                isAdminResponse = IsAdminResponse.TRUE;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return isAdminResponse;
    }
}
