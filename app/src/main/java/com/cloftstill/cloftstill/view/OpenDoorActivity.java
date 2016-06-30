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
import com.cloftstill.cloftstill.controller.AdminValidityController;
import com.cloftstill.cloftstill.controller.FirstAccessController;
import com.cloftstill.cloftstill.controller.RemoteDoorController;
import com.cloftstill.cloftstill.controller.UsersController;
import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.ServerResponse;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.Solicitation;
import com.cloftstill.cloftstill.model.User;

import java.util.List;

public class OpenDoorActivity extends AppCompatActivity {
    Context thisContext = this;
    String pin = "";
    private TextView txtSignUp;

    static List<User> usersList;
    static List<Solicitation> openSolicitationsList;

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

                    String doorResponse = RemoteDoorController.requestOpen(authenticable, "LOCALIZACAO");

                    if (doorResponse == null) {
                        //TODO - Colocar mensagens repetidas/comuns no xml de Strings
                        doorResponse = "IT'S NULL";
                    } else if (doorResponse.equals(ServerResponse.INCORRECT_PASSWORD.toString())) {
                        doorResponse = "Senha incorreta!";
                    } else if (doorResponse.equals(ServerResponse.UNREGISTERED_USER.toString())) {
                        doorResponse = "Este dispositivo não se encontra cadastrado no sistema!";
                    } else if(doorResponse.equals(ServerResponse.REQUEST_SENT.toString())) {
                        doorResponse = "Sinal de abertura enviado!";
                    } else if (doorResponse.equals(ServerResponse.INTERNAL_ERROR.toString())) {
                        doorResponse = "Um erro interno ocorreu no servidor, tente novamente mais tarde :(";
                    } else if (doorResponse.equals(ServerResponse.REQUEST_DENIED.toString())) {
                        doorResponse = "Você não possui autorização para abrir a porta";
                    } else {
                        doorResponse = "Falhou em todos os casos???";
                    }

