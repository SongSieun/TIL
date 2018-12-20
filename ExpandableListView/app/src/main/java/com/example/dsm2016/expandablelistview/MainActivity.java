package com.example.dsm2016.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String,String>>>();

        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "꽃");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "새");

        groupData.add(groupA);
        groupData.add(groupB);

        ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childAA = new HashMap<String, String>();
        childAA.put("group", "꽃");
        childAA.put("name", "민들레");
        HashMap<String, String> childAB = new HashMap<String, String>();
        childAB.put("group", "꽃");
        childAB.put("name", "코스모스");
        HashMap<String, String> childAC = new HashMap<String, String>();
        childAC.put("group", "꽃");
        childAC.put("name", "장미");

        childListA.add(childAA);
        childListA.add(childAB);
        childListA.add(childAC);

        childData.add(childListA);
        ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childBA = new HashMap<String, String>();
        childBA.put("group", "새");
        childBA.put("name", "독수리");
        HashMap<String, String> childBB = new HashMap<String, String>();
        childBB.put("group", "새");
        childBB.put("name", "참새");

        childListB.add(childBA);
        childListB.add(childBB);

        childData.add(childListB);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(getApplicationContext(), groupData, android.R.layout.simple_expandable_list_item_1, new String[] {"group"}, new int[] {android.R.id.text1}, childData,android.R.layout.simple_expandable_list_item_2, new String[] {"name", "group"}, new int[] {android.R.id.text1, android.R.id.text2});

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.ExpandablelistView);
        listView.setAdapter(adapter);
    }
}
