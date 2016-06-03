package com.cloftstill.cloftstill.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.controller.ServerComunicate;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.User;

public class OpenDoorActivity extends AppCompatActivity {

    Context context = this;
    String pin = "";
    private TextView txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtSignUp = (TextView) findViewById(R.id.txtBtnSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToSignUp = new Intent(OpenDoorActivity.this, SignUpActivity.class);
                startActivity(intentGoToSignUp);
            }
        });

        Session.setMacAdress(getMACAddress());
        Session.setContext(this);
        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = telemamanger.getSimSerialNumber();
        Session.setSerialNumber(simSerialNumber);

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
        final ServerComunicate serverComunicate = new ServerComunicate(this);

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

                    Toast.makeText(context, "SIM number: " + Session.getSerialNumber(), Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, "PIN password: " + pin, Toast.LENGTH_SHORT).show();
                Toast.makeText(context,serverComunicate.comunicate(), Toast.LENGTH_LONG).show();

                TelephonyManager telMngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                String mensagemResposta = serverComunicate.comunicaAbertura(Session.getMacAdress(),Session.getSerialNumber(), pin);

                if (mensagemResposta != null) {
                    Log.d("NOT NULL", mensagemResposta);
                    Toast.makeText(context, mensagemResposta, Toast.LENGTH_SHORT);
                } else {
                    Log.d("RETURNED NULL", "THIS C0DE");
                }
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
        Session.setMenu(menu);
        if (Session.getAdmin() == null){
            getMenuInflater().inflate(R.menu.menu_open_door, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_open_door_admin, menu);
        }
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
            finish();
            Intent intentGoToSettings = new Intent(OpenDoorActivity.this, SettingsActivity.class);
            startActivity(intentGoToSettings);
        } else if (id == R.id.action_admin){
            //ir para a tela de login do admin
            showAdminLoginDialog(this);
        } else if (id == R.id.action_logout){
            //fazer as operações de logout do admin
            Session.setAdmin(null);
            restartActivity();
        } else if (id == R.id.action_report){

        }

        return super.onOptionsItemSelected(item);
    }

    private void showAdminLoginDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Login");
        builder.setMessage("Digite a senha de administrador");
        final EditText prompt = new EditText(this);
        prompt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(prompt);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (prompt.getText().toString().equals("1234")){
                        Session.setAdmin(new User());
                        restartActivity();
                    } else {
                        Toast.makeText(Session.getContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("CANCELAR", null);
        builder.show();
    }

    private void restartActivity(){
        finish();
        Intent intentRestart = new Intent(OpenDoorActivity.this, OpenDoorActivity.class);
        startActivity(intentRestart);
    }

    private boolean isNetworkAvaliable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo.isAvailable();
    }

    /**
     * Recupera o endereço MAC do aparelho.
     *
     * @return Endereço MAC da interface wireless do aparelho.
     */
    private String getMACAddress() {
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    public void toastRequestMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }
}
