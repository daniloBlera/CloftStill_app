package com.cloftstill.cloftstill.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.cloftstill.cloftstill.R;

public class ReportActivity extends AppCompatActivity {

    public String urlAgua;
    private WebView grafico;
    private String customHtml;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        String strURL;


        customHtml = "<html>"
                + "<head>"
                + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>" +
                "<script type=\"text/javascript\">" +
                "  google.charts.load(\"current\", {packages:[\"timeline\"]});" +
                "  google.charts.setOnLoadCallback(drawChart);" +
                "  function drawChart() {" +
                "    var container = document.getElementById('example5.2');" +
                "    var chart = new google.visualization.Timeline(container);" +
                "    var dataTable = new google.visualization.DataTable();" +
                "    dataTable.addColumn({ type: 'string', id: 'Room' });" +
                "    dataTable.addColumn({ type: 'string', id: 'Name' });" +
                "    dataTable.addColumn({ type: 'date', id: 'Start' });" +
                "    dataTable.addColumn({ type: 'date', id: 'End' });" +
                "    dataTable.addRows([" +
                "      [ 'Ful',  'CSS',    new Date(0,0,0,7,0,0),  new Date(0,0,0,9,0,0) ]," +
                "      [ 'Ful',  'Intro',    new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                "      [ 'Ful',  'Java', new Date(0,0,0,16,30,0), new Date(0,0,0,23,0,0) ]," +
                "      [ 'Cic', 'Perl',   new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                "      [ 'Cic', 'Perl2',       new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                "      [ 'Cic', 'Perl3',        new Date(0,0,0,16,30,0), new Date(0,0,0,18,0,0) ]," +
                "      [ 'Joao',   'G',       new Date(0,0,0,3,30,0), new Date(0,0,0,15,0,0) ]," +
                "      [ 'Bel',   'G',       new Date(0,0,0,12,30,0), new Date(0,0,0,14,0,0) ]," +
                "      [ 'Bel',   'Clo',             new Date(0,0,0,14,30,0), new Date(0,0,0,16,0,0) ]," +
                "      [ 'Bel',   'App',          new Date(0,0,0,16,30,0), new Date(0,0,0,18,30,0) ]]);" +
                "    var options = {" +
                "      timeline: { singleColor: 'lightblue' }," +
                "    };" +
                "    chart.draw(dataTable, options);" +
                "  }" +
                "</script>" +
                "</head>"
                + "<body>" +
                "<div id=\"example5.2\" style=\"height: 500px;\"></div>"
                + "</body>"
                + "</html>";


        grafico = (WebView) findViewById(R.id.reportWebView);

        WebSettings webSettings = grafico.getSettings();
        webSettings.setJavaScriptEnabled(true);
        grafico.requestFocusFromTouch();
        grafico.loadDataWithBaseURL("file:///android_asset/", customHtml, "text/html", "utf-8", null);

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(ReportActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }
}

