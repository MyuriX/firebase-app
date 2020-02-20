package com.example.myfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myfirebase.firebase.FirebaseDBHelper;
import com.example.myfirebase.interfaces.FBDataListner;
import com.example.myfirebase.model.User;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FBDataListner {
    FirebaseDBHelper dbHelper;
    RecyclerView rvUsersList;
    UsersAdapter usersAdapter;
    List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUsersList = findViewById(R.id.rv_user_list);

        usersAdapter = new UsersAdapter(this, mUserList);
        rvUsersList.setLayoutManager(new LinearLayoutManager(this));
        rvUsersList.setAdapter(usersAdapter);

        dbHelper = new FirebaseDBHelper(this);
        dbHelper.getUserCollection();



    }

    @Override
    public void onSuccess(List<DocumentSnapshot> users) {
        for (DocumentSnapshot documentSnapshot : users){
         mUserList.add(new User(String.valueOf(documentSnapshot.get("first_name")),String.valueOf(documentSnapshot.get("last_name"))));
        }
        usersAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError(Exception e) {

    }
}
