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
    private static String rolls;
    private String startHTLM;
    private String endHTML;
    private String fullHTML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        startHTLM = "<html>"
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
                "    dataTable.addRows([";
        endHTML = "      ]);" +
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
        fullHTML = startHTLM + rolls + endHTML;
        WebSettings webSettings = grafico.getSettings();
        webSettings.setJavaScriptEnabled(true);
        grafico.requestFocusFromTouch();
        grafico.loadDataWithBaseURL("file:///android_asset/", fullHTML, "text/html", "utf-8", null);

    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(ReportActivity.this, ChooseReportActivity.class);
        startActivity(intentBackOpenDoor);
    }
    public static void setRolls(String rolls) {
        ReportActivity.rolls = rolls;
    }
}

