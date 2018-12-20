package com.example.dsm2016.mycalendar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.dsm2016.mycalendar.R;
import com.example.dsm2016.mycalendar.activity.MainActivity;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);  // Intent 선언
                startActivity(intent);   // Intent 시작
                finish();
            }
        }, 2000);  // 로딩화면 시간
    }
}
