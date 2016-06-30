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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.model.Solicitation;
import com.cloftstill.cloftstill.model.User;

import java.util.List;

public class UsersActivity extends AppCompatActivity {
    //TODO Popular lista de solicitações com entidade Solicitation
    private static List<User> solicitations;
//    private static List<Solicitation> solicitations;
    private static List<User> users;
    private ListView solicitationList;
    private ListView usersList;
    TextView txtSolicitations;
    TextView txtUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        solicitationList = (ListView) findViewById(R.id.listPendent);
        usersList = (ListView) findViewById(R.id.listUsers);
        txtSolicitations = (TextView) findViewById(R.id.txtWaiting);
        txtUsers = (TextView) findViewById(R.id.txtUsers);
        solicitationPopulate();
        userPopulate();
        setListViewHeightBasedOnItems(usersList);
        setListViewHeightBasedOnItems(solicitationList);

        solicitationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserViewActivity.setSolicitation(true);
                UserViewActivity.setUser(solicitations.get(position));
                Intent intentGoUserView = new Intent(UsersActivity.this, UserViewActivity.class);
                startActivity(intentGoUserView);
            }
        });
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserViewActivity.setSolicitation(false);
                UserViewActivity.setUser(solicitations.get(position));
                Intent intentGoUserView = new Intent(UsersActivity.this, UserViewActivity.class);
                startActivity(intentGoUserView);
            }
        });


        try {
            if (solicitations.isEmpty()) {
                txtSolicitations.setText("Nenhuma solicitação em aberto no sistema");
            }
            if (users.isEmpty()) {
                txtUsers.setText("Nenhum usuário cadastrado");
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
    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
