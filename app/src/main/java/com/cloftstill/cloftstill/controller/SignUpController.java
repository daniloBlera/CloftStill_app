package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by laser info on 02/06/2016.
 */
public class SignUpController {

    public boolean checkSignUp(User user){
        boolean isAllOk = true;
        // Verificar o cadastro com o servidor
        return isAllOk;
    }

    /**
     * Envia uma requisição de abertura do portão assim como as credenciais do usuário.
     *
     * @return Resposta do servidor quanto a autorização para abertura do portão.
     * // TODO melhorar a implementação e documentação das respostas do servidor.
     */
    public static String requestSignUp(User user) throws JSONException {
        String response = null;

        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.REQUEST_SIGNUP.toString());

        JSONObject jsonBody = new JSONObject();

        jsonBody.put("Nome", user.getName());
        jsonBody.put("Senha", user.getPinPassword());
        jsonBody.put("Tipo", user.getType().toString());
        jsonBody.put("EnderecoMAC", user.getMacAdress());
        jsonBody.put("CodigoSIM", user.getSerialNumber());
        jsonBody.put("Celular", user.getCellNumber());
        jsonBody.put("CPF", user.getCpf());

        response = handler.executePOST(uri, jsonBody.toString());
        return response;
    }
}
