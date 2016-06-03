package com.cloftstill.cloftstill.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.cloftstill.cloftstill.utility.StreamProcessor;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dcblera on 30/05/16.
 */
public class RESTfulPOST {
    String resposta;

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String mensagem) {
        resposta = mensagem;
    }

//    public String requestPOST(String url, JSONObject data) {
    public String requestPOST(String url, String data) {
        Log.d("INSIDE REQUEST", "HEY-YEEE");

        final LongRunningGetIO longRun = new LongRunningGetIO();

//        longRun.execute(url, data.toString());
        longRun.execute(url, data);
//        longRun.execute(url, "AYYY");

        String strget = "FAIL";
        try {

            strget = longRun.get();
        } catch (Exception e) {
            Log.e("EXCEPTION ON THREADING", e.getMessage());
            e.printStackTrace();
        }

//        Log.d("longRun.get()", getResposta());
        return getResposta();
    }

    private class LongRunningGetIO extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "https://10.0.3.2:5000/porta/abre/";
            String responseMessae = null;

            try {
                HttpClient client = HttpClientBuilder.create().build();
                HttpPost post = new HttpPost(url);

                // add header
                post.setHeader("Content-Type", "application/json; charset=utf-8");


                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("Senha", "senha4"));
                urlParameters.add(new BasicNameValuePair("EnderecoMAC", "mac4"));
                urlParameters.add(new BasicNameValuePair("CodigoSIM", "sim4"));
                urlParameters.add(new BasicNameValuePair("PosicaoGPS", "gpsArg"));

                post.setEntity(new UrlEncodedFormEntity(urlParameters));

                HttpResponse response = client.execute(post);
                Log.d("RESPONSE CODE", Integer.toString(response.getStatusLine().getStatusCode()));

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();

                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                setResposta(responseMessae);
                responseMessae = result.toString();

            }catch (Exception e) {
                e.printStackTrace();
                responseMessae = e.getMessage();
            }

            return responseMessae;
        }
    }
}
