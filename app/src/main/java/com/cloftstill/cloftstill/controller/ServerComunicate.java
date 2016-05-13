package com.cloftstill.cloftstill.controller;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

public class ServerComunicate {

    public String comunicate(){

        // isNetworkAvaliable();

        final LongRunningGetIO L = new LongRunningGetIO();
        L.execute("http://172.16.206.96:5000/open/");

        try {
            L.get(); // ESPERA EXECUTAR A ASYNC TASK
        } catch (Exception e) {
            e.printStackTrace();
        }
        return L.getTexto();

    }

    public class LongRunningGetIO extends AsyncTask<String, Void, String> {

        public String texto;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }


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
            HttpGet httpGet = new HttpGet(urlString);
            //String texto = null;

            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                texto = getASCIIContentFromEntity(entity);

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            return texto;
        }
    }
}
