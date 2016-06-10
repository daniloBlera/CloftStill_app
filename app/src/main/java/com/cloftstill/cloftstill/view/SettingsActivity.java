package com.cloftstill.cloftstill.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloftstill.cloftstill.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentBackOpenDoor = new Intent(SettingsActivity.this, OpenDoorActivity.class);
        startActivity(intentBackOpenDoor);
    }
}
