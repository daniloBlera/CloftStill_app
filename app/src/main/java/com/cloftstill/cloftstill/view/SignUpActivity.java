package com.cloftstill.cloftstill.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cloftstill.cloftstill.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinnerArray = new String[3];
        spinnerArray[0] = "Administrador";
        spinnerArray[1] = "Comum";
        spinnerArray[2] = "Visita";

        edtName = (EditText) findViewById(R.id.edtName);
        edtCPF = (EditText) findViewById(R.id.edtCPf);
        edtPIN = (EditText) findViewById(R.id.edtPin);
        edtConfirmPIN = (EditText) findViewById(R.id.edtConfirmPin);
        edtCell = (EditText) findViewById(R.id.edtCellPhone);
        btnSignUp = (Button) findViewById(R.id.btnSingUp);
        txtSerial = (TextView) findViewById(R.id.txtSerial);
        txtMac = (TextView) findViewById(R.id.txtMAC);
        spinnerUserType = (Spinner) findViewById(R.id.spinnerUserType);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_sign_up, spinnerArray);
        spinnerUserType.setAdapter(adapter);
        spinnerUserType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
