package com.example.myfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore db;
    Query next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.tv_limit).setOnClickListener(this);
        findViewById(R.id.tv_main).setOnClickListener(this);
        findViewById(R.id.tv_all).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_all:
                break;
            case R.id.tv_main:
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                break;
            case R.id.tv_limit:
                break;
        }
    }
}
