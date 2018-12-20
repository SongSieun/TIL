package com.example.dsm2016.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int year = 2000;
    private int month = 6 - 1;
    private int day = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatePicker dp = (DatePicker)findViewById(R.id.DatePicker);
        Switch sw1 = (Switch)findViewById(R.id.Calemdar_hide);
        Switch sw2 = (Switch)findViewById(R.id.year_hide);
        Switch sw3 = (Switch)findViewById(R.id.month_hide);
        Switch sw4 = (Switch)findViewById(R.id.date_hide);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dp.setCalendarViewShown(!isChecked);
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout parentFrame = (LinearLayout)dp.getChildAt(0);
                LinearLayout dateFrame = (LinearLayout)parentFrame.getChildAt(0);
                dateFrame.getChildAt(0).setVisibility(!isChecked? View.VISIBLE : View.GONE);
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout parentFrame = (LinearLayout)dp.getChildAt(0);
                LinearLayout dateFrame = (LinearLayout)parentFrame.getChildAt(0);
                dateFrame.getChildAt(1).setVisibility(!isChecked ? View.VISIBLE : View.GONE);
            }
        });

        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout parentFrame = (LinearLayout)dp.getChildAt(0);
                LinearLayout dateFrame = (LinearLayout) parentFrame.getChildAt(0);
                dateFrame.getChildAt(2).setVisibility(!isChecked ? View.VISIBLE : View.GONE);
            }
        });

        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "/" + (monthOfYear + 1 )+ "/" + dayOfMonth;
                Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
