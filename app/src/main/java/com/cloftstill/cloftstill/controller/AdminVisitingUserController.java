package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.AuthorizationPeriod;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.ServerResponse;
import com.cloftstill.cloftstill.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dcblera on 23/06/16.
 */
public class AdminVisitingUserController {
    private AdminVisitingUserController(){}

    public static String register(User visitingUser, AuthorizationPeriod period) {
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.ADMIN_VISITING_USER_REGISTER.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.NAME.toString(), visitingUser.getName());
            jsonBody.put(User.Fields.PASSWORD.toString(), visitingUser.getPinPassword());
            jsonBody.put(User.Fields.CPF.toString(), visitingUser.getCpf());
            jsonBody.put(User.Fields.PHONE_NUMBER.toString(), visitingUser.getCellNumber());
            jsonBody.put("ValidadeInicio", period.getStartDate());
            jsonBody.put("ValidadeFim", period.getEndDate());

            String response = handler.executePOST(uri, jsonBody.toString());
            String message = new JSONObject(response).getString("message");

            return message;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
