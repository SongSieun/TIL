package com.sesong.screenfilterexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.onbutton);

        button.setOnClickListener(new View.OnClickListener() {
            int clickFlag = 1;
            @Override
            public void onClick(View v) {
                if (clickFlag == 1) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    startService(intent);
                    clickFlag = 0;
                } else {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    stopService(intent);
                    clickFlag = 1;
                }


            }
        });
    }
}
