package com.example.dsm2016.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView)findViewById(R.id.gridView);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, WEEK);
        gridView.setAdapter(adapter);
    }
    private static final String[] WEEK = new String[]  {"월", "화", "수", "목", "금", "토", "일"};
}
