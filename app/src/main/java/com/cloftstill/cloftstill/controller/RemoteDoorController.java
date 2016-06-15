package com.cloftstill.cloftstill.controller;

import android.util.Log;

import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.OpenDoorResponse;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.ServerResponse;
import com.cloftstill.cloftstill.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * CLasse responsavel por enviar requisições de abertura do portão.
 */
public class RemoteDoorController {

    /**
     * Envia uma requisição de abertura do portão assim como as credenciais do usuário.
     *
     * @param authenticable Dados autenticaveis para abertura do portão.
     * @param gpsPosition Posição do dispositivo captada no momento da requisição.
     * @return Resposta do servidor quanto a autorização para abertura do portão.
     * // TODO melhorar a implementação e documentação das respostas do servidor.
     */
    public static ServerResponse requestOpen(Authenticable authenticable, String gpsPosition) {

        ServerResponse doorResponse = null;
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.OPEN_DOOR_PATH.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.MAC_ADDRESS.toString(), authenticable.getMacAddress());
            jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), authenticable.getSerialNumber());
            jsonBody.put(User.Fields.PASSWORD.toString(), authenticable.getPassword());
            jsonBody.put("PosicaoGPS", gpsPosition);

            String response = handler.executePOST(uri, jsonBody.toString());

            String message = new JSONObject(response).getString("message");
            Log.d("REM_DOOR_CT", message);
            doorResponse = getEnumFromString(message);
            return doorResponse;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static ServerResponse getEnumFromString(String message) {
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
        }

        return doorResponse;
    }
}
