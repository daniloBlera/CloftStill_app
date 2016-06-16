package com.cloftstill.cloftstill.view;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.controller.UsersController;
import com.cloftstill.cloftstill.model.Authenticable;
import com.cloftstill.cloftstill.model.Session;
import com.cloftstill.cloftstill.model.User;

import java.util.LinkedList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private static List<User> solicitations;
    private static List<User> users;
    private ListView solicitationList;
    private ListView usersList;
    TextView txtSolicitations;
    TextView txtUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        //solicitations = new LinkedList<>();
//        users = new LinkedList<>();

        solicitationList = (ListView) findViewById(R.id.listPendent);
        usersList = (ListView) findViewById(R.id.listUsers);
        txtSolicitations = (TextView) findViewById(R.id.txtWaiting);
        txtUsers = (TextView) findViewById(R.id.txtUsers);
        solicitationPopulate();
        userPopulate();

        try {
            if (solicitations.isEmpty()) {
                txtSolicitations.setText("Nenhuma solicitação em aberto no sistema");
            }
            if (users.isEmpty()) {
                txtUsers.setText("Nenhum usuário cadastrado");
            } else {
                txtUsers.setText("TEM ALGO AÍ...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intentOpenDoor = new Intent(UsersActivity.this, OpenDoorActivity.class);
        startActivity(intentOpenDoor);
    }

    private void solicitationPopulate(){
        ArrayAdapter<User> adapter = new SolicitationListAdapter();
        solicitationList.setAdapter(adapter);
    }
    private void userPopulate(){
        ArrayAdapter<User> adapter = new UserListAdapter();
        usersList.setAdapter(adapter);
    }
    private class SolicitationListAdapter extends ArrayAdapter<User> {
        public SolicitationListAdapter(){
            super(UsersActivity.this, R.layout.users_item, solicitations);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.users_item, parent, false);
            }
            TextView name = (TextView) view.findViewById(R.id.txtUserName);
            TextView cpf = (TextView) view.findViewById(R.id.txtUserCPF);
            TextView cell = (TextView) view.findViewById(R.id.txtUserCell);
            name.setText(solicitations.get(position).getName());
            cpf.setText(solicitations.get(position).getCpf());
            cell.setText(solicitations.get(position).getCellNumber());
            return view;
        }
    }
    private class UserListAdapter extends ArrayAdapter<User> {
        public UserListAdapter(){
            super(UsersActivity.this, R.layout.users_item, users);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null){
                view = getLayoutInflater().inflate(R.layout.users_item, parent, false);
            }
            TextView name = (TextView) view.findViewById(R.id.txtUserName);
            TextView cpf = (TextView) view.findViewById(R.id.txtUserCPF);
            TextView cell = (TextView) view.findViewById(R.id.txtUserCell);
            name.setText(solicitations.get(position).getName());
            cpf.setText(solicitations.get(position).getCpf());
            cell.setText(solicitations.get(position).getCellNumber());

            return view;
        }
    }

    public static void setSolicitations(List<User> solicitations) {
        UsersActivity.solicitations = solicitations;
    }

    public static void setUsers(List<User> users) {
        UsersActivity.users = users;
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
