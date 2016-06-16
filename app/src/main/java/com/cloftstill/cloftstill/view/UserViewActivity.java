package com.cloftstill.cloftstill.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.model.User;

public class UserViewActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtCPF;
    private TextView txtSerialSim;
    private TextView txtMac;
    private TextView txtCell;
    private TextView txtAprove;
    private FloatingActionButton saveChanges;

    private static User user;
    private static boolean solicitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        txtName = (TextView) findViewById(R.id.txtUserName);
        txtCPF = (TextView) findViewById(R.id.txtUserCPF);
        txtSerialSim = (TextView) findViewById(R.id.txtSerial);
        txtMac = (TextView) findViewById(R.id.txtMAC);
        txtCell = (TextView) findViewById(R.id.txtCell);
        txtAprove = (TextView) findViewById(R.id.textView);

        saveChanges = (FloatingActionButton) findViewById(R.id.floatingOk);
        if (!solicitation){
            saveChanges.setVisibility(View.INVISIBLE);
            txtAprove.setText("");

        }

        txtName.setText(txtName.getText() + user.getName());
        txtCPF.setText(txtCPF.getText() + user.getCpf());
        txtSerialSim.setText(txtSerialSim.getText() + user.getSerialNumber());
        txtMac.setText(txtMac.getText() + user.getMacAdress());
        txtCell.setText(txtCell.getText() + user.getCellNumber());

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO aprovar solicitação
            }
        });
    }

    public static void setUser(User user) {
        UserViewActivity.user = user;
    }

    public static void setSolicitation(boolean solicitation) {
        UserViewActivity.solicitation = solicitation;
    }
}
