package com.cloftstill.cloftstill.controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Classe responsavel por executar requests HTTP ao servidor.
 */
public class HttpRequestHandler {
    public static final MediaType JSON = MediaType.parse("application/json");
    /**
     * Executa uma requisição POST na url fornecida e com o corpo json.
     *
     * Exemplo:
     *
     * HttpRequestHandler handler = new HttpRequestHandler();
     *
     * String uri = "http://www.meuloginplaintxt.com/login/";
     * String body = {"nome":"Juvenal", "senha":"querty12345"};
     *
     * String response = handler.executePOST(uri, body);
     *
     * @param uri URI de destino da requisição, i.e, "http://host:port/abs_path/".
     * @param jsonBody Corpo JSON de argumentos da requisição.
     * @return Mensagem de resposta do servidor. // TODO Implementar melhor formato para resposta
     */
    public String executePOST(String uri, String jsonBody) {
        String message;

        LongRunningIO task = new LongRunningIO();

        try {
            task.execute(uri, jsonBody);
            task.get();
            message = task.responseMessage;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        return message;
    }

    /**
     * Executa uma requisição do tipo GET no uri fornecido.
     *
     * @param uri URI de destino da requisição.
     * @return Resposta do sevidor.
     */
    public String executeGET(String uri) {
        String message;

        LongRunningIO task = new LongRunningIO();

        task.execute(uri);

        try {
            task.execute(uri);
            task.get();
            message = task.responseMessage;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        return message;
    }

    private class LongRunningIO extends AsyncTask<String, Void, String> {
        String responseMessage;

        @Override
        protected String doInBackground(String... params) {
            String message;
            Response response = null;
            this.responseMessage = null;

            try {
                OkHttpClient client = new OkHttpClient();
                Request request;

                String urlString = params[0];
                if (params.length >= 2) {
                    String jsonBody = params[1];

                    RequestBody body = RequestBody.create(JSON, jsonBody);

                    request = new Request.Builder().url(urlString).post(body).build();
                } else {
                    request = new Request.Builder().url(urlString).build();
                }

                response = client.newCall(request).execute();

                message = response.body().string();
                responseMessage = message;
            } catch (IOException e) {
                e.printStackTrace();
                message = e.getMessage();
            } finally {
                if (response != null) {
                    response.close();
                }
            }

            return message;
        }
    }
}
