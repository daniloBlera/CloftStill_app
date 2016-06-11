package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.ServerConnectionLinks;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * CLasse responsavel por enviar requisições de abertura do portão.
 */
public class RemoteDoorController {

    /**
     * Envia uma requisição de abertura do portão assim como as credenciais do usuário.
     *
     * @param macAddress Endereço MAC do dispositivo do usuário que envia a requisião.
     * @param simcardSerial Código serial do cartão SIM do dispositivo.
     * @param password Senha de acesso do usuário.
     * @param gpsPosition Posição do dispositivo captada no momento da requisição.
     * @return Resposta do servidor quanto a autorização para abertura do portão.
     * // TODO melhorar a implementação e documentação das respostas do servidor.
     */
    public static String requestOpen(
            String macAddress, String simcardSerial, String password, String gpsPosition)
            throws JSONException {

        String response = null;
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.OPEN_DOOR_PATH.toString());

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("EnderecoMAC", macAddress);
        jsonBody.put("CodigoSIM", simcardSerial);
        jsonBody.put("Senha", password);
        jsonBody.put("PosicaoGPS", gpsPosition);

        response = handler.executePOST(uri, jsonBody.toString());
        return response;
    }
}
