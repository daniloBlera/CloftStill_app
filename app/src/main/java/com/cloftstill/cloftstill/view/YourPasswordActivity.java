package com.cloftstill.cloftstill.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cloftstill.cloftstill.R;


public class YourPasswordActivity extends AppCompatActivity {

    private static String mac;
    private static String serial;
    private static String pin;
    private static String cpf;
    private static String name;

    private TextView txtMac;
    private TextView txtSerial;
    private TextView txtPIN;
    private TextView txtCPF;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_password);

        txtMac = (TextView) findViewById(R.id.txtMAC);
        txtSerial = (TextView) findViewById(R.id.txtSerial);
        txtPIN = (TextView) findViewById(R.id.txtYourPassword);
        txtCPF = (TextView) findViewById(R.id.txtUserCPF);
        txtName = (TextView) findViewById(R.id.txtUserName);

        txtMac.setText(txtMac.getText() + mac);
        txtSerial.setText(txtSerial.getText() + serial);
        txtPIN.setText(txtPIN.getText() + pin);
        txtCPF.setText(txtCPF.getText() + cpf);
        txtName.setText(txtName.getText() + name);

    }

    public static void setAttributes(String mac, String serial, String pin, String cpf, String name){
        YourPasswordActivity.mac = mac;
        YourPasswordActivity.serial = serial;
        YourPasswordActivity.pin = pin;
        YourPasswordActivity.cpf = cpf;
        YourPasswordActivity.name = name;
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(YourPasswordActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }
}
