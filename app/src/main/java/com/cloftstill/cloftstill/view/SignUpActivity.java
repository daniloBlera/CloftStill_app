package com.cloftstill.cloftstill.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.controller.SignUpController;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.User;
import com.cloftstill.cloftstill.controller.CPFCheck;
import com.cloftstill.cloftstill.model.UserType;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtCPF;
    private EditText edtPIN;
    private EditText edtConfirmPIN;
    private EditText edtCell;
    private Button btnSignUp;
    private TextView txtSerial;
    private TextView txtMac;
    private Spinner spinnerUserType;
    private String[] spinnerArray;
    private UserType[] userTypeArray;
    private UserType userType;
    private SignUpController signUpController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpController = new SignUpController();

        spinnerArray = new String[3];
        spinnerArray[0] = "Administrador";
        spinnerArray[1] = "Comum";
        spinnerArray[2] = "Visita";

        userTypeArray = new UserType[3];
        userTypeArray[0] = UserType.ADMIN;
        userTypeArray[1] = UserType.COMMON;
        userTypeArray[2] = UserType.VISIT;

        edtName = (EditText) findViewById(R.id.edtName);
        edtCPF = (EditText) findViewById(R.id.edtCPf);
        edtPIN = (EditText) findViewById(R.id.edtPin);
        edtConfirmPIN = (EditText) findViewById(R.id.edtConfirmPin);
        edtCell = (EditText) findViewById(R.id.edtCellPhone);
        btnSignUp = (Button) findViewById(R.id.btnSingUp);
        txtSerial = (TextView) findViewById(R.id.txtSerial);
        txtMac = (TextView) findViewById(R.id.txtMAC);
        spinnerUserType = (Spinner) findViewById(R.id.spinnerUserType);
        txtMac.setText(txtMac.getText() + Session.getMacAdress());
        txtSerial.setText(txtSerial.getText() + Session.getSerialNumber());
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()){
                    User user = new User();
                    user.setPinPassword(edtPIN.getText().toString());
                    user.setName(edtName.getText().toString());
                    user.setMacAdress(Session.getMacAdress());
                    user.setSimNumber(Session.getSerialNumber());
                    user.setType(userType);
                    user.setCpf(edtCPF.getText().toString());
                    boolean isAllOk = signUpController.checkSignUp(user);
                }
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, spinnerArray);
        spinnerUserType.setAdapter(adapter);
        spinnerUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType = userTypeArray[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("DEFAULT SELECTED", "default");
            }
        });
    }
    private boolean checkFields(){
        boolean ok = true;
        if (edtPIN.getText().toString().length() != 4){
            ok = false;
            showErrorMessage("A senha deve ter 4 dígitos");
        } else if (edtConfirmPIN.getText().toString().equals(edtConfirmPIN.getText().toString())){
            ok = false;
            showErrorMessage("As senhas devem ser iguais");
        } else if (!CPFCheck.isCPF(edtCPF.getText().toString())){
            ok = false;
            showErrorMessage("CPF inválido");
        } else if (edtName.getText().toString().length() <= 0){
            ok = false;
            showErrorMessage("O campo 'Nome' deve ser preenchido");
        } else if (edtCell.getText().toString().length() < 10 || edtCell.getText().length() > 11){
            ok = false;
            showErrorMessage("Número de celular inválido");
        }
        return ok;
    }
    private void showErrorMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}