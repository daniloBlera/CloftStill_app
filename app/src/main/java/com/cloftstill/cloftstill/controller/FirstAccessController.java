package com.cloftstill.cloftstill.controller;

import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.User;
import com.cloftstill.cloftstill.model.UserType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dcblera on 24/06/16.
 *///TODO Javadoc
public class FirstAccessController {

    //TODO A ser removido
    public static User validateALT(String cpf) {
        HttpRequestHandler handler = new HttpRequestHandler();
        User userResponse = null;

        String uri = String.format(
                ServerConnectionLinks.HTTP_FORMAT.toString(),
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.GET_USER_DETAILS.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.CPF.toString(), cpf);

            String response = handler.executePOST(uri, jsonBody.toString());
            JSONObject jsonResponse = new JSONObject(response);

            if (jsonResponse.has("result")) {
                User usr = new User();
                usr.setName("RESULT");
                return usr;
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return userResponse;
    }

    //TODO Javadoc
    public static String validade(String cpf, String macAddress, String simcardSerial) {
        String userResponse = null;

        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format(
                ServerConnectionLinks.HTTP_FORMAT.toString(),
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.VALIDATE_VISITOR_FIRST_ACCESS.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.CPF.toString(), cpf);
            jsonBody.put(User.Fields.MAC_ADDRESS.toString(), macAddress);
            jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), simcardSerial);

            String response = handler.executePOST(uri, jsonBody.toString());
            userResponse = new JSONObject(response).getString("message");

            return userResponse;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera do servidor uma instância de User contendo os atributos do visitante cadastrado no
     * sistema com o CPF fornecido com estatus de 'primeiro acesso'.
     *
     * @param cpf   -- CPF do visitante em primeiro acesso a ser recuperado.
     * @return      -- Instância de User contendo um visitante com status de 'primeiro acesso' com
     *                  CPF fornecido.
     */
    public static User getMyAccountDetails(String cpf) {
        String myUser = null;
        User user = null;

        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format(
                ServerConnectionLinks.HTTP_FORMAT.toString(),
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.GET_USER_DETAILS);

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.CPF.toString(), cpf);

            String response = handler.executePOST(uri, jsonBody.toString());
            JSONObject jsonUser = new JSONObject(response).getJSONObject("result");

            user = getUserFromJSON(jsonUser);

            return user;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtêm uma instância de User a partir de um JSONObject contendo os atributos de um usuário.
     *
     * @param jsonUser          -- JSON contendo os atributos de um usuário.
     * @return                  -- Instância de User.
     * @throws JSONException    -- Caso a mensagem do servidor esteja incorreta.
     */
    private static User getUserFromJSON(JSONObject jsonUser) throws JSONException {
        User user = new User();

        user.setCpf(jsonUser.getString(User.Fields.CPF.toString()));
        user.setSerialNumber(jsonUser.getString(User.Fields.SIM_SERIAL_NUMBER.toString()));
        user.setMacAdress(jsonUser.getString(User.Fields.MAC_ADDRESS.toString()));
        user.setPinPassword(jsonUser.getString(User.Fields.PASSWORD.toString()));
        user.setName(jsonUser.getString(User.Fields.NAME.toString()));
        user.setCellNumber(jsonUser.getString(User.Fields.PHONE_NUMBER.toString()));
        user.setType(UserType.VISIT);

        return user;
    }
}
