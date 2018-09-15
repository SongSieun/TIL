package com.sesong.alarmmanagerexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buttonA);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarmDialog(v);
            }
        });
    }

    public void showAlarmDialog(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
