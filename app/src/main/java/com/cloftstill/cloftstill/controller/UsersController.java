package com.cloftstill.cloftstill.controller;

import android.util.Log;

import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.ServerConnectionLinks;
import com.cloftstill.cloftstill.model.Solicitation;
import com.cloftstill.cloftstill.model.User;
import com.cloftstill.cloftstill.model.UserType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Controla o acesso do aplicativo aos usuários cadastrados no serviço.
 */
public class UsersController {

    /**
     * Recupera todos os usuários cadastrados no banco, levando-se em consideração que a activity
     * requisita que aplicativo esteja cadastrado em um usuário do tipo Administrador, a lista
     * retornará com pelo menos um usuário (o próprio admin que requisita a lista).
     *
     * @param authenticable
     * @return
     */
    public static List<User> requestAllApprovedUsers(Authenticable authenticable) {
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.GET_ALL_USERS.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(User.Fields.MAC_ADDRESS.toString(), authenticable.getMacAddress());
            jsonBody.put(User.Fields.SIM_SERIAL_NUMBER.toString(), authenticable.getSerialNumber());
            jsonBody.put(User.Fields.PASSWORD.toString(), authenticable.getPassword());

            String response = handler.executePOST(uri, jsonBody.toString());

            JSONObject jsonResponse = new JSONObject(response);
//            String message = jsonResponse.toString();

//            Log.d("USR_CTRL_RSVP", message);
//            Log.d("USR_CTRL_jRes", jsonResponse.toString());

            JSONArray jsonArray = jsonResponse.getJSONArray("results");
//            Log.d("USR_CTRL_jArr", jsonArray.toString());

            List<User> users = getUsersFromJSONArray(jsonArray);

            if (users.isEmpty()) {
//                Log.d("USR_CTRL_RSVP_EMPTY", "YES");
            } else {
//                Log.d("USR_CTRL_RSVP_EMPTY", "NOT");
            }

            return users;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Solicitation> requestAllPendingSolicitation(Authenticable authenticable) {
        HttpRequestHandler handler = new HttpRequestHandler();

        String uri = String.format("http://%s/%s",
                ServerConnectionLinks.LOCALHOST_DOMAIN.toString(),
                ServerConnectionLinks.GET_ALL_PENDING_SOLICITATIONS.toString());

        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put(Authenticable.Fields.MAC_ADDRESS.toString(),
                    authenticable.getMacAddress());

            jsonBody.put(Authenticable.Fields.SIM_SERIAL_NUMBER.toString(),
                    authenticable.getSerialNumber());

            jsonBody.put(Authenticable.Fields.PASSWORD.toString(),
                    authenticable.getPassword());

            String response = handler.executePOST(uri, jsonBody.toString());

            JSONObject jsonResponse = new JSONObject(response);
            String message = jsonResponse.toString();

            Log.d("USR_CTRL_RSVP", message);
            Log.d("USR_CTRL_jRes", jsonResponse.toString());

            JSONArray jsonArray = jsonResponse.getJSONArray("results");
            Log.d("USR_CTRL_jArr", jsonArray.toString());

            List<Solicitation> solicitations = getSolicitationsFromJSONArray(jsonArray);

            if (solicitations.isEmpty()) {
                Log.d("USR_CTRL_RSVP_EMPTY", "YES");
            } else {
                Log.d("USR_CTRL_RSVP_EMPTY", "NOT");
            }

            return solicitations;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Solicitation> getSolicitationsFromJSONArray(JSONArray jsonArray)
            throws JSONException{

        List<Solicitation> solicitations = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Solicitation solicitation = new Solicitation();

            solicitation.setName(jsonObj.getString(Solicitation.Fields.NAME.toString()));
//            Log.d("GET-NAME",User.Fields.NAME.toString());

            solicitation.setCpf(jsonObj.getString(Solicitation.Fields.CPF.toString()));
//            Log.d("GET-CPF",User.Fields.CPF.toString());

            solicitation.setMacAdress(jsonObj.getString(Solicitation.Fields.MAC_ADDRESS.toString()));
//            Log.d("GET-MAC",User.Fields.MAC_ADDRESS.toString());

            solicitation.setSerialNumber(jsonObj.getString(Solicitation.Fields.SIM_SERIAL_NUMBER.toString()));
//            Log.d("GET-SIM",User.Fields.SIM_SERIAL_NUMBER.toString());

            solicitation.setCellNumber(jsonObj.getString(Solicitation.Fields.PHONE_NUMBER.toString()));
//            Log.d("GET-TEL",User.Fields.PHONE_NUMBER.toString());

            // O servidor não inclui a senha do usuário no JSON :(
            solicitation.setPinPassword("YOU'RE NOT SUPPOSED TO GET THIS!!!");

            String typeString = jsonObj.getString(Solicitation.Fields.TYPE.toString());
            UserType type = getUserTypeFromString(typeString);
            solicitation.setType(type);

            solicitations.add(solicitation);
        }

//        if (usersList.isEmpty()) {
//            Log.d("FINALLY", "EMPTY");
//        } else {
//            Log.d("FINALLY", "NOT EMPY");
//        }

        return solicitations;
    }

    public static List<User> requestAllApprovedUsersMOCKUP(Authenticable authenticable) {

        List<User> users = null;
        try {
            JSONObject jsonObject = new JSONObject("{\"results\": [ {\"CPF\": \"cpf1\", \"Celular\": \"cel1\", \"CodigoSIM\": \"sim1\", \"EnderecoMAC\": \"mac1\", \"Nome\": \"nome1\", \"Tipo\": \"ADMIN\" }, {\"CPF\": \"cpf2\", \"Celular\": \"cel2\", \"CodigoSIM\": \"sim2\", \"EnderecoMAC\": \"mac2\", \"Nome\": \"nome2\", \"Tipo\": \"FUNCIONARIO\"},{\"CPF\": \"cpf3\", \"Celular\": \"cel3\", \"CodigoSIM\": \"sim3\", \"EnderecoMAC\": \"mac3\", \"Nome\": \"nome3\", \"Tipo\": \"VISITANTE\"},{\"CPF\": \"05766722460\", \"Celular\": \"81988888888\", \"CodigoSIM\": \"89014103211118510720\", \"EnderecoMAC\": \"08:00:27:90:ab:6f\", \"Nome\": \"GenyTest\", \"Tipo\": \"ADMIN\"}]}");
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            users = getUsersFromJSONArray(jsonArray);

            if (users.isEmpty())
                Log.d("USR_CTRL-MOCK", "LIST IS EMPTY");
            else
                Log.d("USR_CTRL-MOCK", "LIST NOT EMPTY");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    private static List<User> getUsersFromJSONArray(JSONArray jsonArray) throws JSONException {
//        List<User> usersList = new ArrayList<>();
        List<User> usersList = new LinkedList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);

            User user = new User();

            user.setName(jsonObj.getString(User.Fields.NAME.toString()));
//            Log.d("GET-NAME",User.Fields.NAME.toString());

            user.setCpf(jsonObj.getString(User.Fields.CPF.toString()));
//            Log.d("GET-CPF",User.Fields.CPF.toString());

            user.setMacAdress(jsonObj.getString(User.Fields.MAC_ADDRESS.toString()));
//            Log.d("GET-MAC",User.Fields.MAC_ADDRESS.toString());

            user.setSerialNumber(jsonObj.getString(User.Fields.SIM_SERIAL_NUMBER.toString()));
//            Log.d("GET-SIM",User.Fields.SIM_SERIAL_NUMBER.toString());

            user.setCellNumber(jsonObj.getString(User.Fields.PHONE_NUMBER.toString()));
//            Log.d("GET-TEL",User.Fields.PHONE_NUMBER.toString());

            // O servidor não inclui a senha do usuário no JSON :(
            user.setPinPassword("YOU'RE NOT SUPPOSED TO GET THIS!!!");

            String typeString = jsonObj.getString(User.Fields.TYPE.toString());
            UserType type = getUserTypeFromString(typeString);

            user.setType(type);
            usersList.add(user);
        }

        return usersList;
    }

    private static UserType getUserTypeFromString(String type) {
        UserType userType = UserType.VISIT;

        if (type == UserType.ADMIN.toString())
            userType = UserType.ADMIN;
        else if (type == UserType.WORKER.toString())
            userType = UserType.WORKER;

        return userType;
    }
}
