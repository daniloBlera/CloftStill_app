package com.cloftstill.cloftstill.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.model.Answer;
import com.cloftstill.cloftstill.model.UL;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class ServerActivity extends AppCompatActivity {

    LongRunning L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        L = new LongRunning();
        openDoor();
    }
    public void openDoor(){
        L.execute("http://10.0.3.2:5000/porta/abre/");
        doRequest();
    }
    public void doRequest(){
        try {
            L.get(); // ESPERA EXECUTAR A ASYNC TASK
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Toast.makeText(ServerActivity.this, L.text, Toast.LENGTH_LONG).show();
        finish();
    }

    private class LongRunning extends AsyncTask<String, Void, String> {

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

                Gson gson2 = new Gson();
                Answer cr = new Answer();
                cr = gson2.fromJson(text, new TypeToken<Answer>() {
                }.getType());
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }

            return text;
        }
    }
}
