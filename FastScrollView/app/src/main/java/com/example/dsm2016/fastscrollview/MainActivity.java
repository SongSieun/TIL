package com.example.dsm2016.fastscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 100; i++){
            adapter.add("hoge" + i);
        }

        final ListView lv = (ListView)findViewById(R.id.ListView);
        final Switch sw = (Switch)findViewById(R.id.FastScroller);

        lv.setAdapter(adapter);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lv.setFastScrollEnabled(isChecked);
                lv.setFastScrollAlwaysVisible(isChecked);
            }
        });

    }
}
