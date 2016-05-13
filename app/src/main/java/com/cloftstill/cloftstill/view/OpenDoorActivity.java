package com.cloftstill.cloftstill.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.model.ServerComunicate;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class OpenDoorActivity extends AppCompatActivity {

    Context context = this;
    String pin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button openDoorBtn = (Button) findViewById(R.id.openDoorButton);
        final Button numpad1 = (Button) findViewById(R.id.btn1);
        final Button numpad2 = (Button) findViewById(R.id.btn2);
        final Button numpad3 = (Button) findViewById(R.id.btn3);
        final Button numpad4 = (Button) findViewById(R.id.btn4);
        final Button numpad5 = (Button) findViewById(R.id.btn5);
        final Button numpad6 = (Button) findViewById(R.id.btn6);
        final Button numpad7 = (Button) findViewById(R.id.btn7);
        final Button numpad8 = (Button) findViewById(R.id.btn8);
        final Button numpad9 = (Button) findViewById(R.id.btn9);
        final Button numpad0 = (Button) findViewById(R.id.btn0);
        final Button numpadOK = (Button) findViewById(R.id.btnOK);
        final Button numpadErase = (Button) findViewById(R.id.btnErase);
        final ServerComunicate serverComunicate = new ServerComunicate();

        numpad0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "0";
            }
        });
        numpad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "1";
            }
        });
        numpad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "2";
            }
        });
        numpad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "3";
            }
        });
        numpad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "4";
            }
        });
        numpad5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "5";
            }
        });
        numpad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "6";
            }
        });
        numpad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "7";
            }
        });
        numpad8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "8";
            }
        });
        numpad9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin += "9";
            }
        });
        numpadErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin.length() > 0) {
                    pin = pin.substring(0, pin.length() - 1);
                }
            }
        });
        numpadOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String getSimSerialNumber = telemamanger.getSimSerialNumber();
                    String getSimNumber = telemamanger.getLine1Number();
                    Toast.makeText(context, "SIM number: " + getSimNumber, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, "PIN password: " + pin, Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,serverComunicate.comunicate(), Toast.LENGTH_LONG).show();

                pin = ""; //reset password
            }
        });

        /**
         * Liberar permissões
         */
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);


        assert openDoorBtn != null;
        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //t = L.getTexto();
                //DoorController controller = new DoorController();
                //controller.requestOpen();
                numpad0.setEnabled(true);
                numpad1.setEnabled(true);
                numpad2.setEnabled(true);
                numpad3.setEnabled(true);
                numpad4.setEnabled(true);
                numpad5.setEnabled(true);
                numpad6.setEnabled(true);
                numpad7.setEnabled(true);
                numpad8.setEnabled(true);
                numpad9.setEnabled(true);
                numpadOK.setEnabled(true);
                numpadErase.setEnabled(true);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open_door, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvaliable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo.isAvailable();
    }
}
