package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.SignupResponse;
import com.cloftstill.cloftstill.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by laser info on 02/06/2016.
 */
public class SignUpController {

    /**
     * Envia uma requisição ao servidor para o cadastro de solicitação.
     *
     * @return Resposta do servidor para a tentativa de cadastro de solicitação.
     * // TODO melhorar a implementação e documentação das respostas do servidor.
     */
    public static String requestSignUp(User user) {
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.REQUEST_SIGNUP.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.NAME.toString(), user.getName());
            jsonBody.put(User.Fields.PASSWORD.toString(), user.getPinPassword());
            jsonBody.put(User.Fields.TYPE.toString(), user.getType().toString());
            jsonBody.put(User.Fields.MAC_ADDRESS.toString(), user.getMacAdress());
            jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), user.getSerialNumber());
            jsonBody.put(User.Fields.PHONE_NUMBER.toString(), user.getCellNumber());
            jsonBody.put(User.Fields.CPF.toString(), user.getCpf());

            String response = handler.executePOST(uri, jsonBody.toString());
            String message = new JSONObject(response).getString("message");

            return message;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
