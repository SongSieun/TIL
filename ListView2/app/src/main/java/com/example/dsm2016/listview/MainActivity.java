package com.example.dsm2016.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View header = (View) getLayoutInflater().inflate(R.layout.list_header_item, null);
        View footer = (View) getLayoutInflater().inflate(R.layout.list_footer_item, null);

        ListView listView = (ListView)findViewById(R.id.ListView);
        listView.addHeaderView(header);
        listView.addFooterView(footer);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    return;
                }
            }
        });
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, DAYS));
        listView.setItemsCanFocus(false);
    }
    private static final String [] DAYS = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Thursday", "Friday", "Saturday", "Thursday", "Friday", "Saturday", "Thursday", "Friday", "Saturday"};
}
