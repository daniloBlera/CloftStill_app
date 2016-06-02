package com.cloftstill.cloftstill.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.cloftstill.cloftstill.utility.StreamProcessor;

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

    public String requestPOST(String url, JSONObject data) {
        Log.d("INSIDE REQUEST", "HEY-YEEE");

        final LongRunningGetIO longRun = new LongRunningGetIO();

        longRun.execute(url, data.toString());

        try {
            longRun.get();
        } catch (Exception e) {
            Log.e("EXCEPTION ON THREADING", e.getMessage());
            e.printStackTrace();
        }

        return getResposta();
    }

    private class LongRunningGetIO extends AsyncTask <String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String response = null;
            BufferedWriter writer = null;
            BufferedReader reader = null;

            OutputStream os = null;
            InputStream is = null;

            try {
                URL url = new URL(params[0]);
                String arguments = params[1];

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setFixedLengthStreamingMode(arguments.getBytes().length);
                urlConnection.setRequestProperty("Content-Type", "application/json/charset=utf-8");

                urlConnection.connect();
                writer = new BufferedWriter(
                        new OutputStreamWriter(urlConnection.getOutputStream()));

                writer.write(arguments);

    //  É AQUI QUE AS COISAS FICAM DANDO ERRO
    //            os = urlConnection.getOutputStream();
    //            is = urlConnection.getInputStream();
    //            byte[] bytes = params[1].getBytes();
    //
    //            os.write(bytes);
    //            reader = new BufferedReader(
    //                    new InputStreamReader(urlConnection.getInputStream()));
    //
    //            response = StreamProcessor.getMessageFromReader(reader);
    //
    //            return response;
                response = "SO FAR NO ERRORS";
            } catch (ConnectException e) {
                Log.d("RESTfulPOST ConnExc", e.getMessage());
                response = e.getMessage();
            } catch (Exception e) {
                Log.d("RestfulPOST Exc", e.getMessage());
                e.printStackTrace();
                response = e.getMessage();

            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {}
                }

                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {}
                }

                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }

                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            setResposta(response);
            return response;
        }
    }
}
