package com.cloftstill.cloftstill.controller;

import android.os.AsyncTask;

import com.cloftstill.cloftstill.model.UL;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by dcblera on 03/06/16.
 */
public class ServerController {

    LongRunning L = new LongRunning();

    public void openDoor(){
        try {
            L.get(); // ESPERA EXECUTAR A ASYNC TASK
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private class LongRunning extends AsyncTask<String, Void, String>{

        public String text;

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();
            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            String urlString = params[0];
            HttpPost httpPost = new HttpPost(urlString);
            Gson gson= new Gson();
            UL u = new UL("mac4", "sim4", "senha4","qlq");

            try {
                StringEntity paramsss =new StringEntity((gson.toJson(u)));
                httpPost.addHeader("content-type", "application/json");
                httpPost.setEntity(paramsss);
                HttpResponse response = httpClient.execute(httpPost, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);

                //Gson gson2 = new Gson();
                //cr = gson2.fromJson(text, new TypeToken<UserLogin>() {
                //}.getType());




            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            return text;
        }
    }
}
