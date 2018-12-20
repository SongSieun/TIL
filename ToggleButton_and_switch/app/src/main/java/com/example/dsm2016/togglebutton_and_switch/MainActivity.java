package com.example.dsm2016.togglebutton_and_switch;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton tb = (ToggleButton) findViewById(R.id.ToggleButton);
        final Switch sw = (Switch) findViewById(R.id.Switch);

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, getString(R.string.toggle_shift, isChecked),Toast.LENGTH_SHORT).show();
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, getString(R.string.switch_shift, isChecked),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
