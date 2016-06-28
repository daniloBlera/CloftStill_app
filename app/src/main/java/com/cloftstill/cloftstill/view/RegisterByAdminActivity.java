package com.cloftstill.cloftstill.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.controller.CPFCheck;
import com.cloftstill.cloftstill.controller.SignUpController;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.SignupResponse;
import com.cloftstill.cloftstill.model.User;

import java.io.OutputStream;
import java.util.Calendar;

public class RegisterByAdminActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPIN;
    private EditText edtConfirmPIN;
    private EditText edtCPF;
    private EditText edtCell;
    private TextView txtDayStart;
    private TextView txtDayEnd;

    private int dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd;

    private FloatingActionButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_admin);

        edtName = (EditText) findViewById(R.id.edtName);
        edtCPF = (EditText) findViewById(R.id.edtCPf);
        edtPIN = (EditText) findViewById(R.id.edtPin);
        edtConfirmPIN = (EditText) findViewById(R.id.edtConfirmPin);
        edtCell = (EditText) findViewById(R.id.edtCellPhone);
        btnOk = (FloatingActionButton) findViewById(R.id.fabOk);
        txtDayStart = (TextView) findViewById(R.id.txtDayStart);
        txtDayEnd = (TextView) findViewById(R.id.txtDayEnd);

        Calendar calendar = Calendar.getInstance();
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);
        dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
        monthStart = calendar.get(Calendar.MONTH);
        monthEnd = calendar.get(Calendar.MONTH);
        yearStart = calendar.get(Calendar.YEAR);
        yearEnd = calendar.get(Calendar.YEAR);


        txtDayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtDayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFields()){
                    User user = new User();
                    user.setPinPassword(edtPIN.getText().toString());
                    user.setName(edtName.getText().toString());
                    user.setCellNumber(edtCell.getText().toString());
                    user.setCpf(edtCPF.getText().toString());

                    try {
                        Log.d("SIGNUP_ACTIVITY", "DENTRO");
                        //TODO guardar previamente o usuario
                    } catch (Exception e) {
                        Log.d("SIGNUP_ACTIVITY", "FORA_TEMER");
                        e.printStackTrace();
                    }
                }
            }
        });




    }
    private boolean checkFields(){
        boolean ok = true;
        if (edtPIN.getText().toString().length() != 4){
            ok = false;
            showErrorMessage("A Senha deve ter 4 dígitos");
        } else if (!edtPIN.getText().toString().equals(edtConfirmPIN.getText().toString())){
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
    private Dialog startDayPickDialog(){
        return new DatePickerDialog(this, startPickerListener, yearStart, monthStart, dayStart);
    }

    private DatePickerDialog.OnDateSetListener startPickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            yearStart  = year;
            monthStart = monthOfYear;
            dayStart   = dayOfMonth;

            txtDayStart.setText(new StringBuilder()
                    // Month is 0 based, just add 1
                    .append(monthStart + 1).append("-").append(dayStart).append("-")
                    .append(year).append(" "));
        }
    };
}
