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

    public static ServerResponse register(User visitingUser, AuthorizationPeriod period) {
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

            return getServerResponseFromString(message);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String registerALTERNATIVE(User visitingUser, AuthorizationPeriod period) {
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

    private static ServerResponse getServerResponseFromString(String message) {
        ServerResponse doorResponse = null;

        if (message.equals(ServerResponse.INCORRECT_PASSWORD.toString())) {
            doorResponse = ServerResponse.INCORRECT_PASSWORD;
        } else if (message.equals(ServerResponse.INTERNAL_ERROR.toString())) {
            doorResponse = ServerResponse.INTERNAL_ERROR;
        } else if (message.equals(ServerResponse.JSON_MISSING_ARGS.toString())) {
            doorResponse = ServerResponse.JSON_MISSING_ARGS;
        } else if (message.equals(ServerResponse.JSON_MISSING_KEYS.toString())) {
            doorResponse = ServerResponse.JSON_MISSING_KEYS;
        } else if (message.equals(ServerResponse.UNREGISTERED_USER.toString())) {
            doorResponse = ServerResponse.UNREGISTERED_USER;
        } else if (message.equals(ServerResponse.REQUEST_SENT.toString())) {
            doorResponse = ServerResponse.REQUEST_SENT;
        } else if (message.equals(ServerResponse.REQUEST_DENIED.toString())) {
            doorResponse = ServerResponse.REQUEST_DENIED;
        } else if (message.equals(ServerResponse.ALREADY_ACCEPTED.toString())) {
            doorResponse = ServerResponse.ALREADY_ACCEPTED;
        } else if (message.equals(ServerResponse.UNREGISTERED_REQUEST.toString())) {
            doorResponse = ServerResponse.UNREGISTERED_REQUEST;
        } else if (message.equals(ServerResponse.SOLICITATION_APPROVED.toString())) {
            doorResponse = ServerResponse.SOLICITATION_APPROVED;
        } else if (message.equals(ServerResponse.PHONE_NUMBER_ALREADY_REGISTERED.toString())) {
            doorResponse = ServerResponse.PHONE_NUMBER_ALREADY_REGISTERED;
        } else if (message.equals(ServerResponse.CPF_ALREADY_REGISTERED.toString())) {
            doorResponse = ServerResponse.CPF_ALREADY_REGISTERED;
        }

        return doorResponse;
    }
}
