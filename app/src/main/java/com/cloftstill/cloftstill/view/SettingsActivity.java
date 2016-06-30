package com.cloftstill.cloftstill.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.User;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import com.cloftstill.cloftstill.R;

public class SettingsActivity extends AppCompatActivity {

    private FloatingActionMenu materialDesignFAM;
    private FloatingActionButton alterarNome;
    private FloatingActionButton alterarSenha;
    private android.support.design.widget.FloatingActionButton saveChanges;
    private TextView txtNome;
    private TextView txtCPF;
    private TextView txtSerialSim;
    private TextView txtMac;
    private static String name;
    private static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (name == null) //TODO Recuperar credenciais de Session
            name = "Ricardo Lucas R. M. da Silva";
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.floatingOptions);
        alterarNome = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        alterarSenha = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        txtNome = (TextView) findViewById(R.id.txtUserName);
        txtCPF = (TextView) findViewById(R.id.txtUserCPF);
        txtSerialSim = (TextView) findViewById(R.id.txtSerial);
        txtMac = (TextView) findViewById(R.id.txtMAC);
        saveChanges = (android.support.design.widget.FloatingActionButton) findViewById(R.id.floatingOk);
        txtCPF.setText(txtCPF.getText() + "10811008428");
        txtNome.setText(txtNome.getText() + name);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO enviar alterações pra o banco, name and password
            }
        });

        txtMac.setText(txtMac.getText() + Session.getMacAdress());
        txtSerialSim.setText(txtSerialSim.getText() + Session.getSerialNumber());

        alterarNome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showChangeNameDialog(SettingsActivity.this);

            }
        });
        alterarSenha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showConfirmPasswordDialog(SettingsActivity.this);

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(SettingsActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }

    private void showChangeNameDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Alterar Nome");
        builder.setMessage("Digite novo nome");
        final EditText prompt = new EditText(this);
        prompt.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(prompt);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SettingsActivity.name = prompt.getText().toString();
            }
        });
        builder.setNegativeButton("CANCELAR", null);
        builder.show();
    }
    private void showConfirmPasswordDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Confirmar Senha");
        builder.setMessage("Digite Senha antiga");
        final EditText prompt = new EditText(this);
        prompt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(prompt);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (prompt.getText().toString().equals("1234")){
                        showChangePasswordDialog(SettingsActivity.this);
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
    private void showChangePasswordDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Alterar Senha");
        builder.setMessage("Digite Nova Senha");
        final EditText prompt = new EditText(this);
        prompt.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(prompt);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SettingsActivity.password = prompt.getText().toString();
            }
        });
        builder.setNegativeButton("CANCELAR", null);
        builder.show();
    }
    public static void setAtributtes(String name, String password){
        SettingsActivity.name = name;
        SettingsActivity.password = password;
    }
}