                    Log.d("OpenDoorACTIVITY", doorResponse);
                    Toast.makeText(thisContext, doorResponse, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(thisContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                Authenticable authenticable = new Authenticable(pin.toString(), getMACAddress(),
                        getSimCardSerial());

                pin = ""; //reset password
            }
        });

        assert openDoorBtn != null;
        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    public void onBackPressed() {
        finish();
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
        } else if (id == R.id.action_pre_register){
            getCPFDialog(this);
        } else if (id == R.id.action_admin){
            //ir para a tela de login do admin
            showAdminLoginDialog(this);
        } else if (id == R.id.action_logout){
            //fazer as operações de logout do admin
            Session.setAdmin(null);
            restartActivity();
        } else if (id == R.id.action_report){
            finish();
            /*String rolls = "                      [ 'Ricardo',  '',    new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                    "                      [ 'Castro',  '', new Date(0,0,0,16,30,0), new Date(0,0,0,23,0,0) ]," +
                    "                      [ 'Stefany', '',   new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                    "                      [ 'Nichene',   '',       new Date(0,0,0,6,30,0), new Date(0,0,0,15,0,0) ]," +
                    "                      [ 'Danilo',   '',          new Date(0,0,0,16,30,0), new Date(0,0,0,18,30,0) ]";
            ReportActivity.setRolls(rolls);*/
            Intent intentGoReport = new Intent(OpenDoorActivity.this, ChooseReportActivity.class);
            startActivity(intentGoReport);
        } else if (id == R.id.action_users){
            Authenticable authAble = getAuthenticableCredentials();

            //TODO MAKE THIS LINE WORK
            usersList = UsersController.requestAllApprovedUsers(authAble);
//            usersList = UsersController.requestAllApprovedUsersMOCKUP(authAble);
            openSolicitationsList = UsersController.requestAllPendingSolicitation(authAble);

            if (usersList.isEmpty()) {
                Log.d("DOOR_OPN_USR - LIST", "IS_EMPTY");   //Só pra saber se a lista tá vazia,
            } else {
                Log.d("DOOR_OPN_USR - LIST", "NOT_EMPTY");
            }

            if (openSolicitationsList.isEmpty()) {
                Log.d("DOOR_OPN_SOL - LIST", "IS_EMPTY");   //Só pra saber se a lista tá vazia,
            } else {
                Log.d("DOOR_OPN_SOL - LIST", "NOT_EMPTY");
            }

            try {
                UsersActivity.setUsers(usersList);
                //TODO Mudar setSolicitations() para aceitar List<Solicitation>
                UsersActivity.setSolicitations(usersList);
//                UsersActivity.setSolicitations(openSolicitationsList);
                Intent intentGoUsers = new Intent(OpenDoorActivity.this, UsersActivity.class);
                startActivity(intentGoUsers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_registerVisits){
            finish();
            Intent goRegisterByAdmin = new Intent(OpenDoorActivity.this, RegisterByAdminActivity.class);
            startActivity(goRegisterByAdmin);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAdminLoginDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

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

                    Authenticable authenticable = getNewAuthenticable(password);

                    String isAdminResponse =
                            AdminValidityController.requestAdminCheck(authenticable);

                    if (isAdminResponse.equals(ServerResponse.ADMIN_VERIFICATION_TRUE.toString())) {
                        Log.d("DOOR_ACTY_ADM_LOG", "TRUE");
                        User adminSession = new User();
                        adminSession.setPinPassword(password);
                        Session.setAdmin(adminSession);

                        restartActivity();
                    } else if (isAdminResponse.equals(
                            ServerResponse.ADMIN_VERIFICATION_FALSE.toString())) {

                        Log.d("DOOR_ACTY_ADM_LOG", "FALSE");
                        Toast.makeText(Session.getContext(),
                                "Sua conta não é do tipo Administrador", Toast.LENGTH_SHORT).show();
                    } else if (isAdminResponse.equals(
                            ServerResponse.INCORRECT_PASSWORD.toString())) {

                        Log.d("DOOR_ACTY_ADM_LOG", "PWORD");
                        Toast.makeText(thisContext, "Senha incorreta!", Toast.LENGTH_LONG).show();
                    } else if (isAdminResponse.equals(
                            ServerResponse.UNREGISTERED_USER.toString())) {

                        Log.d("DOOR_ACTY_ADM_LOG", "UNREG_USR");
                        Toast.makeText(thisContext,
                                "Este dispositivo não se encontra cadastrado no sistema",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        builder.setNegativeButton("CANCELAR", null);
        builder.show();
    }

    private void getCPFDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("CPF");
        builder.setMessage("Digite seu CPF");

        final EditText prompt = new EditText(this);
        builder.setView(prompt);
//        prompt.setInputType(InputType.TYPE_CLASS_NUMBER);//TODO CPF com Strings
        prompt.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cpf = prompt.getText().toString();

                //TODO re-inserir casos quando cpf digitado válido ou inválido
                //TODO Substituir if-else-if por switch(case)
//                if (CPFCheck.isCPF(cpf)) {
                if (true) {
                    String macAddress = getMACAddress();
                    String simcardSerial = getSimCardSerial();

                    String validationResponse = FirstAccessController.validade(
                            cpf, macAddress, simcardSerial);

                    User thisUser = null;

                    if (validationResponse == null) {
                        validationResponse = "ITS NULL";

                        Log.d("DOOR_ACTY_FST", validationResponse);
                        Toast.makeText(thisContext, validationResponse, Toast.LENGTH_LONG).show();

                    } else if (validationResponse.equals(
                            ServerResponse.SOLICITATION_ACCEPTED.toString())) {

                        thisUser = FirstAccessController.getMyAccountDetails(cpf);
                        Log.d("DOOR_ACTY_ACC_DETAIL", thisUser.getMacAdress());

                        YourPasswordActivity.setAttributes(thisUser);

                        Intent goYourPasswordActivity = new Intent(
                                OpenDoorActivity.this, YourPasswordActivity.class);

                        startActivity(goYourPasswordActivity);
                        finish();

                    } else {
                        //Sobrescreve validationResponse com uma versão "human-readable" para cada
                        // possível resposta do servidor e dá um Toast ao final.
                        if (validationResponse.equals(
                                ServerResponse.UNREGISTERED_USER.toString())) {

                            validationResponse = "Usuario não registrado!";
                        } else if (validationResponse.equals(
                                ServerResponse.CPF_NOT_FIRST_ACCESS.toString())) {

                            validationResponse = "Este usuário não está marcado para primeiro acesso!";
                        } else if (validationResponse.equals(
                                ServerResponse.INTERNAL_ERROR.toString())) {

                            validationResponse = "Um erro interno ocorreu no servidor, tente" +
                                    "novamente mais tarde :(";

                        } else if (validationResponse.equals(
                                ServerResponse.MAC_SIM_ALREADY_REGISTERED.toString())) {

                            validationResponse = "Seu dispositivo já se encontra cadastrado!";
                        } else if (validationResponse.equals(
                                ServerResponse.CPF_NOT_FIRST_ACCESS.toString())) {

                            validationResponse = "Esta conta não se encontra no estatus de" +
                                    "'primeiro acesso'!";

                            Toast.makeText(thisContext, validationResponse,
                                    Toast.LENGTH_LONG).show();

                        } else {
                            validationResponse = "Falhou em todos os casos???";
                            Toast.makeText(thisContext, validationResponse,
                                    Toast.LENGTH_LONG).show();
                        }

                        Log.d("DOOR_ACTY_FST", validationResponse);
                        Toast.makeText(thisContext, validationResponse,
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(thisContext, "CPF inválido!", Toast.LENGTH_LONG).show();
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

    //Só utilizar por um Admin logado, Session.getAdmin().getPinPassword() gera nullptrExcept.
    private Authenticable getAuthenticableCredentials() {
        String macAddress = getMACAddress();
        String simcardSerial = getSimCardSerial();
        String password = Session.getAdmin().getPinPassword();

        return new Authenticable(password, macAddress, simcardSerial);
    }

    /**
     * Cria uma instância de Authenticable com a senha fornecida.
     *
     * @param password -- Senha fornecida pelo usuário para verificação de validade do status de
     *                    administrador.
     * @return -- Instância de Authenticable com o endereço mac e código do cartão SIM do
     *            dispositivo assim como a senha fornecida pelo usuário.
     */
    private Authenticable getNewAuthenticable(String password) {
        String macAddress = getMACAddress();
        String simcardSerial = getSimCardSerial();

        return new Authenticable(password, macAddress, simcardSerial);
    }
}
