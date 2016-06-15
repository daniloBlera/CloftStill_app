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
import com.cloftstill.cloftstill.controller.RemoteDoorController;
import com.cloftstill.cloftstill.controller.ServerComunicate;
import com.cloftstill.cloftstill.controller.AdminValidityController;
import com.cloftstill.cloftstill.controller.UsersController;
import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.IsAdminResponse;
import com.cloftstill.cloftstill.model.OpenDoorResponse;
import com.cloftstill.cloftstill.model.ServerResponse;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.User;

import java.util.ArrayList;
import java.util.List;

public class OpenDoorActivity extends AppCompatActivity {
    Context thisContext = this;
    String pin = "";
    String adminSessionPassword;
    private TextView txtSignUp;

    static List<User> usersList;

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

        final TelephonyManager telemamanger = (TelephonyManager) getSystemService(
                Context.TELEPHONY_SERVICE);

        final String simSerialNumber = telemamanger.getSimSerialNumber();
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
                    String macAddress = getMACAddress();
                    String simSerialNO = getSimCardSerial();

                    Authenticable authenticable = new Authenticable(pin.toString(), macAddress,
                            simSerialNO);

                    ServerResponse doorResponse = RemoteDoorController.requestOpen(authenticable,
                            "LOCALIZACAO"); //TODO - Implementar Localização

                    String message = null;

                    if (doorResponse == ServerResponse.INCORRECT_PASSWORD) {
                        //TODO - Colocar mensagens repetidas/comuns no xml de Strings
                        message = "Senha incorreta!";
                    } else if (doorResponse == ServerResponse.UNREGISTERED_USER) {
                        message = "Este dispositivo não se encontra cadastrado no sistema!";
                    } else if(doorResponse == ServerResponse.REQUEST_SENT) {
                        message = "Sinal de abertura enviado!";
                    } else if (doorResponse == ServerResponse.INTERNAL_ERROR) {
                        message = "Um erro interno ocorreu no servidor, tente novamente mais tarde :(";
                    } else if (doorResponse == ServerResponse.REQUEST_DENIED) {
                        message = "Você não possui autorização para abrir a porta";
                    }

                    Log.d("OpenDoorACTIVITY", message);
                    Toast.makeText(thisContext, message, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(thisContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                Authenticable authenticable = new Authenticable(pin.toString(), getMACAddress(),
                        getSimCardSerial());

                try {
                    List<User> list = UsersController.requestAllApprovedUsers(authenticable);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                pin = ""; //reset password
            }
        });

        assert openDoorBtn != null;
        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //t = L.getTexto();
                //DoorController controller = new DoorController();
                //controller.requestSignUp();
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
            finish();
            ReportActivity.setRolls(" [ 'Ful',  'CSS',    new Date(0,0,0,7,0,0),  new Date(0,0,0,9,0,0) ]," +
                    "                      [ 'Ful',  'Intro',    new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                    "                      [ 'Ful',  'Java', new Date(0,0,0,16,30,0), new Date(0,0,0,23,0,0) ]," +
                    "                      [ 'Cic', 'Perl',   new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                    "                      [ 'Cic', 'Perl2',       new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                    "                      [ 'Cic', 'Perl3',        new Date(0,0,0,16,30,0), new Date(0,0,0,18,0,0) ]," +
                    "                      [ 'Joao',   'G',       new Date(0,0,0,3,30,0), new Date(0,0,0,15,0,0) ]," +
                    "                      [ 'Bel',   'G',       new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                    "                      [ 'Bel',   'Clo',             new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                    "                      [ 'Bel',   'App',          new Date(0,0,0,16,30,0), new Date(0,0,0,18,30,0) ]");
            Intent intentGoReport = new Intent(OpenDoorActivity.this, ReportActivity.class);
            startActivity(intentGoReport);
        } else if (id == R.id.action_users){
            Intent intentGoUsers = new Intent(OpenDoorActivity.this, UsersActivity.class);

            String pwrd = Session.getAdmin().getPinPassword();
            Authenticable authAble = getAuthenticableCredentials();

            //TODO MAKE THIS LINE WORK
            usersList = UsersController.requestAllApprovedUsers(authAble);

            if (usersList.isEmpty()) {
                Log.d("DOOR_OPN - LIST", "IS_EMPTY");   //Só pra saber se a lista tá vazia,
                                                        // imprimindo "NOT EMPTY" por sinal
            } else {
                Log.d("DOOR_OPN - LIST", "NOT_EMPTY");
            }

            UsersActivity.setUsers(usersList);
            startActivity(intentGoUsers);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAdminLoginDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        final String macAddressAdm = getMACAddress();
        final String simSerialNOAdm = getSimCardSerial();

        builder.setTitle("Login");
        builder.setMessage("Digite a Senha de administrador");

        final EditText prompt = new EditText(this);
        prompt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(prompt);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String password = prompt.getText().toString();

                    Authenticable authenticable = new Authenticable(
                            password, macAddressAdm, simSerialNOAdm);

                    IsAdminResponse isAdminResponse =
                            AdminValidityController.requestAdminCheck(authenticable);

                    if (isAdminResponse == IsAdminResponse.TRUE) {

                        User adminSession = new User();
                        adminSession.setPinPassword(password);
                        Session.setAdmin(adminSession);

                        restartActivity();

                    }else if (isAdminResponse == IsAdminResponse.INCORRECT_PASSWORD) {
                        Toast.makeText(thisContext, "Senha incorreta!", Toast.LENGTH_LONG).show();

                    } else if (isAdminResponse == IsAdminResponse.FALSE) {

                        Toast.makeText(Session.getContext(),
                                "Sua conta não é do tipo Administrador", Toast.LENGTH_SHORT).show();
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

    /**
     * Recupera o código serial do cartão SIM do aparelho.
     *
     * @return - Código serial do cartão SIM.
     */
    private String getSimCardSerial() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(
                Context.TELEPHONY_SERVICE);

        return telephonyManager.getSimSerialNumber();
    }

    private Authenticable getAuthenticableCredentials() {
        String macAddress = getMACAddress();
        String simcardSerial = getSimCardSerial();
        String password = Session.getAdmin().getPinPassword();

        return new Authenticable(password, macAddress, simcardSerial);
    }
}
