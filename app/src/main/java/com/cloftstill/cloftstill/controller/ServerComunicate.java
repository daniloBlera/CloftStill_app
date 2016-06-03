package com.cloftstill.cloftstill.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.cloftstill.cloftstill.model.ServerInfo;
import com.cloftstill.cloftstill.utility.StreamProcessor;
import com.cloftstill.cloftstill.view.OpenDoorActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ServerComunicate {
    private OpenDoorActivity theView;

    public String comunicate(){

        // isNetworkAvaliable();

        final LongRunningGetIO L = new LongRunningGetIO();
//        L.execute("http://172.16.206.96:5000/open/");
        String strUrl = String.format(
                "http://%s:%s/open/", ServerInfo.ENDPOINT, ServerInfo.PORT_NUMBER);

        L.execute(strUrl);

        try {
            L.get();// ESPERA EXECUTAR A ASYNC TASK
        } catch (Exception e) {
            e.printStackTrace();
        }

        return L.getTexto();

    }

    public ServerComunicate(OpenDoorActivity theView) {
        this.theView = theView;
    }

    public String comunicaAbertura(String enderecoMAC, String codigoSIM, String senha) {
        Log.d("PROGRESS", "INSIDE comunicaAbertura()");

        JSONObject json =  new JSONObject();
        final RESTfulPOST post = new RESTfulPOST();

        String response = null;

        try {
            String jsonBody = new String("'{\"Senha\":\"senha4\", \"EnderecoMAC\":\"mac4\", \"CodigoSIM\":\"sim4\", \"PosicaoGPS\":\"<ARG>\"}'");
//            String jsonBody = "'{\"Senha\":\"senha4\", " +
//                    "'EnderecoMAC':'mac4', " +
//                    "'CodigoSIM':'sim4', " +
//                    "'PosicaoGPS':'<ARG>'}";

            json.put("EnderecoMAC", enderecoMAC);
            json.put("CodigoSIM", codigoSIM);
            json.put("Senha", senha);
            json.put("PosicaoGPS","<ARG>");

            String url = String.format(
                    "http://%s:%s/porta/abre/", ServerInfo.ENDPOINT, ServerInfo.PORT_NUMBER);

            String arguments = json.toString();

            Log.d("HERE STARTH", "post.requestPOST()");
//            response = post.requestPOST(url, json);
            response = post.requestPOST(url, jsonBody);
            Log.d("HERE ENDETH", "post.requestPOST()");

        } catch (JSONException e) {
            Log.e("EXCEPTION IN JSON PREP", e.getMessage());
            response =  "MISSING ARGUMENTS";
        }

        return post.getResposta();
//        return response;
    }

    public class LongRunningGetIO extends AsyncTask<String, Void, String> {

        public String texto;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

//        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
//            InputStream in = entity.getContent();
//            StringBuffer out = new StringBuffer();
//            int n = 1;
//            while (n > 0) {
//                byte[] b = new byte[4096];
//                n = in.read(b);
//                if (n > 0) out.append(new String(b, 0, n));
//            }
//            return out.toString();
//        }

//        @Override
//        protected String doInBackground(String... params) {
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpContext localContext = new BasicHttpContext();
//            String urlString = params[0];
//            HttpGet httpGet = new HttpGet(urlString);
//            //String texto = null;
//
//            try {
//                HttpResponse response = httpClient.execute(httpGet, localContext);
//                HttpEntity entity = response.getEntity();
//                texto = getASCIIContentFromEntity(entity);
//
//            } catch (Exception e) {
//                return e.getLocalizedMessage();
//            }
//
//            return texto;
//        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String response = null;
            BufferedReader reader = null;

            try {
                String urlString = params[0];
                URL url = new URL(urlString);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader = new BufferedReader(
                            new InputStreamReader(urlConnection.getInputStream()));

                    response = StreamProcessor.getMessageFromReader(reader);
                } else {
                    response = Integer.toString(responseCode);
                }

                setTexto(response);

            } catch (ConnectException e) {
                Log.e("FALHA DE CONEXÃO", e.getMessage());
                response = "A conexão com o servidor falhou (servidor offline?)";

            } catch (Exception e) {
                e.printStackTrace();
                response = e.getMessage();

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            setTexto(response);
            return response;
        }
    }
}
