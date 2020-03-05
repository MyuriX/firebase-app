package com.example.myfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidTelePhonyActivity extends AppCompatActivity {
    Button btnCall , btnSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_tele_phony);
        btnCall = findViewById(R.id.btn_call);
        btnSMS = findViewById(R.id.btn_sms);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AndroidTelePhonyActivity.this, PhoneCallActivity.class));
            }
        });
    }
}
