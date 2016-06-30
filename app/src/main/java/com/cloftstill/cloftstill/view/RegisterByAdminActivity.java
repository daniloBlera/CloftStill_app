package com.cloftstill.cloftstill.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.controller.AdminVisitingUserController;
import com.cloftstill.cloftstill.controller.CPFCheck;
import com.cloftstill.cloftstill.model.AuthorizationPeriod;
import com.cloftstill.cloftstill.model.ServerResponse;
import com.cloftstill.cloftstill.model.User;

import java.util.Calendar;

public class RegisterByAdminActivity extends AppCompatActivity {
    Context thisContext = this;

    private EditText edtName;
    private EditText edtPIN;
    private EditText edtConfirmPIN;
    private EditText edtCPF;
    private EditText edtCell;
    private TextView txtDayStart;
    private TextView txtDayEnd;
    private String startDate;
    private String endDate;

    private String startDateFormat;
    private String endDateFormat;

    private Integer dayStart, monthStart, yearStart, dayEnd, monthEnd, yearEnd;

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
                setDayStartDialog(RegisterByAdminActivity.this);
            }
        });

        txtDayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDayEndDialog(RegisterByAdminActivity.this);
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

                    AuthorizationPeriod period = new AuthorizationPeriod();
                    period.setStartDate(startDateFormat);
                    period.setEndDate(endDateFormat);

                    try {
                        Log.d("ADM_REGISTER", "DENTRO");
                        String result = AdminVisitingUserController.register(user, period);

                        if (result == null) {
                            Log.d("ADM_REG_RES", "NOT NULL");
                            Toast.makeText(thisContext, "Deu NULL...", Toast.LENGTH_LONG).show();

                        } else if (result.equals(ServerResponse.SOLICITATION_APPROVED.toString())) {
                            Log.d("ADM_REG_RES", "APPROVED");

                            Toast.makeText(
                                    thisContext, "Visitante cadastrado", Toast.LENGTH_LONG).show();

                        } else if (result.equals(ServerResponse.ALREADY_ACCEPTED.toString())) {
                            Log.d("ADM_REG_RES", "ALREADY_ACCEPTED");

                            Toast.makeText(thisContext, "Este visitante já está cadastrado",
                                    Toast.LENGTH_SHORT).show();

                        } else if (result.equals(
                                ServerResponse.PHONE_NUMBER_ALREADY_REGISTERED.toString())) {

                            Log.d("ADM_REG_RES", "ALREADY_HAS_PHONE_NO");

                            Toast.makeText(
                                    thisContext, "Este número de telefone já está cadastrado.",
                                    Toast.LENGTH_SHORT).show();

                        } else if (result.equals(
                                ServerResponse.CPF_ALREADY_REGISTERED.toString())) {

                            Log.d("ADM_REG_RES", "ALREADY_HAS_CPF");

                            Toast.makeText(thisContext, "Este CPF já está cadastrado.",
                                    Toast.LENGTH_SHORT).show();

                        } else if (result.equals(ServerResponse.INTERNAL_ERROR.toString())) {
                            Log.d("ADM_REG_RES", "INTERNAL ERROR");

                            Toast.makeText(thisContext, "Um erro interno ocorreu no servidor, " +
                                    "tente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("ADM_REGISTER", "FORA_TEMER");
                        e.printStackTrace();
                        Toast.makeText(thisContext, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(RegisterByAdminActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }

    private void setDayStartDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Data de Inicio");
        builder.setMessage("Escolha a data");

        final DatePicker datePicker = new DatePicker(this);
        builder.setView(datePicker);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dayStart = datePicker.getDayOfMonth();
                monthStart = datePicker.getMonth() + 1;
                yearStart = datePicker.getYear();

                startDate = dayStart.toString()+"/"+monthStart.toString()+"/"+yearStart.toString();
                String dateFormat = "%4d-%02d-%02d 00:00:00";

                startDateFormat = String.format(dateFormat, yearStart.intValue(),
                        monthStart.intValue(), dayStart.intValue());

                txtDayStart.setText(startDate);

                Toast.makeText(RegisterByAdminActivity.this, dayStart.toString()+"/"+monthStart.toString()+"/"+yearStart.toString(), Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("CANCELAR", null);
        builder.show();
    }

    private void setDayEndDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Data de Inicio");
        builder.setMessage("Escolha a data");

        final DatePicker datePicker = new DatePicker(this);
        builder.setView(datePicker);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dayEnd = datePicker.getDayOfMonth();
                monthEnd = datePicker.getMonth() + 1;
                yearEnd = datePicker.getYear();

                endDate = dayEnd.toString()+"/"+monthEnd.toString()+"/"+yearEnd.toString();
                String dateFormat = "%4d-%02d-%02d 23:59:59";

                endDateFormat = String.format(dateFormat, yearEnd.intValue(),
                        monthEnd.intValue(), dayEnd.intValue());

                txtDayEnd.setText(endDate);

                Toast.makeText(RegisterByAdminActivity.this, dayEnd.toString()+"/"+monthEnd.toString()+"/"+yearEnd.toString(), Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("CANCELAR", null);
        builder.show();
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

}
