package com.example.myfirebase.logistics_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirebase.R;

public class LogisticsComActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle , tvLocation;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_com);
        tvTitle = findViewById(R.id.tv_title);
        tvLocation = findViewById(R.id.tv_location);
        findViewById(R.id.iv_back).setOnClickListener(this);

        tvTitle.setText("Logistics Com");
        tvLocation.setText("Mumbai");
        tvLocation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
