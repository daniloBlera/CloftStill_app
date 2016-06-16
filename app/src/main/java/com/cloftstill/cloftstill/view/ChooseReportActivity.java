package com.cloftstill.cloftstill.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cloftstill.cloftstill.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseReportActivity extends AppCompatActivity {

    private ArrayList days = new ArrayList<String>();
    private ArrayList months = new ArrayList<String>();
    private ArrayList years = new ArrayList<String>();
    private ArrayList users = new ArrayList<String>();
    private Spinner spinnerDays;
    private Spinner spinnerMonths;
    private Spinner spinnerYears;
    private Spinner spinnerUsers;

    private String allUserRolls16;
    private String allUserRolls17;

    private String ricardoRolls16;
    private String ricardoRolls17;
    private String nicheneRolls16;
    private String nicheneRolls17;
    private String stefanyRolls16;
    private String stefanyRolls17;
    private String daniloRolls16;
    private String daniloRolls17;
    private String castroRolls16;
    private String castroRolls17;

    private String rollChoosen;

    private String[] arrayOptions = new String[12];

    private int dayChoosen = 0;
    private int userChoosen = 0;

    private Button btnOk;
    private boolean yearChoosen = false;
    private boolean monthChoosen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_report);

        String[] monthArray = {"Mes", "Julho"};
        String[] usersArray = {"Usuário", "Todos", "Ricardo", "Nichene", "Castro", "Danilo", "Stefany"};
        String[] daysArray = {"Dia", "16", "17"};
        String[] yearsArray = {"Ano", "2016"};

        months = new ArrayList(Arrays.asList(monthArray));
        users = new ArrayList(Arrays.asList(usersArray));
        days = new ArrayList(Arrays.asList(daysArray));
        years = new ArrayList(Arrays.asList(yearsArray));

        spinnerDays = (Spinner) findViewById(R.id.spinnerDay);
        spinnerMonths = (Spinner) findViewById(R.id.spinnerMonth);
        spinnerYears = (Spinner) findViewById(R.id.spinnerYear);
        spinnerUsers = (Spinner) findViewById(R.id.spinnerUsers);

        spinnerDays.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, daysArray));
        spinnerMonths.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, monthArray));
        spinnerYears.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, yearsArray));
        spinnerUsers.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, usersArray));

        btnOk = (Button) findViewById(R.id.btnOKReport);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dayChoosen > -1 && userChoosen  > -1 && yearChoosen && monthChoosen) {
                    int choice = dayChoosen + userChoosen;
                    rollChoosen = arrayOptions[choice];
                    ReportActivity.setRolls(rollChoosen);
                    Intent intentGoReport = new Intent(ChooseReportActivity.this, ReportActivity.class);
                    startActivity(intentGoReport);

                }
            }
        });
        spinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    yearChoosen = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                yearChoosen = false;
            }
        });

        spinnerMonths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    monthChoosen = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                monthChoosen = false;
            }
        });

        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayChoosen = position - 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dayChoosen = -1;
            }
        });

        spinnerUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userChoosen = (position - 1)*2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                userChoosen = -1;
            }
        });

        allUserRolls16 = "                      [ 'Ricardo',  '',    new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                "                      [ 'Castro',  '', new Date(0,0,0,16,30,0), new Date(0,0,0,23,0,0) ]," +
                "                      [ 'Stefany', '',   new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                "                      [ 'Nichene',   '',       new Date(0,0,0,6,30,0), new Date(0,0,0,15,0,0) ]," +
                "                      [ 'Danilo',   '',          new Date(0,0,0,16,30,0), new Date(0,0,0,18,30,0) ]";

        allUserRolls17 = "                      [ 'Ricardo',  '',    new Date(0,0,0,8,30,0), new Date(0,0,0,12,40,0) ]," +
                "                      [ 'Castro',  '', new Date(0,0,0,7,30,0), new Date(0,0,0,18,0,0) ]," +
                "                      [ 'Stefany', '',   new Date(0,0,0,12,30,0), new Date(0,0,0,16,0,0) ]," +
                "                      [ 'Nichene',   '',       new Date(0,0,0,12,30,0), new Date(0,0,0,20,0,0) ]," +
                "                      [ 'Danilo',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,18,30,0) ]";

        ricardoRolls16 = "                      [ '16/06/2016',   '',       new Date(0,0,0,12,30,0), new Date(0,0,0,20,0,0) ]," +
                "                      [ '16/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,18,30,0) ]";

        ricardoRolls17 = "                      [ '17/06/2016',   '',       new Date(0,0,0,8,30,0), new Date(0,0,0,14,0,0) ]," +
                "                      [ '17/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,18,30,0) ]";

        nicheneRolls16 = "                      [ '16/06/2016',   '',       new Date(0,0,0,7,30,0), new Date(0,0,0,13,30,0) ]," +
                "                      [ '16/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,15,30,0) ]";

        nicheneRolls17 = "                      [ '17/06/2016',   '',       new Date(0,0,0,6,30,0), new Date(0,0,0,14,30,0) ]," +
                "                      [ '17/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,11,30,0) ]";

        daniloRolls16 = "                      [ '16/06/2016',   '',       new Date(0,0,0,12,30,0), new Date(0,0,0,20,0,0) ]," +
                "                      [ '16/06/2016',   '',          new Date(0,0,0,9,30,0), new Date(0,0,0,14,30,0) ]";

        daniloRolls17 = "                      [ '17/06/2016',   '',       new Date(0,0,0,7,30,0), new Date(0,0,0,15,20,0) ]," +
                "                      [ '17/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,18,30,0) ]";

        stefanyRolls16 = "                      [ '16/06/2016',   '',       new Date(0,0,0,7,30,0), new Date(0,0,0,13,30,0) ]," +
                "                      [ '16/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,15,30,0) ]";

        stefanyRolls17 = "                      [ '16/06/2016',   '',       new Date(0,0,0,6,30,0), new Date(0,0,0,14,30,0) ]," +
                "                      [ '17/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,11,30,0) ]";

        castroRolls16 = "                      [ '16/06/2016',   '',       new Date(0,0,0,7,30,0), new Date(0,0,0,13,30,0) ]," +
                "                      [ '16/06/2016',   '',          new Date(0,0,0,8,30,0), new Date(0,0,0,15,30,0) ]";

        castroRolls17 = "                      [ '16/06/2016',   '',       new Date(0,0,0,10,30,0), new Date(0,0,0,22,30,0) ]," +
                "                      [ '17/06/2016',   '',          new Date(0,0,0,9,30,0), new Date(0,0,0,13,30,0) ]";


        arrayOptions[0] = allUserRolls16;
        arrayOptions[1] = allUserRolls17;
        arrayOptions[2] = ricardoRolls16;
        arrayOptions[3] = ricardoRolls17;
        arrayOptions[4] = nicheneRolls16;
        arrayOptions[5] = nicheneRolls17;
        arrayOptions[6] = daniloRolls16;
        arrayOptions[7] = daniloRolls17;
        arrayOptions[8] = stefanyRolls16;
        arrayOptions[9] = stefanyRolls17;
        arrayOptions[10] = castroRolls16;
        arrayOptions[11] = castroRolls17;

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(ChooseReportActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }
}
