package com.cloftstill.cloftstill.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.cloftstill.cloftstill.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseReportActivity extends AppCompatActivity {

    private ArrayList days = new ArrayList<String>();
    private ArrayList months = new ArrayList<String>();
    private ArrayList years = new ArrayList<String>();
    private Spinner spinnerDays;
    private Spinner spinnerMonths;
    private Spinner spinnerYears;
    private Spinner spinnerUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_report);

        String[] monthArray = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};

        months = new ArrayList(Arrays.asList(monthArray));

        Integer dayInitial = 1;
        for (int i = 0; i < 31; i++){
            days.add(dayInitial.toString());
            dayInitial ++;
        }
        years.add("2016");
    }
}
