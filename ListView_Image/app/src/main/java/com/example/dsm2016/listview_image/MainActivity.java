package com.example.dsm2016.listview_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image;
        image = BitmapFactory.decodeResource(getResources(), R.drawable.images);

        List<CustomData> objects = new ArrayList<CustomData>();
        CustomData item1 = new CustomData();
        item1.setImageData(image);
        item1.setTextData("1st");

        CustomData item2 = new CustomData();
        item2.setImageData(image);
        item2.setTextData("2nd");

        CustomData item3 = new CustomData();
        item3.setImageData(image);
        item3.setTextData("3rd");

        objects.add(item1);
        objects.add(item2);
        objects.add(item3);

        CustomAdapter customAdapter = new CustomAdapter(this, 0, objects);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(customAdapter);
    }

    public class CustomData{
        private Bitmap bitmapData;
        private String textData;

        public void setImageData(Bitmap image){
            bitmapData = image;
        }

        public Bitmap getImageData(){
            return bitmapData;
        }

        public void setTextData(String text){
            textData = text;
        }

        public String getTextData(){
            return textData;
        }
    }

    public class CustomAdapter extends ArrayAdapter <CustomData>{
        private LayoutInflater layoutInflater;

        public  CustomAdapter(Context context, int textViewResourceId, List<CustomData> objects){
            super(context, textViewResourceId, objects);
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            CustomData item = (CustomData)getItem(position);

            if (null == convertView){
                convertView = layoutInflater.inflate(R.layout.list_item, null);
            }

            ImageView imageView;
            imageView = (ImageView)convertView.findViewById(R.id.image);
            imageView.setImageBitmap(item.getImageData());

            TextView textView;
            textView = (TextView) convertView.findViewById(R.id.text);
            textView.setText(item.getTextData());

            return convertView;
        }
    }
}
