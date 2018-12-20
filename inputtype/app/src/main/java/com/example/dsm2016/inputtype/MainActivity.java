package com.example.dsm2016.inputtype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] androids = {"hello", "sieun", "donut", "choco", "seongryun", "kury", "soyeon", "study"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, androids);
        textView.setAdapter(adapter);
    }
}
