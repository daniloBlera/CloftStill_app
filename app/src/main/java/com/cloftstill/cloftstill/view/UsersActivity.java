package com.cloftstill.cloftstill.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cloftstill.cloftstill.R;
import com.cloftstill.cloftstill.model.User;

import java.util.LinkedList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private List<User> solicitations;
    private List<User> users;
    private ListView solicitationList;
    private ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        solicitations = new LinkedList<>();
        solicitationList = (ListView) findViewById(R.id.listPendent);
        usersList = (ListView) findViewById(R.id.listUsers);
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

}
